public class Test {
    public static int recursiveMultiplication(int x, int y) {
        if(y == 1)
        {
            return x;
        }
        if(y == 0)
            return 0;
        return x + recursiveMultiplication(x,y-1);
    }

    public static String reverse(String s) {
        if(s.length() == 1 || s.length() == 0)
            return s;
        return s.substring(s.length()-1,s.length()) + reverse(s.substring(0,s.length()-1));
    }

    private static int maxHelper(int[] array, int index, int max) {
        if(index == array.length)
            return max;
        if(array[index] > max)
            max = array[index];
        return maxHelper(array, index+1, max);
    }
    public static int max(int[] array) {

        return maxHelper(array, 0, Integer.MIN_VALUE);
    }

    public static boolean isPalindrome(String s) {
        if(s.length() == 1 || s.length() == 0)
        {
            return true;
        }
        if(s.charAt(0) != s.charAt(s.length()-1))
        {
            return false;
        }


        return isPalindrome(s.substring(1,s.length()-1));
    }

    private static boolean memberHelper(int key, int[] array, int index) {
        if(index == array.length)
        {
            return false;
        }
        if(array[index] == key)
        {
            return true;
        }

        return memberHelper(key, array,index+1);
    }
    public static boolean isMember(int key, int[] array) {
        return memberHelper(key, array, 0);
    }

    public static String separateIdentical(String s) {
        if(s.length() == 0 || s.length() == 1)
        {
            return s;
        }
        if(s.charAt(0) == s.charAt(1))
        {
            return s.charAt(0) + "~" + separateIdentical(s.substring(1));
        }
        return s.charAt(0) + separateIdentical(s.substring(1));
    }

    public static void main(String[] args)
    {
        System.out.println(recursiveMultiplication(4,3));
        System.out.println(reverse("apples"));
        int[] array = new int[] {13,12,6,32,7,53,45,231,43,34};
        System.out.println(max(array));
        System.out.println(isPalindrome("31213"));
        System.out.println(isMember(33,array));
        System.out.println(separateIdentical("pllluuggg"));
    }

}
