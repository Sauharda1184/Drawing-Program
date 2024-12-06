import java.awt.*;

public class PolygonItem extends Item {
    private Polygon polygon;

    public PolygonItem(Polygon polygon) {
        this.polygon = polygon;
    }

    @Override
    public boolean includes(Point point) {
        return polygon.contains(point);
    }

    @Override
    public void render() {
        uiContext.drawPolygon(polygon); // Assuming a method to draw polygons in UIContext
    }
} 