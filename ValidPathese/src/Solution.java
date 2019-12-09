//import java.util.Stack;
public class Solution {
    public boolean isValid(String s){
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i ++){
           char c = s.charAt(i);   // 从字符串中取第一个字符
           if (c == '(' || c == '[' || c == '{')
               stack.push(c);
           else{
               if (stack.isEmpty()){
                   return false;
               }

               char topChar = stack.pop();

               if (c == '(' && topChar != '(')
                   return false;
               if (c == '[' && topChar != '[')
                   return false;
               if (c == '{' && topChar != '{')
                   return false;
           }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args){
        System.out.println((new Solution()).isValid("(()){"));
    }
}
