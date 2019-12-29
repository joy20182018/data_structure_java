import java.util.HashMap;
import java.util.HashSet;

public class Main {
    /*
    hashCode() 方法用于返回字符串的哈希码。

    字符串对象的哈希码根据以下公式计算：

    s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
    使用 int 算法，这里 s[i] 是字符串的第 i 个字符，n 是字符串的长度，
    ^ 表示求幂。空字符串的哈希值为 0，32代表一个字符用几进制计算
     */
    public static void main(String[] args) {
	// write your code here

        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        Student st = new Student(3, 4, "fess", "rewed");
        System.out.println(st.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(st);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(st, 100);

    }
}
