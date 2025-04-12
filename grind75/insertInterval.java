/**
 * https://leetcode.com/problems/insert-interval/
 */
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<List<Integer>> answer = new ArrayList<>();

        int i = 0;
        for (;i < intervals.length; i++) {
            if (newInterval[0] > intervals[i][1]) {
                answer.add(List.of(intervals[i][0], intervals[i][1]));
            } else if (newInterval[1] < intervals[i][0]) {
                answer.add(List.of(newInterval[0], newInterval[1]));
                break;
            } else if (newInterval[1] >= intervals[i][0]) {
                int start = Math.min(newInterval[0], intervals[i++][0]);
                while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
                    i++;
                }
                answer.add(List.of(start, Math.max(newInterval[1], intervals[i-1][1])));
                break;
            }
        }


        while (i < intervals.length) {
            answer.add(List.of(intervals[i][0], intervals[i++][1]));
        }

        if (intervals.length == 0 || newInterval[0] > intervals[i-1][1]) {
            answer.add(List.of(newInterval[0], newInterval[1]));
        }

        int[][] ans = new int[answer.size()][2];
        i = 0;
        for (List<Integer> a : answer) {
            ans[i][0] = a.get(0);
            ans[i++][1] = a.get(1);
        }

        return ans;
    }
}