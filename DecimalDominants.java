import java.util.*;

/* The following class uses a hashmap to find elements that occur more than n/10 times */
public class DecimalDominants {
    public static List<Integer> findDecimalDominants(int[] nums) {
        int n = nums.length;
        int threshold = n / 10; // Elements must appear more than n/10 times
        Map<Integer, Integer> myMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Count occurrences of each element
        for (int num : nums) {
            myMap.put(num, myMap.getOrDefault(num, 0) + 1);
        }

        // Collect elements that appear more than n/10 times
        for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
            if (entry.getValue() > threshold) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 2, 3, 4, 1, 1, 3, 1, 5, 5};
        System.out.println("Decimal Dominants: " + findDecimalDominants(nums));
    }
}
