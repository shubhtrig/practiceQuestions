//https://leetcode.com/problems/top-k-frequent-elements
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Element> map = new HashMap<>();
        PriorityQueue<Element> pq = new PriorityQueue<>((a,b)-> b.count - a.count);

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.get(num).count++;
            } else {
                Element el = new Element(num);
                el.count++;
                map.put(num, el);
            }
        }

        map.values().stream().forEach(v-> pq.add(v));

        int[] ans = new int[k];
        for (int i=0; i<k; i++) {
            ans[i] = pq.poll().val;
        }
        return ans;
    }

    class Element {
        int val;
        int count;

        Element(int val) {
            this.val = val;
            this.count = 0;
        }
    }
}