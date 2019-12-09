public class Main {

    public static void main(String[] args) {
	// write your code here

        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i ++){
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(2, 2);
        System.out.println(arr);
        System.out.println("arr.getSize() = " + arr.getSize());
        System.out.println("arr.getCapacity() = " + arr.getCapacity());
        arr.addFirst(-1);
//        arr.add(16, 100);
        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
//        arr.remove(4);
//        System.out.println(arr);
        arr.remove(3);
        System.out.println(arr);
//        arr.addFirst('b');
//        System.out.println(arr);
//        arr.remove(0);
//        System.out.println(arr);
//        arr.removeLast();
////        System.out.println(arr);
////        if (arr.removeElement('c')){
////            System.out.println("succeed");
////            System.out.println(arr);
//        }
//        else{
//            System.out.println("failed");
//        }
//        System.out.println(arr);
        arr.contains(10);
    }
}
