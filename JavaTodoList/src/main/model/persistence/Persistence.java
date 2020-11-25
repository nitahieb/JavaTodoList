package model.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import model.ToDoList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

//deals with ToDoList Object Persistence
public class Persistence extends ToDoList {
    String fileLocation;

    public Persistence(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    //EFFECTS saves all the current tasks
    public void saveAll(ArrayList<Task> toDoList) {
        String json = new Gson().toJson(toDoList);
        try {
            FileWriter newFile = new FileWriter(fileLocation);
            newFile.write(json);
            newFile.close();
        } catch (IOException e) {
            System.out.println("WARNING: No tasks will be saved");
            System.out.println("The save file could not be located");
            e.printStackTrace();
        }
    }


    //EFFECTS loads in all tasks from previous session
    public ArrayList<Task> loadAll() {
        try {
            FileReader listJson = new FileReader(fileLocation);
            BufferedReader bf = new BufferedReader(listJson);
            Type arrayType = new TypeToken<ArrayList<Task>>() {
            }.getType();  // https://stackoverflow.com/questions/32444863/google-gson-linkedtreemap-class-cast-to-myclass
            ArrayList<Task> loadedlist = new Gson().fromJson(bf, arrayType);
            if (loadedlist == null) {
                return new ArrayList();
            } else {
                todoList.addAll(loadedlist);
                return loadedlist;
            }
        } catch (FileNotFoundException e) {
            return new ArrayList();
        }
    }
}
