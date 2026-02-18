import java.util.*;

class Solution {
    static class Event {
        int y, x1, x2, v;
        Event(int a, int b, int c, int d) {
            y = a; x1 = b; x2 = c; v = d;
        }
    }

    public double separateSquares(int[][] s) {
        List<Event> ev = new ArrayList<>();
        TreeSet<Integer> xs = new TreeSet<>();

        for (int[] a : s) {
            ev.add(new Event(a[1], a[0], a[0] + a[2], 1));
            ev.add(new Event(a[1] + a[2], a[0], a[0] + a[2], -1));
            xs.add(a[0]);
            xs.add(a[0] + a[2]);
        }

        Collections.sort(ev, Comparator.comparingInt(e -> e.y));
        List<Integer> x = new ArrayList<>(xs);
        Seg st = new Seg(x);

        double total = 0;
        int py = ev.get(0).y;

        for (Event e : ev) {
            total += st.len() * (e.y - py);
            st.add(e.x1, e.x2, e.v);
            py = e.y;
        }

        double half = total / 2;
        st = new Seg(x);
        py = ev.get(0).y;
        double cur = 0;

        for (Event e : ev) {
            double h = st.len() * (e.y - py);
            if (cur + h >= half)
                return py + (half - cur) / st.len();
            cur += h;
            st.add(e.x1, e.x2, e.v);
            py = e.y;
        }
        return py;
    }

    static class Seg {
        int[] c;
        double[] len, xs;

        Seg(List<Integer> x) {
            xs = new double[x.size()];
            for (int i = 0; i < x.size(); i++) xs[i] = x.get(i);
            c = new int[xs.length * 4];
            len = new double[xs.length * 4];
        }

        void add(int L, int R, int v) {
            add(1, 0, xs.length - 1, L, R, v);
        }

        void add(int i, int l, int r, int L, int R, int v) {
            if (xs[r] <= L || xs[l] >= R) return;
            if (L <= xs[l] && xs[r] <= R) c[i] += v;
            else {
                int m = (l + r) / 2;
                add(i * 2, l, m, L, R, v);
                add(i * 2 + 1, m, r, L, R, v);
            }
            if (c[i] > 0) len[i] = xs[r] - xs[l];
            else if (r - l == 1) len[i] = 0;
            else len[i] = len[i * 2] + len[i * 2 + 1];
        }

        double len() {
            return len[1];
        }
    }
}
