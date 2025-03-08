import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class MostAnagramsFinder {

    //instance variables
    static MyMap<String, MyLinkedList<String>> hmap = new MyHashMap<>();
    static MyMap<String, MyLinkedList<String>> bmap = new BSTreeMap<>();
    static MyMap<String, MyLinkedList<String>> rbmap = new RBTreeMap<>();
    static int biggestSize = 0;
    static BufferedReader br;

    /**
     * Returns nothing/it is void. It will only modify the red black tree.
     * The method is supposed to add all the words in the file into the red black tree.
     * If the word is an anagram of an entry in the tree, then it will add that word to the linked list of that entry.
     * Next, it will find the entry with the largest linked list.
     * Then it will remove any entries with a linked list smaller than that size.
     * So, the final tree will only contain entries with the largest anagrams
     * @param file the file necessary to go through to find anagrams
     * @throws IOException
     */
    public static void wordsToRBTree(String file) throws IOException {
        String st;
        boolean played = false;

        while ((st = br.readLine()) != null) {
            played = true;
            //this is to lower case the word
            String original = st.toLowerCase();

            //creating a linked list just in case i need it
            MyLinkedList<String> list = new MyLinkedList<>();

            //this is to put the individual characters into arrays
            char[] ar = new char[st.length()];
            for (int i = 0; i < st.length(); i++) {
                ar[i] = original.charAt(i);
            }

            //time to sort it
            insertionSort(ar, ar.length);

            //make the sorted array a word again
            String sorted = "";
            for (int i = 0; i < ar.length; i++) {
                sorted += ar[i];
            }

            //if the rbt is empty or its lower cased sorted key does not exist then we add it
            if (rbmap.isEmpty() || rbmap.get(sorted) == null)//this means it does not exist
            {
                list.add(st);
                rbmap.put(sorted, list);
            } else//this means the value exists so we add it to linked list
            {
                rbmap.get(sorted).add(st);
            }
        }
        //if not played that means the file was empty and we can just forgo everything else
        if(played == false)
        {
            return;
        }
        //time to go through the whole map to see which words dont have any anagrams
        Iterator<Entry<String, MyLinkedList<String>>> it = rbmap.iterator();

        //holding key with the biggest size
        String big = "";

        //iterate the whole map and check if the linked list size is 1
        //if it is one then add it to remove array to remove it later
        //will also be getting the key with the biggest anagram size
        while (it.hasNext()) {
            //holding entry
            Entry<String, MyLinkedList<String>> entry = it.next();

            //the entry's key
            String key = entry.key;

            //the entry's value linked list
            MyLinkedList<String> value = entry.value;

            //if null meaning nothing was stored there to begin with
            //current size is bigger than what is already stored then we replace
            if (rbmap.get(big) == null || rbmap.get(big).size() < value.size()) {
                big = key;
            }
        }
        //another iterator
        it = rbmap.iterator();
        String[] remove = new String[rbmap.size()];
        int count = 0;
        //time to remove the ones that don't have the size of big
        biggestSize = rbmap.get(big).size();
        while (it.hasNext()) {
            Entry<String, MyLinkedList<String>> entry = it.next();
            String key = entry.key;
            MyLinkedList<String> value = entry.value;

            if (value.size() != biggestSize) {
                remove[count] = key;
                count++;
            }
        }
        for(int i = 0; i < count;i++)
        {
            rbmap.remove(remove[i]);
        }
    }

    /**
     * Returns nothing/it is void. It will only modify the binary search tree.
     * The method is supposed to add all the words in the file into the binary search tree.
     * If the word is an anagram of an entry in the tree, then it will add that word to the linked list of that entry.
     * Next, it will find the entry with the largest linked list.
     * Then it will remove any entries with a linked list smaller than that size.
     * So, the final tree will only contain entries with the largest anagrams
     * @param file the file necessary to go through to find anagrams
     * @throws IOException
     */
    public static void wordsToBSTree(String file) throws IOException {
        String st;
        boolean played = false;

        while ((st = br.readLine()) != null) {
            played = true;
            //this is to lower case the word
            String original = st.toLowerCase();

            //creating a linked list just in case i need it
            MyLinkedList<String> list = new MyLinkedList<>();

            //this is to put the individual characters into arrays
            char[] ar = new char[st.length()];
            for (int i = 0; i < st.length(); i++) {
                ar[i] = original.charAt(i);
            }

            //time to sort it
            insertionSort(ar, ar.length);

            //make the sorted array a word again
            String sorted = "";
            for (int i = 0; i < ar.length; i++) {
                sorted += ar[i];
            }

            //if the bst is empty or its lower cased sorted key does not exist then we add it
            if (bmap.isEmpty() || bmap.get(sorted) == null)//this means it does not exist
            {
                list.add(st);
                bmap.put(sorted, list);
            } else//this means the value exists so we add it to linked list
            {
                bmap.get(sorted).add(st);
            }
        }

        //if there was nothing in the file, then the rest of the method is not needed
        if(played == false)
        {
            return;
        }

        //time to go through the whole tree to see which words dont have any anagrams
        Iterator<Entry<String, MyLinkedList<String>>> it = bmap.iterator();

        //holding key with the biggest size
        String big = "";

        //iterate the whole tree and check if the linked list size is 1
        //if it is one then add it to remove array to remove it later
        //will also be getting the key with the biggest anagram size
        while (it.hasNext()) {
            //holding entry
            Entry<String, MyLinkedList<String>> entry = it.next();

            //the entry's key
            String key = entry.key;

            //the entry's value linked list
            MyLinkedList<String> value = entry.value;

            //if null meaning nothing was stored there to begin with
            //current size is bigger than what is already stored then we replace
            if (bmap.get(big) == null || bmap.get(big).size() < value.size()) {
                big = key;
            }
        }
        //another iterator
        it = bmap.iterator();
        String[] remove = new String[bmap.size()];
        int count = 0;

        //adding the keys that don't have the size of big to an array
        biggestSize = bmap.get(big).size();
        while (it.hasNext()) {
            Entry<String, MyLinkedList<String>> entry = it.next();
            String key = entry.key;
            MyLinkedList<String> value = entry.value;

            if (value.size() != biggestSize) {
                remove[count] = key;
                count++;
            }
        }

        //this is where we remove
        for(int i = 0; i < count;i++)
        {
            bmap.remove(remove[i]);
        }

    }
    /**
     * Returns nothing/it is void. It will only modify the hash map.
     * The method is supposed to add all the words in the file into the hash map.
     * If the word is an anagram of an entry in the tree, then it will add that word to the linked list of that entry.
     * Next, it will find the entry with the largest linked list.
     * Then it will remove any entries with a linked list smaller than that size.
     * So, the final map will only contain entries with the largest anagrams
     * @param file the file necessary to go through to find anagrams
     * @throws IOException
     */
    public static void wordsToHashMap(String file) throws IOException {
        ///https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        //this is to read the file guys
        String st;
        boolean played = false;
        while ((st = br.readLine()) != null) {
            played = true;
            //this is to lower case the word
            String original = st.toLowerCase();

            //creating a linked list just in case I need it
            MyLinkedList<String> list = new MyLinkedList<>();

            //this is to put the individual characters into arrays
            char[] ar = new char[st.length()];
            for (int i = 0; i < st.length(); i++) {
                ar[i] = original.charAt(i);
            }

            //time to sort it
            insertionSort(ar,ar.length);

            //make the sorted array a word again
            String sorted = "";
            for(int i = 0; i < ar.length;i++)
            {
                sorted += ar[i];
            }

            //if the hash map is empty or its lower cased sorted key does not exist then we add it
            if(hmap.isEmpty() || hmap.get(sorted) == null)//this means it does not exist
            {
                list.add(st);
                hmap.put(sorted,list);
            }
            else//this means the key exists so we add it to linked list
            {
                hmap.get(sorted).add(st);
            }
        }
        if(played == false)
        {
            return;
        }
        //time to go through the whole map to see which words dont have any anagrams
        Iterator<Entry<String, MyLinkedList<String>>> it = hmap.iterator();

        //holding key with the biggest size
        String big = "";

        //iterate the whole map and check if the linked list size is 1
        //if it is one then add it to remove array to remove it later
        //will also be getting the key with the biggest anagram size
        while(it.hasNext())
        {
            //holding entry
            Entry<String, MyLinkedList<String>> entry = it.next();

            //the entry's key
            String key = entry.key;

            //the entry's value linked list
            MyLinkedList<String> value = entry.value;

            //if null meaning nothing was stored there to begin with
            //current size is bigger than what is already stored then we replace
            if(hmap.get(big) == null || hmap.get(big).size() < value.size())
            {
                big = key;
            }
        }

        //another iterator
        it = hmap.iterator();
        String[] remove = new String[hmap.size()];
        int count = 0;
        //time to remove the ones that don't have the size of big
        biggestSize = hmap.get(big).size();
        while (it.hasNext()) {
            Entry<String, MyLinkedList<String>> entry = it.next();
            String key = entry.key;
            MyLinkedList<String> value = entry.value;

            if (value.size() != biggestSize) {
                remove[count] = key;
                count++;
            }
        }
        for(int i = 0; i < count;i++)
        {
            hmap.remove(remove[i]);
        }

    }

    /**
     * returns nothing/void
     * it is supposed to sort an array of strings using insertion sort
     * @param arr a string array    
     */
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
    /**
     * Returns a sorted array of the words that are anagrams of each other
     * The method is supposed to iterate over the linked list and add it into a string array
     * Then using insertion sort and comparing the strings lexicographically, the string array will be sorted
     * @param list a linked list of words that are anagrams of each other
     * @return a sorted array of the words in the given LinkedList
     */
    public static String[] insertionSort(MyLinkedList<String> list)
    {
        //time to add all the values in that list into an array
        String[] arr = new String[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            arr[i] = list.get(i);
        }

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
        return arr;

    }

    /**
     * returns nothing/is void
     * sorts an array of chars using insertion sort by comparing the chars' unicode
     * @param arr an array of chars
     * @param n the length of the array of chars
     */
    //https://www.geeksforgeeks.org/insertion-sort/
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

    /**
     * This method will accept two string arguments, the first one being the file name and the second one being the desired data structure to be used.
     * If there are not exactly two string arguments, then an error message will be printed and exit the program.
     * When there are two string arguments, the method will check if the file exists, if not it will print an error message and exit the program.
     * Then it will check if the second argument is a valid data structure, if not it will print an error message and exit the program.
     * Once a valid file and data structure is given, the method will go to its respective if statements depending on the data structure given.
     * The different if statements implenting the different data structures are the same. The only difference is adding the words to a different data structure varaible.
     * In each if statement block, it will try-catch wordsToHashMap, wordsToBSTree, or wordsToRBTree to check if there are any I/O errors. If so, an error message will be printed and exit the program.
     * Then it will check if the biggest size of the linkedlist in each map/tree is greater than 1, if its not then it will print that there are no anagrams.
     * wordsToHashMap, wordsToBSTree, or wordsToRBTree will make sure that the trees only have the greatest size anagrams, so then this method will add all those words
     * in the proper format and sort them alphabetically and print the largest group size, the amount of groups with that size, and all the groups of words in brackets.
     * @param args only accepts two string arguments-
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //checking if the arguments given are of length two
        if(args.length != 2)
        {
            System.err.println("Usage: java MostAnagramsFinder <dictionary file> <bst|rbt|hash>");
            System.exit(1);
        }
        //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        //getting to directory of where file will be then again who knows where the file exists in this world
        //String directory = "C:\\Users\\tvytr\\IdeaProjects\\CapstoneProject\\src\\";

        //getting to file
        File file = new File(args[0]);

        //checking if the file does not exists
        if(!file.exists())
        {
            System.err.println("Error: Cannot open file '" + args[0] + "' for input.");
            System.exit(1);
        }


        //checking if the second argument is valid
        if(!args[1].equals("bst") && !args[1].equals("rbt") && !args[1].equals("hash"))
        {
            System.err.println("Error: Invalid data structure '" + args[1] + "' received.");
            System.exit(1);
        }

        try{
            br = new BufferedReader(new FileReader(file));
        }
        catch(IOException e)
        {
            System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
            System.exit(1);
        }


        //using hash map
        if(args[1].equals("hash"))
        {
            try {
                wordsToHashMap(args[0]);
            }
            catch(IOException e)
            {
                System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                System.exit(1);
            }
            if(biggestSize == 1 ||biggestSize == 0)
                System.out.println("No anagrams found.");
            else
            {
                System.out.println("Groups: " + hmap.size() + ", Anagram count: " + biggestSize);

                //iterating
                Iterator<Entry<String, MyLinkedList<String>>> it = hmap.iterator();
                String[] array = new String[hmap.size()];
                int count = 0;

                while (it.hasNext()) {
                    Entry<String, MyLinkedList<String>> entry = it.next();
                    MyLinkedList<String> value = entry.value;

                    //sorting the linked list
                    String[] sorted = insertionSort(value);

                    //printing all the anagrams which are sorted or at least should be hopefully
                    StringBuilder printed = new StringBuilder();
                    printed.append("[");
                    for (int i = 0; i < sorted.length; i++) {
                        if (i == sorted.length - 1) {
                            printed.append(sorted[i] + "]");
                        } else {
                            printed.append(sorted[i] + ", ");
                        }
                    }
                    array[count] = printed.toString();
                    count++;
                }
                insertionSort(array);
                for(int i =0; i < array.length; i++)
                {
                    System.out.println(array[i]);
                }
            }
        }
        //using bs tree
        if(args[1].equals("bst"))
        {
            try
            {
                wordsToBSTree(args[0]);
            }
            catch(IOException e)
            {
                System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                System.exit(1);
            }
            if(biggestSize == 1 || biggestSize == 0)
                System.out.println("No anagrams found.");
            else
            {
                System.out.println("Groups: " + bmap.size() + ", Anagram count: " + biggestSize );
                //iterating
                Iterator<Entry<String, MyLinkedList<String>>> it = bmap.iterator();
                String[] array = new String[bmap.size()];
                int count = 0;

                while (it.hasNext()) {
                    Entry<String, MyLinkedList<String>> entry = it.next();
                    MyLinkedList<String> value = entry.value;

                    //sorting the linked list
                    String[] sorted = insertionSort(value);

                    //printing all the anagrams which are sorted or at least should be hopefully
                    StringBuilder printed = new StringBuilder();
                    printed.append("[");
                    for (int i = 0; i < sorted.length; i++) {
                        if (i == sorted.length - 1) {
                            printed.append(sorted[i] + "]");
                        } else {
                            printed.append(sorted[i] + ", ");
                        }
                    }
                    array[count] = printed.toString();
                    count++;
                }
                insertionSort(array);
                for(int i =0; i < array.length; i++)
                {
                    System.out.println(array[i]);
                }
            }
        }
        //using red black tree
        if(args[1].equals("rbt"))
        {
            try {
                wordsToRBTree(args[0]);
            }
            catch(IOException e)
            {
                System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                System.exit(1);
            }
            if(biggestSize == 1||biggestSize == 0)
            {
                System.out.println("No anagrams found.");
            }
            else {
                System.out.println("Groups: " + rbmap.size() + ", Anagram count: " + biggestSize);

                //iterating
                Iterator<Entry<String, MyLinkedList<String>>> it = rbmap.iterator();
                String[] array = new String[rbmap.size()];
                int count = 0;

                while (it.hasNext()) {
                    Entry<String, MyLinkedList<String>> entry = it.next();
                    MyLinkedList<String> value = entry.value;

                    //sorting the linked list
                    String[] sorted = insertionSort(value);

                    //printing all the anagrams which are sorted or at least should be hopefully
                    StringBuilder printed = new StringBuilder();
                    printed.append("[");
                    for (int i = 0; i < sorted.length; i++) {
                        if (i == sorted.length - 1) {
                            printed.append(sorted[i] + "]");
                        } else {
                            printed.append(sorted[i] + ", ");
                        }
                    }
                    array[count] = printed.toString();
                    count++;
                }
                insertionSort(array);
                for(int i =0; i < array.length; i++)
                {
                    System.out.println(array[i]);
                }
            }
        }
    }
}
