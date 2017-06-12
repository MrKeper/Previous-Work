/******************************************************************************************************
    Keenan Parker 1001024878
    DATE     ****************************************!!!!!!!!!!!!!!!!!!!!!
    CSE-1320-004
    Homework assignment 3
    Assignment: Write a program that stores each line of the file as a string in its own character array in main().
    Your program will pass each of these to a function that when given a single array of characters and
    an array of ints, determines the number of words in the string and the number of characters in
    the longest word. From main(), print the number of words in each string as well as the number of
    characters of the longest word in the string.
65 90   97 122
*******************************************************************************************************/
#include <stdio.h>
void longestWord(char s[], int results[]);
int main(void)
{
    char string1[] = "This is one of Several strings2use.";
    char string2[] = "This sample has less than 987654321 leTTers.";
    char string3[] = "Is thIs a string?  (definitely)";
    char string4[] = "Twitter loves its hashtags #twitterlove";
    char string5[] = "123 four five.";
    int results[2] = {0,0};
    int i =0;
    int test = 0;
    longestWord(string1, results);
    printf("string  1: words =  %d, longest =  %2d characters\n", 
results[0], results[1]);
    longestWord(string2, results);
    printf("string  2: words =  %d, longest =  %2d characters\n", 
results[0], results[1]);
    longestWord(string3, results);
    printf("string  3: words =  %d, longest =  %2d characters\n", 
results[0], results[1]);
    longestWord(string4, results);
    printf("string  4: words =  %d, longest =  %2d characters\n", 
results[0], results[1]);
    longestWord(string5, results);
    printf("string  5: words =  %d, longest =  %2d characters\n", 
results[0], results[1]);
}

void longestWord(char s[], int counts[])
{
    int i =0;
    counts[0] = 0;
    counts[1] = 0;
    while (s[i] != 0)
    {

         ++i;
         if (s[i] > 122)
        {
            if (counts[0] < counts[1])
            {
                counts[0] = counts[1];
            }
            counts[1] =0;
            continue;
        }
        if (s[i] < 65)
        {
            if (counts[0] < counts[1])
            {
                counts[0] = counts[1];
            }
            counts[1] =0;
            continue;
        }
        if(s[i] > 90 && s[i] < 97)
        {
            if (counts[0] < counts[1])
            {
                counts[0] = counts[1];
            }
            counts[1] =0;
            continue;
        }
        counts[1] += 1;
    }
        counts[1] = counts[0];
        counts[0] = 0;
    for (i=0; s[i] != 0; i++)
    {
        if (s[i] == 32)
        {
           if (s[i+2] < 65 || s[i+2] > 122)
                {
                    counts[0] += 1;
                }
           if (s[i+2] > 90 && s[i+2] < 97)
                {
                    counts[0] += 1;
                }
        }
        if (s[i+1] != 0)
        {

            if (s[i] < 65 || s[i] > 122)
            {
                if (s[i-2] < 65 || s[i-2] > 122)
                {
                    continue;
                }
                if (s[i-2] > 90 && s[i-2] < 97)
                {
                    continue;
                }
                if (s[i+1] >= 65 && s[i+1] <= 90)
                {
                    counts[0] += 1;
                    continue;
                }
                if (s[i+1] >= 97 && s[i+1] <= 122)
                {
                    counts[0] += 1;
                    continue;
                }
            }

            if (s[i] > 90 && s[i] < 97)
            {
               if (s[i-2] < 65 || s[i-2] > 122)
                {
                    continue;
                }
                if (s[i-2] > 90 && s[i-2] < 97)
                {
                    continue;
                }
                if (s[i+1] >= 65 && s[i+1] <= 90)
                {
                    counts[0] += 1;
                    continue;
                }
                if (s[i+1] >= 97 && s[i+1] <= 122)
                {
                    counts[0] += 1;
                    continue;
                }
            }
        }
    }
        counts[0] += 1;
}
/*
• A word will consist only of consecutive lowercase and uppercase alphabetic characters (i.e.,
a-z and A-Z). Any other characters cannot be part of a word.

• You should use the same function for each string, but each string will be passed to the function
separately. The function declaration will look like this:
void longestWord(char s[], int counts[]);
You can rename the function and function parameters, but the types and quantity of function
parameters should be as given (i.e., a string and an array of ints). Since an array can’t return
two values, the purpose of the array of ints is to allow us to get the two counts back to the
calling location. No printing should take place in this function.

• The function should work for other strings as well, so you should not hard-code it to this data.
This means that you should also be able to deal with ANY valid non-alphabetic characters
in the ASCII set, for example, &, #, ~ and so forth. Note that this can be accomplished
without explicitly handing each specific non-alphabetic character.
*/





