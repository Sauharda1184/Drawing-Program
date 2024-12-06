import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.*;

public class PolygonButton extends JButton implements ActionListener {
    private View view;
    private JPanel drawingPanel;
    private UndoManager undoManager;
    private MouseHandler mouseHandler;

    public PolygonButton(UndoManager undoManager, View view, JPanel drawingPanel) {
        super("Polygon");
        this.view = view;
        this.drawingPanel = drawingPanel;
        this.undoManager = undoManager;
        this.mouseHandler = new MouseHandler();
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.addMouseMotionListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private PolygonCommand polygonCommand;

        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (polygonCommand == null) {
                    polygonCommand = new PolygonCommand();
                    undoManager.beginCommand(polygonCommand);
                }
                
                polygonCommand.addPoint(View.mapPoint(e.getPoint()));
                
            } else if (SwingUtilities.isRightMouseButton(e)) {
                if (polygonCommand != null && polygonCommand.end()) {
                    polygonCommand.execute();
                    undoManager.endCommand(polygonCommand);
                    
                    drawingPanel.removeMouseListener(this);
                    drawingPanel.removeMouseMotionListener(this);
                    view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    polygonCommand = null;
                }
            }
        }

        public void mouseMoved(MouseEvent e) {
            if (polygonCommand != null) {
                view.refresh();
            }
        }
    }
} 