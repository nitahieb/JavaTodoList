package model;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import exceptions.InvalidTitleException;
import model.persistence.Persistence;

//Represents a ToDolist that can hold tasks

public class ToDoList {
    protected ArrayList<Task> todoList;


    //EFFECTS creates an empty ToDoList
    public ToDoList() {
        this.todoList = new ArrayList<>();
    }

    //EFFECTS selects the task based on taskTitle input
    public Task selectTask(String taskTitle) {
        try {
            Task task = new Task(taskTitle, null, 0);
            for (Task tasksearch : todoList) {
                if (task.equals(tasksearch)) {
                    return tasksearch;
                }
            }
        } catch (InvalidTitleException i) {
            return null;
        }
        return null;
    }


    //MODIFIES this
    //EFFECTS adds a task to ToDoList unless task with same title in list in that case do nothing
    public void addTask(Task task) {
        if (selectTask(task.getName()) == null) {
            todoList.add(task);
        }
    }

    //MODIFIES this
    //EFFECTS removes the task with the containing taskTitle if no matches do nothing
    public void removeTask(String taskTitle) {
        todoList.remove(selectTask(taskTitle));
    }

    //MODIFIES this
    //EFFECTS removes task in this at i
    public void removeTask(int i) {
        todoList.remove(i);
    }

    //MODIFIES this
    //EFFECTS removes all the completed tasks from the ToDOlist
    public void removeCompletedTasks() {
        todoList.removeIf(Task::getComplete);
    }


    //EFFECTS returns true if task is in ToDoList false otherwise
    public Boolean containsInToDo(Task task) {
        for (Task checkTask : todoList) {
            if (checkTask.equals(task)) {
                return true;
            }
        }
        return false;
    }


    //REQUIRES for tasks in input list to not be in this
    //MODIFIES this
    //EFFECTS if input list is not empty add all the input onto this
    public void addTasks(ArrayList<Task> addList) {
        if (!(addList.isEmpty())) {
            todoList.addAll(addList);
        }
    }

    //EFFECTS returns length of ToDoList
    public int toDoListLength() {
        return todoList.size();
    }

    //EFFECTS returns Queue
    public ArrayList<Task> getQueue() {
        return todoList;
    }


    //EFFECTS Return task from TodoList in position i
    public Task getTask(int i) {
        return todoList.get(i);
    }

    //EFFECTS: loads list
    public void loadAll(String fileLocation) {
        Persistence loadall = new Persistence(fileLocation);
        todoList.addAll(loadall.loadAll());
    }

    //EFFECTS saves list
    public void saveAll(String fileLocation) {
        Persistence saveall = new Persistence(fileLocation);
        saveall.saveAll(todoList);
    }
}



