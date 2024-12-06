import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class TriangleButton extends JButton implements ActionListener {

    protected JPanel drawingPanel;
    protected View view;

    private MouseHandler mouseHandler;
    private TriangleCommand triangleCommand;
    private UndoManager undoManager;

    public TriangleButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Triangle");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        // change cursor when button is clicked.
        drawingPanel.addMouseListener(mouseHandler);

        // action when mouse moved.
        drawingPanel.addMouseMotionListener(mouseHandler);
        // stat listening gor mouseclicks on the drawing panel.
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;

        public void mouseMoved(MouseEvent event) {
            if (pointCount == 1) {
                triangleCommand.trySetTrianglePoint(View.mapPoint(event.getPoint()));
                undoManager.endCommand2(triangleCommand);
            } else if (pointCount == 2) {
                triangleCommand.trySetTrianglePoint(View.mapPoint(event.getPoint()));
                triangleCommand.trySetTrianglePoint(View.mapPoint(event.getPoint()));
                undoManager.endCommand2(triangleCommand);
            }

        }

        public void mouseClicked(MouseEvent event) {
            if (++pointCount == 1) {
                triangleCommand = new TriangleCommand(View.mapPoint(event.getPoint()));
                undoManager.beginCommand(triangleCommand);
            } else if (pointCount == 2) {
                triangleCommand.setTrianglePoint(View.mapPoint(event.getPoint()));
                undoManager.endCommand(triangleCommand);

            }
            // else if (pointCount == 3) {
            // quadrilateralCommand.setQuadrilateralPoint(View.mapPoint(event.getPoint()));
            // undoManager.endCommand(quadrilateralCommand);

            // }

            else if (pointCount == 3) {
                triangleCommand.setTrianglePoint(View.mapPoint(event.getPoint()));
                undoManager.endCommand(triangleCommand);

                pointCount = 0;
                Point p = View.mapPoint(event.getPoint());
                triangleCommand.setTrianglePoint(p);
                drawingPanel.removeMouseListener(this);
                drawingPanel.removeMouseMotionListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(triangleCommand);
            }
        }

    }
}