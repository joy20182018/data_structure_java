import java.util.TreeMap;

public class WordDictionary {
    private class Node {

        public boolean isWord;   // 判断是否是一个单词
        public TreeMap<Character, Node> next;   // 下一节点是这个字符对引得一个映射

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
    private Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        Node cur = new Node();
        for (int i = 0 ; i < word.length(); i ++){
            char c = word.charAt(i);
            if (cur.next.get(i) == null){
                cur.next.put(c, new Node() );
            }
            cur = cur.next.get(c);
        }

        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
            return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index){
        // 递归到头的情况
        if (index == word.length()){
            return node.isWord;
        }


        char c = word.charAt(index);
        if (c != '.'){
            if (node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        }
        else{
            for (char nextChar: node.next.keySet()){
                if (match(node.next.get(nextChar), word, index + 1))
                    return true;
            }
            return false;
        }
    }
}
