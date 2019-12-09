public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        System.out.println("preOrder");
        bst.preOrder();
        System.out.println();

        System.out.println("inOrder");
        bst.inOrder();
        System.out.println();

        System.out.println("postOrder");
        bst.postOrder();
        System.out.println();
        System.out.println("preOrderNR");
        bst.preOrderNR();
//
//        System.out.println("levelOrder");
//        bst.levelOrder();

        System.out.println("inOrderNR");
        bst.InOrderNR();
    }
}
