import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args){

        System.out.println("e:/java/RedBlackTree/PAP.txt");

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("e:/java/RedBlackTree/PAP.txt", words)){

            System.out.println("total words: " + words.size());

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
            
        }
    }
}