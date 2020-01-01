//
// Created by hpcomputer on 2019/12/30.
//

#ifndef AVLTREE_C_AVLTREE_H
#define AVLTREE_C_AVLTREE_H

#endif //AVLTREE_C_AVLTREE_H

#include<stdbool.h>

struct Node{
    int data;
    int height;    // 这个节点的高度
    struct Node *left, *right;    // 左右孩子节点高度
};

struct AVLTree{
    struct Node *AVLTreeNode;
    int size;    // AVL树中元素个数
};



// 判断是否为空
bool isEmpty(struct AVLTree avlTree);

// 判断该树是否为一颗二分搜索树
bool isBST(struct AVLTree avlTree);

// AVL树的中序遍历
void inOrder(struct AVLTree avlTree, int *a);

// 判断二分搜索树是否是一个平衡的二叉树
bool isBalanced(struct AVLTree avlTree);

// 获得节点高度
int getHeight(struct Node node);

// 获得节点的平衡因子
int getBalanceFactor(struct Node node);

// 对节点node右旋转
struct Node rightRotata(struct Node *node);

// 对节点node左旋转
struct Node leftRotate(struct Node *node);

