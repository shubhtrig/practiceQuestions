/**
 * 
 * https://leetcode.com/problems/valid-anagram/
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        
        int[] arr = new int[123];
        int len = s.length();
        char[] ch = s.toCharArray();
        char[] ch2 = t.toCharArray();
        for (int i = 0; i<len; i++) {
            arr[ch[i]]++;
            arr[ch2[i]]--;
        }

        for (int i =97; i < 123; i++) {
            if (arr[i] != 0)
                return false;
        }
        return true;
    }
}