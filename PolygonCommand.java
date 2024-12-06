import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolygonCommand extends Command {
    private List<Point> points;
    private Polygon polygon;
    private PolygonItem polygonItem;

    public PolygonCommand() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
        if (points.size() >= 3) {
            polygon = new Polygon(
                points.stream().mapToInt(p -> p.x).toArray(),
                points.stream().mapToInt(p -> p.y).toArray(),
                points.size()
            );
            polygonItem = new PolygonItem(polygon);
        }
    }

    public void execute() {
        if (polygonItem != null) {
            model.addItem(polygonItem);
        }
    }

    public boolean undo() {
        if (polygonItem != null) {
            model.removeItem(polygonItem);
        }
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public boolean end() {
        return points.size() >= 3;
    }
} 