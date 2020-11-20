package ui.gui.dialogs;

import javax.swing.*;
import java.awt.*;

public class EditDialog extends JDialog {
    JLabel textEnter;
    JTextField dateText;
    JButton changeButton;

    public EditDialog() {
        setLayout(new GridLayout(3, 1));
        textEnter = new JLabel("    Enter the new due date for your task! (has to be an int)");
        dateText = new JTextField();
        changeButton = new JButton("Change");
        add(textEnter);
        add(dateText);
        add(changeButton);
        setSize(500, 300);
        setVisible(true);
    }

    public JTextField getDateText() {
        return dateText;
    }

    public JButton getChangeButton() {
        return changeButton;
    }

}
