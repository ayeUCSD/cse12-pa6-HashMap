import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

    MyHashMap<String, ArrayList<FileData>> nameMap;
    MyHashMap<String, ArrayList<FileData>> dateMap;

    // TODO
    public FileSystem() {
        nameMap = new MyHashMap<String, ArrayList<FileData>>();
        dateMap = new MyHashMap<String, ArrayList<FileData>>();
    }

    // TODO
    public FileSystem(String inputFile) {
        // Add your code here
        nameMap = new MyHashMap<String, ArrayList<FileData>>();
        dateMap = new MyHashMap<String, ArrayList<FileData>>();
        


        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    // TODO
    public boolean add(String fileName, String directory, String modifiedDate) {

    }

    // TODO
    public FileData findFile(String name, String directory) {

    }

    // TODO
    public ArrayList<String> findAllFilesName() {

    }

    // TODO
    public ArrayList<FileData> findFilesByName(String name) {

    }

    // TODO
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {

    }

    // TODO
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {

    }

    // TODO
    public boolean removeByName(String name) {

    }

    // TODO
    public boolean removeFile(String name, String directory) {

    }

}
