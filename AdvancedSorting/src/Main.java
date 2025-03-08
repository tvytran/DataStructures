import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void mergesortHelper(int[] array, int[] scratch, int low, int high)
    {
        if(low <high)
        {
            int mid = low + (high-low)/2;
            mergesortHelper(array,scratch,low,mid);
            mergesortHelper(array,scratch,mid+1,high);
            int i = low, j = mid+1;
            for(int k = low; k <= high;k++)
            {
                if(i <= mid && (j > high|| array[i] <= array[j]))
                {
                    scratch[k] = array[i++];
                }
                else {
                    scratch[j] = array[j++];
                }
            }
            for(int k = low; k <= high;k++)
            {
                array[k] = scratch[k];
            }

        }

    }
    public static void mergesort(int[] array)
    {
        int[] scratch = new int[array.length];
        mergesortHelper(array,scratch,0,array.length-1);
    }
    public static void main(String[] args) {

        Random rand = new Random();
        int[] array = new int[10];
        for(int i = 0;i < 10;i++)
        {
            array[i] = rand.nextInt(100);//ints from 0-99 inclusive
        }
        System.out.println("Originial Array: " + Arrays.toString(array));

        int[] clone1 = array.clone();
        mergesort(clone1);
        System.out.println("Sorted array (mergesort): " + Arrays.toString(clone1));


    }
}