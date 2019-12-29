import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args){

        System.out.println("e:/java/RedBlackTree/PAP.txt");

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("E:\\java\\RedBlackTree\\src\\PAP.txt", words)){

            System.out.println("total words: " + words.size());


            // test rbtree
            long startTime = System.nanoTime();

            RBTree<String, Integer> rbTree = new RBTree<>();

            for (String word: words){
                if (rbTree.contains(word))
                    rbTree.set(word, rbTree.get(word) + 1);
                else
                    rbTree.add(word, 1);
            }

            for (String word: words)
                rbTree.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("RBTree: " +  time + " s");

            // test bst
            startTime = System.nanoTime();

            BST<String, Integer> bst = new BST<>();

            for (String word: words){
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for (String word: words)
                bst.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("bst: " +  time + " s");


            // test AVLTree
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();

            for (String word: words){
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for (String word: words)
                avl.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("avl: " +  time + " s");


            // test hashtable
            startTime = System.nanoTime();

            HashTable<String, Integer> ht = new HashTable<>(13);
            // M的选择很重要，对性能有影响

            for (String word: words){
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }

            for (String word: words)
                ht.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("ht: " +  time + " s");


            // test hashtable2
            startTime = System.nanoTime();

            HashTable2<String, Integer> ht2 = new HashTable2<>();
            // M的选择很重要，对性能有影响

            for (String word: words){
                if (ht2.contains(word))
                    ht2.set(word, ht2.get(word) + 1);
                else
                    ht2.add(word, 1);
            }

            for (String word: words)
                ht2.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("ht2: " +  time + " s");

        }
    }
}