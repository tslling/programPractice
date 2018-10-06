package ch33.clrs;

public class Point{
    public int x;
    public int y;
    public Point(){
        this.x = this.y = 0;
    }
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    // 在边界返回0，在内部返回-1，在外部返回+1
    public int inPolygon(Point[] points){
        //该点向右的射线 与 多边形的边相交点的个数 如果为偶数，则不在多边形内
        int xMax = Integer.MIN_VALUE;
        for(int i = 0; i < points.length; i++){
            if (points[i].x > xMax){
                xMax = points[i].x;
            }
        }
        if (xMax < this.x){
            return 1;
        }
        int intersectCount = 0;
        Segment xLine = new Segment(this, new Point(xMax, this.y));
        for(int i = 0; i < points.length; i++) {
            Segment s;
            if (i == points.length - 1){
                s = new Segment(points[i], points[0]);
            }else{
                s = new Segment(points[i], points[i+1]);
            }
            if(new Segment(s.begin, this).cross(new Segment(this, s.end)) == 0){//共线
                if((s.begin.x <= this.x && this.x <= s.end.x && s.begin.y <= this.y && this.y <= s.end.y)
                        ||(s.end.x <= this.x && this.x <= s.begin.x && s.end.y <= this.y && this.y <= s.begin.y)){
                    return 0;
                }
            }
            boolean inter = xLine.intersect(s);
            if(inter){//相交
                intersectCount++;
                if(s.begin.y == s.end.y){//如果是横躺着的一条线，那这种相交应该算两次交点（起点和终点都算一次）
                    intersectCount++;
                }
                if(xLine.cross(new Segment(xLine.begin, s.begin)) == 0){//s的起点在xLine上
                    Point p1 = points[i==0 ? points.length-1 : i-1];
                    Point p2 = s.end;
                    if(p1.y * p2.y < 0){
                        intersectCount--;
                    }
                }
            }
        }
        if (intersectCount % 2 == 1){
            return -1;
        }else{
            return 1;
        }
    }
    //return true if p is on the segment defined by begin point and end point
    static boolean in(Point p, Point begin, Point end){
        if(new Segment(p, begin).cross(new Segment(p, end)) != 0){
            return false;
        }
        boolean x, y;
        if(begin.x < end.x){
            x = begin.x <= p.x && p.x <= end.x;
        }else{
            x = end.x <= p.x && p.x <= end.x;
        }
        if(begin.y < end.y){
            y = begin.y <= p.y && p.y <= end.y;
        }else{
            y = end.y <= p.y && p.y <= begin.y;
        }
        return x && y;
    }
}