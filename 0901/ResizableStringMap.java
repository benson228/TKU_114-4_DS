import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap {
    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<List<Entry>> buckets;
    private int size;

    public ResizableStringMap(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount");
        }

        buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int index(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key");
        }
        return Math.floorMod(key.hashCode(), buckets.size());
    }

    public void put(String key, String value) {
        List<Entry> chain = buckets.get(index(key));

        for (Entry entry : chain) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        chain.add(new Entry(key, value));
        size++;

        if (loadFactor() > 0.75) {
            rehash(buckets.size() * 2 + 1);
        }
    }

    public String get(String key) {
        List<Entry> chain = buckets.get(index(key));

        for (Entry entry : chain) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(String key) {
        List<Entry> chain = buckets.get(index(key));

        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)) {
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

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    public int bucketCount() {
        return buckets.size();
    }

    private void rehash(int newBucketCount) {
        List<List<Entry>> oldBuckets = buckets;

        buckets = new ArrayList<>();
        for (int i = 0; i < newBucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (List<Entry> chain : oldBuckets) {
            for (Entry entry : chain) {
                buckets.get(index(entry.key)).add(entry);
            }
        }
    }
}