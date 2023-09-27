package rs.sbb.bozic.marko;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class CopyFile {

    private final String rootPathForDir;
    private final String fileName;
    private final String rootPathForCopy;
    private final List<String> dirs;
    private final List<String> imgs;

    public CopyFile(String rootPathForDir, String fileName, String rootPathForCopy) {
        this.rootPathForDir = rootPathForDir;
        this.fileName = fileName;
        this.rootPathForCopy = rootPathForCopy;
        dirs = new ArrayList<>();
        imgs = new ArrayList<>();
        getDirs();
        getImg();
        searchAndPaste();
    }

    private void getDirs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(rootPathForDir))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dirs.add(line);
            }

            System.out.println("all files are imported");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getImg() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                imgs.add(line);
            }
            System.out.println("all imga are imported");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchAndPaste(){

        for(String dir: dirs){

            // Create a File object representing the directory
            File directory = new File(dir);

            // Check if the provided path is a directory
            if (directory.isDirectory()) {
                // Get an array of all files and directories in the specified directory
                File[] files = directory.listFiles();

                // Iterate through the files and print their names
                if (files != null) {
                    for (File file : files) {

                        if(imgs.contains(file.getName())){
                        copyFileToFinalDestination(file,dir);
                        System.out.println(file.getName());
                    }}
                } else {
                    System.out.println("The directory is empty.");
                }
            } else {
                System.out.println("The specified path is not a directory.");
            }
        }


    }

    private void copyFileToFinalDestination(File file, String dirName){

        String [] splits = dirName.split("\\\\");

        String location= rootPathForCopy + "\\"+splits[splits.length-1]+"\\";
        File file1 = new File(location);
        Path startLocation = file.toPath();
        Path endLocation = Path.of(location + file.getName());

        try {
            if (!file1.exists()) {
                boolean created = file1.mkdirs();
            }
            Files.copy(startLocation, endLocation, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println(file.getName());
            e.printStackTrace();
        }
    }
    }


