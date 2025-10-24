
#include <vector>
#include <algorithm>
using namespace std;

class Solution {

public:
    int maxFrequency(vector<int>& input, int rangePerDirection, int targetNumberOfOperations) const {
        int maxValue = *ranges::max_element(input);
        vector<int> frequency(maxValue + 1);
        for (const auto& value : input) {
            ++frequency[value];
        }

        vector<int> prefixSumFrequency(maxValue + 1);
        for (int value = 1; value <= maxValue; ++value) {
            prefixSumFrequency[value] = prefixSumFrequency[value - 1] + frequency[value];
        }

        int maxFrequency = 0;
        for (int value = 1; value <= maxValue; ++value) {

            int lowerIndex = max(value - rangePerDirection - 1, 0);
            int upperIndex = min(value + rangePerDirection, maxValue);

            int possibleNumberOfOperations = prefixSumFrequency[upperIndex] - prefixSumFrequency[lowerIndex] - frequency[value];
            int currentFrequency = frequency[value] + min(possibleNumberOfOperations, targetNumberOfOperations);

            maxFrequency = max(maxFrequency, currentFrequency);
        }

        return maxFrequency;
    }
};
