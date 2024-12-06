import java.awt.event.*;
import javax.swing.*;
public class UndoButton extends JButton implements ActionListener {
  private final UndoManager undoManager;
  public UndoButton(UndoManager undoManager) {
    super("Undo");
    this.undoManager = undoManager;
    init();
  }
  private void init() {
    addActionListener(this);
  }
  @Override
  public void actionPerformed(ActionEvent event) {
    undoManager.undo();
  }
}