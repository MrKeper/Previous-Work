/*
    to compile, type
      make
*/

#include "hw09-lib.h"
#include <stdio.h>
int main(void)
{
	struct person data[25];
	char *names[15];
	int line_count = 0,i;
	read_files(data,names, &line_count);
	sort_array(data,line_count);
	for (i = 0; i < line_count;i++)
	{
		printf("|course: %s| \n", data[i].course);
	}		
}
