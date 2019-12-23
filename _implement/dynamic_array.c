#include<stdio.h>
#include<stdlib.h>

typedef struct dynamic_array{
    int *addr;   // 存放数组的首地址
    int size;   // 当前元素个数
    int capacity;   // 数组的容量
}dynamic_array;


// 动态数组初始化
dynamic_array *init_dynamic_array(){
    dynamic_array *D = malloc(sizeof(dynamic_array));
    D -> size = 0;
    D -> capacity = 10;
    D -> addr = malloc(sizeof(int) * D -> capacity);
    return D;
}

// 插入元素
void add(dynamic_array *D, int e){
    if (D ==NULL)
        return;

    if (D -> size == D -> capacity){
        int *new_addr = malloc(sizeof(int) * D -> capacity * 2);
        for (int i = 0 ; i < D -> capacity * 2; i ++){
            new_addr[i] = D -> addr[i];
        }

        free(D -> addr);

        D -> capacity = 2 * D -> capacity;
        D -> addr = new_addr;
    }

    D -> addr[D -> size] = e;
    D -> size ++;
}

// 根据位置删除
void remove_index(dynamic_array *D, int index){
    if (D == NULL)
        return;
    if (index < 0 || index >= D -> size)
        return;
    for (int i = index; i < D -> size; i ++){
        D -> addr[i] = D -> addr[i + 1];
    }
    D -> size --;
}

//查找
int find(dynamic_array *D, int e) {
    if(D == NULL){
        return -1;
    }
    //找到值的位置
    int pos = -1;
    for(int i = 0; i< D->size; i++){
        if(D -> addr[i] == e){
            pos = i;
            break;
        }
    }
    return pos + 1;
}

// 打印数组
void print_dynamic_array(dynamic_array D){
    printf("");
    printf("[ ");
    for (int i = 0; i < D.size ; i ++){
        printf("%d", D.addr[i]);
        if (i != D.size - 1){
            printf(", ");
        }
    }
    printf(" ]\n");
}


int main(void){

    dynamic_array *D = init_dynamic_array();
    printf("after create arr, capacity: %d, size: %d \n",D->capacity, D->size);
    for (int i = 0 ; i  < 10; i ++){
        add(D, i + 1);
    }
    // printf("D -> addr[3] = %d\n", D -> addr[3]);
    print_dynamic_array(*D);
    printf("after create arr, capacity: %d, size: %d \n",D->capacity, D->size);

    add(D, 12);
    print_dynamic_array(*D);
    printf("after create arr, capacity: %d, size: %d \n",D->capacity, D->size);

    printf("find(3) is in %d\n", find(D, 3));
    printf("find(12) is in %d\n", find(D, 12));

    return 0;
}