package geometry;

/**
 *
 * @author Douglas Maitelli
 */
public class Line {

    private Double a;
    private Double b;

    public Line(Number a, Number b) {
        this.a = a.doubleValue();
        this.b = b.doubleValue();
    }

    public Line(Number a, Point p) {
        fromPoint(a, p);
    }

    public Line(Point p1, Point p2) {
        fromPoints(p1, p2);
    }

    /**
     * @return the a
     */
    public Double getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Number a) {
        this.a = a.doubleValue();
    }

    /**
     * @return the b
     */
    public Double getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(Number b) {
        this.b = b.doubleValue();
    }

    public Double getAngle() {
        return a * (double) 90;
    }

    public final void fromPoint(Number a, Point p) {
        Line s = lineFromPoint(a, p);

        this.a = s.getA();
        b = s.getB();
    }

    public final void fromPoints(Point p1, Point p2) {
        Line s = lineFromPoints(p1, p2);

        a = s.getA();
        b = s.getB();
    }

    public boolean hasPoint(Point p) {
        return Math.abs(getY(p.getX()) - p.getY()) < 0.0000001;
    }

    public Point getPointFromX(Number x) {
        return new Point(x, this.getY(x));
    }

    public Point getPointFromY(Number y) {
        return new Point(this.getX(y), y);
    }

    public Double getInverseAngularCoeficient() {
        return Math.pow(a, -1) * -1;
    }

    public boolean isAscendant() {
        return a > 0;
    }

    public boolean isDescendant() {
        return a < 0;
    }

    public boolean isConstant() {
        return a == 0;
    }

    public Line getParallelFromPoint(Point p) {
        return new Line(a, p);
    }

    public Boolean isParallel(Line s) {
        return a.equals(s.getA());
    }

    public Line getPerpendicularFromPoint(Point p) {
        return new Line(getInverseAngularCoeficient(), p);
    }

    public boolean isPerpendicular(Line s) {
        return getInverseAngularCoeficient().equals(s.getA());
    }

    public boolean isConcurrent(Line s) {
        return !this.isParallel(s) && !this.isPerpendicular(s);
    }

    public Double distanceFromPoint(Point p) {
        return Math.abs(a * p.getX() + p.getY() * -1 + b) / Math.sqrt(Math.pow(a, 2) + 1);
    }

    public Point getIntersectionPoint(Line line) {
        if (this.isParallel(line)) {
            return null;
        }

        Double x = (line.getB() - b) / (a - line.getA());

        return getPointFromX(x);
    }

    public Point getClosestPoint(Point p) {
        return getIntersectionPoint(getPerpendicularFromPoint(p));
    }

    @Override
    public String toString() {
        StringBuilder eqStr = new StringBuilder("f(x) = ");

        if (a != 0) {
            eqStr.append(a).append("x");

            if (b != 0) {
                eqStr.append(" ");
            }
        }

        if (b < 0 || a == 0 && b == 0) {
            eqStr.append(b);
        } else if (b > 0) {
            eqStr.append("+ ").append(b);
        }

        return eqStr.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Line s = (Line) obj;

        return a.equals(s.getA()) && b.equals(s.getB());
    }

    public static Double distanceBetweenLines(Line s1, Line s2, Number x) {
        return s1.getPointFromX(x).distanceFromPoint(s2.getPointFromX(x));
    }

    public static Line lineFromPoint(Number a, Point p) {
        return new Line(a, a.doubleValue() * (p.getX() * -1) + p.getY());
    }

    public static Line lineFromPoints(Point p1, Point p2) {
        Double a = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        return Line.lineFromPoint(a, p2);
    }

}
