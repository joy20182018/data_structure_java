public class Main {

    public static void main(String[] args) {
	// write your code here
        LinkedList<Integer> linklist  = new LinkedList<>();
        for (int i = 0; i < 10; i ++){
            linklist.addFirst(i);
            System.out.println(linklist);
            System.out.println("linklist.getsize() = " + linklist.getSize());
            System.out.println("\n");
        }
        linklist.add(3, 666);
        System.out.println(linklist);
        System.out.println("linklist.getsize() = " + linklist.getSize());

        linklist.remove(3);
        System.out.println(linklist);
        System.out.println("linklist.getsize() = " + linklist.getSize());

        linklist.removeFist();
        System.out.println(linklist);
        System.out.println("linklist.getsize() = " + linklist.getSize());

        linklist.removeLast();
        System.out.println(linklist);
        System.out.println("linklist.getsize() = " + linklist.getSize());
    }
}
