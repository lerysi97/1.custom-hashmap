public class MyHashMap<K, V> {
    Entry<K, V>[] table;
    int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        capacity = 16;
        table = (Entry<K, V>[]) new Entry[capacity];
    }

    public static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не может быть null");
        }

       int index = key.hashCode() & (capacity - 1);
       Entry<K, V> current = table[index];

       while (current != null) {
          if (current.key.equals(key)) {
              current.value = value;
              return;
          }
          current = current.next;
       }

       Entry<K, V> newEntry = new Entry<>(key, value, table[index]);
       table[index] = newEntry;
       size++;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не может быть null");
        }

        int index = key.hashCode() & (capacity - 1);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не может быть null");
        }

        int index = key.hashCode() & (capacity - 1);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }

                V oldValue = current.value;
                size--;
                return oldValue;
                }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(1, "яблоко");
        myHashMap.put(2, "банан");
        myHashMap.put(3, "вишня");

        myHashMap.put(2, "ананас");

        for (int i = 0; i < myHashMap.capacity(); i++) {
            MyHashMap.Entry<Integer, String> entry = myHashMap.table[i];
            while (entry != null) {
                System.out.println("Ключ: " + entry.key + ", Значение: " + entry.value);
                entry = entry.next;
            }
        }
    }
}
