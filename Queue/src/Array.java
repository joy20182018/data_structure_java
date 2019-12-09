public class Array <E> {   // 表示泛型
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];   // 做一个强制类型转换
        size = 0;
    }

    // 无参构造函数
    public Array() {
        this(10);
    }   // 如果构建实例时没有定义容量，则按默认赋值

    // 获取数组元素个数
    public int getSize() {
        return size;
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取index处的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("failed");
        }
        return data[index];
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }


    // 设置index处的元素为e
    void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("failed");
        }
        data[index] = e;
    }

    // 向数组尾插入元素
    public void addLast(E e) {
//        if (size == data.length)
//            throw new IllegalArgumentException("AddList failed");
//        data[size] = e;
//        size ++;
        add(size, e);
    }

    // 向数组头插入元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 在index处插入元素e
    public void add(int index, E e) {
        if (size == data.length){
            resize(2 * data.length);   // 如果数组满了，则进行扩容
        }
        // throw new IllegalArgumentException("AddList failed");
        if (index < 0 || index > size) {
            System.out.println("failed");
            throw new IllegalArgumentException("AddList failed");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
//        for (int i = 0; i < size; i ++){
//            System.out.println(data[i]);
//        }
    }

    // 在指定index位置删除一个元素并返回被删除的元素
    public E remove(int index) {
        if (index < 0 || index > size) {
            System.out.println("failed");
            throw new IllegalArgumentException("AddList failed");
        }
        E ret = data[index];
        // data[size] = null;   // 不是必须的，这样写可以被java自动的垃圾回收 loitering object != memory leak

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        // 设置一个lazy的算法，防止复杂度的震荡
        if (size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return ret;
    }

    //    // 删除数组中第一个元素，并返回删除元素
    public E removeFirst() {
        return remove(0);
    }
    //    // 删除数组中最后一个元素
    public E removeLast () {
        return remove(size - 1);
    }
    //
//    // 从数组中删除元素e
    public boolean removeElement(E e){
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    // 查找数组中是否有元素e
    public void contains (E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                System.out.println("exists");
                return;
            }
        }
        System.out.println("not exists");
    }

    // 查找数组元素e，如果存在则返回e的索引，不存在返回e
    public int find (E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public String toString () {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(',');
            }
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapcity){
        E[] newData = (E[])new Object[newCapcity];
        for (int i = 0; i < size; i ++){
            newData[i] = data[i];
        }
        data = newData;   // 将data指向新的空间
    }


}
