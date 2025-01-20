/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
class Solution {
    public int maxProfit(int[] prices) {
        int maxPrice = 0;
        int minValue = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else if (prices[i] - minValue > maxPrice) {
                maxPrice = prices[i] - minValue;
            }
        }

        return maxPrice;
    }
}