import java.util.Arrays;

public class ThreeSum {
    // Assumes the array is sorted
    public static int binarySearch(int[] a, int key, int exclude1, int exclude2){
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;

            if (mid == exclude1 || mid == exclude2) { // Avoid indices i and j
                if (mid < exclude1 || mid < exclude2) lo = mid + 1;
                else hi = mid - 1;
                continue;
            }

            if (key < a[mid]){
                hi = mid - 1;
            }
            else if (key > a[mid]) {
                lo = mid + 1;
            }
            else return mid;
        }
        return -1;
    }

    public static int[][] threeSum(int[] a){
        Arrays.sort(a);
        int count = 0;
        
        // Count the number of valid triplets
        for (int i = 0; i < a.length; i++){
            for (int j = i + 1; j < a.length; j++){
                int target = -(a[i] + a[j]);
                if (binarySearch(a, target, i, j) != -1){
                    count++;
                }
            }
        }

        //Initialize result array
        int[][] result = new int[count][3];
        int index = 0;

        // Populate the array
        for (int i = 0; i < a.length; i++){
            for (int j = i + 1; j < a.length; j++){
                int target = -(a[i] + a[j]);
                
                if (binarySearch(a, i, j, target) != -1){
                    result[index][0] = a[i];
                    result[index][1] = a[j];
                    result[index][2] = target;
                    index++;
                }
            }
        }
        return result;
    }
}
