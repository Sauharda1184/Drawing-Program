import java.awt.*;

public class MoveCommand extends Command {
    private SelectCommand selectCommand;
    Item initialItem = null;
    Point startPoint;
    Point endPoint;

    public MoveCommand() {
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setSartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setSelectCommand(SelectCommand selectCommand) {
        this.selectCommand = selectCommand;
        Item item = selectCommand.getItem();
        if (item instanceof Line) {
            Line line = (Line) item;
            Point p1 = new Point((int) line.getPoint1().getX(), (int) line.getPoint1().getY());
            Point p2 = new Point((int) line.getPoint2().getX(), (int) line.getPoint2().getY());
            initialItem = new Line(p1, p2);
        }

        if (item instanceof Triangle) {
            Triangle triangle = (Triangle) item;
            Point p1 = new Point((int) triangle.getPoint1().getX(), (int) triangle.getPoint1().getY());
            Point p2 = new Point((int) triangle.getPoint2().getX(), (int) triangle.getPoint2().getY());
            Point p3 = new Point((int) triangle.getPoint3().getX(), (int) triangle.getPoint3().getY());

            initialItem = new Triangle(p1, p2, p3);
        }
    }

    @Override
    public boolean undo() {

        return true;
    }

    @Override
    public boolean redo() {

        return true;
    }

    public void postActions() {
        model.unSelect(selectCommand.getItem());
        initialItem = null;
    }

    @Override
    public void execute() {
        // Logic to handle moving the polygon if needed
    }

    public boolean end() {
        Item item = selectCommand.getItem();
        if (item instanceof Line) {
            Line lineToMove = (Line) item;
            Line initialLine = (Line) initialItem;
            movePoint(lineToMove.getPoint1(), initialLine.getPoint1(), startPoint, endPoint);
            movePoint(lineToMove.getPoint2(), initialLine.getPoint2(), startPoint, endPoint);
        }
        if (item instanceof Triangle) {
            Triangle triangleToMove = (Triangle) item;
            Triangle initialTriangle = (Triangle) initialItem;
            movePoint(triangleToMove.getPoint1(), initialTriangle.getPoint1(), startPoint, endPoint);
            movePoint(triangleToMove.getPoint2(), initialTriangle.getPoint2(), startPoint, endPoint);
            movePoint(triangleToMove.getPoint3(), initialTriangle.getPoint3(), startPoint, endPoint);

        }
        return true;
    }

    private void movePoint(Point pointToMove, Point initialPoint, Point startPoint, Point endPoint) {
        int deltaX = (int) (endPoint.getX() - startPoint.getX());
        int deltaY = (int) (endPoint.getY() - startPoint.getY());
        pointToMove.x = initialPoint.x + deltaX;
        pointToMove.y = initialPoint.y + deltaY;
    }

}
