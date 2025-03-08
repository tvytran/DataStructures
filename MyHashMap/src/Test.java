public class Test {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        //map.put(String.valueOf(10),10);
        //System.out.println(map.toString());
        //map.put(String.valueOf(55),55);
        //System.out.println(map.toString());
        for (int i = 1; i <= 1000; i++) {
            map.put(String.valueOf(i), i);
        }
        System.out.println(map.toString());
        map.remove(String.valueOf(143));
        System.out.println(map.toString());
    }
}
