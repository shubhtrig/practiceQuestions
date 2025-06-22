/**
 * https://leetcode.com/problems/01-matrix/
 * BFS
 */
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        Queue<qClass>  q = new LinkedList<>();
        int m = mat.length;
        int n = mat[0].length;
        int[][] answer = new int[m][n];

        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new qClass(i, j, 0));
                    answer[i][j]=0;
                } else {
                    answer[i][j] = -1;
                }
            }
        }

        int[] dir = new int[] { 1,0,0,1,-1,0,0,-1};
        while (!q.isEmpty()) {
            qClass ele = q.poll();
            for (int k = 0; k<8; k+=2) {
                int i = ele.i, j = ele.j, dist = ele.dist;
                i = i+dir[k];
                j = j+dir[k+1];
                if (i<0 || j<0 || i>=m || j>=n || answer[i][j] != -1)
                    continue;
                answer[i][j] = dist+1;

                q.add(new qClass(i, j, dist+1));
            }
            
        }
        return answer;
    }

    class qClass {
        int i, j, dist;

        qClass(int i,int j,int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }
}