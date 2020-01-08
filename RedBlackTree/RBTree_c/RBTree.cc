// 左倾红黑树的添加元素操作
#include<iostream>
#include<queue>
#include "RBTree.h"
using namespace std;


Node* newNode(int data){
    Node* node = new Node();
    node -> data = data;
    node -> left = node -> right = NULL;
    node -> color = RED;

    return node;
}

// 左旋转
Node* leftRotate(Node* node){
    Node* x = node -> right;
    node -> right = x -> left;
    x -> left = node;
    x -> color = node -> color;
    node -> color = RED;

    return x;
}


// 右旋转
Node* rightRotate(Node* node){

    Node* x = node -> left;
    node -> left = x -> right;
    x -> right = node;
    x -> color = node -> color;
    node -> color = RED;

    return x;
}

// 颜色翻转
void flipColor(Node* node){

    node -> color = RED;
    node -> left -> color = BLACK;
    node -> right -> color = BLACK;
}


bool isRed(Node* node){
    if (node == NULL)
        return BLACK;

    return node -> color;
}


// 向红黑树中添加元素
void add(Node **node, int data){
    *node = addR(*node, data);
    (*node) -> color = BLACK;
}

// 递归添加元素
Node* addR(Node* node, int data){
    if (node == NULL){
        return newNode(data);
    }

    if (data < node -> data){
        node -> left = addR(node -> left, data);
    }
    else if (data > node -> data){
        node -> right = addR(node -> right, data);
    }
    else{
        node -> data = data;
    }

    if (isRed(node -> right) && !isRed(node -> left)){
        node = leftRotate(node);
    }

    if (isRed(node -> left) && isRed(node -> left -> left)){
        node = rightRotate(node);
    }

    if (isRed(node -> left) && isRed(node -> right)){
        flipColor(node);
    }

    return node;


}


// 层序遍历
void levelOrder(Node* root){
    queue<Node*> que;
    que.push(root);
  
    while (!que.empty()){
        Node* node = que.front();
        cout << node -> data << "  ";
        que.pop();
    
        if (node -> left != NULL)
            que.push(node -> left);
        if (node -> right != NULL)
            que.push(node -> right);
    }

    cout << "\n";
}





int main(){
    
    Node* root = NULL;
    for (int i = 0; i < 5; i ++){
        add(&root, i);
    }
    levelOrder(root);

    printf("hello world! \n");
    return 0;
}