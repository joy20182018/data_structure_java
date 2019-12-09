import java.util.TreeMap;

public class Trie {   // 多叉树

    private class Node{

        public boolean isWord;   // 判断是否是一个单词
        public TreeMap<Character, Node> next;   // 下一节点是这个字符对引得一个映射

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;  // 存储单词数

    public Trie(){
        root = new Node();
        size = 0;
    }

    // 获得trie中的单词数
    public int getSize(){
        return size;
    }

    // 向trie中添加一个单词
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i ++){   // 找到下一个节点的映射中是否存在该字符对应的映射
            char c = word.charAt(i);
            if (cur.next.get(c) == null){   // 如果这个字符的下一个节点为空
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (! cur.isWord){   // 如果这个单词之前没有
            cur.isWord = true;
            size ++;
        }
    }

    // 在trie中查询单词
    public boolean contains(String word){

        Node cur = root;

        for (int i = 0 ; i < word.length(); i ++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }


    // 查找是否在trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){

        Node cur = root;
        for (int i = 0; i < prefix.length(); i ++){
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }
}
