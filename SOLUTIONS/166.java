import java.util.*;

class Solution {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder sb = new StringBuilder();

        if ((numerator < 0) ^ (denominator < 0)) sb.append("-");

        long n = Math.abs((long) numerator);
        long d = Math.abs((long) denominator);

        sb.append(n / d);
        long rem = n % d;

        if (rem == 0) return sb.toString();

        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();

        while (rem != 0) {
            if (map.containsKey(rem)) {
                sb.insert(map.get(rem), "(");
                sb.append(")");
                break;
            }

            map.put(rem, sb.length());
            rem *= 10;
            sb.append(rem / d);
            rem %= d;
        }

        return sb.toString();
    }
}
