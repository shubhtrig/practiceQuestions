//https://leetcode.com/problems/course-schedule-ii
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        int[] inDegree = new int[numCourses];
        int coursesDone = 0;
        List<List<Integer>> nodes = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i=0; i<numCourses; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            nodes.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int co = q.poll();
            ans[coursesDone] = co;
            coursesDone++;
            for (int node : nodes.get(co)) {
                inDegree[node]--;
                if (inDegree[node] == 0) q.add(node);
            }
        }

        if (coursesDone != numCourses)
            return new int[0];
        return ans;
    }
}