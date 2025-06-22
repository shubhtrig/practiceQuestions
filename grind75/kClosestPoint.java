/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<PQ> pq = new PriorityQueue<>(k,
            (p,q) -> Double.compare(p.dist, q.dist));

        for (int i =0; i<points.length; i++) {
            PQ p = new PQ();
            p.i = points[i][0];
            p.j = points[i][1];
            double dist = Math.sqrt((double)(p.i * p.i) + (p.j * p.j));
            p.dist = dist;
            pq.add(p);
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i<k; i++) {
            PQ p = pq.remove();
            ans[i][0] = p.i;
            ans[i][1] = p.j;
        }

        return ans;
    }

    class PQ {
        int i, j;
        double dist;
    }
}