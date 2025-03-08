public class Main {
    public static void main(String[] args){
        Pair<Integer, Integer>[] pairs = new Pair[10];
        for (int i = 0; i < 10; i++) {
            pairs[i] = new Pair(i, i+1);
            //System.out.println(pairs[i].key);
        }
        BSTreeMap<Integer, Integer> map = new BSTreeMap<>(pairs, true);
        System.out.println(map.toAsciiDrawing());
        System.out.println(map.preorder());
        System.out.println(map.inorder());
        System.out.println(map.postorder());
        map.put(1,3);
        map.remove(7);
        System.out.println(map.toAsciiDrawing());
        System.out.println(map.sumLevels());
        System.out.println(map.sumNullLevels());
        //V n = map.get(1);
    }
}
