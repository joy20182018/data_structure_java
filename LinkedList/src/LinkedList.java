public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyhead;   // 添加一个虚拟头节点
    int size;

    public LinkedList(){
        dummyhead = new Node(null, null);
        size = 0;
    }

    // 获取链表元素个数
    public int getSize(){
        return size;
    }

    // 判断链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }


    // 在链表index处添加节点
    // 关键是找到前一个节点
    public void add(int index, E e){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("failed");
        }

//        if (index == 0){
//            addFirst(e);
//        }
//        else{
//            Node prev = head;
//            for (int i = 0 ; i < index -1; i ++){
//                prev = prev.next;
//            }
////            Node node = new Node(e);
////            node.next = prev.next;
////            prev.next = node;
//            prev.next = new Node(e, prev.next);
//            size ++;
//        }

        Node prev = dummyhead;
        for (int i = 0 ; i < index; i ++){
            prev = prev.next;   // 找插入节点的前一个节点
        }
        prev.next = new Node(e, prev.next);
        size ++;
    }

    //在链表头部添加新元素
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        head = new Node(e, head);
//        size ++;
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }


    // 获得链表第index处的元素
    public E get(int index){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("failed");
        }

        // 遍历元素
        Node cur = dummyhead.next;
        for (int i = 0; i < index; i ++){
            cur = cur.next;
        }
        return cur.e;
    }

    // 获得第一个元素
    public E getFist(){
        return get(0);
    }
    // 获得最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 修改index处的元素
    public void set(int index, E e){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("failed");
        }

        Node cur = dummyhead.next;
        for (int i = 0 ; i < index; i ++){
            cur = cur.next;
        }
        cur.e = e;
    }

    // 如果是基本类型比较，那么只能用==来比较，不能用equals ，如果是基本类型的包装类型,那么用equals
    // 查找数据
    public boolean contains(E e){
        Node cur = dummyhead.next;
        while (cur != null){
            if (cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 从index位置删除一个元素
    public E remove(int index){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("failed");
        }
        Node prev = dummyhead;
        for (int i = 0 ; i < index; i ++){
            prev = prev.next;   // 找到待删除节点的前一个节点
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;

    }

    public E removeFist(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyhead.next;
        while(cur != null){
            res.append(cur + " -> ");
            cur = cur.next;
        }
//        for (Node cur = dummyhead.next; cur != null; cur = cur.next)
//            res.append(cur + " -> ");
        res.append("NULL");
        return res.toString();
    }
}
