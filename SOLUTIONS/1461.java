class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k;
        boolean[] seen = new boolean[need];
        int mask = need - 1;
        int hash = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            if (i >= k - 1 && !seen[hash]) {
                seen[hash] = true;
                count++;
                if (count == need) return true;
            }
        }
        return false;
    }
}
