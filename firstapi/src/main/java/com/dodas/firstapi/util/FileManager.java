package com.dodas.firstapi.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dodas.firstapi.entity.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FileManager {
    static FileManager fileManager;
    static DirectoryManager dirMan = DirectoryManager.getManager();

    Gson gson = new Gson();

    public static FileManager getFileManager() {
        if (fileManager == null) {
            fileManager = new FileManager();
        }
        return fileManager;
    }

    @SuppressWarnings("static-access")
    public boolean fileExist(String dir, String fileName) {
        Path fileDir = dirMan.absolutePath.resolve(fileName);
        return Files.exists(fileDir);
    }

    @SuppressWarnings("static-access")
    public void createFile(String dir, Response response) {
        Path fileDir = Paths.get(dirMan.absolutePath.toString(), dir);
        Path filePath = fileDir.resolve(response.getUuid() + ".json");
        String jsonString = gson.toJson(response);
        try (FileWriter fileWriter = new FileWriter(filePath.toFile())){

            fileWriter.write(jsonString);
            fileWriter.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void updateFile(String uuid, Response res) {
        File fileToUpdate = getFileByUuid(uuid);

        try {
            
            FileReader reader = new FileReader(fileToUpdate.getAbsolutePath());
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            jsonObject.addProperty("name", res.getName());
            jsonObject.addProperty("age", res.getAge());
            jsonObject.addProperty("gender", res.getGender());
            jsonObject.addProperty("profession", res.getProfession());

            FileWriter writer = new FileWriter(fileToUpdate.getAbsolutePath());
            gson.toJson(jsonObject, writer);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-access")
    public List<Response> getAllFilesContent(String dir) {
        List<Response> responses = new ArrayList<>();
        Path fileDir = Paths.get(dirMan.absolutePath.toString(), dir);
        File folderPath = new File(fileDir.toString());
        File[] allFiles = folderPath.listFiles();

        for(File file : allFiles) {
            try(FileReader reader = new FileReader(String.format("%s/%s/%s", dirMan.absolutePath, dir, file.getName()))) {
                responses.add(gson.fromJson(reader, Response.class));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responses;
    }

    @SuppressWarnings("static-access")
    public void deleteFiles(String dir, String uuid) {
        File filesDirPath = new File(Paths.get(dirMan.absolutePath.toString(), dir).toString());
        List<File> allFiles = Arrays.asList(filesDirPath.listFiles());
        
        allFiles.forEach((file) -> {
            try(FileReader jsonReader = new FileReader(String.format("%s/%s/%s", dirMan.absolutePath, dir, file.getName()))) {
                Response res = gson.fromJson(jsonReader, Response.class);
                jsonReader.close();
                if (res.getUuid().equals(uuid)) {
                    Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("static-access")
    private File getFileByUuid(String uuid) {
        File filesPath = new File(Paths.get(dirMan.absolutePath.toString(), dirMan.getFilesDirectory()).toString());
        File[] allFiles = filesPath.listFiles();

        for(File file : allFiles) {
            if (file.getName().replace(".json", "").equals(uuid)) {
                return file;
            }
        }

        return null;
    }
}
