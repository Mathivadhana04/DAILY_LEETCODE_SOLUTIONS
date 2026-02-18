import java.util.*;

class Solution {
    static class Edge {
        int to;
        int w;
        Edge(int t, int w) {
            to = t;
            this.w = w;
        }
    }

    public int minCost(int n, int[][] edges) {
        List<Edge>[] out = new ArrayList[n];
        List<Edge>[] in = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            out[i] = new ArrayList<>();
            in[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            out[e[0]].add(new Edge(e[1], e[2]));
            in[e[1]].add(new Edge(e[0], e[2]));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist[0] = 0;
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (d != dist[u]) continue;

            for (Edge e : out[u]) {
                if (d + e.w < dist[e.to]) {
                    dist[e.to] = d + e.w;
                    pq.add(new long[]{dist[e.to], e.to});
                }
            }

            for (Edge e : in[u]) {
                long nd = d + 2L * e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new long[]{nd, e.to});
                }
            }
        }

        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}
