package ui.gui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Class for the AddDialog
public class AddDialog extends JDialog {

    JLabel name;
    JLabel desc;
    JLabel date;
    JButton accept;
    JButton decline;
    JTextField taskName;
    JTextField taskDesc;
    JTextField taskDate;

    //constructor & sets up decline listener
    public AddDialog() {
        setup();

        decline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    //sets up everything for AddDialog
    private void setup() {
        setLayout(new GridLayout(4, 2));
        name = new JLabel("   Insert the Task Title");
        desc = new JLabel("   Insert the Task Description");
        date = new JLabel("   Insert the Task DueDate");
        taskName = new JTextField(20);
        taskDesc = new JTextField(20);
        taskDate = new JTextField("0", 20);
        accept = new JButton("Accept");
        decline = new JButton("Decline");
        add(name);
        add(taskName);
        add(desc);
        add(taskDesc);
        add(date);
        add(taskDate);
        add(accept);
        add(decline);
    }

    public JButton getAccept() {
        return accept;
    }

    public JTextField getTaskName() {
        return taskName;
    }

    public JTextField getTaskDesc() {
        return taskDesc;
    }

    public JTextField getTaskDate() {
        return taskDate;
    }
}
