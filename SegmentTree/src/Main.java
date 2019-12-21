public class Main {

    public static void main(String[] args) {
	// write your code here
        Integer[] nums = {-1, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);

        System.out.println(segTree.query(0, 2));
    }
}
