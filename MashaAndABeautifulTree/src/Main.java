import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * Reads an array of integers from stdin.
     * @return an array of integers
     * @throws IOException if data cannot be read from stdin
     * @throws NumberFormatException if there is an invalid character in the
     * input stream
     */
    private static int[] readArrayFromStdin() throws IOException, NumberFormatException {
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
        System.out.print("Enter the number of test cases: ");
        int[] t = new int[0];
        try {
            t = readArrayFromStdin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch(NumberFormatException n)
        {
            System.out.println("Error: " + n.getMessage());
            System.exit(1);
        }
        while (t[0]-- > 0) {
            int[] m = new int[0];
            System.out.print("Enter the size of the permuation: ");
            try {
                m = readArrayFromStdin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch(NumberFormatException n)
            {
                System.out.println("Error: " + n.getMessage());
                System.exit(1);
            }

            int[] p = new int[m[0]];
            System.out.print("Enter the number sequece: ");
            try {
                p = readArrayFromStdin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch(NumberFormatException n)
            {
                System.out.println("Error: " + n.getMessage());
                System.exit(1);
            }

            int result = countMinimumOperations(p, 0, m[0] - 1);
            System.out.println(result);
        }
    }

    public static int countMinimumOperations(int[] p, int start, int end) {
        if (start == end) {
            return 0;
        }

        //separate the tree into two parts
        int mid = (start + end) / 2;
        int[] leftSubtree = Arrays.copyOfRange(p, start, mid + 1);
        int[] rightSubtree = Arrays.copyOfRange(p, mid + 1, end + 1);

        //sort the arrays
        Arrays.sort(leftSubtree);
        Arrays.sort(rightSubtree);

        //sorting the total arrays
        int[] sortedArray = Arrays.copyOfRange(p, start, end + 1);
        Arrays.sort(sortedArray);

        //checking if the sorted array is equal to the sorted total array
        boolean canSortDirectly = Arrays.equals(sortedArray, concatArrays(leftSubtree, rightSubtree));
        boolean canSortBySwapping = Arrays.equals(sortedArray, concatArrays(rightSubtree, leftSubtree));

        //if both are not possible for simply swapping them then return -1
        if (!canSortDirectly && !canSortBySwapping) {
            return -1;
        }


        //checking left side and right side for amount of permuations
        int leftOperations = countMinimumOperations(p, start, mid);
        int rightOperations = countMinimumOperations(p, mid + 1, end);


        if (leftOperations == -1 || rightOperations == -1) {
            return -1;
        }


        if (canSortDirectly) {
            return leftOperations + rightOperations;
        } else {//needed swapping
            return 1 + leftOperations + rightOperations;
        }
    }

    public static int[] concatArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
