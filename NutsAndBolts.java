/* A disorganized carpenter has a mixed pile of n nuts and n bolts.
 The goal is to find the corresponding pairs of nuts and bolts. 
 Each nut fits exactly one bolt and each bolt fits exactly one nut. 
 By fitting a nut and a bolt together, the carpenter can see which one is bigger 
 (but the carpenter cannot compare two nuts or two bolts directly) */

public class NutsAndBolts implements Comparable<NutsAndBolts> {
    private int value;

    public NutsAndBolts(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public int compareTo(NutsAndBolts other) {
        return Integer.compare(this.value, other.value);
    }

    public static <T extends Comparable<T>> void exchange(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T extends Comparable<T>> void matchNutsAndBolts(T[] nuts, T[] bolts, int low, int high) {
        if (low < high) {
            // Partition nuts using the first bolt as the pivot
            int pivotIndex = partition(nuts, low, high, bolts[low]);

            // Partition bolts using the matched nut as the pivot
            partition(bolts, low, high, nuts[pivotIndex]);

            // Recur for the left and right partitions
            matchNutsAndBolts(nuts, bolts, low, pivotIndex - 1);
            matchNutsAndBolts(nuts, bolts, pivotIndex + 1, high);
        }
    }

    public static <T extends Comparable<T>> int partition(T[] array, int low, int high, T pivot) {
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                exchange(array, i, j);
                i++;
            } else if (array[j].compareTo(pivot) == 0) {
                exchange(array, j, high);
                j--; // Recheck the swapped element
            }
        }
        exchange(array, i, high);
        return i;
    }

    public static void main(String[] args) {
        NutsAndBolts[] nuts = { new NutsAndBolts(3), new NutsAndBolts(1), new NutsAndBolts(4), new NutsAndBolts(2) };
        NutsAndBolts[] bolts = { new NutsAndBolts(4), new NutsAndBolts(1), new NutsAndBolts(3), new NutsAndBolts(2) };

        matchNutsAndBolts(nuts, bolts, 0, nuts.length - 1);

        System.out.println("Matched Nuts and Bolts:");
        for (int i = 0; i < nuts.length; i++) {
            System.out.println("Nut: " + nuts[i] + ", Bolt: " + bolts[i]);
        }
    }
}
