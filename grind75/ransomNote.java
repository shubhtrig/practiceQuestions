/**
 * https://leetcode.com/problems/ransom-note/
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

        int[] cnt = new int[128];
        for (char ch : magazine.toCharArray()) {
            cnt[ch]++;
        }

        for (char ch : ransomNote.toCharArray()) {
            if (cnt[ch]-- < 1)
                return false;
        }
        return true;        
    }
}