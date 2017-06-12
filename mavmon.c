// The MIT License (MIT)
// 
// Copyright (c) 2016 Trevor Bakker 
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
#include <pthread.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>
#include "train.h"
void trainArrives( uint32_t train_id, enum TRAIN_DIRECTION train_direction );
void trainCross  ( uint32_t train_id, enum TRAIN_DIRECTION train_direction );
void trainLeaves ( uint32_t train_id, enum TRAIN_DIRECTION train_direction );
void  * trainLogic(void * val);
void updateRepeatedPassCount(enum TRAIN_DIRECTION direction);

pthread_t tid[255];
pthread_cond_t  direction_cond[NUM_DIRECTIONS];//cond for N S E W trains
pthread_mutex_t cond_direction_mutex[255];
pthread_mutex_t intersection_mutex = PTHREAD_MUTEX_INITIALIZER;
int trainCount[NUM_DIRECTIONS];
int repeated_pass_count[NUM_DIRECTIONS];
int deadlock_check;
// Current time of day in seconds since midnight
int32_t  current_time;
uint32_t clock_tick;
// Current train in the intersection
uint32_t in_intersection;
ScheduleEntry * train;
/*
 * Function: trainLogic
 * Parameter(s): void * val - A variable to carry the ScheduleEntry * train
    data for the pthread create
 * Returns: N/A
 * Description: Sets pthread_cond to wait until the mediate sends the signal
    and a train enters the mutex (intersection) then crosses, rinse and repeat.
*/
void * trainLogic( void * val )
{
  if(val == NULL)
  {
    return;
  }
  ScheduleEntry * ts = (ScheduleEntry*)val;
  enum TRAIN_DIRECTION train_dir = ts->train_direction;
  uint32_t train_id = ts->train_id;
  //printf("-------------Entering Intersection Queue %d %s-------------\n",
      //  train_id,directionAsString[train_dir]);
  pthread_cond_wait(&direction_cond[ts->train_direction],&cond_direction_mutex[ts->train_id]);
  pthread_mutex_lock(&intersection_mutex);
  {
    pthread_mutex_unlock(&cond_direction_mutex[ts->train_id]);
    trainCross(ts->train_id,ts->train_direction);
  }
}
/*
 * Function: trainLeaves
 * Parameter(s):uint32_t train_id - id of the train leaving the intersection.
 *      enum TRAIN_DIRECTION train_direction - direction the train with id, train_id, is going.
 * Returns: N/A
 * Description:prints who is leaing, sets intersection to empty, 
 *  decrements the trainCount, unlocks the mutex and exits the pthread.
*/
void trainLeaves( uint32_t train_id, enum TRAIN_DIRECTION train_direction )
{
  fprintf( stdout, "Current time: %d MAV %d heading %s leaving the intersection\n", 
           current_time, train_id, directionAsString[ train_direction ] );
  in_intersection = INTERSECTION_EMPTY;
  // TODO: Handle any cleanup 
  trainCount[train_direction]--;
  pthread_mutex_unlock(&intersection_mutex);
  pthread_exit(NULL);
  return;
}
/*
 * Function: trainCross
 * Parameter(s): uint32_t train_id - id of the train crossing the intersection.
      enum TRAIN_DIRECTION train_direction - direction the train with id, train_id, is going.
 * Returns: N/A
 * Description: checks to see if there was deadlock and prrints out the deadlock sentence. ,
  it updates the repeatedTrainCount , handles train crossing time, and the set train to leave.
*/
void trainCross( uint32_t train_id, enum TRAIN_DIRECTION train_direction )
{
  // TODO: Handle any crossing logic
  if(deadlock_check)
  {
  //  fprintf( stdout, "Current time: %d DEADLOCK detected. "
   // "Signaling train %d to enter the intersection\n", 
    //       current_time,train_id);
    deadlock_check = 0;
  }
  updateRepeatedPassCount(train_direction);
  fprintf( stdout, "Current time: %d MAV %d heading %s entering the intersection\n", 
           current_time, train_id, directionAsString[ train_direction ] );
  if( in_intersection == INTERSECTION_EMPTY )
  {
    in_intersection = train_id;
  }
  else
  {
    fprintf( stderr, "CRASH: Train %d collided with train %d\n",
                      train_id, in_intersection );
    exit( EXIT_FAILURE );
  }
  // Sleep for 10 microseconds to simulate crossing
  // the intersection
  usleep( 10 * 1000000 / clock_tick );
  // Leave the intersection
  trainLeaves( train_id, train_direction );
  return;
}
/*
 * Function: trainArrives
 * Parameter(s): uint32_t train_id - id of the train Arriving the intersection.
    enum TRAIN_DIRECTION train_direction - direction the train with id, train_id, is going.
 * Returns: N/A
 * Description:shows which train arrived allocates the train struct and creates the pthread.
*/
void trainArrives( uint32_t train_id, enum TRAIN_DIRECTION train_direction )
{
  fprintf( stdout, "Current time: %d MAV %d heading %s arrived at the intersection\n", 
           current_time, train_id, directionAsString[ train_direction ] );
  // TODO: Handle the intersection logic
  train = (struct ScheduleEntry*)malloc(sizeof(struct ScheduleEntry));
  trainCount[train_direction]++;
  train->train_id = train_id;
  train->train_direction = train_direction;
  pthread_create(&tid[train->train_id],NULL,trainLogic, (void *)train);
  return;
}
/*
 * Function: mediate
 * Parameter(s): N/A
 * Returns: N/A
 * Description: Goes through to check the current state and sends signal 
 to approaite train to go. Also handles starrvation and deadlock.
*/
void mediate( )
{
  if(in_intersection != INTERSECTION_EMPTY)
  {
    return;
  }
  // TODO: MAV monitor code
  //single train
  if(trainCount[NORTH] && !trainCount[SOUTH] && !trainCount[EAST] && !trainCount[WEST])//NORTH
  {
    pthread_cond_signal(&direction_cond[NORTH]);
    return;
  }
  if(!trainCount[NORTH] && trainCount[SOUTH] && !trainCount[EAST] && !trainCount[WEST])//SOUTH
  {
    pthread_cond_signal(&direction_cond[SOUTH]);
    return;
  }
  if(!trainCount[NORTH] && !trainCount[SOUTH] && trainCount[EAST] && !trainCount[WEST])//EAST
  {
    pthread_cond_signal(&direction_cond[EAST]);
    return;
  }
  if(!trainCount[NORTH] && !trainCount[SOUTH] && !trainCount[EAST] && trainCount[WEST])//WEST
  {
    pthread_cond_signal(&direction_cond[WEST]);
    return;
  }
   //deadlock
   //2-trains
  if(trainCount[NORTH] && trainCount[SOUTH] && !trainCount[EAST] && !trainCount[WEST])//NS
  {
    if(repeated_pass_count[NORTH]  == 5)
    {
      pthread_cond_signal(&direction_cond[SOUTH]);
      return;
    }
    deadlock_check = 1;
    pthread_cond_signal(&direction_cond[NORTH]);
    return;
  }
  if(trainCount[NORTH] && !trainCount[SOUTH] && trainCount[EAST] && !trainCount[WEST])//NE
  {
    if(repeated_pass_count[NORTH]  == 5)
    {
      pthread_cond_signal(&direction_cond[EAST]);
      return;
    }
    pthread_cond_signal(&direction_cond[NORTH]);
    return;
  }
  if(trainCount[NORTH] && !trainCount[SOUTH] && !trainCount[EAST] && trainCount[WEST])//NW
  {
    if(repeated_pass_count[WEST]  == 5)
    {
      pthread_cond_signal(&direction_cond[NORTH]);
      return;
    }
    pthread_cond_signal(&direction_cond[WEST]);
    return;
  }
  if(!trainCount[NORTH] && trainCount[SOUTH] && trainCount[EAST] && !trainCount[WEST])//SE
  {
    if(repeated_pass_count[EAST]  == 5)
    {
      pthread_cond_signal(&direction_cond[SOUTH]);
      return;
    }
    pthread_cond_signal(&direction_cond[EAST]);
    return;
  }
  if(!trainCount[NORTH] && trainCount[SOUTH] && !trainCount[EAST] && trainCount[WEST])//SW
  {
    if(repeated_pass_count[SOUTH]  == 5)
    {
      pthread_cond_signal(&direction_cond[WEST]);
      return;
    }
    pthread_cond_signal(&direction_cond[SOUTH]);
    return;
  }
  if(!trainCount[NORTH] && !trainCount[SOUTH] && trainCount[EAST] && trainCount[WEST])//EW
  {
    if(repeated_pass_count[EAST]  == 5)
    {
      pthread_cond_signal(&direction_cond[WEST]);
      return;
    }
    deadlock_check = 1;
    pthread_cond_signal(&direction_cond[EAST]);
    return;
  }
  //3-trains
  if(trainCount[NORTH] && trainCount[SOUTH] && trainCount[EAST] && !trainCount[WEST])//NSE
  {
    if(repeated_pass_count[NORTH]  == 5)
    {
      pthread_cond_signal(&direction_cond[EAST]);
      return;
    }
    pthread_cond_signal(&direction_cond[NORTH]);
    return;
  }
  if(!trainCount[NORTH] && trainCount[SOUTH] && trainCount[EAST] && trainCount[WEST])//SEW
  {
   if(repeated_pass_count[EAST]  == 5)
    {
      pthread_cond_signal(&direction_cond[SOUTH]);
      return;
    }
    pthread_cond_signal(&direction_cond[EAST]);
    return;
  }
  if(trainCount[NORTH] && trainCount[SOUTH] && !trainCount[EAST] && trainCount[WEST])//NSW
  {
   if(repeated_pass_count[SOUTH]  == 5)
    {
      pthread_cond_signal(&direction_cond[WEST]);
      return;
    }
    pthread_cond_signal(&direction_cond[SOUTH]);
    return;
  }
  if(trainCount[NORTH] && !trainCount[SOUTH] && trainCount[EAST] && trainCount[WEST])//NEW
  {
   if(repeated_pass_count[WEST]  == 5)
    {
      pthread_cond_signal(&direction_cond[NORTH]);
      return;
    }
    pthread_cond_signal(&direction_cond[WEST]);
    return;
  }
  //4-trains
  if(repeated_pass_count[NORTH] == 5)
  {
    pthread_cond_signal(&direction_cond[EAST]);
    return;
  }
  else
  {
    pthread_cond_signal(&direction_cond[NORTH]);
    return;
  }
}
/*
 * Function: init
 * Parameter(s): N/A
 * Returns: N/A
 * Description: initialzes the global variables.
*/
void init( )
{
  // TODO: Any code you need called in the initialization of the application
  
  //pthread_t tid[255];
  in_intersection = INTERSECTION_EMPTY;
  deadlock_check = 0;
  int i;
  for(i =0; i < 255; i++)
  {  
    tid[i] = i+1;  
    pthread_mutex_init( &cond_direction_mutex[i] ,NULL);
  }
  //pthread_cond_t  direction_cond[NUM_DIRECTIONS]
  //pthread_mutex_t cond_direction_mutex[NUM_DIRECTIONS]
  //int trainCount[NUM_DIRECTIONS];
  //int repeated_pass_count[NUM_DIRECTIONS];
  for(i =0; i < NUM_DIRECTIONS; i++)
  {
    pthread_cond_init( &direction_cond[i] ,NULL);
    trainCount[i] = 0;
    repeated_pass_count[i]=0;
  }
}
/*
 * Function: updateRepeatedPassCount
 * Parameter(s): enum TRAIN_DIRECTION direction - direction that the train crossing has.
 * Returns: N/A
 * Description: increaments the repeatedPasscount in the direction of the train.
*/
void updateRepeatedPassCount(enum TRAIN_DIRECTION direction)
{
  int i;
  int check_for_trains_from_differnt_direction = 0;
  for(i = 0; i < NUM_DIRECTIONS; i++)
  {
    if(trainCount[i] != 0)
    {
      check_for_trains_from_differnt_direction++;
    }
  }
  if(check_for_trains_from_differnt_direction == 1)
  {
    repeated_pass_count[direction] = 0;
    return;
  }
  repeated_pass_count[direction]++;
  for(i = 0; i < NUM_DIRECTIONS; i++)
  {
    if(i != direction)
    {
      repeated_pass_count[i] = 0;
    }
  }
  return;
}
/*
 * Function: isIntersectonEmpty
 * Parameter(s): N/A
 * Returns:  N/A
 * Description: checks to see wheter intersection is empty or not. 1 empty. 0 not empty.
*/
int isIntersectonEmpty()
{
  if(in_intersection == INTERSECTION_EMPTY)
  {
    return 1;
  }
  return 0;
}
/*
 * Function: isTrainCountEmpty
 * Parameter(s): N/A
 * Returns:  N/A
 * Description: checks to see if any trains are still waiting to go. 
 1 no trains waiting. 0 trains waiting.
*/
int isTrainCountEmpty()
{
  int i;
  for(i = 0; i < NUM_DIRECTIONS; i++)
  {
    if(trainCount[i] != 0)
    {
      return 0;
    }
  }
  return 1;
}
/***************************************************************************************************
 *
 *
 *  DO NOT MODIFY CODE BELOW THIS LINE
 *
 *
 *
 ****************************************************************************************************/
/*
 * Function: process
 * Parameter(s): N/A 
 * Returns: N/A
 * Description: Checks to see if Schedule is empty or day has ended. if not it mediates
                 then adds new trains in.
*/
int process( )
{
  // If there are no more scheduled train arrivals
  // then return and exit
  
  //code to make program wait till all trains left
  //if( scheduleEmpty() && isIntersectonEmpty() && isTrainCountEmpty() ) return 0;
  if( scheduleEmpty() ) return 0;
  // If we're done with a day's worth of schedule then
  // we're done.
  if( current_time > SECONDS_IN_A_DAY ) return 0;
  // Check for deadlocks
  mediate( );
  // While we still have scheduled train arrivals and it's time
  // to handle an event
  while( !scheduleEmpty() && current_time >= scheduleFront().arrival_time ) 
  {
#ifdef DEBUG
    fprintf( stdout, "Dispatching schedule event: time: %d train: %d direction: %s\n",
                      scheduleFront().arrival_time, scheduleFront().train_id, 
                      directionAsString[ scheduleFront().train_direction ] );
#endif  
    trainArrives( scheduleFront().train_id, scheduleFront().train_direction );
    // Remove the event from the schedule since it's done
    schedulePop( );
  }
  // Sleep for a simulated second. Depending on clock_tick this
  // may equate to 1 real world second down to 1 microsecond
  usleep( 1 * 1000000 / clock_tick );
  current_time ++;
  return 1;
}
int main ( int argc, char * argv[] )
{
  // Initialize time of day to be midnight
  current_time = 0;
  clock_tick   = 1;
  // Verify the user provided a data file name.  If not then
  // print an error and exit the program
  if( argc < 2 )
  {
    fprintf( stderr, "ERROR: You must provide a train schedule data file.\n");
    exit(EXIT_FAILURE);
  }
  // See if there's a second parameter which specifies the clock
  // tick rate.  
  if( argc == 3 )
  {
    int32_t tick = atoi( argv[2] );
    
    if( tick <= 0 )
    {
      fprintf( stderr, "ERROR: tick rate must be positive.\n");
      exit(EXIT_FAILURE);
    }
    else
    {
      clock_tick = tick;
    }
  }
  buildTrainSchedule( argv[1] );
  // Initialize the intersection to be empty
  in_intersection = INTERSECTION_EMPTY;
  // Call user specific initialization
  init( );
  // Start running the MAV manager
  while( process() );
  return 0;
}

