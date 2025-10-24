
using System;

public class Solution
{
    public int MaxFrequency(int[] input, int rangePerDirection, int targetNumberOfOperations)
    {
        int maxValue = input.Max();
        int[] frequency = new int[maxValue + 1];
        foreach (int value in input)
        {
            ++frequency[value];
        }

        int[] prefixSumFrequency = new int[maxValue + 1];
        for (int value = 1; value <= maxValue; ++value)
        {
            prefixSumFrequency[value] = prefixSumFrequency[value - 1] + frequency[value];
        }

        int maxFrequency = 0;
        for (int value = 1; value <= maxValue; ++value)
        {

            int lowerIndex = Math.Max(value - rangePerDirection - 1, 0);
            int upperIndex = Math.Min(value + rangePerDirection, maxValue);

            int possibleNumberOfOperations = prefixSumFrequency[upperIndex] - prefixSumFrequency[lowerIndex] - frequency[value];
            int currentFrequency = frequency[value] + Math.Min(possibleNumberOfOperations, targetNumberOfOperations);

            maxFrequency = Math.Max(maxFrequency, currentFrequency);
        }

        return maxFrequency;
    }
}
