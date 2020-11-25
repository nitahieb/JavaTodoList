package model;


import exceptions.InvalidTitleException;

import java.util.Objects;

//Represents a Task having a name, description, and due date
public class Task {
    private String name;
    private String description;
    private int dueDate;
    private Boolean complete;

    // Constructs a task
    // EFFECTS Constructs a task where taskName is its name, taskDescription is its description
    // and taskDueDate is its dueDate (yyyy/mm/dd) throws InvalidTitleException if taskname.length() == 0
    public Task(String taskName, String taskDescription, int taskDueDate) throws InvalidTitleException {
        if (taskName.length() == 0) {
            throw new InvalidTitleException();
        }
        this.name = taskName;
        this.description = taskDescription;
        this.dueDate = taskDueDate;
        this.complete = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDueDate() {
        return dueDate;
    }

    public Boolean getComplete() {
        return complete;
    }

    //MODIFIES this
    //EFFECTS changes the name of a task to newName if newName.length() = 0 throws InvalidTitleException
    public void changeName(String newName) throws InvalidTitleException {
        if (newName.length() == 0) {
            throw new InvalidTitleException();
        } else {
            this.name = newName;
        }
    }

    //MODIFIES this
    //EFFECTS changes the description of a task to newDescription
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    //MODIFIES this
    //EFFECTS changes the date of a task to newDueDate
    public void changeDueDate(int newDueDate) {
        this.dueDate = newDueDate;
    }

    //MODIFIES this
    //EFFECTS marks the task a complete
    public void setComplete() {
        complete = true;
    }

    //EFFECTS returns string representation of a Task
    public String toString() {
        return name + " " + description + " that is due on " + Integer.toString(dueDate) + ". And is it complete? "
                + Boolean.toString(complete);
    }

    //EFFECTS compares two objects by title and returns true if they match
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(name, task.name);
    }
}
