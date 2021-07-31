package ui.gui;

import exceptions.InvalidTitleException;
import model.Task;
import model.ToDoList;
import ui.gui.dialogs.AddDialog;
import ui.gui.dialogs.EditDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

//Class that holds the main JFrame and puts everything together
public class MainFrame extends JFrame {

    private ButtonPanel buttonPanel;
    private JPanel radioPanel;
    private final ToDoList toDoList;
    private AddDialog addDialog;
    private EditDialog editDialog;
    private final String fileLocation = "JavaTodoList/data/ToDoList.json";
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private JRadioButton task1 = new JRadioButton("");
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private JRadioButton task2 = new JRadioButton("");
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private JRadioButton task3 = new JRadioButton("");
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private JRadioButton task4 = new JRadioButton("");
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private JRadioButton task5 = new JRadioButton("");
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private JRadioButton task6 = new JRadioButton("");
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private ArrayList<JRadioButton> toDoButtonList = new ArrayList<JRadioButton>();


    //Sets up the main Jframe
    public MainFrame() {
        super("ToDo List");
        setLayout(new BorderLayout());
        toDoList = new ToDoList();
        toDoList.loadAll(fileLocation);
        initializeButtonPanel();
        add(buttonPanel, BorderLayout.NORTH);
        add(radioPanel, BorderLayout.LINE_START);

        setSize(1000, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setListeners();
        exitListener();
        refresh();
    }


    //Initalizes all that is needed in the ButtonPanel
    private void initializeButtonPanel() {
        buttonPanel = new ButtonPanel();
        radioPanel = new JPanel(new GridLayout(0, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        Collections.addAll(toDoButtonList,task1,task2,task3,task4,task5,task6);
        for (int i=0;i<toDoButtonList.size();i++){
            JRadioButton task = toDoButtonList.get(i);
            buttonGroup.add(task);
            radioPanel.add(task);
        }

        refresh();

    }

    //refreshes the program so tasks go where they need to go
    public void refresh() {
        clearList();
        for(int i =0;i<toDoList.toDoListLength();i++){
            JRadioButton task = toDoButtonList.get(i);
            task.setText(toDoList.getTask(i).toString());
        }
    }


    //sets up all listeners for MainFrame
    public void setListeners() {
        buttonPanel.getAddTask().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toDoList.toDoListLength() >= 6) {
                    JDialog errorBox = new JDialog();
                    JLabel errorMessage = new JLabel("You already have 6 Tasks remove one to add another");
                    errorBox.add(errorMessage);
                    errorBox.setSize(450, 100);
                    errorBox.setVisible(true);
                } else {
                    addDialog = new AddDialog();
                    addDialog.setVisible(true);
                    addDialog.setSize(500, 500);
                    acceptListener();
                }
            }
        });
        saveListener();
        removeListener();
        completeListener();
        editSetup();
    }

    // listener for editButton
    private void editSetup() {
        buttonPanel.getEditTask().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog = new EditDialog();
                acceptButtonListener();
            }
        });
    }


    //listener for acceptButton
    private void acceptButtonListener() {
        editDialog.getChangeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 for (int i = 0;i<toDoList.toDoListLength();i++) {
                     if (toDoButtonList.get(i).isSelected()) {
                         toDoList.getTask(i).changeDueDate(Integer.parseInt(editDialog.getDateText().getText()));
                     }
                 }
                helperAcceptButtonListener();
            }
        });
    }

    //helper to shorten AcceptButtonListener
    private void helperAcceptButtonListener() {
        refresh();
        editDialog.dispose();
    }

    // sets up save listener
    private void saveListener() {
        buttonPanel.getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoList.saveAll(fileLocation);
            }
        });
    }

    //sets up completeTask listener
    private void completeListener() {
        buttonPanel.getCompleteTask().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0;i<toDoList.toDoListLength();i++) {
                    JRadioButton task = toDoButtonList.get(i);
                    if (task.isSelected()&& !task.getText().equals("")){
                        toDoList.getTask(i).setComplete();
                    }
                }
                refresh();
            }
        });
    }

    //sets up accept Listener
    private void acceptListener() {
        addDialog.getAccept().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Task newTask = new Task(addDialog.getTaskName().getText(), addDialog.getTaskDesc().getText(),
                            Integer.parseInt(addDialog.getTaskDate().getText()));
                    toDoList.addTask(newTask);
                } catch (NumberFormatException | InvalidTitleException a) {
                    addDialog.dispose();
                }
                addDialog.dispose();
                refresh();
            }
        });
    }

    //sets up the exit Listener
    private void exitListener() {
        buttonPanel.getQuit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoList.saveAll(fileLocation);
                System.exit(0);
            }
        });
    }

    //Sets up the removeTask listener
    private void removeListener() {
        buttonPanel.getRemoveTask().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0;i<toDoList.toDoListLength();i++) {
                    JRadioButton task = toDoButtonList.get(i);
                    if (task.isSelected()&& !task.getText().equals("")) {
                        toDoList.removeTask(i);
                    }
                }
                refresh();
            }
        });
    }

    //clears the gui todoList
    private void clearList() {
        for (JRadioButton task : toDoButtonList){
            task.setText("");
        }
    }


}
