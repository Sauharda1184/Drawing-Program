import java.awt.*;

public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;

  private NewSwingUI() {
  }

  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }

  public void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }

  public void drawLabel(Point startingPoint, String text) {
    if (startingPoint != null) {
      if (text != null) {
        graphics.drawString(text, (int) startingPoint.getX(), (int) startingPoint.getY());
      }
      int length = graphics.getFontMetrics().stringWidth(text);
      graphics.drawString("-", (int) startingPoint.getX() + length, (int) startingPoint.getY());
    }

  }

  public void drawLine(Point point1, Point point2) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (point1 != null) {
      i1 = Math.round((float) (point1.getX()));
      i2 = Math.round((float) (point1.getY()));
      if (point2 != null) {
        i3 = Math.round((float) (point2.getX()));
        i4 = Math.round((float) (point2.getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }

  // ! Drawing method for line items
  public void draw(Line line) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (line.getPoint1() != null) {
      i1 = Math.round((float) (line.getPoint1().getX()));
      i2 = Math.round((float) (line.getPoint1().getY()));
      if (line.getPoint2() != null) {
        i3 = Math.round((float) (line.getPoint2().getX()));
        i4 = Math.round((float) (line.getPoint2().getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }

  public void draw(Triangle triangle) {
    Point p1 = triangle.getPoint1();
    Point p2 = triangle.getPoint2();
    Point p3 = triangle.getPoint3();

    if (triangle.getLine1() != null) {
      draw(triangle.getLine1());
    }
    if (triangle.getLine2() != null) {
      draw(triangle.getLine2());
    }
    if (triangle.getLine3() != null) {
      draw(triangle.getLine3());
    }

  }

  public void draw(Item item) {
    System.out.println("Cant draw unknown Item \n");
  }

  public void drawPolygon(Polygon polygon) {
    if (polygon != null) {
      graphics.drawPolygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
  }
}