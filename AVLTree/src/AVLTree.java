import java.util.ArrayList;

// AVL平衡二叉树
public class AVLTree<K extends Comparable<K>, V>{

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;   // 记录每个节点的高度

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    
    public int getSize() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    // 判断该二叉树是否是一颗二分搜索树
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);

        // 进行中序遍历之后检查存入到keys数组中的元素是不是升序
        for (int i = 1; i < keys.size(); i ++){
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0 )
                return false;
        }

        return true;
    }

    // 二分搜索树的中序遍历,将中序遍历的结果存入keys数组中
    private void inOrder(Node node, ArrayList<K> keys){

        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否是一颗平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一颗平衡二叉树，递归算法
    private boolean isBalanced(Node node){

        if (node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 获得节点node的高度
    private int getHeight(Node node){
        if (node == null)
            return 0;

        return node.height;
    }

    // 获得节点node的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /*
    对y节点进行向右旋转的操作，返回旋转后新的根节点
                 y                              x
                / \                          /     \
               x    T4   向右旋转（y）        z       y
              / \       ---------->       /  \      /  \
             z   T3                      T1  T2    T3  T4
            / \
           T1  T2
     在y这个节点不满足平衡性
     */
    private Node rightRotate(Node y){

        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转的过程
        x.right = y;
        y.left = T3;

        // 更新height
        //只有x和y节点的高度发生变化
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /*
    对节点有、进行向左旋转，返回旋转后新的根节点x

     */

    private Node leftRotate(Node y){

        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新Height值
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;

    }

    // 向AVL树中添加新的元素（key， value）
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素（key， value），递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        // 递归过程， 典型二分搜索树添加过程
        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else    // key.compareTo(node.key) == 0
            node.value = value;

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        System.out.println("unbalanced : " + balanceFactor);
        if (Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced : " + balanceFactor);
        }


        // 平衡维护
        // 左节点的高度大于右节点高度，且这个不平衡性是由于该节点左侧的左侧多添加了一个节点
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            // 使用右旋转，使这棵树进行平衡
            // 返回新的根节点
            return rightRotate(node);
        }

        // 平衡维护
        // 左节点的高度大于右节点高度，且这个不平衡性是由于该节点右侧的右侧多添加了一个节点
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            // 使用左旋转，使这棵树进行平衡
            // 返回新的根节点
            return leftRotate(node);
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if (node == null)
            return null;

        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else    // if (key.compareTo(node.key)) > 0
            return getNode(node.right, key);
    }
    
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    
    public V get(K key) {
        Node node = getNode(root, key);

        return node == null ? null : node.value;
    }

    // 更新操作
    public void set(K key, V newvalue) {
        Node node = getNode(root, key);

        if (node == null)
            throw new IllegalArgumentException(key  + " deesn`t exists");

        node.value = newvalue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }


    // 从二分搜索树中删除键为key的节点
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }

        return null;
    }


    // 删除以node为根的二分搜索树中键为key的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key){

        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }
        else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null){
                // 此时的node为待删除节点
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;   // 返回右子树根节点
            }

            // 待删除节点右子树为空的情况
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树都不为空的情况
            // 找到比待删除节点大的节点, 即待删除节点右子树的最小节点
            // 或比待删除结点小的节点，即左子树最大节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args) {
        // write your code here

        System.out.println("PAP.txt");

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("E:/java/AVLTree/PAP.txt", words)){

            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();

            for (String word: words){
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of F : " + map.get("r") );

            System.out.println("is BST: " + map.isBST());
            System.out.println("is balanced: " + map.isBalanced());
        }
    }

}
