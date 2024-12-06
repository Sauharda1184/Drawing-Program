import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolygonCommand extends Command {
    private List<Point> points;
    private PolygonItem polygonItem;
    private PolygonItem previewItem;
    private boolean isComplete = false;

    public PolygonCommand() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
        updatePreview();
    }

    private void updatePreview() {
        if (points.size() >= 2) {
            if (previewItem != null) {
                model.removeItem(previewItem);
            }
            
            Polygon previewPolygon = new Polygon(
                points.stream().mapToInt(p -> p.x).toArray(),
                points.stream().mapToInt(p -> p.y).toArray(),
                points.size()
            );
            previewItem = new PolygonItem(previewPolygon);
            model.addItem(previewItem);
        }
    }

    public void execute() {
        if (previewItem != null) {
            model.removeItem(previewItem);
            previewItem = null;
        }
        
        if (points.size() >= 3) {
            Polygon finalPolygon = new Polygon(
                points.stream().mapToInt(p -> p.x).toArray(),
                points.stream().mapToInt(p -> p.y).toArray(),
                points.size()
            );
            polygonItem = new PolygonItem(finalPolygon);
            model.addItem(polygonItem);
        }
    }

    public boolean undo() {
        if (polygonItem != null) {
            model.removeItem(polygonItem);
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (polygonItem != null) {
            model.addItem(polygonItem);
            return true;
        }
        return false;
    }

    public boolean end() {
        if (points.size() >= 3) {
            isComplete = true;
            return true;
        }
        return false;
    }
} 