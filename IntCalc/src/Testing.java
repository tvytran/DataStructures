import java.util.Arrays;
import java.util.HashMap;
public class Testing {

    public static void main(String[] args){
        try {
            MyStack<Character> stack = new MyArrayList<>();
            System.out.println(stack);
            stack.push('s');
            System.out.println(stack);
            char c = stack.pop();
            System.out.println(c);
            System.out.println(stack);
            stack.push('t');
            stack.push('i');
            stack.push('c');
            System.out.println(stack);
            System.out.println(stack.peek());
            while((!stack.peek().equals('k')))
            {
                stack.pop();
                System.out.println("yup");
            }

            int i = 0;
            boolean j = true;
            while (i < 6 && j)
            {
                if(i == 3)
                    j = false;
                System.out.println("apples");
                i++;
            }

        }
        catch (Exception e) {
            System.err.print("Error");
        }

    }
}
