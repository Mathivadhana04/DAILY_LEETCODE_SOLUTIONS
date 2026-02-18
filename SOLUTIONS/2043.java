class Bank {
    long[] a;
    public Bank(long[] balance) {
        a = balance;
    }
    public boolean transfer(int i, int j, long m) {
        if(i>a.length||j>a.length||a[i-1]<m) return false;
        a[i-1]-=m;
        a[j-1]+=m;
        return true;
    }
    public boolean deposit(int i, long m) {
        if(i>a.length) return false;
        a[i-1]+=m;
        return true;
    }
    public boolean withdraw(int i, long m) {
        if(i>a.length||a[i-1]<m) return false;
        a[i-1]-=m;
        return true;
    }
}
