#include<stdio.h>
#include<stdlib.h>

typedef int ElemType;

typedef struct StackNode{
    ElemType data;
    struct StackNode *next;
}StackNode, *StackNodePtr;   // 定义节点变量及指向节点的指针变量

typedef struct Stack{
    StackNodePtr top;
    int count;
}Stack;   // 定义栈

void InitStack(Stack *S){
    /*
     initial Stack
    */
    S -> top = NULL;
    S -> count = -1;

}

void push(Stack *S, ElemType e){
    StackNodePtr p = malloc(sizeof(StackNode));
    // printf("push input data is %d\n", e);
    p -> data = e;
    p -> next = S -> top;
    S -> top = p;
    S -> count ++;
    // printf("S -> count = %d\n", S ->count);
}

ElemType pop(Stack *S){
    ElemType e;
    StackNodePtr p;
    p = S -> top;
    e = p -> data;

    S -> top = p -> next; 
    S -> count --;
    return e;

}

int isEmpty(Stack S){
    // printf("S.count = %d\n", S.count);
    return S.count == -1;
}

ElemType peek(Stack S){
    if (S.count == -1){
        printf("Stack is empty");
        return 0;
    }
    return S.top -> data;
}

int getSize(Stack S){
    return S.count;
}

void PrintStack(Stack S){
    if (S.count == -1){
        printf("Stack is empty\n");
        return;
    }
    printf("top [ ");
    StackNodePtr p;
    p = S.top;
    while (S.count != -1){
        printf("%d", p -> data);
        p = p -> next;
        // printf("S.count = %d\n", S.count);
        if (S.count != 0){
            printf(", ");
        }
        S.count --;
    }
    printf(" ] \n");

}

//
int main(void){
    /* code */
    Stack S;
    InitStack(&S);
     实现括号匹配算法
     char *c = "[()()({}{})";
     // printf("%s\n", c);
     printf("%d\n", strlen(c));
     for (int i = 0; i < strlen(c); i ++){
         char ch = c[i];    // 从字符串中取出第一个元素
         printf ("%c\n", ch);

         if (ch == '(' || ch == '[' || ch == '{'){
             push(&S, ch);   // 如果字符是以上的字符，则放入栈中
         }
         else{
             if (isEmpty(S)){
                 printf("not match");
                 return 0;
             }

             char topChar = pop(&S);   // 从栈中取出栈顶的元素
             if (ch == '(' && topChar != '('){
                 printf("not match");
                 return 0;
             }
             if (ch == '[' && topChar != '['){
                 printf("not match");
                 return 0;
             }
             if (ch == '{' && topChar != '{'){
                 printf("not match");
                 return 0;
             }
         }
     }
     if (isEmpty(S)){
         printf("match");
         return 0;
     }

     printf("not match");

//    // 判断是否可以按这样的顺序出栈
//
//
//    return 0;
}


//    for (int i = 1; i <= 10; i ++){
//        push(&S, i);
//    }
//    int sum = 0;
//    for (int i = 0; i < 10; i ++){
//        int e = pop(&S);
//        printf("pop() = %d\n", e);
//        sum += e;
//        printf("sum = %d\n", sum);
//    }
//    printf("sum = %d\n", sum);
//    // for (int i = 0; i < 4; i ++){
//    //     push(&S, i + 1);
//    // }
//    // // if (!isEmpty(S)){
//    // //     printf("not empty\n");
//    // // }
//    // // else{
//    // //     printf("empty\n");
//    // // }
//    // // PrintStack(S);
//    // // printf("pop a data is %d\n", pop(&S));
//    // // printf("pop a element\n");
//    // // PrintStack(S);
//
//    // printf("pop a data is %d\n", pop(&S));
//    // printf("pop a element\n");
//    // PrintStack(S);
//
//    // // printf("pop a data is %d\n", pop(&S));
//    // // printf("pop a element\n");
//    // // PrintStack(S);
//
//    // // printf("pop a data is %d\n", pop(&S));
//    // // printf("pop a element\n");
//    // // PrintStack(S);
//
//    // printf("Stack's top data is %d\n", peek(S));
//