import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Test {

    public static void insertionSort(char[] arr, int n)
    {
        int i, j;
        char key;
        for(i = 0; i < n; i++)
        {
            key = arr[i];
            j = i - 1;

            while(j >= 0 && Character.compare(arr[j],key) > 0)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j + 1] = key;
        }
    }

    public static void insertionSort(String[] arr)
    {
        //sorting the words
        int i, j;
        String key;
        for(i = 0; i < arr.length ;i++)
        {
            key = arr[i];
            j = i - 1;

            while(j >= 0 && arr[j].compareTo(key) > 0)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j + 1] = key;
        }

    }

    public static void helpMe()
    {
        System.out.println(Integer.parseInt("my life without you"));
    }
    public static void main(String[] args) throws IOException {
        //getting to directory of where file will be then again who knows where the file exists in this world
        String directory = "C:\\Users\\tvytr\\IdeaProjects\\CapstoneProject\\src\\";
        //getting to file
        String fileName = "which.txt";
        File file = new File(directory + fileName);
        if (!file.exists()) {
            System.err.println("Error: Cannot open file '" + fileName + "' for input.");
            System.exit(1);
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String st;

        while ((st = br.readLine()) != null)
        {
            System.out.println(st);
        }

            char[] arr = {'a', 'E', ' '};
        insertionSort(arr,3);
        System.out.println(arr);


        MyMap<String, MyLinkedList<String>> hmap = new MyHashMap<>();
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("apples");
        list.add("bananas");
        list.add("oranges");
        MyLinkedList<String> list2 = new MyLinkedList<>();
        list2.add("strawberries");
        list2.add("kiwis");
        list2.add("mangoes");
        hmap.put("regular",list);
        hmap.put("more",list2);

        Iterator<Entry<String, MyLinkedList<String>>> iterator = hmap.iterator();
        while(iterator.hasNext()) {
            Entry<String, MyLinkedList<String>> entry = iterator.next();
            String key = entry.key;
            MyLinkedList<String> itty = entry.value;
            System.out.println("key = " + key + " itty = " + itty);

        }

        iterator = hmap.iterator();
        while(iterator.hasNext()) {
            Entry<String, MyLinkedList<String>> entry = iterator.next();
            String key = entry.key;
            MyLinkedList<String> itty = entry.value;
            System.out.println("key = " + key + " itty = " + itty);

        }
        String number1 = "[apples]";
        String number2 = "[bananas]";
        System.out.println(number1.compareTo(number2));

        String[] array = {"hello","from","the other side","please"};
        insertionSort(array);
        for(int i = 0; i < array.length;i++)
        {
            System.out.println(array[i]);
        }
        try
        {
            int number = Integer.parseInt("2");
        }
        catch(NumberFormatException e)
        {
            System.err.println("Error: Cannot parse number: " + number1);
            System.exit(1);
        }
        int number = Integer.parseInt("2");
        System.out.println(number);

        String original = "love";
        String[] ar = new String[4];
        for (int i = 0; i < 4; i++) {
            ar[i] = String.valueOf(original.charAt(i));
        }
        //helpMe();
        /*
        try
        {
            helpMe();
        }
        catch(NumberFormatException e) {
            System.err.println("I hope I can lose ten pounds i really need to");
            System.exit(1);
        }

         */
    }
}