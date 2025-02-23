/**
 * https://leetcode.com/problems/longest-palindrome/
 */
class Solution {
    public int longestPalindrome(String s) {
        int[] cnt = new int[256];
        int oddChar = 0;
        for (char ch : s.toCharArray()) {
            cnt[ch]++;
            if (cnt[ch] %2 == 1) {
                oddChar++;
            } else {
                oddChar--;
            }
        }

        if (oddChar > 0) {
            return s.length() - oddChar +1;
        }
        
        return s.length();
    }
}