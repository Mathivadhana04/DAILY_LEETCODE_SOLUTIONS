class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    int dfs(TreeNode n) {
        if (n == null) return 0;
        int l = dfs(n.left);
        if (l == -1) return -1;
        int r = dfs(n.right);
        if (r == -1) return -1;
        if (Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }
}
