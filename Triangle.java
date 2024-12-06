import java.awt.*;

public class Triangle extends Item {
    private Point point1; // first point
    private Point point2; // second point
    private Point point3; // third point

    // lines
    private Line line1;
    private Line line2;
    private Line line3;

    // constructor taking two points.
    public Triangle(Point point1, Point point2) {
        this(point1, point2, null);
    }

    // constructor taking one point.
    public Triangle(Point point1) {
        this(point1, null, null);
    }

    // construtor taking 0 points
    public Triangle() {
        this(null, null, null);
    }

    // constructor taking three points
    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;

        if (point1 != null && point2 != null) {
            line1 = new Line(point1, point2);
        } else if (point2 != null && point3 != null) {
            line2 = new Line(point2, point3);
        }

        else if (point1 != null && point3 != null) {
            line3 = new Line(point3, point1);
        }
    }

    // includes
    public boolean includes(Point point) {
        int a = 0, b = 0;

        Point vectorAP = new Point(point.x - point1.x, point.y - point1.y);
        Point vectorBP = new Point(point.x - point2.x, point.y - point.y);
        Point vectorCP = new Point(point.x - point3.x, point.y - point3.y);

        a = vectorAP.x * vectorBP.y - vectorAP.y * vectorBP.x;
        b = vectorBP.x * vectorCP.y - vectorBP.y * vectorCP.x;

        if ((a <= 0 && b <= 0) || (a > 0 && b > 0)) {
            return true;
        }
        return false;
    }

    // // ! Abstract method implementation, tells the ui to tell the view to draw
    // this
    // Triangle.
    public void render() {
        uiContext.draw(this);
    }

    public void setPoint1(Point point) {
        point1 = point;
        line1 = new Line(point1, point1);
        line3 = new Line(point1, point1);

    }

    public void setPoint2(Point point) {
        point2 = point;
        line1.setPoint2(point2);
        line2 = new Line(point2, point2);

    }

    public void setPoint3(Point point) {
        point3 = point;
        line2.setPoint2(point3);
        line3.setPoint1(point3);
    }

    // ! Returns the first point
    public Point getPoint1() {
        return point1;
    }

    // ! Returns the second point
    public Point getPoint2() {
        return point2;
    }

    // ! Returns the third point
    public Point getPoint3() {
        return point3;
    }

    public Line getLine1() {
        if (line1 == null) {
            line1 = new Line(point1, point2);
        }
        return line1;
    }

    public Line getLine2() {
        if (line2 == null) {
            line2 = new Line(point2, point3);
        }
        return line2;
    }

    public Line getLine3() {
        if (line3 == null) {
            line3 = new Line(point3, point1);
            // line3 = new Line(point3, point4);
        }
        return line3;
    }

    public String toString() {
        return "Triangle  from " + point1 + " to " + point2 + " to " + point3;
    }

    // ! Abstract method implementation for additional movement operation
    public void accept(MoveVisitor visitor, int dx, int dy) {
        visitor.visit(this, dx, dy);
    }
}
