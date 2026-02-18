class Solution {
    public int largestMagicSquare(int[][] g) {
        int m=g.length,n=g[0].length;
        int[][] rs=new int[m+1][n+1];
        int[][] cs=new int[m+1][n+1];
        int[][] d1=new int[m+1][n+1];
        int[][] d2=new int[m+1][n+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                rs[i+1][j+1]=rs[i+1][j]+g[i][j];
                cs[i+1][j+1]=cs[i][j+1]+g[i][j];
                d1[i+1][j+1]=d1[i][j]+g[i][j];
                d2[i+1][j]=d2[i][j+1]+g[i][j];
            }
        }
        for(int k=Math.min(m,n);k>=2;k--){
            for(int i=0;i+k<=m;i++){
                for(int j=0;j+k<=n;j++){
                    int s=rs[i+1][j+k]-rs[i+1][j];
                    if(d1[i+k][j+k]-d1[i][j]!=s) continue;
                    if(d2[i+k][j]-d2[i][j+k]!=s) continue;
                    boolean ok=true;
                    for(int x=0;x<k&&ok;x++){
                        if(rs[i+x+1][j+k]-rs[i+x+1][j]!=s) ok=false;
                        if(cs[i+k][j+x+1]-cs[i][j+x+1]!=s) ok=false;
                    }
                    if(ok) return k;
                }
            }
        }
        return 1;
    }
}
