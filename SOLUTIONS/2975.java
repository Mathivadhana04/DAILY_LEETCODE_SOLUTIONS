class Solution {
    public int maximizeSquareArea(int m, int n, int[] h, int[] v) {
        Set<Integer> hs=new HashSet<>();
        Set<Integer> vs=new HashSet<>();
        int[] H=new int[h.length+2];
        int[] V=new int[v.length+2];
        H[0]=1; H[H.length-1]=m;
        V[0]=1; V[V.length-1]=n;
        for(int i=0;i<h.length;i++) H[i+1]=h[i];
        for(int i=0;i<v.length;i++) V[i+1]=v[i];
        Arrays.sort(H);
        Arrays.sort(V);
        for(int i=0;i<H.length;i++){
            for(int j=i+1;j<H.length;j++){
                hs.add(H[j]-H[i]);
            }
        }
        int max=0;
        for(int i=0;i<V.length;i++){
            for(int j=i+1;j<V.length;j++){
                int d=V[j]-V[i];
                if(hs.contains(d)) max=Math.max(max,d);
            }
        }
        return max==0?-1:(int)((long)max*max%1000000007);
    }
}
