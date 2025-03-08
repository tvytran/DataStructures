import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class with two different methods to count inversions in an array of integers.
 * @author Thuy Vy Tran tt2964
 * @version 1.0.0 November 17, 2022
 */
public class InversionCounter {

    /**
     * Returns the number of inversions in an array of integers.
     * This method uses nested loops to run in Theta(n^2) time.
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long countInversionsSlow(int[] array) {
        // TODO
        long count = 0;
        for(int i = 0; i < array.length; i++)
        {
            for(int j = i+1; j < array.length;j++){
                int a = array[i];
                int b = array[j];
                if(a > b)
                    count++;
            }
        }
        return count;
    }



    /**
     * Returns the number of inversions in an array of integers.
     * This method uses mergesort to run in Theta(n lg n) time.
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long countInversionsFast(int[] array) {
        // Make a copy of the array so you don't actually sort the original
        // array.
        int[] arrayCopy = new int[array.length],
              scratch =  new int[array.length];
        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        // TODO - fix return statement
        return countInversionsHelper(arrayCopy, scratch, 0,array.length-1);


    }
    private static long countInversionsHelper(int[]  array, int[] scratch, int low, int high) {
        long count = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            long left = countInversionsHelper(array,scratch,low,mid);
            long right = countInversionsHelper(array,scratch,mid+1,high);

            int i = low, j = mid + 1;
            for(int k = low; k <=high; k++) {
                if (i <= mid && (j > high || array[i] <= array[j]))
                {
                    scratch[k] = array[i++];
                }
                else
                {
                    count += mid - i + 1;
                    //if(i <= mid)
                      //count++;
                    scratch[k] = array[j++];
                }
            }
            for(int k = low; k <= high; k++)
            {
                array[k] = scratch[k];
            }
            return count + left + right;
        }
        return 0;
    }

    /**
     * Reads an array of integers from stdin.
     * @return an array of integers
     * @throws IOException if data cannot be read from stdin
     * @throws NumberFormatException if there is an invalid character in the
     * input stream
     */
    private static int[] readArrayFromStdin() throws IOException,
                                                     NumberFormatException {
        List<Integer> intList = new LinkedList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int value = 0, index = 0, ch;
        boolean valueFound = false;
        while ((ch = reader.read()) != -1) {
            if (ch >= '0' && ch <= '9') {
                valueFound = true;
                value = value * 10 + (ch - 48);
            } else if (ch == ' ' || ch == '\n' || ch == '\r') {
                if (valueFound) {
                    intList.add(value);
                    value = 0;
                }
                valueFound = false;
                if (ch != ' ') {
                    break;
                }
            } else {
                /*
                System.err.println("Error: Invalid character '" + (char)ch +
                        "' found at index " + index + " in input stream.");
                System.exit(1);

                 */
                throw new NumberFormatException(
                        "Invalid character '" + (char)ch +
                        "' found at index " + index + " in input stream.");
            }
            index++;
        }

        int[] array = new int[intList.size()];
        Iterator<Integer> iterator = intList.iterator();
        index = 0;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return array;
    }

    public static void main(String[] args) {
        // TODO
        if(args.length > 1) {
            System.err.println("Usage: java InversionCounter [slow]");
            System.exit(1);
        }
        /*
        if(args.length == 0)
        {
            System.err.println("Error: Invalid character 'x' found at index 0 in input stream." );
        }*/

        long answer = 1;
        if(args.length == 0 || args[0].equals("fast")) {
            System.out.print("Enter sequence of integers, each followed by a space: ");
            int[] array = new int[0];
            try {
                array = readArrayFromStdin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch(NumberFormatException n)
            {
                System.out.println("Error: " + n.getMessage());
                System.exit(1);
            }

            if(array.length == 0)
            {
                System.out.println( "Error: Sequence of integers not received.");
                System.exit(1);
            }
            answer = countInversionsFast(array);
        }
        else if(args[0].equals("slow")) {
            System.out.print("Enter sequence of integers, each followed by a space: ");
            int[] array = new int[0];
            try {
                array = readArrayFromStdin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch(NumberFormatException n)
            {
                System.out.println("Error: " + n.getMessage());
                System.exit(1);
            }

            if(array.length == 0)
            {
                System.err.println("Error: Sequence of integers not received.");
                System.exit(1);
            }
            answer = countInversionsSlow(array);
        }
        else {
            System.err.println("Error: Unrecognized option '" + args[0] + "'.");
            System.exit(1);
        }
        System.out.println("Number of inversions: " + answer);
        System.exit(0);
    }
}
