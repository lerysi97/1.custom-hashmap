public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(1, "яблоко");
        myHashMap.put(2, "банан");
        myHashMap.put(3, "вишня");
        myHashMap.put(2, "ананас");

        printAll(myHashMap);

        String delete = myHashMap.remove(2);
        System.out.println("Удалили: " + delete);

        myHashMap.remove(2);

        System.out.println("Значение по ключу 2: " + myHashMap.get(2));
        System.out.println("Значение по ключу 99: " + myHashMap.get(99));
    }

    public static <K, V> void printAll(MyHashMap<K, V> map) {
        for (int i = 0; i < map.capacity(); i++) {
            MyHashMap.Entry<K, V> entry = map.table[i];
            while (entry != null) {
                System.out.println("Ключ: " + entry.key + ", Значение: " + entry.value);
                entry = entry.next;;
            }
        }
    }
}
