import java.util.*;

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int[] lastSeen = new int[100001];
        Arrays.fill(lastSeen, -1);
        
        Node[] tree = new Node[4 * n];
        build(tree, 1, 0, n - 1);
        
        int maxLen = 0;
        Map<Integer, List<Integer>> balanceToIndices = new HashMap<>();
        balanceToIndices.computeIfAbsent(0, k -> new ArrayList<>()).add(-1);
        
        int currentBalance = 0;
        for (int j = 0; j < n; j++) {
            int val = nums[j];
            int delta = (val % 2 == 0) ? 1 : -1;
            
            int prevIdx = lastSeen[val];
            update(tree, 1, 0, n - 1, prevIdx + 1, j, delta);
            lastSeen[val] = j;
            
            int bestI = findBestI(tree, 1, 0, n - 1);
            if (bestI != -1) {
                maxLen = Math.max(maxLen, j - bestI + 1);
            }
        }
        
        return maxLen;
    }

    static class Node {
        int min, max, lazy;
    }

    void build(Node[] tree, int node, int start, int end) {
        tree[node] = new Node();
        if (start == end) return;
        int mid = (start + end) / 2;
        build(tree, 2 * node, start, mid);
        build(tree, 2 * node + 1, mid + 1, end);
    }

    void update(Node[] tree, int node, int start, int end, int l, int r, int val) {
        if (tree[node].lazy != 0) {
            tree[node].min += tree[node].lazy;
            tree[node].max += tree[node].lazy;
            if (start != end) {
                tree[2 * node].lazy += tree[node].lazy;
                tree[2 * node + 1].lazy += tree[node].lazy;
            }
            tree[node].lazy = 0;
        }
        if (start > end || start > r || end < l) return;
        if (start >= l && end <= r) {
            tree[node].min += val;
            tree[node].max += val;
            if (start != end) {
                tree[2 * node].lazy += val;
                tree[2 * node + 1].lazy += val;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(tree, 2 * node, start, mid, l, r, val);
        update(tree, 2 * node + 1, mid + 1, end, l, r, val);
        tree[node].min = Math.min(tree[2 * node].min, tree[2 * node + 1].min);
        tree[node].max = Math.max(tree[2 * node].max, tree[2 * node + 1].max);
    }

    int findBestI(Node[] tree, int node, int start, int end) {
        if (tree[node].lazy != 0) {
            tree[node].min += tree[node].lazy;
            tree[node].max += tree[node].lazy;
            if (start != end) {
                tree[2 * node].lazy += tree[node].lazy;
                tree[2 * node + 1].lazy += tree[node].lazy;
            }
            tree[node].lazy = 0;
        }
        if (tree[node].min > 0 || tree[node].max < 0) return -1;
        if (start == end) return start;
        
        int mid = (start + end) / 2;
        int leftRes = findBestI(tree, 2 * node, start, mid);
        if (leftRes != -1) return leftRes;
        return findBestI(tree, 2 * node + 1, mid + 1, end);
    }
}
