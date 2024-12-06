
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MoveButton extends JButton implements ActionListener {

    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private SelectCommand selectCommand;
    private MoveCommand moveCommand;
    private UndoManager undoManager;

    public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Move");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        selectCommand = new SelectCommand();
        moveCommand = new MoveCommand();
        mouseHandler = new MouseHandler();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        // Change cursor when button is clicked
        drawingPanel.addMouseListener(mouseHandler);
        // Add listener for mouse moving.
        drawingPanel.addMouseMotionListener(mouseHandler);
        // Start listening for mouseclicks on the drawing panel
    }

    private class MouseHandler extends MouseAdapter {
        private boolean sourceSelected = false;

        public void mouseMoved(MouseEvent event) {
            if (!sourceSelected) {
                return;
            }
            moveCommand.setEndPoint(event.getPoint());
            undoManager.tryEndCommand(moveCommand);

        }

        public void mouseClicked(MouseEvent event) {
            if (!sourceSelected) {
                sourceSelected = true;
                selectCommand.setPoint(View.mapPoint(event.getPoint()));
                undoManager.endCommand(selectCommand);
                moveCommand.setSelectCommand(selectCommand);
                moveCommand.setSartPoint(event.getPoint());
            } else {
                moveCommand.setEndPoint(event.getPoint());
                drawingPanel.removeMouseListener(this);
                drawingPanel.removeMouseMotionListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(moveCommand);
                moveCommand.postActions();
                sourceSelected = false;
            }
        }
    }

}
