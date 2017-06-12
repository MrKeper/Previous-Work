/******************************************************************************************************
    Keenan Parker 1001024878
    DATE 3/3/2015
    CSE-1320-004
    Homework assignment 05
    Assignment:Write a program that reads a set of files, calculating and displaying basic statistical
    values for each.
*******************************************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(int argc, char* argv[])
{
    FILE * fileA;
    FILE * fileB;
    char buffer[100]; //140
    char completed[] = "completed.txt";
    char bscs[] = "bscs-2015.csv";
    char filename[30];
    fileA = fopen(completed,"r");
    fileB = fopen(bscs,"r");

    if (argc != 3)
	{
		printf("usage: executable startInt stopInt\n");
		//exit(1);
	}

    fgets(buffer,100,fileB);
    printf("%s\n",buffer);

	while( fgets(buffer,100,fileA) != NULL)
    {
        printf("%s\n",buffer);
    }

}


