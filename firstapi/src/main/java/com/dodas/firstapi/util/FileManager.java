package com.dodas.firstapi.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.dodas.firstapi.entity.Response;
import com.google.gson.Gson;

public class FileManager {
    static FileManager fileManager;
    static DirectoryManager dirMan = DirectoryManager.getManager();

    public static FileManager getFileManager() {
        if (fileManager == null) {
            fileManager = new FileManager();
        }
        return fileManager;
    }

    @SuppressWarnings("static-access")
    public boolean fileExist(String dir, String fileName) {
        Path fileDir = dirMan.applicationPath.resolve(fileName);
        return Files.exists(fileDir);
    }

    @SuppressWarnings("static-access")
    public void createFile(String dir, Response response) {
        Path fileDir = Paths.get(dirMan.applicationPath.toString(), dir);
        Path filePath = fileDir.resolve(response.getName() + ".json");
        Gson gson = new Gson();
        String jsonString = gson.toJson(response);
        try (FileWriter fileWriter = new FileWriter(filePath.toFile())){

            fileWriter.write(jsonString);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @SuppressWarnings("static-access")
    public List<Response> getAllFilesContent(String dir) {
        List<Response> responses = new ArrayList<>();
        Gson gson = new Gson();
        Path fileDir = Paths.get(dirMan.applicationPath.toString(), dir);
        File folderPath = new File(fileDir.toString());
        File[] allFiles = folderPath.listFiles();

        for(File file : allFiles) {
            try(FileReader reader = new FileReader(String.format("%s/%s/%s", dirMan.applicationPath, dir, file.getName()))) {
                responses.add(gson.fromJson(reader, Response.class));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responses;
    }
}
