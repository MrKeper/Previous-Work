/******************************************************************************************************
    Keenan Parker 1001024878
    DATE 4/21/2015
    CSE-1320-004
    Homework assignment 05
    Assignment:Write a program that reads a set of files, calculating and displaying basic statistical
    values for each.
*******************************************************************************************************/
#define TRUE 1
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct node
{
char* word;
int count;
struct node* left;
struct node* right;
};
struct node * addNode(char * word);
void addLeaf(struct node * root, char * word);
void printTree(struct node * root);


int main(void)
{
    FILE *fp;
    struct node *root = NULL;
    char buffer[1000];
    char hw[] = "test1.txt";
    char * del = " \n", *token;

    fp = fopen(hw,"r");
    while ( fgets(buffer,sizeof(buffer),fp) != NULL)
    {
        token = strtok(buffer,del);
        while(token != NULL)
        {
            if(root == NULL)
            {
                root = addNode(token);
                printf("add: %s\n", token);
            }
             else   /*if it is in the tree then it will add to the count in the function*/
            {
                    addLeaf(root, token);

            }
            token = strtok(NULL,del);
        }
    }
    printf("\nRESULTS\n");
    printTree(root);
    fclose(fp);
}





struct node * addNode (char * word)
{
    struct node* temp = malloc(sizeof(struct node));

    temp->word = malloc( (strlen(word)+1) * sizeof(char));
    strcpy(temp->word,word);
    temp->count = 1;
    temp->left = NULL;
    temp->right = NULL;

    return temp;

}


void addLeaf(struct node * root, char * word)
{
if ( strcmp(root->word, word) == 0)
    {
        printf("updating count: %s\n",word);
        (root->count) += 1;
        return;
    }
if ( strcmp(word,root->word) < 0)
    {
        if(root->left == NULL)
        {
            printf("left add: %s\n",word);
            root->left = addNode(word);
        }
        else
        {
            printf("going left\n");
            addLeaf(root->left,word);
        }
    }
    else
    {
        if(root->right == NULL)
        {
            printf("right add: %s\n",word);
            root->right = addNode(word);
        }
        else
        {
             printf("going right\n");
            addLeaf(root->right,word);
        }
    }
}

void printTree(struct node * root)
{
    if (root != NULL)
    {
        printTree(root->left);
        printf("%s, %d \n", root->word,root->count);
        printTree(root->right);

    }
}









