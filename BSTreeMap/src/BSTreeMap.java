import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Stack;

/**
 * Class that implements a binary search tree which implements the MyMap
 * interface.
 * @author Thuy Vy Tran tt2964
 * @version 1.1.1 February 27, 2024
 */
public class BSTreeMap<K extends Comparable<K>, V> implements MyMap<K, V> {
    public static final int PREORDER = 1, INORDER = 2, POSTORDER = 3;
    protected Node<K, V> root;
    protected int size;

    /**
     * Creates an empty binary search tree map.
     */
    public BSTreeMap() { }

    /**
     * Creates a binary search tree map of the given key-value pairs.
     * @param elements an array of key-value pairs
     */
    public BSTreeMap(Pair<K, V>[] elements) {
        insertElements(elements);
    }

    /**
     * Creates a binary search tree map of the given key-value pairs. If
     * sorted is true, a balanced tree will be created. If sorted is false,
     * the pairs will be inserted in the order they are received.
     * @param elements an array of key-value pairs
     */
    public BSTreeMap(Pair<K, V>[] elements, boolean sorted) {
        if (!sorted) {
            insertElements(elements);
        } else {
            root = createBST(elements, 0, elements.length - 1);
        }
    }

    /**
     * Recursively constructs a balanced binary search tree by inserting the
     * elements via a divide-and-conquer approach. The middle element in the
     * array becomes the root. The middle of the left half becomes the root's
     * left child. The middle element of the right half becomes the root's right
     * child. This process continues until low > high, at which point the
     * method returns a null Node.
     * @param pairs an array of <K, V> pairs sorted by key
     * @param low   the low index of the array of elements
     * @param high  the high index of the array of elements
     * @return      the root of the balanced tree of pairs
     */
    protected Node<K, V> createBST(Pair<K, V>[] pairs, int low, int high) {
        // TODO
        int length = low + high;
        if(low > high) {
            //System.out.println("i");
            return null;
        }
        int half = length/2;
        //int lHalf = (low +(half-1))/2;
        //int rHalf = (high + (half+1))/2;
        Node<K,V> root = new Node<K,V>(pairs[half].key,pairs[half].value);
        size++;
        Node<K,V> left = createBST(pairs,low,half-1);
        if(left != null) {
            root.setLeft(left);
            root.getLeft().setParent(root);
        }
        Node<K,V> right = createBST(pairs, half+1,high);
        if(right != null){
            root.setRight(right);
            root.getRight().setParent(root);
        }

        //System.out.println("low: " + low);
        //System.out.println("high "+high);


        //Node<K,V> left = createBST(pairs,low,half-1);
        //Node<K,V> right = createBST(pairs, half+1,high);
        return root;

    }

    /**
     * Inserts the pairs into the tree in the order they appear in the given
     * array.
     * @param pairs the array of <K, V> pairs to insert
     */
    protected void insertElements(Pair<K, V>[] pairs) {
        for (Pair<K, V> pair : pairs) {
            put(pair);
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */
    public int size() {
        // TODO
        return size;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    /**
     * Returns a String of the key-value pairs visited with a preorder
     * traversal. Uses a StringBuilder for efficiency.
     * @return a String of the key-value pairs visited with a preorder
     *         traversal
     */
    public String preorder() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        preorder(root, builder, 0);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Visits the Nodes of the tree in a preorder traversal. Each Node's
     * toString() return value should be appended to the StringBuilder. A ", "
     * must appear between each Node's data in the final String.
     * @param n            the current Node
     * @param builder      the StringBuilder used to build up the output
     * @param nodesVisited the number of nodes visited so far. Useful for
     *                     determining when to append ", ".
     * @return the number of nodes visited after each recursive call
     */
    private int preorder(Node<K, V> n, StringBuilder builder,
                         int nodesVisited) {
        // TODO
        int v = nodesVisited;
        if(n == null)
        {
            return nodesVisited;
        }
        if(nodesVisited == 0)
        {
            builder.append(n.toString());
            v++;
            v = preorder(n.getLeft(),builder,v);
            v = preorder(n.getRight(),builder,v);
        }
        else
        {
            builder.append(", ");
            builder.append(n.toString());
            v++;
            v = preorder(n.getLeft(),builder,v);
            v = preorder(n.getRight(),builder,v);
        }
        return v;


    }

    /**
     * Returns a String of the key-value pairs visited with an inorder
     * traversal. Uses a StringBuilder for efficiency.
     * @return a String of the key-value pairs visited with an inorder
     *         traversal
     */
    public String inorder() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        inorder(root, builder, 0);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Visits the Nodes of the tree in an inorder traversal. Each Node's
     * toString() return value should be appended to the StringBuilder. A ", "
     * must appear between each Node's data in the final String.
     * @param n            the current Node
     * @param builder      the StringBuilder used to build up the output
     * @param nodesVisited the number of nodes visited so far. Useful for
     *                     determining when to append ", ".
     * @return the number of nodes visited after each recursive call
     */
    private int inorder(Node<K, V> n, StringBuilder builder,
                        int nodesVisited) {
        // TODO
        int v = nodesVisited;
        if(n == null)
        {
            return nodesVisited;
        }
        v = inorder(n.getLeft(),builder,nodesVisited);
        if(v == 0)
        {
            builder.append(n.toString());
            v++;
        }
        else
        {
            builder.append(", ");
            builder.append(n.toString());
            v++;
        }
        v = inorder(n.getRight(),builder,v);
        return v;

    }

    /**
     * Returns a String of the key-value pairs visited with a postorder
     * traversal. Uses a StringBuilder for efficiency.
     * @return a String of the key-value pairs visited with a postorder
     *         traversal
     */
    public String postorder() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        postorder(root, builder, 0);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Visits the Nodes of the tree in a postorder traversal. Each Node's
     * toString() return value should be appended to the StringBuilder. A ", "
     * must appear between each Node's data in the final String.
     * @param n            the current Node
     * @param builder      the StringBuilder used to build up the output
     * @param nodesVisited the number of nodes visited so far. Useful for
     *                     determining when to append ", ".
     * @return the number of nodes visited after each recursive call
     */
    private int postorder(Node<K, V> n, StringBuilder builder,
                          int nodesVisited) {
        // TODO
        int v = nodesVisited;
        if(n == null)
        {
            return nodesVisited;
        }
        v = postorder(n.getLeft(),builder,nodesVisited);
        v = postorder(n.getRight(),builder,v);
        if(v == 0)
        {
            builder.append(n.toString());
            v++;
        }
        else
        {
            builder.append(", ");
            builder.append(n.toString());
            v++;
        }
        return v;

    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     * @param  key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this
     *         map contains no mapping for the key
     */
    public V get(K key) {
        Node<K, V> x = iterativeSearch(key);
        return x != null ? x.value : null;
    }

    /**
     * Determines if the supplied key is found in the tree. If so, it returns a
     * reference to the Node containing the key. Otherwise, null is returned.
     * @param key key whose mapping is to be removed from the map
     * @return a reference to the Node containing the specified key
     */
    private Node<K, V> iterativeSearch(K key) {
        // TODO
        Node<K,V> x = root;
        //System.out.println("x = " + x);
        //System.out.println("key = " + key);
        while(x!= null && x.key.compareTo(key) != 0)
        {
            //System.out.println("x " +x);
            if(x.key.compareTo(key) < 0) {
                x = x.getRight();
            }
            else {
                x = x.getLeft();
            }
            //System.out.println("x = " +x);
        }
        return x;

    }

    /**
     * Associates the specified value with the specified key in this map. If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     * @param pair  the key-value mapping to insert into the tree
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V put(Pair<K, V> pair) {
        return put(pair.key, pair.value);
    }

    /**
     * Associates the specified value with the specified key in this map. If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be with the specified key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V put(K key, V value) {
        // TODO
        Node<K,V> y = null;
        Node<K,V> x = root;
        Node<K,V> z = new Node<K,V>(key,value);
        if(x == null)
        {
            root = new Node<K,V>(key,value);
            root.setParent(y);
            size++;
            return null;
        }
        do
        {
            y = x;
            if(x.key.compareTo(key) < 0)
            {
                x = x.getRight();
                //System.out.println("please");
            }
            else
                x = x.getLeft();

            //System.out.println('P');
        }while(x != null && y!= null && y.key.compareTo(key) != 0 );
        if(y == null)
        {
            root = new Node<K,V>(key,value);
            root.setParent(y);
            size++;
            return null;
        }
        if(y.key.compareTo(key) == 0)
        {
            V oldValue = y.value;
            y.value = value;
            return oldValue;
        }
        else
        {
            if(y.key.compareTo(key) < 0)
            {
                y.setRight(z);
                y.getRight().setParent(y);
                size++;
                return null;
            }
            else
            {
                y.setLeft(z);
                y.getLeft().setParent(y);
                size++;
                return null;
            }
        }

    }

    public void transplant(Node<K,V> u, Node<K,V> v)
    {
        if(u.getParent() == null)
        {
            root = v;
        }
        else if(u.getParent().getLeft() == u)
        {
            u.getParent().setLeft(v);
        }
        else
        {
            u.getParent().setRight(v);
        }

        if(v != null)
        {
            v.setParent(u.getParent());
        }
    }



    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V remove(K key) {
        // TODO
        Node<K,V> z = iterativeSearch(key);
        if(z == null)
            return null;
        V oldValue = z.value;
        if(z.getLeft() == null)
            transplant(z,z.getRight());
        else if(z.getRight() == null)
            transplant(z,z.getLeft());
        else
        {
            Node<K,V> y = treeMinimum(z.getRight());
            if(y.getParent() != z)
            {
                transplant(y,y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z,y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
        }
        size--;
        return oldValue;
    }

    /**
     * Returns a reference to the Node whose key value is the minimum key in the
     * tree.
     * @param x the Node at which to start the traversal
     * @return a reference to the Node whose key value is the minimum key in the
     *         tree
     */
    protected Node<K, V> treeMinimum(Node<K, V> x) {
        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    /**
     * Returns a String representation of the tree, where the Nodes are visited
     * with an inorder traversal.
     * @return a String representation of the tree
     */
    public String toString() {
        return inorder();
    }

    /**
     * Returns an ASCII drawing of the tree.
     * @return an ASCII drawing of the tree
     */
    public String toAsciiDrawing() {
        BinarySearchTreePrinter<K, V> printer =
                new BinarySearchTreePrinter<>();
        printer.createAsciiTree(root);
        return printer.toString();
    }

    public void printTraversal(int type) {
        switch (type) {
            case PREORDER -> {
                System.out.print("Preorder traversal:       ");
                System.out.println(preorder());
            }
            case INORDER -> {
                System.out.print("Inorder traversal:        ");
                System.out.println(inorder());
            }
            case POSTORDER -> {
                System.out.print("Postorder traversal:      ");
                System.out.println(postorder());
            }
        }
    }

    /**
     * Returns an iterator over the Entries in this map in the order
     * in which they appear.
     * @return an iterator over the Entries in this map
     */
    public Iterator<Entry<K, V>> iterator() {
        return new BinaryTreeItr();
    }

    private class BinaryTreeItr implements Iterator<Entry<K, V>> {
        private Node<K, V> current;
        private final Stack<Node<K, V>> parentStack = new Stack<>();

        BinaryTreeItr() {
            current = root;
        }

        @Override
        public boolean hasNext() {
            return !parentStack.isEmpty() || current != null;
        }

        @Override
        public Entry<K, V> next() {
            while (hasNext()) {
                if (current != null) {
                    parentStack.push(current);
                    current = current.getLeft();
                } else {
                    Node<K, V> toReturn = parentStack.pop();
                    current = toReturn.getRight();
                    return toReturn;
                }
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns the height of the tree. If the tree is null, the height is -1.
     * @return the height of the tree
     */
    public int height() {
        return height(root) ;
    }

    protected int height(Node<K, V> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /**
     * Returns the number of null references in the tree. Uses a recursive
     * helper method to count the null references.
     * @return the number of null references in the tree
     */
    public int nullCount() {
        return nullCount(root);
    }

    private int nullCount(Node<K, V> node) {
        if (node == null) {
            return 1;
        }
        return nullCount(node.getLeft()) + nullCount(node.getRight());
    }

    /**
     * Returns the sum of the levels of each non-null node in the tree starting
     * at the root.
     * For example, the tree
     *   5 <- level 0
     *  / \
     * 2   8 <- level 1
     *      \
     *       10 <- level 2
     * has sum 0 + 2(1) + 2 = 4.
     * @return the sum of the levels of each non-null node in the tree starting
     *         at the root
     */
    public int sumLevels() {
        return sumLevels(root, 0);
    }

    private int sumLevels(Node<K, V> node, int level) {
        // TODO
        if(node == null)
            return 0;
        int anotherL = level +1;
        int result = level +sumLevels(node.getLeft(),anotherL)+sumLevels(node.getRight(),anotherL);
        return result;
    }

    /**
     * Returns the sum of the levels of each null node in the tree starting at
     * the root.
     * For example, the tree
     *    5 <- level 0
     *   / \
     *  2   8 <- level 1
     * / \ / \
     * * * * * 10 <- level 2
     *        / \
     *        * * <- level 3
     * has sum 3(2) + 2(3) = 12.
     * @return the sum of the levels of each null node in the tree starting at
     *         the root
     */
    public int sumNullLevels() {
        return sumNullLevels(root, 0);
    }

    private int sumNullLevels(Node<K, V> node, int level) {
        // TODO
        if(node == null)
            return level;
        int anotherL = level+1;
        int result = sumNullLevels(node.getLeft(),anotherL)+sumNullLevels(node.getRight(),anotherL);
        return result;
    }

    public double successfulSearchCost() {
        return size == 0 ? 0 : 1 + (double)sumLevels() / size;
    }

    public double unsuccessfulSearchCost() {
        return (double)sumNullLevels() / nullCount();
    }
}
