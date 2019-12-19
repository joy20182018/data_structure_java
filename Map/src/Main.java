import java.util.ArrayList;

public class Main {

    private static double testMap(Map<String, Integer> map, String filename){
        // 词频统计
        long startTime = System.nanoTime();

        System.out.println(filename);

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("e:/java/" + filename, words)){

            System.out.println("Total words: " + words.size());

            for (String word: words){
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of a: " + map.get("a") );
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
	// write your code here
        String filename = "Pride and Prejudice";

        System.out.println("BSTMap : " + testMap(BSTMap, filename) + " s");

        System.out.println("LinkedListMap : " + testMap(LinkedListMap, filename) + " s");
    }
}
