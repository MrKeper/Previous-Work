#include <stdio.h>
#include <stdlib.h>
/******************************************************************************************************
    Keenan Parker
    January 26th, 2014
    CSE-1320-004
    Homework assignment 01
    Assignment: Write a program that produces a 10 × 4 table of random values. For these values,
                produce the arithmetic mean (i.e., the average) of the printed row values.
                Additional requirements can be found on the assignment pdf file.

*******************************************************************************************************/
int main(void)
{
    double value, average;
    int i, count, n;
    value = rand()%10000/100.0;
    count = 0;
    average = 0;
    n=0;/*Number of values in column */
    for (i=0; i<40; i++)
        {
        count += 1;
        if (count <= 4)
        {
            if (value >= 67.0)
            {
                printf("%.2f", value);
                n+=1;
            }
            else
            {
                printf("     ");
                value = 0;
            }
        printf("  ");
        }
        average += value;
        if (count == 4)
        {
            if (n == 0)
                n = 1;
            average = average/n;
            if (average > 0)
                printf("|  %.2f\n", average);
            else
                printf("|\n");
            count = 0;
            average = 0;
            n = 0;
            value = 0;
        }
        value = rand()%10000/100.0;
    }
}

