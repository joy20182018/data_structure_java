import java.util.ArrayList;
import java.util.TreeSet;
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
        String filename = "E:/java/Map/PAP.txt";
        BSTMap<String, Integer>bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BSTMap : " + time1 + " s");

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(bstMap, filename);
        System.out.println("LinkedListMap : " + time2 + " s");

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        double time3 = testMap(avlMap, filename);
        System.out.println("AVLMap : " + time3 + " s");
    }
}