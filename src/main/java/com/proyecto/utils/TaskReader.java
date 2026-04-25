package com.proyecto.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proyecto.App;
import com.proyecto.model.Task;
import com.proyecto.model.PokemonDTO;

public class TaskReader {

    private static final String taskUrl = "data/tasks.json";

    public static List<Task> readTasks(){
        Gson gson = new Gson();
        List<Task> tasks = new ArrayList<>();

        try {
            String json = Files.readString(Path.of(App.class.getResource(taskUrl).toURI()));
            Type listType = new TypeToken<List<PokemonDTO>>(){}.getType();

            List<PokemonDTO> tasksDTO = gson.fromJson(json, listType);

            for (PokemonDTO t : tasksDTO) {
                Task task = new Task(t.name(), t.description(), t.priority());
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
