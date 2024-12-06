import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class View extends JFrame {
  private final JPanel drawingPanel;
  private final JPanel buttonPanel;
  private final JButton lineButton;
  private final JButton deleteButton;
  private final JButton labelButton;
  private final JButton triangleButton;
  private final JButton moveButton;
  private final JButton selectButton;
  private final JButton saveButton;
  private final JButton openButton;
  private final JButton undoButton;
  private final JButton redoButton;
  private static UndoManager undoManager;
  private String fileName;
  private static Model model;
  private final JButton polygonButton;

  public static void setModel(Model model) {
    View.model = model;
  }

  public static void setUndoManager(UndoManager undoManager) {
    View.undoManager = undoManager;
  }

  private class DrawingPanel extends JPanel {
    private MouseListener currentMouseListener;
    private KeyListener currentKeyListener;
    private FocusListener currentFocusListener;

    public DrawingPanel() {
      setLayout(null);
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      (NewSwingUI.getInstance()).setGraphics(g);
      g.setColor(Color.BLUE);
      Enumeration enumeration = model.getItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();
      }
      g.setColor(Color.RED);
      enumeration = model.getSelectedItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();
      }
    }

    public void addMouseListener(MouseListener newListener) {
      removeMouseListener(currentMouseListener);
      currentMouseListener = newListener;
      super.addMouseListener(newListener);
    }

    public void addKeyListener(KeyListener newListener) {
      removeKeyListener(currentKeyListener);
      currentKeyListener = newListener;
      super.addKeyListener(newListener);
    }

    public void addFocusListener(FocusListener newListener) {
      removeFocusListener(currentFocusListener);
      currentFocusListener = newListener;
      super.addFocusListener(newListener);
    }
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
    setTitle("Drawing Program 1.1  " + fileName);
  }

  public String getFileName() {
    return fileName;
  }

  public View() {
    super("Drawing Program 1.1  Untitled");
    fileName = null;
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        System.exit(0);
      }
    });
    model.setUI(NewSwingUI.getInstance());
    drawingPanel = new DrawingPanel();
    buttonPanel = new JPanel();
    Container contentpane = getContentPane();
    contentpane.add(buttonPanel, "North");
    contentpane.add(drawingPanel);
    lineButton = new LineButton(undoManager, this, drawingPanel);
    labelButton = new LabelButton(undoManager, this, drawingPanel);
    triangleButton = new TriangleButton(undoManager, this, drawingPanel);
    moveButton = new MoveButton(undoManager, this, drawingPanel);
    selectButton = new SelectButton(undoManager, this, drawingPanel);
    deleteButton = new DeleteButton(undoManager);
    saveButton = new SaveButton(undoManager, this);
    openButton = new OpenButton(undoManager, this);
    undoButton = new UndoButton(undoManager);
    redoButton = new RedoButton(undoManager);
    polygonButton = new PolygonButton(undoManager, this, drawingPanel);
    buttonPanel.add(lineButton);
    buttonPanel.add(labelButton);
    buttonPanel.add(triangleButton);
    buttonPanel.add(moveButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(openButton);
    buttonPanel.add(undoButton);
    buttonPanel.add(redoButton);
    buttonPanel.add(polygonButton);
    this.setSize(800, 400);
    this.setLocationRelativeTo(null);
  }

  public void refresh() {
    // code to access the Model update the contents of the drawing panel.
    drawingPanel.repaint();
  }

  public static Point mapPoint(Point point) {

    return point;
  }
}