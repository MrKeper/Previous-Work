#include <stdio.h>
#include <stdlib.h>
#include "hw08-header.h"

int main (void)
{
    int i = 0;
    char *value = "go";
    int key;

    key = 10;
    initialize(key);


    while(strcmp(value, "stop") != 0)
    {

        value = getString();
        printf("%2d:  %s\n", ++i, value);
    }

}
