/**
 * https://leetcode.com/problems/add-binary/
 */
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder answer = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;

        int carryOver = 0;
        int curr = 0;
        while ( i >= 0 || j >= 0) {
            if (i >=0)
                carryOver += getInt(a.charAt(i));
            if (j>=0)
                carryOver += getInt(b.charAt(j));
            answer.append(carryOver % 2);
            carryOver = carryOver/2;
            i--;
            j--;
        }

        if (carryOver == 1)
            answer.append(1);

        return answer.reverse().toString();
    }

    private int getInt(char a) {
        if (a == '0')
            return 0;
        return 1;    
    }
}   