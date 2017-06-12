/******************************************************************************************************
    Keenan Parker 1001024878
    DATE   3/21/2015
    CSE-1320-004
    Homework assignment 06
    Assignment: Write a program that reads a comma-delimited file of UTA courses and, for each
                unique CSE course, determines the number of sections and the combined enrollment.

*******************************************************************************************************/
#include <stdio.h>
#include <string.h>
struct course
{
    char course_num[5];
    int sec_count;
    int enroll_count;
};
void course_check(char *linedata[], struct course classes[]);
void sort_struct(struct course classes[]);

int main(void)
{
    char line[350];
    char filename[30];
    int i,w=0;
    char *lineinfo[100];
    char *del = ",", *token;
    char subj[] = "CSE";
    struct course classes[100];
    for (i = 0; i < 100; i++)
    {
        strcpy(classes[i].course_num,"blank");
        classes[i].sec_count = 0;
        classes[i].enroll_count = 0;
    }

        fgets(line, sizeof(line), stdin); /* gets rid of first line*/

        while ( fgets(line, sizeof(line), stdin) != NULL)
        {
            token = strtok(line, del);
            while (token != NULL)
            {
                lineinfo[w] = token;
                token= strtok(NULL,del);
                w++;
                break;
            }
            w = 0;

            if(strcmp(lineinfo[3], subj)  == 0)
            {

                    course_check(lineinfo, classes);

            }
        }

    sort_struct(classes);
    for (i = 0; i < 100; i++)
    {
        if (classes[i].sec_count > 0)
        {

            if(classes[i].sec_count == 1)
            {
                printf("%s %s has %d section with a combined enrollment of %d\n", subj, classes[i].course_num, classes[i].sec_count, classes[i].enroll_count);
            }
            else
            {
                printf("%s %s has %d sections with a combined enrollment of %d\n", subj, classes[i].course_num, classes[i].sec_count, classes[i].enroll_count);
            }
        }
    }

}

void course_check(char *linedata[], struct course classes[])
{
    int i, w=0;

    for (i = 0; i < 100 ;i++)
    {
        if (atoi(linedata[4]) == atoi(classes[i].course_num))
        {
            classes[i].sec_count += 1;
            classes[i].enroll_count += atoi(linedata[8]);
            break;
        }
        else
        {
            if(strcmp(classes[i].course_num, "blank")  == 0)
            {
                strcpy(classes[i].course_num, linedata[4]);
                classes[i].sec_count += 1;
                classes[i].enroll_count += atoi(linedata[8]);
                break;

            }
        }
    }

}


void sort_struct(struct course classes[])
{
    int i, unsorted;
    struct course temp;

    do {
        unsorted = 0;

        for(i = 0; i < 100-1; i++)
            if(atoi(classes[i].course_num) > atoi(classes[i+1].course_num))
            {
                temp = classes[i];
                classes[i] = classes[i+1];
                classes[i+1] = temp;
                unsorted = 1;
            }

    } while(unsorted);
}


