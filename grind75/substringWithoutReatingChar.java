/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        
        int[] cnt = new int[256];
        int ans =1;
        int i = 0; 
        int j = 0;
        while (j<s.length()) {
            cnt[s.charAt(j)]++;
            if (cnt[s.charAt(j)] != 1) {
                if (j-i > ans) {
                    ans = j - i;
                }
                while (s.charAt(i) != s.charAt(j) && i<j) {
                    cnt[s.charAt(i)]--;
                    i++;
                }
                cnt[s.charAt(i)]--;
                i++;
            }
            j++;
        }

        if (j-i > ans) {
            ans = j - i;
        }
        return ans;
    }
}