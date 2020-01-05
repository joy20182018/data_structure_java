// C++ program to insert a node in AVL tree 
// #include<bits/stdc++.h> 
#include<iostream>
#include<queue>
using namespace std; 

// An AVL tree node 
class Node 
{ 
    public: 
    int key; 
    Node *left; 
    Node *right; 
    int height; 
}; 

// A utility function to get maximum 
// of two integers 
int max(int a, int b); 

// A utility function to get the 
// height of the tree 
int height(Node *N) 
{ 
    if (N == NULL) 
        return 0; 
    return N->height; 
} 

// A utility function to get maximum 
// of two integers 
int max(int a, int b) 
{ 
    return (a > b)? a : b; 
} 

/* Helper function that allocates a 
new node with the given key and 
NULL left and right pointers. */
Node* newNode(int key) 
{ 
    Node* node = new Node(); 
    node->key = key; 
    node->left = NULL; 
    node->right = NULL; 
    node->height = 1; // new node is initially 
                    // added at leaf 
    return(node); 
} 

// A utility function to right 
// rotate subtree rooted with y 
// See the diagram given above. 
Node *rightRotate(Node *y) 
{ 
    Node *x = y->left; 
    Node *T2 = x->right; 

    // Perform rotation 
    x->right = y; 
    y->left = T2; 

    // Update heights 
    y->height = max(height(y->left), height(y->right)) + 1; 
    x->height = max(height(x->left), height(x->right)) + 1; 

    // Return new root 
    return x; 
} 

// A utility function to left 
// rotate subtree rooted with x 
// See the diagram given above. 
Node *leftRotate(Node *x) 
{ 
    Node *y = x->right; 
    Node *T2 = y->left; 

    // Perform rotation 
    y->left = x; 
    x->right = T2; 

    // Update heights 
    x->height = max(height(x->left), height(x->right)) + 1; 
    y->height = max(height(y->left), height(y->right)) + 1; 

    // Return new root 
    return y; 
} 

// Get Balance factor of node N 
int getBalanceFactor(Node *N) 
{ 
    if (N == NULL) 
        return 0; 
    return height(N -> left) - height(N -> right); 
} 

// Recursive function to insert a key 
// in the subtree rooted with node and 
// returns the new root of the subtree. 
Node* insert(Node* node, int key) 
{ 
    /* 1. Perform the normal BST insertion */
    if (node == NULL) 
        return(newNode(key)); 

    if (key < node->key) 
        node->left = insert(node->left, key); 
    else if (key > node->key) 
        node->right = insert(node->right, key); 
    else // Equal keys are not allowed in BST 
        return node; 

    /* 2. Update height of this ancestor node */
    node->height = 1 + max(height(node->left), height(node->right)); 

    /* 3. Get the balance factor of this ancestor 
        node to check whether this node became 
        unbalanced */
    int balanceFactor = getBalanceFactor(node); 

    // If this node becomes unbalanced, then 
    // there are 4 cases 

    // Left Left Case 
    if (balanceFactor > 1 && key < node->left->key) 
        return rightRotate(node); 

    // Right Right Case 
    if (balanceFactor < -1 && key > node->right->key) 
        return leftRotate(node); 

    // Left Right Case 
    if (balanceFactor > 1 && key > node->left->key) 
    { 
        node->left = leftRotate(node->left); 
        return rightRotate(node); 
    } 

    // Right Left Case 
    if (balanceFactor < -1 && key < node->right->key) 
    { 
        node->right = rightRotate(node->right); 
        return leftRotate(node); 
    } 
    // 另一种方式
    // 右旋   LL
    // if (balanceFactor > 1 && getBalanceFactor(node -> left) >= 0){
    //     return rightRotate(node);
    // }
    
    // //左旋   RR
    // if (balanceFactor < -1 && getBalanceFactor(node -> right) <= 0){
    //     return leftRotate(node);
    // }

    // // LR型
    // if (balanceFactor > 1 && getBalanceFactor(node -> left) < 0){
    //     node -> left = leftRotate(node -> left);
    //     return rightRotate(node);
    // }

    // // RL型
    // if (balanceFactor < -1 && getBalanceFactor(node -> right) > 0){
    //     node -> right = rightRotate(node -> right);
    //     return leftRotate(node);
    // }



    /* return the (unchanged) node pointer */
    return node; 
} 


void add(Node** root, int data){
    *root = insert(*root, data);
}

// A utility function to print preorder 
// traversal of the tree. 
// The function also prints height 
// of every node 
void preOrder(Node *root) 
{ 
    if(root != NULL) 
    { 
        preOrder(root->left); 
        cout << root->key << " "; 
        preOrder(root->right); 
    } 
} 

// 层序遍历
void levelOrder(Node* root){
    queue<Node*> que;
    que.push(root);
  
    while (!que.empty()){
        Node* node = que.front();
        cout << node -> key << "  ";
        que.pop();
    
        if (node -> left != NULL)
            que.push(node -> left);
        if (node -> right != NULL)
            que.push(node -> right);
    }

    cout << "\n";
}

// input tree structure
void picTreeStructure(Node* node){
    queue<Node*> que;
    que.push(node);
  
    int count = 1;
    int p = 1;
    // int i = 0;
    while (!que.empty()){
        Node* n = que.front();
        cout << n -> key << "  ";
        que.pop();
        if ((count + 1) / 2  - p == 0){
            printf("\n");
            p  = p * 2;
            // i += 1;
        }
        count += 1;
        if (n -> left != NULL)
            que.push(n -> left);
        if (n -> right != NULL)
            que.push(n -> right);
    }

    cout << "\n";
}

// 返回以node为根的二分搜索树中，data所在的节点
Node* getNode(Node* node, int data){
    if (node == NULL)
        return NULL;

    if (data == node -> key)
        return node;
    else if (data < node -> key)
        return getNode(node -> left, data);
    else
        return getNode(node -> right, data);
}

// 返回以node为根的树的最小值所在的节点
Node* minium(Node* node){
    Node* n = node;
    while (n -> left != NULL){
        n = n -> left;
    }
    return n;
}


Node* deleteNode(Node *node, int data){
    if (node == NULL)
        return NULL;
    
    Node* retNode;
    if (data < node -> key){
        node -> left = deleteNode(node -> left, data);
        retNode = node;
    }
    else if (data > node -> key){
        node -> right = deleteNode(node -> right, data);
        retNode = node;
    }
    else{

        if (node -> left == NULL){
            retNode = node -> right;
            node -> right = NULL;
        }
        else if (node -> right == NULL){
            retNode = node -> left;
            node -> left = NULL;
        }
        else{
            Node* sNode = minium(node -> right);
            sNode -> right = deleteNode(node -> right, sNode -> key);
            sNode -> left = node -> left;
            node -> left = node -> right = NULL;
            retNode = sNode;
        }
    }

    if (retNode == NULL)
        return NULL;

    // 自平衡处理

    retNode -> height = 1 + max(height(retNode -> left), height(retNode -> right)); 

    int balanceFactor = getBalanceFactor(retNode);

    if (balanceFactor > 1 && getBalanceFactor(retNode -> left) >= 0){
        return rightRotate(retNode);
    }
    
    //左旋   RR
    if (balanceFactor < -1 && getBalanceFactor(retNode -> right) <= 0){
        return leftRotate(retNode);
    }

    // LR型
    if (balanceFactor > 1 && getBalanceFactor(retNode -> left) < 0){
        retNode -> left = leftRotate(retNode -> left);
        return rightRotate(retNode);
    }

    // RL型
    if (balanceFactor < -1 && getBalanceFactor(retNode -> right) > 0){
        retNode -> right = rightRotate(retNode -> right);
        return leftRotate(retNode);
    }

    return retNode;

}

// remove data
int remove(Node** root, int data){
    Node* node = getNode(*root, data);
    if (node == NULL)
        return 0;
    else{
        *root = deleteNode(*root, data);
        return node -> key;
    }
}


// Driver Code 
int main() 
{ 
    Node *root = NULL; 
    for (int i = 20; i >= 14; i --){
        // root = insert(root, i);
        add(&root, i);
        cout << "tree structure: \n";
        picTreeStructure(root);
        cout << "\n";
    }



    // cout << "Preorder: \n"; 
    // preOrder(root); 
    // cout << "\n";
    // cout << "root -> key = "<< root -> key << "\n";
    // cout << "levelOrder: \n";
    // levelOrder(root);

    cout << "tree structure: \n";
    picTreeStructure(root);
    
    cout << "remove 10 \n";
    root = deleteNode(root, 10);
    picTreeStructure(root);

    return 0; 
} 
