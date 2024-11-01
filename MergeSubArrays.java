/*The following algorithm merges two sorted subarrays so that the new array is sorted
 * with an auxilliary array of length n instead of 2n. 
*/

// The class MergeSubArrays represents a single element can be compared to other MergeSubArrays objects based on its value
public class MergeSubArrays implements Comparable<MergeSubArrays> {
    private int value;

    // Constructor to initialize the field
    public MergeSubArrays(int value) {
        this.value = value;
    }

    //Implement the compareTo method
    @Override
    public int compareTo(MergeSubArrays other){
        return Integer.compare(this.value, other.value);
    }

    //Utility method to compare two comparable objects
    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> void exchange(T[] a, int i, int j){
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int n){
        //Copy items from a[0] to a[n-1] onto the auxilliary array
        for (int i = 0; i < n; i++){
            aux[i] = a[i];
        }

        // Initialize pointers
        int i = 0; // Pointer for auxilliary array
        int j = n; // Pointer for second half of a (from a[n] to a[2n-1])
        int k = 0; // Pointer for merged array in a

        // Merge process
        while (i < n && j < 2 * n) {
            if (less(aux[i], a[j])) {
                a[k++] = aux[i++];
            } else {
                a[k++] = a[j++];
            }
        }

        // Copy the remaining elements from aux if any
        while (i < n) {
            a[k++] = aux[i++];
        }
    }

    // Main method to test functionality
    public static void main(String[] args) {
        MergeSubArrays[] arr = {
            new MergeSubArrays(1), new MergeSubArrays(3), new MergeSubArrays(5),
            new MergeSubArrays(2), new MergeSubArrays(4), new MergeSubArrays(6)
        };

        // Convert to comparable array
        MergeSubArrays[] a = arr;
        MergeSubArrays[] aux = new MergeSubArrays[a.length / 2];

        //Call merge with length of one subarray (n)
        merge(a, aux, a.length/2);

        // Print the merged array
        for (MergeSubArrays item : a){
            System.out.println(((MergeSubArrays) item).value);
        }
    }
}