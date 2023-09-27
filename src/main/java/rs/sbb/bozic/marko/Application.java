package rs.sbb.bozic.marko;

public class Application {

    public static void main(String[] args) {

        CopyFile copyFile = new CopyFile(args[0],args[1],args[2]);
//        CopyFile copyFile = new CopyFile("C:\\Users\\bozic\\Documents\\dirs.txt","C:\\Users\\bozic\\Documents\\imgs.txt","C:\\test123321123321");

        copyFile.searchAndPaste();

    }
}
