package ch33.clrs;

public class Segment {
    Point begin;
    Point end;
    public Segment(Point begin, Point end){
        this.begin = begin;
        this.end = end;
    }
    public Segment(int x1, int y1, int x2, int y2){
        this.begin = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    private Point toVector(){
        return new Point(this.end.x - this.begin.x, this.end.y - this.begin.y);
    }
    public int cross(Segment s){
        Point v1 = this.toVector();
        Point v2 = s.toVector();
        return v1.x * v2.y - v1.y * v2.x;
    }
    public boolean onClockwiseOf(Segment s){
        return this.cross(s) > 0;
    }

    // 返回0：没有交点，返回2：与seg在其端点相交，返回1：在'seg'的非端点处相交
    public boolean intersect(Segment seg){
        Segment[] ss = new Segment[]{this, seg};
        boolean[] cross = new boolean[2];
        for(int i = 0; i < 2; i++){
            Segment thisSegment = ss[i];
            Segment otherSegment = ss[(i+1)%2];
            int c1 = thisSegment.cross(new Segment(thisSegment.end, otherSegment.begin));
            int c2 = thisSegment.cross(new Segment(thisSegment.end, otherSegment.end));
            if (c1 == 0){
                if(Point.in(otherSegment.begin, thisSegment.begin, thisSegment.end)){
                    return true;
                }
            }
            if (c2 == 0){
                if(Point.in(otherSegment.end, thisSegment.begin, thisSegment.end)){
                    return true;
                }
            }
            cross[i] = (c1 > 0) ^ (c2 > 0);
        }
        if(cross[0] && cross[1]){
            return true;
        }else{
            return false;
        }
    }
}