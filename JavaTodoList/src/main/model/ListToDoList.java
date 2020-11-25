//package model;
//
//import com.google.gson.Gson;
//import com.sun.org.apache.xpath.internal.operations.Bool;
//
//import java.io.FileReader;

//import java.lang.reflect.Type;
//import java.util.LinkedList;
//import java.util.Objects;
//
//public class ListToDoList {
//    LinkedList<ToDoList> listOfToDoLists;
//
//
//    public ListToDoList() {
//        this.listOfToDoLists = new LinkedList<>();
//    }
//
//    //MODIFIES this
//    //EFFECTS
//    public void addList(ToDoList toDoList) {
//        listOfToDoLists.add(toDoList);
//    }
//
//
//    //MODIFIES this
//    //EFFECTS if title is the same as Todolist remove Todolist otherwise do nothing
//    public void removeList(String toDoListname) {
//        for (ToDoList list : listOfToDoLists) {
//            if (toDoListname == list.getToDoListName()) {
//                listOfToDoLists.remove(list);
//            }
//        }
//    }
//
//    //EFFECTS if input matches title of a ToDolist return true, otherwise return false
//    public Boolean containedInList(String toDoListname) {
//        for (ToDoList list : listOfToDoLists) {
//            if (toDoListname == list.getToDoListName()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//
//    //EFFECTS returns the todolist based on where in list, else return null
//    public ToDoList returnToDoList(int i) {
//        if (listOfToDoLists.size() == 0) {
//            return null;
//        } else {
//            return listOfToDoLists.get(i);
//        }
//    }
//
//    public int getLength() {
//        return listOfToDoLists.size();
//    }
//}
