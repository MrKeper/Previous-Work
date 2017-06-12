/******************************************************************************************************
    Keenan Parker 1001024878
    DATE     8/27/15
    CSE 2320 Homework 0
    Purpose: Have the user enter two integers and print the number in-between the two integers including the two integers

*******************************************************************************************************/

#include <stdio.h>

int main (void)
{
    int first, second;
    printf("Enter a integer: ");
    scanf("%d",&first);
    printf("Enter a second integer: ");
    scanf("%d",&second);

    while (first <= second)
    {
        printf("%d\n",first);
        first++;
    }


}
