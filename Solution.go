
package main
import "slices"

func maxFrequency(input []int, rangePerDirection int, targetNumberOfOperations int) int {
    maxValue := slices.Max(input)
    frequency := make([]int, maxValue + 1)
    for _, value := range input {
            frequency[value]++
    }

    prefixSumFrequency := make([]int, maxValue + 1)
    for value := 1; value <= maxValue; value++ {
            prefixSumFrequency[value] = prefixSumFrequency[value - 1] + frequency[value]
    }

    var maxFrequency = 0
    for value := 1; value <= maxValue; value++ {

        lowerIndex := max(value - rangePerDirection - 1, 0)
        upperIndex := min(value + rangePerDirection, maxValue)

        possibleNumberOfOperations := prefixSumFrequency[upperIndex] - prefixSumFrequency[lowerIndex] - frequency[value]
        currentFrequency := frequency[value] + min(possibleNumberOfOperations, targetNumberOfOperations)

        maxFrequency = max(maxFrequency, currentFrequency)
    }

    return maxFrequency
}
