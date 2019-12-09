public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);   // 压入栈一个元素
    E pop();   // 弹出一个元素,并返回弹出的元素
    E peek();   // 看一下栈顶的元素
}
