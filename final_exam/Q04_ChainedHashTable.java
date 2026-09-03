import java.util.ArrayList;
import java.util.List;

public class Q04_ChainedHashTable {

    private static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final List<List<Entry>> buckets;
    private int size;

    public Q04_ChainedHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount");
        }

        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int index(int key) {
        return Math.floorMod(key, buckets.size());
    }

    public void put(int key, String value) {
        List<Entry> chain = buckets.get(index(key));

        for (Entry entry : chain) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        chain.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        List<Entry> chain = buckets.get(index(key));

        for (Entry entry : chain) {
            if (entry.key == key) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(int key) {
        List<Entry> chain = buckets.get(index(key));

        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key == key) {
                chain.remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public int longestChain() {
        int longest = 0;

        for (List<Entry> chain : buckets) {
            if (chain.size() > longest) {
                longest = chain.size();
            }
        }

        return longest;
    }
}
