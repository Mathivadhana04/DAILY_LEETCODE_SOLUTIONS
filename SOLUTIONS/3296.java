class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 1;
        long right = (long)1e16;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (can(mid, mountainHeight, workerTimes)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean can(long t, int mountainHeight, int[] workerTimes) {
        long h = 0;
        for (int wt : workerTimes) {
            long x = (long)((Math.sqrt(1 + 8.0 * t / wt) - 1) / 2);
            h += x;
            if (h >= mountainHeight) return true;
        }
        return false;
    }
}
