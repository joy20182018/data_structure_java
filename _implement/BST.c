//
// Created by hpcomputer on 2019/12/18.
//

#include "BST.h"


/*********************************************
*函数名：二分查找法，在有序数组中查找target
*        如果找到target就返回索引，否则返回-1
*********************************************/
int binarySearch(int arr[],int n,int target){
    //在arr[l,r]中查找target
    int l = 0,r = n-1;

    while(l <= r){
        //int mid = (l+r)/2;
        int mid = l + (r-l)/2;
        if(arr[mid] == target){
            return mid;
        }
        else if(arr[mid] > target){
            r = mid - 1;
        }
        else{
            l = mid + 1;
        }
    }
    return -1;

}

/*********************************************
*函数名：递归二分查找法，在有序数组中查找target
*        如果找到target就返回索引，否则返回-1
*********************************************/
int binarySearch2(int arr[],int l,int r,int target){
    //在arr[l,r]中查找target
    while(l <= r){
        //int mid = (l+r)/2;
        int mid = l + (r-l)/2;
        if(arr[mid] == target){
            return mid;
        }
        else if(target < arr[mid]){
            return binarySearch2(arr,l,mid-1,target);
        }
        else{
            return binarySearch2(arr,mid+1,r,target);
        }
    }
    return -1;

}

/*********************************************
*函数名：二分搜索树binarySearchTree
*********************************************/
Node* newNode(Node *node,int key,int value){

    node->key = key;
    node->value = value;

    node->left = (Node *)malloc(sizeof(struct Node));
    node->right = (Node *)malloc(sizeof(struct Node));

    node->left = node->right = 0;
    return node;
}
void init_BST(BST *bst){
    bst =(BST *) malloc(sizeof(struct BST));    //对结构体指针进行初始化,malloc包含于stdlib.h中
    bst->count = 0;
    bst->root = (Node *)malloc(sizeof(struct Node));
    bst->root = newNode(bst->root,0,0);

}

//向以node为根的二叉搜索树中插入节点(key,value);
//返回的值是插入新的节点后二叉搜索树相应的根
Node* __insert(BST *bst,int key,int value){
    if(bst -> root == NULL){
        bst -> count ++;

        bst -> root = (Node *)malloc(sizeof(struct Node));
        return newNode(bst->root,key,value);
    }

    if(key == bst->root->key){
        bst->root->value = value;
    }
    else if(key < bst->root->key){
        bst->root->left = __insert(bst,key,value);
    }
    else{
        bst->root->right = __insert(bst,key,value);
    }

    return bst->root;
}


/*********************************************
*函数名：向二叉搜索树插入元素（递归的方法）
*********************************************/
void insert_BST(BST *bst,int key,int value){
    __insert(bst,key,value);
}

int size_BST(BST *bst){
    return bst->count;
}

int isEmpty_BST(BST *bst){
    return bst->count == 0;
}

/*********************************************
*函数名：删除二叉树元素释放空间
*        使用后序遍历的方法对二叉树进行空间的释放
*********************************************/
void destory_BST(BST *bst,Node *node){
    assert(bst->root == node);
    if(node != NULL){
        destory_BST(bst,node->left);
        destory_BST(bst,node->right);

        free(node);
        bst->count --;
    }
}

/*********************************************
*函数名：二叉搜索树中查找元素
*********************************************/
bool __contain(Node *node,int key){
    if(node == NULL){
        return false;
    }
    if(key == node->key){
        return true;
    }
    else if(key < node->key){
        return __contain(node->left,key);
    }
    else{
        return __contain(node->right,key);
    }

}

bool contain_BST(BST *bst,int key){
    return __contain(bst->root,key);
}


/*********************************************
*函数名：二叉搜索树中查找元素
*********************************************/
int* __search(Node *node,int key){
    if(node == NULL)
        return NULL;
    if(key == node->key){
        return &(node->value);
    }
    else if(key < node->key){
        return __search(node->left,key);
    }
    else{
        return __search(node->right,key);
    }
}

int* search_BST(BST *bst,int key){
    return __search(bst->root,key);
}





/*********************************************
*函数名：二叉搜索树前序遍历
*********************************************/
//对以node为根的二叉搜索树进行前序遍历
int* __preOrder(Node *node,int key[]){
    static int i;//静态变量的初始化
    if(node != NULL){
        key[i] = node->key;
        i++;

        __preOrder(node->left,key);
        __preOrder(node->left,key);
    }
    return key;
}


int* preOrder_BST(BST *bst){
    int key[bst->count - 1];
    return __preOrder(bst->root,key);
}


/*********************************************
*函数名：二叉搜索树中序遍历
*********************************************/
//对以node为根的二叉搜索树进行中序遍历
int* __inOrder(Node *node,int key[]){
    static int i;//静态变量的初始化
    if(node != NULL){


        __inOrder(node->left,key);

        key[i] = node->key;i++;

        __inOrder(node->left,key);
    }
    return key;
}


int* inOrder_BST(BST *bst){
    int key[bst->count - 1];
    return __inOrder(bst->root,key);
}

/*********************************************
*函数名：二叉搜索树后序遍历
*********************************************/
//对以node为根的二叉搜索树进行后序遍历
int* __postOrder(Node *node,int key[]){
    static int i;//静态变量的初始化
    if(node != NULL){


        __postOrder(node->left,key);

        __postOrder(node->left,key);

        key[i] = node->key;i++;

    }
    return key;
}


int* postOrder_BST(BST *bst){
    int key[bst->count - 1];
    return __postOrder(bst->root,key);
}

/*********************************************
*函数名：二叉搜索树获取最小值
*********************************************/
Node* __mini(Node *node){
    if(node->left != NULL){
        return __mini(node->left);
    }
    else{
        return node;
    }
}
int min_BST(BST *bst){
    assert(bst->count != 0);
    Node* minNode = __mini(bst->root);
    return minNode->key;
}
/*********************************************
*函数名：二叉搜索树获取最大值
*********************************************/
Node* __max(Node *node){
    if(node->right != NULL){
        return __max(node->right);
    }
    else{
        return node;
    }
}
int max_BST(BST *bst){
    assert(bst->count != 0);
    Node* maxNode = __max(bst->root);
    return maxNode->key;
}

/*********************************************
*函数名：二叉搜索树删除最小节点
*********************************************/
//删除以node为节点的二分搜索树中的最小节点
//返回删除节点后新的二分搜索树的根
Node* __removeMin(BST *bst,Node *node){
    assert(bst->root == node);

    if(bst->root->left == NULL){
        //将右节点返回作为新的根
        Node* rightNode = bst->root->right;
        //释放空间
        free(bst->root);
        bst->count --;

        return rightNode;
    }
    else{
        node->left = __removeMin(bst,bst->root->left);
        return node;
    }
}
void removeMin_BST(BST *bst){
    if(bst->root != NULL){
        bst->root = __removeMin(bst,bst->root);
    }
}

/*********************************************
*函数名：二叉搜索树删除最大节点
*********************************************/
//删除以node为节点的二分搜索树中的最大节点
//返回删除节点后新的二分搜索树的根
Node* __removeMax(BST *bst,Node *node){
    assert(bst->root == node);

    if(bst->root->right == NULL){
        //将zuo节点返回作为新的根
        Node* leftNode = bst->root->left;
        //释放空间
        free(bst->root);
        bst->count --;

        return leftNode;
    }
    else{
        node->right = __removeMax(bst,bst->root->right);
        return node;
    }
}
void removeMax_BST(BST *bst){
    if(bst->root != NULL){
        bst->root = __removeMax(bst,bst->root);
    }
}


/*********************************************
*函数名：二叉搜索树删除键值为k的节点
*********************************************/
Node* __remove(BST *bst,Node *node,int key){
    assert(bst->root == node);

    if(node == NULL){
        return NULL;
    }
    if(key < node->key){
        node->left = __remove(bst,node->left,key);
        return node;
    }
    else if(key > node->key){
        node->right = __remove(bst,node->right,key);
        return node;
    }
    else{
        if(node->left == NULL){
            Node *rightNode = node->right;
            free(node);
            bst->count --;
            return rightNode;
        }
        else if(node->right == NULL){
            Node *leftNode = node->left;
            free(node);
            bst->count --;
            return leftNode;
        }
        else{
            //复制一个最小值得node节点,避免删除最小节点后successor失效
            Node *cy;
            //找到右孩子的最小值
            Node *successor = __mini(node->right);

            cy->key = successor->key;
            cy->left = successor->left;
            cy->right = successor->right;
            cy->value = successor->value;

            bst->count ++;

            successor->right = __removeMin(bst,node->right);
            successor->left = node->left;

            free(node);
            bst->count --;

            return successor;


        }
    }
}
void remove_BST(BST *bst,int key){
    assert(bst->count >= key);

    bst->root = __remove(bst,bst->root,key);
}
