//
// Created by hpcomputer on 2019/12/18.
//

#ifndef DS_BST_H
#define DS_BST_H

#endif //DS_BST_H

#ifndef BINARYSEARCH_H_INCLUDED
#define BINARYSEARCH_H_INCLUDED
#include "stdbool.h"
#include "stddef.h"
#include "stdlib.h"
#include "ASSERT.h"

typedef struct Node{
    int key;
    int value;

    struct Node *left;
    struct Node *right;


}Node;

typedef struct BST{
    struct Node *root;
    int count;
}BST;

int binarySearch(int arr[],int n,int target);
int binarySearch2(int arr[],int l,int r,int target);

void init_BST(BST *bst);
void insert_BST(BST *bst,int key,int value);

int size_BST(BST *bst);
int isEmpty_BST(BST *bst);

bool contain_BST(BST *bst,int key);
int* search_BST(BST *bst,int key);

int* preOrder_BST(BST *bst);
int* inOrder_BST(BST *bst);
int* postOrder_BST(BST *bst);

int min_BST(BST *bst);
int max_BST(BST *bst);

void removeMin_BST(BST *bst);
void removeMax_BST(BST *bst);

void remove_BST(BST *bst,int key);

#endif // BINARYSEARCH_H_INCLUDED
