package dp.practice;

import java.util.Scanner;
// poj 1260
public class Pearls {
    void test(){
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt();
        int[] price = new int[110];
        int[] amount = new int[110];
        int[][] rangePrice = new int[110][110];
        while(N-- > 0){
            int n = cin.nextInt();
            for(int i = 0; i < n; i++){
                amount[i] = cin.nextInt();
                price[i] = cin.nextInt();
            }
            for(int i = 0; i < n; i++)
                rangePrice[i][i] = (amount[i]+10) * price[i];
            for(int len = 2; len <= n; len++){
                for(int start = 0; start <= n - len; start++){
                    int end = start + len - 1;
                    int min = Integer.MAX_VALUE;
                    for(int k = start; k < end; k++){
                        int tmp = rangePrice[start][k] + rangePrice[k+1][end];
                        if(tmp < min){
                            min = tmp;
                        }
                    }
                    int tmpAmount = 0;
                    for(int i = start; i <= end; i++){
                        tmpAmount += amount[i];
                    }
                    int tmp = (tmpAmount + 10) * price[end];
                    rangePrice[start][end] = tmp < min ? tmp : min;
                }
            }
            System.out.println(rangePrice[0][n-1]);
        }
    }
}
