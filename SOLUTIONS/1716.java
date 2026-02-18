class Solution {
    public int totalMoney(int n) {
        int w=n/7,d=n%7;
        return 28*w + 7*w*(w-1)/2 + d*(2*w+d+1)/2;
    }
}
