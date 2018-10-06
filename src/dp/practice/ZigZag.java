package dp.practice;

/*
passed:
TopCoder - 1259 (https://vjudge.net/problem/TopCoder-1259)

failed:
UVALive - 7947 (https://vjudge.net/problem/UVALive-7947)
*/
public class ZigZag {
    public static int zigZag_direction(int[] sequence, int direction){
        int n = sequence.length;
        int[] length = new int[n];
        if (n == 1){
            return 1;
        }

        length[0] = 1;
        int maxLen = 1;
        for(int i = 1; i < n; i++){
            length[i] = 1;
            for(int j = 0; j < i; j++){
                if ((direction < 0)^(length[j] % 2 == 0)){
                    // the smaller, the better
                    if (sequence[j] < sequence[i]){
                        if (length[j] + 1 > length[i]){
                            length[i] = length[j] + 1;
                        }
                    }
                }else{
                    // the bigger, the better
                    if (sequence[j] > sequence[i]){
                        if (length[j] + 1 > length[i]){
                            length[i] = length[j] + 1;
                        }
                    }
                }
            }
            if (length[i] > maxLen)
                maxLen = length[i];
        }
        return maxLen;
    }
    public static int longestZigZag(int[] sequence){
        int len1 = zigZag_direction(sequence, 1);
        int len2 = zigZag_direction(sequence, -1);
        if (len1 > len2)
            return len1;
        else
            return len2;
    }
}
