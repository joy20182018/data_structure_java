public class LinkedListStack<E> implements Stack<E>{


    private LinkedList<E> List;

    public LinkedListStack(){
        List = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return List.getSize();
    }

    @Override
    public boolean isEmpty(){
        return List.isEmpty();
    }

    @Override
    public void push(E e){
        List.addFirst(e);
    }

    @Override
    public E pop(){
        return List.removeFist();
    }

    @Override
    public E peek(){
        return List.getFist();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(List);
        return res.toString();
    }

    public static void main(String[] args) {
        // write your code here
        LinkedListStack<Integer> stck = new LinkedListStack<>();
        for (int i = 0; i < 5; i ++) {
            stck.push(i);
            System.out.println(stck);
        }
        System.out.println("stck.pop() = " + stck.pop());
        System.out.println(stck);
    }

}
