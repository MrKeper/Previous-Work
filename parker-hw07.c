
/******************************************************************************************************
    Keenan Parker 1001024878
    DATE 4/1/2015
    CSE-1320-004
    Homework assignment 07
    Assignment:Write a program that reads a file of course information, stores most of the lines of
               the file, and prints the total enrollments for each department.
*******************************************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
char ** read_input(int *line_count);
void sort_array(char ** info, int line_count);
void print_array(char ** info, int line_count);
char* getToken(char buffer[], int pos);

int main(void)
{
    char buffer[350];
    char **fileinfo;
    int i, line_count = 0;

    if (argc != 3)
	{
		printf("usage: executable startInt stopInt\n");
		//exit(1);
	}

    fgets(buffer, sizeof(buffer), stdin); /* gets rid of first line*/

    fileinfo = read_input(&line_count);

    sort_array(fileinfo,line_count);

    print_array(fileinfo,line_count);


        for (i=0;i < line_count; i++)
        {
            free(fileinfo[i]);
        }

        free(fileinfo);
}


char ** read_input(int *line_count)
{
    int i=0, string_len = 0, alloc_val = 10;
    char buffer[350];
    char **temp, ** lineinfo;
    lineinfo = (char**)malloc(10 * sizeof(char*));
    while (fgets(buffer, sizeof(buffer), stdin) != NULL)
    {
        string_len = strlen(buffer) + 1;
        if (i == alloc_val)
            {
                alloc_val = (alloc_val * 2);
                temp = realloc(lineinfo, alloc_val*sizeof(char*) );
                if(temp != NULL)
                {
                    lineinfo = temp;
                    printf("reallocating to %d\n",alloc_val);
                }
                else
                {
                    printf("unable to reallocate\n");
                    exit(1);
                }
            }
        lineinfo[i] = (char*)malloc(string_len * sizeof(char*));
        strcpy(lineinfo[i],buffer);
        i++;
    }
    *line_count = i;

    return lineinfo;
}


char* getToken(char buffer[], int pos)
{
    int i;
    char *del = ",";
    char *token = strtok(buffer, del);

    for(i = 1; i < pos; i++)
        token = strtok(NULL, del);

    return token;
}

void sort_array(char ** info, int line_count)
{
    int i,unsorted;
    char tempA[350];
    char tempB[350];
    char temp_info[350];
    do {
        unsorted = 0;

        for(i = 0; i < line_count-1; i++)
        {
            strcpy(tempA,info[i]);
            strcpy(tempB,info[i+1]);
            if(strcmp(getToken(tempA,4),getToken(tempB,4)) > 0)
            {

                strcpy(temp_info,info[i]);
                strcpy(info[i],info[i+1]);
                strcpy(info[i+1],temp_info);
                unsorted = 1;
            }
        }


    } while(unsorted);


    return;
}

void print_array(char ** info, int line_count)
{
    int i = 0,sum = 0;
    char tempA[350],tempB[350];
    char* subjA,subjB;
    strcpy(tempA,info[0]);
    subjA = getToken(info[i],4);
    sum += atoi(getToken(tempA,9));
    printf("\n");
    for (i=1; i < line_count; i++)
    {
        strcpy(tempA,info[i]);
        strcpy(tempB,info[i]);
        if(strcmp(subjA,getToken(tempB,4)) == 0)
        {
            sum += atoi(getToken(tempA,9));
        }
        else
        {
            printf("%-10s %4d\n", subjA,sum);
            subjA = getToken(info[i],4);
            sum = 0;
            sum += atoi(getToken(tempA,9));


        }

    }
    printf("%-10s %4d\n", subjA,sum);
}
