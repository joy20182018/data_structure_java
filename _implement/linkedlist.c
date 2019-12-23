//
// Created by hpcomputer on 2019/11/30.
//

//
// Created by hpcomputer on 2019/11/30.
//

#include<stdio.h>
#include<stdlib.h>


typedef struct linked_list{
    int data;
    struct linked_list *next;
}linked_list_node, *linked_list;


// 初始化创建虚拟头节点,数据从1开始计数
void init_linked_list(linked_list *l){
    *l = malloc(sizeof(linked_list_node));
    (*l) -> next = NULL;
    // data可以存任何或节点个数
    // printf("l is addr: %d\n", *l);
}


// 判断链表是否为空
int isEmpty(linked_list l){
    return l.next == NULL;
}

// 获取链表元素个数
int get_size(linked_list l){
    if (l -> next == NULL){
        return 0;
    }
    linked_list_node *p;
    int count = 0;
    for ( p = l -> next; p -> next != NULL; p = p -> next){
        count += 1;
        // printf("count : %d\n", count);
    }
    return count;

}

// 在链表index处添加节点
void add_index(linked_list *l, int index, int e){
    linked_list_node *prev;
    prev = (*l);
    for (int i = 0; i < index && prev -> next !=NULL; i ++){
        prev = prev -> next;
        // printf("prev -> data = %d\n", prev -> data);
    }
    linked_list_node *p = malloc(sizeof(linked_list_node));
    p -> data = e;
    p -> next = prev -> next;
    prev -> next = p;
}

// 在链表头部添加节点、
void add_first(linked_list *l, int e){
    add_index(l, -1, e);
}

// 在链表尾部添加节点
void add_last(linked_list *l, int e){
    // linked_list_node *prev;
    // rev = (*l);
    // for ( ; prev -> next != NULL; ){
    //     prev = prev -> next;
    //     // printf("prev -> data = %d\n", prev -> data);
    // }

    // // for (int i = 0 ; i < index)
    // linked_list_node *p = malloc(sizeof(linked_list_node));
    // p -> data = e;
    // p -> next = prev -> next;
    // prev -> next = p;p
    add_index(l, get_size(*l)+1, e);
}

// 获取链表index处的数据
int get(linked_list l, int index){
    linked_list_node *p = l -> next;
    for (int i = 0; i < index; i++){
        p = p -> next;
    }
    return p -> data;
}

// // 获取第一个数据
int get_first(linked_list l){
    return get(l, 0);
}

// // 获取最后一个元素
int get_last(linked_list l){
    return get(l, get_size(l));
}

// 查找数据
int contains(linked_list l, int e){
    linked_list_node *p = l -> next;
    int count = 1;
    while (p != NULL){
        if (p -> data = e){
            return 1;
        }
        p = p -> next;
    }
    return 0;
}


// 移除第index位置上的数据
int remove_index(linked_list *l, int index){
    linked_list_node *prev;
    prev = (*l) -> next;   // 由于该链表存在虚拟头节点，则，循环从1开始
    for (int i = 1; i <= index; i ++){
        // printf("prev -> data = %d\n", prev ->data);
        prev = prev -> next;
    }

    linked_list_node *ret = prev -> next;
    prev -> next = ret -> next;
    return ret -> data;
}

int remove_first(linked_list *l){
    return remove_index(l, 1);
}

int remove_last(linked_list *l){
    return remove_index(l, get_size(*l));
}


// 移除特定的数据
void remove_element(linked_list *l, int val){
    printf("remove_element\n");
    linked_list_node *p;
    p = (*l);
    int count = 0;
    while (p -> next != NULL){
        // printf("p -> data = %d\n", p -> data);
        if (p -> next -> data == val){
            p -> next = p -> next -> next;
            count += 1;
        }
        p = p -> next;
    }

}

//// 利用递归实现
void remove_elements_circulation(linked_list *L, int x)
{
    if ((*L) == NULL)
        return;

    linked_list_node *t;
    if ((*L) -> data == x){
        t = *L;
        (*L) = (*L) -> next;
        free(t);
        return;
    }
    else
        remove_elements_circulation(&((*L) -> next) , x);
}

// 打印链表
void print_linked_list(linked_list *l){
    printf("print linkedlist\n");
    linked_list_node *p = (*l) -> next;
    int count = 1;
    while (p != NULL){
        printf("%d(%d) -> ", p -> data, count);
        p = p -> next;
        count += 1;
    }
    printf("NULL\n");
}

int main(void)
{
    /* code */
    linked_list l;
    // printf("start\n");
    init_linked_list(&l);
    // printf("init end ");
    for (int i = 0; i <= 10; i ++){
        add_last(&l, i);
    }
    // printf("get_size = %d\n", get_size(l));
    // print_linked_list(&l);
    // printf("contains = 0 is %d\n", contains(l,0));
    // printf("get(3) = %d\n", get(l, 3));
    // int e;
    // e = remove_index(&l, 2);
    // printf("remove_index(&l, 4) = \n", e);
//    print_linked_list(&l);

//    remove_element(&l, 2);
    remove_elements_circulation(&l, 7);
    print_linked_list(&l);
    printf("hello world\n");
    return 0;
}