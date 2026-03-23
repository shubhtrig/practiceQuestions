// https://leetcode.com/problems/kth-largest-element-in-an-array
class Solution {
    // heap
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i =0; i<k; i++) {
            pq.add(nums[i]);
        }

        for(int i=k; i<n; i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }

        return pq.peek();
    }

    //quick select
    public int kthLargest(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n-1;
        Random rand = new Random();
        while (true) {
            int pivot = l + rand.nextInt(r - l + 1);
            int ind = partition(nums, l, r, pivot);
            if (ind == n-k) {
                return nums[n-k];
            } else if (ind > n-k) {
                r = ind - 1;
            } else {
                l = ind + 1;
            }
        }
    }

    private int partition(int[] nums, int l, int r, int pivot) {
        int p = nums[pivot];
        swap(nums, r, pivot);
        int stIndex = l;
        for (int i=l; i<r; i++) {
            if (nums[i] < p) {
                swap(nums, i, stIndex);
                stIndex++;
            }
        }
        swap(nums, r, stIndex);
        return stIndex;
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}