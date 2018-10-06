package dp.practice;
public class LongestIncreasingSubsequence {
    // lis using dynamic programming
    public static int lis_dp(int[] num, int n){
        int[] length = new int[n];
        if (n == 1){
            return 1;
        }

        length[0] = 1;
        int maxLen = 1;
        for(int i = 1; i < n; i++){
            length[i] = 1;
            for(int j = 0; j < i; j++){
                if (num[i] > num[j]){
                    if (length[j] + 1 > length[i]){
                        length[i] = length[j] + 1;
                    }
                }
            }
            if (length[i] > maxLen)
                maxLen = length[i];
        }
        return maxLen;
    }
    // lis with o(nlgn) time complexity
    public static int lis_log(int[] num, int n){
        int[] b = new int[n];
        int bLen = 0;
        for(int i = 0; i < n; i++){
            int bIndex = bsearch(num[i], b, bLen);
            if (bIndex <= 0){
                if (-bIndex < bLen){
                    if(b[-bIndex] > num[i]){
                        b[-bIndex] = num[i];
                    }
                }else if(-bIndex == bLen){
                    b[bLen++] = num[i];
                }else{
                    // should not get here
                }
            }
        }
        return bLen;
    }
    // binary search
    public  static int bsearch(int target, int[] num, int n){
        int left = 0, right = n - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if (num[mid] > target){
                right = mid - 1;
            }else if(num[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -left;
    }
}