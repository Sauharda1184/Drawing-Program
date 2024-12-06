import java.awt.*;

public class TriangleCommand extends Command {

    private Triangle triangle; /// quadrilateral item
    private int pointCount; /// Counts the numeber of points it has been passed

    // ! Constructor takes no arguments
    public TriangleCommand() {
        this(null, null, null);
        pointCount = 0;

    }

    // ! Constructor takes a sigle point as an argument
    public TriangleCommand(Point point) {
        this(point, null, null);
        pointCount = 1;
    }

    public TriangleCommand(Point point1, Point point2, Point point3) {
        triangle = new Triangle(point1, point2, point3);
        pointCount = 3;
    }

    // ! Updates the points of the quadrilateral if all points haven't been set
    public void setTrianglePoint(Point point) {
        if (++pointCount == 1) {
            triangle.setPoint1(point);
        } else if (pointCount == 2) {
            triangle.setPoint2(point);
        } else if (pointCount == 3) {
            triangle.setPoint3(point);
        }

    }

    public void trySetTrianglePoint(Point point) {
        if (pointCount == 0) {
            triangle.setPoint1(point);
        } else if (pointCount == 1) {
            triangle.setPoint2(point);
        } else if (pointCount == 2) {
            triangle.setPoint3(point);
        }

    }

    // ! Abstract method implementation, tells the model to add the triangle to
    // the itemList
    public void execute() {
        model.addItem(triangle);
    }

    // ! Abstract method implementation, tells the model to remove the triangle
    public boolean undo() {
        model.removeItem(triangle);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public boolean end() {
        if (triangle.getPoint1() == null) {
            undo();
            return false;
        }
        if (triangle.getPoint2() == null) {
            triangle.setPoint2(triangle.getPoint1());
        }
        if (triangle.getPoint3() == null) {
            triangle.setPoint3(triangle.getPoint1());
        }

        return true;
    }

}
