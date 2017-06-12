#include <stdio.h>
int eight_bit(int d);
int is_error(int x, int y);
/******************************************************************************************************
    Keenan Parker 1001024878
    January 26th, 2014
    CSE-1320-004
    Homework assignment 02
    Assignment: Write a program that prompts a user for two nonnegative integers, a and b, and for
    each integer in the range of a to b, prints the integer in both base-10 and base-2.

*******************************************************************************************************/
int main(void)
{
 int a,b,count;
 count =1;
 a=0;
 b=0;
 while(count == 1)
 {

     printf("enter a nonnegative integer: ");
     scanf("%d", &a);
     printf("enter an integer >= the first integer: ");
     scanf("%d", &b);
     if (is_error(a,b) == -1)
     {
        printf("ERROR: out of bounds\n\n");
        continue;
     }
     if (is_error(a,b) == 0)
     {
         printf("ERROR: invalid relationship\n\n");
         continue;
     }
     if(is_error(a,b) == 1)
     {
         while(a <= b)
         {
             eight_bit(a);
             a++;
         }
         count = 9001;
     }
 }

}

int is_error(int x, int y)
{
  if (x > 0 && y < 256 && x<=y)
  {
      return 1;
  }
   if (x>=y)
  {
      return 0;
  }
  if (x < 0 || x > 256 || y > 256)
  {
      return -1;
  }

}





int eight_bit(int decimal)
{
 printf("%3d: ", decimal);
 if (decimal >= 128)
 {
  printf("1");
  decimal -= 128;
 }
 else
    printf("0");
if (decimal >= 64)
    {
    printf("1");
    decimal -= 64;
    }
 else
    printf("0");
if (decimal >= 32)
    {
    printf("1");
    decimal -= 32;
    }
else
    printf("0");
if (decimal >= 16)
    {
    printf("1");
    decimal-=16;
    }
else
    printf("0");
if (decimal >= 8)
    {
    printf("1");
    decimal -= 8;
    }
else
    printf("0");
if (decimal >= 4)
    {
    printf("1");
    decimal -= 4;
    }
else
    printf("0");
if (decimal >= 2)
    {
    printf("1");
    decimal-=2;
    }
else
    printf("0");
if (decimal >= 1)
    {
    printf("1\n");
    decimal -= 1;
    }
else
    printf("0\n");
}
