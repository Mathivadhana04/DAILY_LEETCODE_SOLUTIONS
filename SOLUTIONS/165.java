class Solution {
    public int compareVersion(String a, String b) {
        String[] x=a.split("\\."), y=b.split("\\.");
        int n=Math.max(x.length,y.length);
        for(int i=0;i<n;i++){
            int v1=i<x.length?Integer.parseInt(x[i]):0;
            int v2=i<y.length?Integer.parseInt(y[i]):0;
            if(v1!=v2) return v1<v2?-1:1;
        }
        return 0;
    }
}
