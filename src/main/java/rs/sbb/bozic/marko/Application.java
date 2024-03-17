package rs.sbb.bozic.marko;

import java.io.File;
import java.util.List;

import static rs.sbb.bozic.marko.CopyTiffFiles.getAllDirectories;
import static rs.sbb.bozic.marko.CopyTiffFiles.listDirs1;

public class Application {

    public static void main(String[] args) {

     /*   CopyFile copyFile = new CopyFile(args[0],args[1],args[2]);
//        CopyFile copyFile = new CopyFile("C:\\Users\\bozic\\Documents\\dirs.txt","C:\\Users\\bozic\\Documents\\imgs.txt","C:\\test123321123321");

        copyFile.searchAndPaste();*/

        //String rootDirectoryPath = "C:\\Users\\bozic\\Documents\\New folder\\";

        List<File> allDirectories = getAllDirectories(new File(args[0]+"\\"));

        for (File directory : allDirectories) {
            listDirs1(directory, args[0]+ "\\", args[1] + "\\");
        }

    }
}
