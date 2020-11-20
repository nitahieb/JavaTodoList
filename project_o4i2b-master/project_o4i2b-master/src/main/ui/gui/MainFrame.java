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

//Class that holds the main JFrame and puts everything together
public class MainFrame extends JFrame {

    private ButtonPanel buttonPanel;
    private JRadioButton task1;
    private JRadioButton task2;
    private JRadioButton task3;
    private JRadioButton task4;
    private JRadioButton task5;
    private JRadioButton task6;
    private ButtonGroup buttonGroup;
    private JPanel radioPanel;
    private ToDoList toDoList;
    private AddDialog addDialog;
    private EditDialog editDialog;
    private String fileLocation = "./data/ToDoList.json";



    //Sets up the main Jframe
    public MainFrame() {
        super("ToDo List");
        setLayout(new BorderLayout());
        toDoList = new ToDoList();
        toDoList.loadAll(fileLocation);
        initializeButtonPanel();
        JLabel instructions = new JLabel("<html>This is a ToDoList App <br>"
                + "1. You can only add 6 tasks to the ToDoList <br>  "
                + "2. Tasks have to have a unique name or they will not be added <br>"
                + "3. The DueDate for tasks has to be an int, if not they will not be added <br>"
                + "4. Don't click remove/complete/edit button when no task is selected (includes blank ones) <br>"
                + "5. Tasks will save when save button is pressed, you can exit app however you please after<br>"
                + "6. Tasks will save and load automatically with the in app exit button </html>");
        add(buttonPanel, BorderLayout.NORTH);
        add(instructions, BorderLayout.SOUTH);
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
        task1 = new JRadioButton("");
        task2 = new JRadioButton("");
        task3 = new JRadioButton("");
        task4 = new JRadioButton("");
        task5 = new JRadioButton("");
        task6 = new JRadioButton("");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(task1);
        buttonGroup.add(task2);
        buttonGroup.add(task3);
        buttonGroup.add(task4);
        buttonGroup.add(task5);
        buttonGroup.add(task6);
        radioPanel.add(task1);
        radioPanel.add(task2);
        radioPanel.add(task3);
        radioPanel.add(task4);
        radioPanel.add(task5);
        radioPanel.add(task6);
        refresh();

    }

    //refreshes the program so tasks go where they need to go
    public void refresh() {
        clearList();
        if (toDoList.toDoListLength() == 1) {
            task1.setText(toDoList.getTask(0).toString());
        }
        if (toDoList.toDoListLength() == 2) {
            task1.setText(toDoList.getTask(0).toString());
            task2.setText(toDoList.getTask(1).toString());
        }
        if (toDoList.toDoListLength() == 3) {
            task1.setText(toDoList.getTask(0).toString());
            task2.setText(toDoList.getTask(1).toString());
            task3.setText(toDoList.getTask(2).toString());
        }
        if (toDoList.toDoListLength() == 4) {
            set4();
        }
        if (toDoList.toDoListLength() == 5) {
            set5();
        }
        if (toDoList.toDoListLength() == 6) {
            set6();
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
                if (task1.isSelected()) {
                    toDoList.getTask(0).changeDueDate(Integer.parseInt(editDialog.getDateText().getText()));
                }
                if (task2.isSelected()) {
                    toDoList.getTask(1).changeDueDate(Integer.parseInt(editDialog.getDateText().getText()));
                }
                if (task3.isSelected()) {
                    toDoList.getTask(2).changeDueDate(Integer.parseInt(editDialog.getDateText().getText()));
                }
                if (task4.isSelected()) {
                    toDoList.getTask(3).changeDueDate(Integer.parseInt(editDialog.getDateText().getText()));
                }
                if (task5.isSelected()) {
                    toDoList.getTask(4).changeDueDate(Integer.parseInt(editDialog.getDateText().getText()));
                }
                if (task6.isSelected()) {
                    toDoList.getTask(5).changeDueDate(Integer.parseInt(editDialog.getDateText().getText()));
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
                if (task1.isSelected()) {
                    toDoList.getTask(0).setComplete();
                }
                if (task2.isSelected()) {
                    toDoList.getTask(1).setComplete();
                }
                if (task3.isSelected()) {
                    toDoList.getTask(2).setComplete();
                }
                if (task4.isSelected()) {
                    toDoList.getTask(3).setComplete();
                }
                if (task5.isSelected()) {
                    toDoList.getTask(4).setComplete();
                }
                if (task6.isSelected()) {
                    toDoList.getTask(5).setComplete();
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
                } catch (NumberFormatException a) {
                    addDialog.dispose();
                } catch (InvalidTitleException i) {
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
                if (task1.isSelected()) {
                    toDoList.removeTask(0);
                }
                if (task2.isSelected()) {
                    toDoList.removeTask(1);
                }
                if (task3.isSelected()) {
                    toDoList.removeTask(2);
                }
                if (task4.isSelected()) {
                    toDoList.removeTask(3);
                }
                if (task5.isSelected()) {
                    toDoList.removeTask(4);
                }
                if (task6.isSelected()) {
                    toDoList.removeTask(5);
                }
                refresh();
            }
        });
    }

    //clears the gui todoList
    private void clearList() {
        task1.setText("");
        task2.setText("");
        task3.setText("");
        task4.setText("");
        task5.setText("");
        task6.setText("");
    }

    //sets text for 4 buttons
    private void set4() {
        task1.setText(toDoList.getTask(0).toString());
        task2.setText(toDoList.getTask(1).toString());
        task3.setText(toDoList.getTask(2).toString());
        task4.setText(toDoList.getTask(3).toString());
    }


    //sets text for 5 buttons
    private void set5() {
        set4();
        task5.setText(toDoList.getTask(4).toString());
    }

    //sets text for 6 buttons
    private void set6() {
        set5();
        task6.setText(toDoList.getTask(5).toString());
    }


}
