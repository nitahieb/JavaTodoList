package ui.gui;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

//Class for ButtonPanel
public class ButtonPanel extends JToolBar {
    private JButton addTask;
    private JButton completeTask;
    private JButton editTask;
    private JButton removeTask;
    private JButton quit;
    private JButton save;

    //Constructor, sets up ButtonPanel
    public ButtonPanel() {
        setup();
    }

    //sets up and initializes for ButtonPanel
    public void setup() {
        addTask = new JButton();
        completeTask = new JButton();
        editTask = new JButton();
        removeTask = new JButton();
        quit = new JButton();
        save = new JButton("Save");
        setIcons();
        add(addTask);
        add(completeTask);
        add(editTask);
        add(removeTask);
        add(Box.createHorizontalGlue());
        add(save);
        add(quit);

    }


    public JButton getAddTask() {
        return addTask;
    }

    public JButton getCompleteTask() {
        return completeTask;
    }

    public JButton getEditTask() {
        return editTask;
    }

    public JButton getRemoveTask() {
        return removeTask;
    }

    public JButton getQuit() {
        return quit;
    }

    public JButton getSave() {
        return save;
    }

    //Sets icons for Jbuttons
    public void setIcons() {
        ImageIcon addimage = new ImageIcon("./data/Plus.png");
        Image img = addimage.getImage();
        Image plusImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        addTask.setIcon(new ImageIcon(plusImg));


        ImageIcon minusImage = new ImageIcon("./data/minus.png");
        Image img2 = minusImage.getImage();
        Image minusImg = img2.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        removeTask.setIcon(new ImageIcon(minusImg));

        ImageIcon editImage = new ImageIcon("./data/edit.png");
        Image img3 = editImage.getImage();
        Image editImg = img3.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        editTask.setIcon(new ImageIcon(editImg));

        ImageIcon completeImage = new ImageIcon("./data/BlueCheck.png");
        Image img4 = completeImage.getImage();
        Image completeImg = img4.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        completeTask.setIcon(new ImageIcon(completeImg));

        ImageIcon exitImage = new ImageIcon("./data/Exit.png");
        Image img5 = exitImage.getImage();
        Image exitImg = img5.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        quit.setIcon(new ImageIcon(exitImg));



    }
}
