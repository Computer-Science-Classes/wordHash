import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class represents a hash set of string.
 * It provides methods for adding strings and retrieving stats about the set
 */
public class HashStringSet {
    // private LinkedList<String>[] buckets;
    private ArrayList<LinkedList<String>> buckets;
    private int size;

    /**
     * Constructs a new HashStringSet with the specified number of buckets
     * 
     * @param numBuckets The number of buckets
     */
    public HashStringSet(int numBuckets) {
        if (numBuckets <= 0) {
            throw new IllegalArgumentException("Number of buckets must be positive");
        }

        // buckets = new LinkedList[numBuckets];
        // for (int i = 0; i < numBuckets; i++) {
        // buckets[i] = new LinkedList<>();
        // }
        buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    /**
     * Adds a string to the set.
     * If the string is already present, this method has no effect
     * 
     * @param str The string to add
     */
    public void add(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Null string found. Cannot add null to the set.");
        }

        int bucketIndex = Math.abs(str.hashCode()) % buckets.size();
        if (!buckets.get(bucketIndex).contains(str)) {
            buckets.get(bucketIndex).add(str);
            size++;
        }
    }

    /**
     * Returns the number of strings in the set
     * 
     * @return The number of strings in the set
     */
    public int size() {
        return size;
    }

    /**
     * Returns the number of buckets in the set
     * 
     * @return The number of buckets in the set
     */
    public int numBuckets() {
        return buckets.size();
    }

    /**
     * Returns the number of used buckets in the set
     * 
     * @return The number of used buckets in the set
     */
    public int numUsedBuckets() {
        int count = 0;
        for (LinkedList<String> bucket : buckets) {
            if (!bucket.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the load factor of the set; defined as:
     * number of strings / number of buckets
     * 
     * @return The load factor of the set
     */
    public double loadFactor() {
        return (double) size / buckets.size();
    }

    /**
     * Returns the memory efficiency factor of the set; defined as:
     * number of bytes used / total number of bytes used by the set
     * 
     * @return
     */
    public double memoryEfficiencyFactor() {
        int totalBytesValues = 0;
        int totalBytesObjects = 4 * size;
        for (LinkedList<String> bucket : buckets) {
            for (String str : bucket) {
                totalBytesValues += str.length();
            }
        }
        return (double) totalBytesValues / (totalBytesValues + totalBytesObjects);
    }
}