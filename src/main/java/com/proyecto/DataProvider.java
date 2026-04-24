package com.proyecto;

import com.proyecto.model.TaskManager;
import com.proyecto.utils.TaskReader;

public class DataProvider {
    private static DataProvider instance;

    private TaskManager taskManager;

    private DataProvider(){
        taskManager = new TaskManager();
        taskManager.setTasks(TaskReader.readTasks());
    }

    public static DataProvider getInstance(){
        if (instance==null) instance = new DataProvider();

        return instance;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }
}
