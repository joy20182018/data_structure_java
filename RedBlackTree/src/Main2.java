import java.util.ArrayList;
import java.util.Random;

public class Main2 {

    public static void main(String[] args){

        int n = 5000000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();

        for (int i = 0; i < n; i ++)
            testData.add(random.nextInt(Integer.MAX_VALUE));

        // test BST
        long startTime = System.nanoTime();

        BST<Integer, Integer> bst = new BST<>();

        for (Integer x: testData)
            bst.add(x, null);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");

        // test AVL
        startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<>();

        for (Integer x: testData)
            avl.add(x, null);

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVLTree: " + time + " s");

        // test RedBlackTree
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbTree = new RBTree<>();

        for (Integer x: testData)
            rbTree.add(x, null);

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RedBlackTree: " + time + " s");

    }
}
