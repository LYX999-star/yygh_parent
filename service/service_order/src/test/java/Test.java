public class Test {
    public static void main(String[] args) {

        int x = 7, n = 370;

        int left = 1, right = n/x;

        while (left <= right) {
            int mid = (left+right)/2;

            int tmp = (mid*(mid+1)) / 2;

            if (tmp > n/x) {
                right = mid-1;
            } else if (tmp < n/x){
                left = mid + 1;
            } else {
                System.out.println(0);
                break;
            }
        }
        System.out.println(left);

        int res = (left*(left+1))/2*x - n;
        System.out.println(res);

    }


    public static void main0(String[] args) {
        int x = 5, k = 2, t = 3;
        System.out.println(dfs(x, k, t));
    }

    static int dfs(int x, int k, int t) {
        if (x == 1) {
            return 0;
        } else if (x < 1){
            return 11111111;
        }
        int res = 11111111;
        if (x % k == 0) {
            res = Math.min(res, dfs(x/k, k, t));
        }
        for (int i = 1; i <= t && x >= t; i++) {
            res = Math.min(res, dfs(x-i, k, t));
        }

        return 1+res;
    }
}
