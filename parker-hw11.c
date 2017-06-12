/************************************************************************************************************************
    Keenan Parker 1001024878
    DATE 4/30/2015
    CSE-1320-004
    Homework assignment 05
    Assignment:Write a program that reads a file of courses required for a degree and a file of courses completed by
               a student, and then indicates which courses the student is eligible to take based on the prerequisites
               of each course.

*************************************************************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FALSE 0
#define TRUE 1
struct course
{
    char name[25];
    char prereq[50];
    char note[50];
};

struct course * readFile_bscs(int * line_count, FILE * bscs);
char ** readCompleted(int * line_count, FILE * comp);
char * getToken(char buffer[], int pos);
void printCourses(struct course * d, char ** completed, int bscs_count, int comp_count);
void freeMem(char ** completed, int completed_count);

int main(int argc, char* argv[])
{
    FILE * course_catalog;
    FILE * completed;
    struct course * data;
    char ** completed_courses;
    int bscs_count = 0,i,completed_count = 0;


    if (argc != 3)
	{
		printf("usage: executable startInt stopInt\n");
		exit(1);
	}
	
    if ( (course_catalog = fopen(argv[1],"r") ) == NULL)
   	{
        printf("Unable to open %s\n",argv[1]);
        exit(1);
       }
    if ( (completed = fopen(argv[2],"r") ) == NULL)
       {
        printf("Unable to open %s\n",argv[2]);
        exit(1);
       }

 	data = readFile_bscs(&bscs_count,course_catalog);
  	completed_courses = readCompleted(&completed_count,completed);
        printCourses(data,completed_courses,bscs_count,completed_count);


        freeMem(completed_courses,completed_count);
        free(data);
 	fclose(course_catalog);
	fclose(completed);

}


struct course * readFile_bscs(int * line_count, FILE * bscs)
{
        struct course * data,* temp;
        char buffer[142], temp_buffer[142];
        int alloc_val = 5;

        data = malloc(alloc_val * sizeof(struct course));

    	while( fgets(buffer,sizeof(buffer),bscs) != NULL)
        {
            if (*line_count == alloc_val)
            {
                alloc_val = (alloc_val + 5);
                temp = realloc(data, (alloc_val*sizeof(struct course)) );
                if(temp != NULL)
                {
                    data = temp;
                }
                else
                {
                    printf("unable to reallocate\n");
                    exit(1);
                }
            }
                if (buffer[0] == '#')
                {
                    continue;
                }
            strcpy(temp_buffer,buffer);
            strcpy(data[*line_count].name,getToken(temp_buffer,1));
            strcpy(temp_buffer,buffer);
            strcpy(data[*line_count].prereq,getToken(temp_buffer,3));
            strcpy(temp_buffer,buffer);
            strcpy(data[*line_count].note,getToken(temp_buffer,4));
            *line_count += 1;
        }
        return data;
}

char ** readCompleted(int * line_count, FILE * comp)
{
    int i=0, alloc_val = 5;
    char buffer[50],course[50];
    char * del = "\n";
    char **temp, ** completed_courses;
     completed_courses = malloc(alloc_val * sizeof(char*));
    while (fgets(buffer, sizeof(buffer), comp) != NULL)
    {
        if (*line_count == alloc_val)
            {
                alloc_val = (alloc_val + 5);
                temp = realloc(completed_courses, alloc_val*sizeof(char*) );
                if(temp != NULL)
                {
                    completed_courses = temp;
                }
                else
                {
                    printf("unable to reallocate\n");
                    exit(1);
                }
            }
	if(strcmp(buffer,"\n") == 0)
		continue;
	strcpy(course,strtok(buffer,del));
        completed_courses[*line_count] = malloc( (strlen(course)+1) * sizeof(char*) );
        strcpy(completed_courses[*line_count],course);
        *line_count += 1;
    }
	
    return completed_courses;
}

char* getToken(char buffer[], int pos)
{
    int i;
    char * del = ",\n";
    char * token = strtok(buffer, del);

    for(i = 1; i < pos; i++)
        token = strtok(NULL, del);

    return token;
}

void printCourses(struct course * d, char ** completed, int bscs_count, int comp_count)
{
    int i,k,req_count=0,w, done=TRUE;
    struct course * temp;
    char *del = "|", *token;
    char * prereq_courses[50];
    temp = d;
    printf("COURSES ELIGIBLE TO TAKE\n------------------------\n");
	for( i = 0; i < bscs_count; i++)
    {

        for( k = 0; k < comp_count; k++)
            {
                if(strcmp(temp[i].name,completed[k]) == 0)
                {
                    strcpy(temp[i].name,"completed");
                }
            }
            if (strcmp(temp[i].prereq,"none") == 0)
            {
                strcpy(temp[i].prereq,"fulfilled");
                continue;
            }
        token = strtok(temp[i].prereq,del);
                while (token != NULL)
                {
                    prereq_courses[req_count] = token;
                    req_count++;
                    token = strtok(NULL,del);
                }
        for (w=0;w<req_count;w++)
        {
                    for( k = 0; k < comp_count; k++)
                    {
                            if(strcmp(prereq_courses[w],completed[k]) == 0)
                            {
                                strcpy(prereq_courses[w],"done");
                                done = TRUE;
                            }
                    }
                    if (strcmp(prereq_courses[w],"done") != 0)
                    {
                            strcpy(temp[i].prereq,"unfulfilled");
                            done = FALSE;
                            continue;
                    }
        }
        req_count = 0;

        if (done == TRUE)
            strcpy(temp[i].prereq,"fulfilled");
    }
    for( i = 0; i < bscs_count; i++)
    {
        if ( (strcmp(temp[i].name,"completed") != 0) && (strcmp(temp[i].prereq,"fulfilled") == 0) )
        {
                printf("  %s", d[i].name);
                if( strcmp(d[i].note,"none") != 0)
                {
                   printf(" (%s)\n", d[i].note);
                }
                else
                {
                    printf("\n");
                }
        }
    }

}

void freeMem(char ** completed, int completed_count)
{
    int i;
    for (i=0;i < completed_count; i++)
        {
            free(completed[i]);
        }
    free(completed);
}

