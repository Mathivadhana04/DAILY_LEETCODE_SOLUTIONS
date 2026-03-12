import java.util.*;

class Solution {
    class DSU {
        int[] p, r;
        DSU(int n) {
            p = new int[n];
            r = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
        }
        int find(int x) {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        }
        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;
            if (r[pa] < r[pb]) p[pa] = pb;
            else if (r[pa] > r[pb]) p[pb] = pa;
            else {
                p[pb] = pa;
                r[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int l = 0, r = 200000, ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (ok(n, edges, k, m)) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        return ans;
    }

    boolean ok(int n, int[][] edges, int k, int x) {
        DSU d = new DSU(n);
        int used = 0, upgrades = 0;

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < x) return false;
                if (!d.union(e[0], e[1])) return false;
                used++;
            }
        }

        List<int[]> opt = new ArrayList<>();
        for (int[] e : edges) if (e[3] == 0) opt.add(e);

        opt.sort((a,b)->b[2]-a[2]);

        for (int[] e : opt) {
            if (used == n - 1) break;
            int u = e[0], v = e[1], s = e[2];
            if (d.find(u) == d.find(v)) continue;
            if (s >= x) {
                d.union(u,v);
                used++;
            } else if (s * 2 >= x && upgrades < k) {
                upgrades++;
                d.union(u,v);
                used++;
            }
        }

        return used == n - 1;
    }
}
