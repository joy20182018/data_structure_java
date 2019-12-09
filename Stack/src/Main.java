import java.util.Random;
public class Main {

    private static double testStack(Stack<Integer> stack, int opCount){

        long startTime = System.nanoTime();
        // ...

        Random random = new Random();
        for (int i = 0; i <opCount; i ++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0 ; i < opCount; i ++){
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) % 1000000000.0;

    }
    public static void main(String[] args) {
        // write your code here
        int opCount = 6000000;
        ArrayStack<Integer> arraystack = new ArrayStack<>();
        double time1 = testStack(arraystack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedliststack = new LinkedListStack<>();
        double time2 = testStack(linkedliststack, opCount);
        System.out.println("linkedliststack, time: " + time2 + " s");
    }
}
