/*
https://leetcode.com/problems/clone-graph/
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Node startNode = new Node(node.val);
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[101];
        Node[] map = new Node[101];
        
        q.add(node);
        visited[node.val] = true;
        map[node.val]= startNode;
        while (!q.isEmpty()) {
            Node aNode = q.poll();
            Node cNode = map[aNode.val];

            for (Node n : aNode.neighbors) {
                if (!visited[n.val]) {
                    q.add(n);
                    map[n.val] = new Node(n.val);
                    visited[n.val] = true;
                }
                cNode.neighbors.add(map[n.val]);
            }
        }

        return startNode;
    }
}