package rs.sbb.bozic.marko;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class CopyTiffFiles {

    public static void listDirs1(final File directory, final String absolutePath, final String copyPath) {

        // Check if the provided path is a directory
        if (directory.isDirectory()) {
            // Get an array of all files and directories in the specified directory
            File[] directories = directory.listFiles();
            // Iterate through the files and print their names
            if (directories != null) {
                for (File file : directories) {
                    if (file.getName().endsWith(".tiff")) {
                        copyFileToFinalDestination1(file,absolutePath,copyPath);
                    }
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("The specified path is not a directory.");
        }
    }

    public static void main(String[] args) {

        String rootDirectoryPath = "C:\\Users\\bozic\\Documents\\New folder\\";

//        List<File> allDirectories = getAllDirectories(new File("C:\\Users\\marko\\Videos\\"));
        List<File> allDirectories = getAllDirectories(new File(args[0]+"\\"));

        for (File directory : allDirectories) {
            listDirs1(directory, args[0], args[1]+"\\");
            //            listDirs1(directory, "C:\\Users\\marko\\Videos", "C:\\Users\\marko\\Pictures\\");

        }
    }

    public static List<File> getAllDirectories(File directory) {
        List<File> allDirectories = new ArrayList<>();

        if (directory.isDirectory()) {
            allDirectories.add(directory);

            File[] subDirectories = directory.listFiles(File::isDirectory);
            if (subDirectories != null) {
                for (File subDirectory : subDirectories) {
                    allDirectories.addAll(getAllDirectories(subDirectory));
                }
            }
        }

        return allDirectories;
    }
    private static void copyFileToFinalDestination1(File file, String finalPath,String copyPath) {

        String path = file.getAbsolutePath().replace(finalPath, "").replace(file.getName(),"");


        String location= copyPath+path+"\\";
        File file1 = new File(location);
        Path startLocation = file.toPath();
        Path endLocation = Path.of(copyPath+"\\" + path.replace("\\","")+"-"+file.getName());

        try {
            if (!file1.exists()) {
                boolean created = file1.mkdirs();
            }
            Files.copy(startLocation, endLocation, StandardCopyOption.REPLACE_EXISTING);
            System.out.printf("File copied successfully.%nStart location: %s%nEnd location: %s%n", startLocation, endLocation);

            System.out.println("\n----------------------------------------------------------\n");
        } catch (IOException e) {
            System.err.println(file.getName());
            e.printStackTrace();
        }
    }

}
