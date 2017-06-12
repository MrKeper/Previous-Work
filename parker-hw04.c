/******************************************************************************************************
    Keenan Parker 1001024878
    DATE   2/20/15
    CSE-1320-004
    Homework assignment 04
    Assignment: The program will determine the index of the column with the maximum sum for each table. It will
    also find the arithmetic mean and sample variance of this column
*******************************************************************************************************/
#include <stdio.h>
void Maxsum(int data[][5], int rows, int *maxindexptr, int *MaxSum);
void mean_variance(int data[][5], int rows, int maxindex, double *mean, double *variance);
int main(void)
{
    int data1[][5] = {  -55, -8, -12, -6, -1,
                        -10, -4, -7, -5, -6,
                        -20, -3, -15, -4, -2};

    int data2[][5] = {  1, -6, -2, 5, 14,
                        0, -4, 3, -5, 12,
                        2, -4, 5, -6, 12,
                        4, -7, 15, 4, 16,
                        3, -9, 4, 0, 17};


    int col_index = 0;
    int *col_indexptr = &col_index;
    int max_sum = 0;
    int *max_sumptr = &max_sum;
    double mean_value = 0;
    double *mean_valueptr = &mean_value;
    double variance_value = 0;
    double *variance_valueptr = &variance_value;

    Maxsum(data1, 3, col_indexptr, max_sumptr);
    mean_variance(data1, 3, col_index, mean_valueptr, variance_valueptr);
    printf("For the first array, the column with index %d has the maximum sum of %d\nIt has a mean of %.3f and a variance of %.3f", col_index, max_sum, mean_value,variance_value);

    Maxsum(data2, 5, col_indexptr, max_sumptr);
    mean_variance(data2, 5, col_index, mean_valueptr, variance_valueptr);
    printf("\n\nFor the second array, the column with index %d has the maximum sum of %d\nIt has a mean of %.3f and a variance of %.3f\n", col_index, max_sum, mean_value,variance_value);
}

void Maxsum(int data[][5], int rows, int *maxindexptr, int *MaxSum)
{
    int col_num = 0; int row_num = 0; int sum;
    sum = 0;
    *MaxSum = 0;
    col_num = 0;
    for(col_num = 0; col_num < 5; col_num+= 1)
    {
        for(row_num = 0; row_num < rows; row_num++)
        {
            sum += data[row_num][col_num];
        }
        if (col_num == 0)
        {
            *MaxSum = sum;
        }
        if(*MaxSum < sum)
        {
            *MaxSum = sum;
            *maxindexptr = col_num;
        }
        sum = 0;

    }
}

void mean_variance(int data[][5], int rows, int colIndex, double *mean, double *variance)
{
   int row_num; *mean = 0; *variance = 0;
   for(row_num =0; row_num < rows; row_num++)
   {
     *mean += data[row_num][colIndex];
   }
   *mean = (*mean/rows);

   for(row_num =0; row_num < rows; row_num++)
   {
       *variance += (((data[row_num][colIndex])-*mean) * ((data[row_num][colIndex])-*mean));
   }
   *variance = (*variance/ (rows-1));
}
