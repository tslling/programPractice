// zoj 1081
package ch33.clrs;

import java.util.Arrays;
import java.util.Scanner;

public class PointInPolygonTest {
    public static void test(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = 0;
        int m = 0;
        Point[] points = new Point[110];
        int count = 1;
        boolean first = true;
        while (s.hasNext()) {
            n = s.nextInt();
            if(s.hasNext()){
                m = s.nextInt();
            }else{
                break;
            }
            for(int i = 0; i < n; i++){
                points[i] = new Point(s.nextInt(), s.nextInt());
            }
            if (first){
                first = false;
            }else{
                System.out.println();
                System.out.println();
            }
            System.out.print("Problem "+(count++)+":");
            for(int i = 0; i < m ;i++){
                System.out.println();
                Point p = new Point(s.nextInt(), s.nextInt());
                int within = p.inPolygon(Arrays.copyOfRange(points, 0, n));
                if(within <= 0){
                    System.out.print("Within");
                }else{
                    System.out.print("Outside");
                }
            }
        }
    }
}
