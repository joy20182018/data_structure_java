// 左倾红黑树
#include<iostream>
#include<queue>
static bool RED = true;
static bool BLACK = false;

class Node{
public:
    int data;
    Node* left;
    Node* right;
    bool color;
};

Node* newNode(int data);    // 添加新节点
Node* leftRotate(Node* node);    // 左旋转
Node* rightRotate(Node* node);    // 右旋转
void flipColor(Node* node);   // 颜色翻转
void add(Node **node, int data);    // 添加元素
Node* addR(Node* node, int data);    // 递归添加元素
bool isRED(Node* node);    // 判断节点颜色
void levelOrder(Node* root);    // 层序遍历