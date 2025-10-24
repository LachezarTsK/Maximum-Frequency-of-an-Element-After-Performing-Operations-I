
public class Solution {

    public int maxFrequency(int[] input, int rangePerDirection, int targetNumberOfOperations) {
        // Alternatiely: int maxValue = Arrays.stream(input).max().getAsInt();
        int maxValue = 0;
        for (int value : input) {
            maxValue = Math.max(maxValue, value);
        }

        int[] frequency = new int[maxValue + 1];
        for (int value : input) {
            ++frequency[value];
        }

        int[] prefixSumFrequency = new int[maxValue + 1];
        for (int value = 1; value <= maxValue; ++value) {
            prefixSumFrequency[value] = prefixSumFrequency[value - 1] + frequency[value];
        }

        int maxFrequency = 0;
        for (int value = 1; value <= maxValue; ++value) {

            int lowerIndex = Math.max(value - rangePerDirection - 1, 0);
            int upperIndex = Math.min(value + rangePerDirection, maxValue);

            int possibleNumberOfOperations = prefixSumFrequency[upperIndex] - prefixSumFrequency[lowerIndex] - frequency[value];
            int currentFrequency = frequency[value] + Math.min(possibleNumberOfOperations, targetNumberOfOperations);

            maxFrequency = Math.max(maxFrequency, currentFrequency);
        }

        return maxFrequency;
    }
}
