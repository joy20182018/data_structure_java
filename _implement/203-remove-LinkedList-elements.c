/**
 * Definition for singly-linked list.
 
 */

struct ListNode {
    int val;
    struct ListNode *next;
 };
struct ListNode* removeElements(struct ListNode* head, int val){
    struct ListNode *dummyHead;
    dummyHead = malloc(sizeof(struct ListNode));
    dummyHead -> next = head;   // 创建一个虚拟头节点

    struct ListNode *prev;
    prev = dummyHead;
    while (prev -> next != NULL){
        if (prev -> next -> val == val){
            prev -> next = prev -> next -> next;
        }
        else{
            prev = prev -> next;
        }
    }

    return dummyHead -> next;

}