//package ui;
//
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
////import model.ListToDoList;
//import com.google.gson.reflect.TypeToken;
//import model.Task;
//import model.ToDoList;
//
//
//import javax.lang.model.type.ArrayType;
//import java.io.*;
//import java.lang.reflect.Array;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class ToDoListApp {
//    private ToDoList todolist;
//    //    private ListToDoList allToDoLists;
//    private Scanner input;
//    private String fileLocation = "./data/ToDoList.json";
//
//    //EFFECTS runs the ToDoList application
//    public ToDoListApp() {
//        runToDoList();
//    }
//
//
////    public void runListToDoList() {
////        boolean keepGoing = true;
////        String command = null;
////        initList();
////
////        while (keepGoing) {
////            displayList();
////            command = input.next();
////            command = command.toLowerCase();
////
////
////            for (int i = allToDoLists.getLength(); i > 0; i--) {
////                if (command.equals((allToDoLists.returnToDoList(i).getToDoListName()))) {
////
////                }
////            }
////
////
////        }
////    }
//
////
////    private void initList() {
////        input = new Scanner(System.in);
////        loadAll();
////    }
////
////    private void displayList() {
////        System.out.println("Would you like to create a new List?");
////        for (int i = allToDoLists.getLength(); i > 0; i--) {
////            System.out.println(allToDoLists.returnToDoList(i).getToDoListName());
////        }
////    }
//
//    // MODIFIES this
//    //EFFECTS processes user input
//    public void runToDoList() {
//        boolean keepGoing = true;
//        String command = null;
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("quit")) {
//                todolist.saveAll(fileLocation);
//                keepGoing = false;
//            } else {
//                selectAction(command);
//            }
//        }
//        System.out.println("Hope you enjoyed!");
//    }
//
//    //MODIFIES this
//    //EFFECTS processes user command from menu options
//    public void selectAction(String command) {
//        if (command.equals("add")) {
//            doAddTask();
//        } else if (command.equals("remove")) {
//            doRemoveTask();
//        } else if (command.equals("edit")) {
//            doEditTask();
//        } else if (command.equals("complete")) {
//            doCompleteTask();
//        } else if (command.equals("refresh")) {
//            doRemoveCompletedTasks();
//        } else if (command.equals("list")) {
//            doPrintToDolist();
//        } else {
//            System.out.println("Could not process input please try again");
//        }
//    }
//
//    //MODIFIES this
//    //EFFECTS intializes ToDoList
//    private void init() {
//        todolist = new ToDoList();
//        todolist.loadAll(fileLocation);
//        input = new Scanner(System.in);
//        //String name = selectName();
//    }
//
//
//    //EFFECTS prints out menu options
//    private void displayMenu() {
//        System.out.println("Do you want to");
//        System.out.println("(add) Add a task?");
//        System.out.println("(remove) Remove a task?");
//        System.out.println("(edit) Edit a task?");
//        System.out.println("(complete) Mark a task as complete");
//        System.out.println("(refresh) remove tasks that are completed");
//        System.out.println("(list) see your current ToDolist");
//        System.out.println("(quit) to quit this session");
//    }
//
//
//    //MODIFIES this
//    //EFFECTS conducts process to add a task
//    private void doAddTask() {
//        System.out.println("Name of task you want to add?");
//        input.nextLine();
//        String addedtaskTitle = input.nextLine();
//        Task temptask = new Task(addedtaskTitle, null, 0);
//        if (todolist.containsInToDo(temptask)) {
//            System.out.println("This task is already in the list");
//
//        } else {
//            System.out.println("describe the task");
//            String taskDescription = input.nextLine();
//
//            System.out.println("When is this task due?");
//            String taskDueDate = input.next();
//            Task task = new Task(addedtaskTitle, taskDescription, Integer.parseInt(taskDueDate));
//            todolist.addTask(task);
//            System.out.println("Your task has been successfully added");
//        }
//    }
//
//    //MODIFIES this
//    //EFFECTS conducts the process to remove a task
//    public void doRemoveTask() {
//        System.out.println("Name of task you want to remove?");
//        input.nextLine();
//        String taskTitle = input.nextLine();
//        todolist.removeTask(taskTitle);
//        System.out.println("Your task has been removed or was not in the ToDoList");
//    }
//
//    //MODIFIES this
//    //EFFECTS conducts process to complete specified task
//    public void doCompleteTask() {
//        System.out.println("What task have you completed?");
//        input.nextLine();
//        String taskTitle = input.nextLine();
//        Task temptask = new Task(taskTitle, null, 0);
//        if (todolist.containsInToDo(temptask)) {
//            todolist.selectTask(taskTitle).setComplete();
//            System.out.println(taskTitle + " has been completed");
//        } else {
//            System.out.println("This task is not in your list");
//        }
//
//    }
//
//    //MODIFIES this
//    //EFFECTS conducts process to edit specified task
//    public void doEditTask() {
//        System.out.println("What task would you like to edit?");
//        String taskTitle = input.next();
//        Task temptask = new Task(taskTitle, null, 0);
//        if (todolist.containsInToDo(temptask)) {
//
//            System.out.println("(title) Would you like to change the task title?");
//            System.out.println("(desc) Would you like to change the description?");
//            System.out.println("(date) Would you like to change the Due Date?");
//
//            String command = input.next();
//            if (command.equals("title")) {
//                input.nextLine();
//                System.out.println("What do you want your new title to be?");
//                todolist.selectTask(taskTitle).changeName(input.next());
//
//            } else if (command.equals("desc")) {
//                input.nextLine();
//                System.out.println("What do you want your new description to be?");
//                todolist.selectTask(taskTitle).changeDescription(input.nextLine());
//
//            } else if (command.equals("date")) {
//                System.out.println("When do you want your task to be due?");
//                todolist.selectTask(taskTitle).changeDueDate(Integer.parseInt(input.next()));
//            }
//        } else {
//            System.out.println("This task is not in your list");
//        }
//    }
//
//    //MODIFIES this
//    //EFFECTS Removes all tasks marked as complete
//    public void doRemoveCompletedTasks() {
//        todolist.removeCompletedTasks();
//
//    }
//
//    //EFFECTS prints out ToDolist
//    public void doPrintToDolist() {
//        if (todolist.toDoListLength() == 0) {
//            System.out.println("Your list is empty");
//        } else {
//            for (Task t : todolist.getQueue()) {
//                System.out.println(t.toString());
//            }
//        }
//    }
//
////    //EFFECTS sets name for ToDolist
////    public String selectName() {
////        System.out.println("What would you like to name your ToDoList");
////        input.nextLine();
////        String name = input.nextLine();
////        return name;
////    }
//
//
//}
//
//
