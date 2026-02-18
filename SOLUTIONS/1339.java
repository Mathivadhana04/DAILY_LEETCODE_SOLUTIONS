class Solution {
    long total=0, max=0;
    public int maxProduct(TreeNode root) {
        total=sum(root);
        dfs(root);
        return (int)(max%1000000007);
    }
    long sum(TreeNode n){
        if(n==null) return 0;
        return n.val+sum(n.left)+sum(n.right);
    }
    long dfs(TreeNode n){
        if(n==null) return 0;
        long s=n.val+dfs(n.left)+dfs(n.right);
        max=Math.max(max,s*(total-s));
        return s;
    }
}
