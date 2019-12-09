public class Sum {
    public static int sum(int[] arr){
        return sum(arr, 0);
    }

    // 计算递归,从arr[l...n]范围内数字的和
    private static int sum(int[] arr, int l){
        if (l == arr.length)
            return 0;
        return arr[l] + sum(arr, l + 1);   // 递归调用
    }
    public static void main(String[] args) {
        // write your code here
        int[] nums = {1,2,3,4,5,6};
        System.out.println(sum(nums));
    }
}
