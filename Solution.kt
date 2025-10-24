
import kotlin.math.min
import kotlin.math.max

class Solution {

    fun maxFrequency(input: IntArray, rangePerDirection: Int, targetNumberOfOperations: Int): Int {
        val maxValue = input.max()
        val frequency = IntArray(maxValue + 1)
        for (value in input) {
            ++frequency[value]
        }

        val prefixSumFrequency = IntArray(maxValue + 1)
        for (value in 1..maxValue) {
            prefixSumFrequency[value] = prefixSumFrequency[value - 1] + frequency[value]
        }

        var maxFrequency = 0
        for (value in 1..maxValue) {

            val lowerIndex = max(value - rangePerDirection - 1, 0)
            val upperIndex = min(value + rangePerDirection, maxValue)

            val possibleNumberOfOperations = prefixSumFrequency[upperIndex] - prefixSumFrequency[lowerIndex] - frequency[value]
            val currentFrequency = frequency[value] + min(possibleNumberOfOperations, targetNumberOfOperations)

            maxFrequency = max(maxFrequency, currentFrequency)
        }

        return maxFrequency
    }
}
