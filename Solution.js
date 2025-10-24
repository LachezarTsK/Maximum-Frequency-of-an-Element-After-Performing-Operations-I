
/**
 * @param {number[]} input
 * @param {number} rangePerDirection
 * @param {number} targetNumberOfOperations
 * @return {number}
 */
var maxFrequency = function (input, rangePerDirection, targetNumberOfOperations) {
    const maxValue = Math.max(...input);
    const frequency = new Array(maxValue + 1).fill(0);
    for (let value of input) {
        ++frequency[value];
    }

    const prefixSumFrequency = new Array(maxValue + 1).fill(0);
    for (let value = 1; value <= maxValue; ++value) {
        prefixSumFrequency[value] = prefixSumFrequency[value - 1] + frequency[value];
    }

    let maxFrequency = 0;
    for (let value = 1; value <= maxValue; ++value) {

        const lowerIndex = Math.max(value - rangePerDirection - 1, 0);
        const upperIndex = Math.min(value + rangePerDirection, maxValue);

        const possibleNumberOfOperations = prefixSumFrequency[upperIndex] - prefixSumFrequency[lowerIndex] - frequency[value];
        const currentFrequency = frequency[value] + Math.min(possibleNumberOfOperations, targetNumberOfOperations);

        maxFrequency = Math.max(maxFrequency, currentFrequency);
    }

    return maxFrequency;
};
