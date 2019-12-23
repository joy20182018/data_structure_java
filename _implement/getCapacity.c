//
// Created by hpcomputer on 2019/11/23.
//
#include<stdio.h>

int get_array_length(int *arr){

    return sizeof(arr) / sizeof(arr[0]);
}

//int get_string_length(char string){
//    return strlen(string);
//}
