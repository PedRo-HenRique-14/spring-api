package com.dodas.firstapi.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryManager {
    static DirectoryManager directoryManager;
    static Path absolutePath = Paths.get(System.getProperty("user.dir"));;

    public static DirectoryManager getManager() {
        if (directoryManager == null) {
            directoryManager = new DirectoryManager();
        }

        return directoryManager;
    }

    public void createDirectory(String dir) {
        Path dirPath = absolutePath.resolve(dir);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(String.format("The directory'%s' exist.", dirPath));
        }
    }
}
