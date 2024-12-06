import java.awt.*;

public interface UIContext {
  // public abstract void drawCircle(Circle circle);
  public abstract void drawLine(Point point1, Point point2);

  public abstract void drawLabel(Point startingPoint, String text);

  public abstract void draw(Triangle triangle);

  public abstract void draw(Item item);

  public abstract void drawPolygon(Polygon polygon);
}
