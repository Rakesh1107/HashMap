package hashmap;

import java.util.LinkedList;
import java.util.Objects;

public class MyHashMap<K, V> {

    int size = 0;
    int currentCapacity = 16;

    LinkedList<Entry<K, V>>[] map;

    public MyHashMap() {
        map = new LinkedList[currentCapacity];
    }

    public void put(K key, V value) {
        if (size == currentCapacity) {
            resize();
        }

        int index = Objects.hash(key) % currentCapacity;

        if (map[index] == null) {
            map[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry1 : map[index]) {
            if (entry1.getKey().equals(key)) {
                entry1.setValue(value);
                return;
            }
        }

        Entry<K, V> entry = new Entry<>(key, value);
        map[index].add(entry);

        size++;
    }

    public V get(K key) {
        int index = Objects.hash(key) % currentCapacity;

        if (map[index] == null) {
            return null;
        }

        for (int i = 0; i < map[index].size(); i++) {
            Entry<K, V> entry = map[index].get(i);
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = Objects.hash(key) % currentCapacity;

        if (map[index] == null) {
            return false;
        }

        for (int i = 0; i < map[index].size(); i++) {
            Entry<K, V> entry = map[index].get(i);
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void remove(K key) {
        int index = Objects.hash(key) % currentCapacity;

        if (map[index] == null) {
            return;
        }

        for (int i = 0; i < map[index].size(); i++) {
            Entry<K, V> entry = map[index].get(i);
            if (entry.getKey().equals(key)) {
                map[index].remove(entry);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentCapacity; i++) {
            if (map[i] != null){
                for (Entry<K, V> entry: map[i]) {
                    sb.append(entry.getKey()).append("=").append(entry.getValue());
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private void resize() {

        LinkedList<Entry<K, V>>[] oldMap = map;
        map = new LinkedList[currentCapacity*2];
        size = 0;

        for (int i = 0; i < oldMap.length; i++) {
            if (oldMap[i] != null) {
                for (Entry<K, V> entry: oldMap[i]) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }

        currentCapacity*=2;
    }

}
