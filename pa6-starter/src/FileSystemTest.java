import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/**
[01 PASSED  0.50/0.50] 
[02 PASSED  0.50/0.50] 
[03 PASSED  0.50/0.50] 
[04 FAILED  0.00/0.50] testSameDate
[05 FAILED  0.00/0.50] testSameFilenameSameDirectory
[06 FAILED  0.00/0.50] testRandomAddNameMap
[07 FAILED  0.00/0.50] testRandomAddDateMap
[08 FAILED  0.00/0.50] testConstructorNameMap
[09 FAILED  0.00/0.50] testConstructorDateMap
[10 PASSED  0.50/0.50] 
[11 PASSED  0.50/0.50] 
[12 PASSED  0.50/0.50] 
[13 PASSED  0.50/0.50] 
[14 PASSED  0.50/0.50] 
[15 PASSED  0.50/0.50] 
[16 PASSED  0.50/0.50] 
[17 PASSED  0.50/0.50] 
[18 PASSED  0.50/0.50] 
[19 PASSED  0.50/0.50] 
[20 PASSED  0.50/0.50] 
[21 PASSED  0.50/0.50] 
[22 PASSED  0.50/0.50] 
[23 PASSED  0.50/0.50] 
[24 PASSED  0.50/0.50] 
[25 PASSED  0.50/0.50] 
[26 PASSED  0.50/0.50] 
[27 FAILED  0.00/0.50] testRemoveByNameSuccessFromNameMap
[28 FAILED  0.00/0.50] testRemoveByNameSuccessFromDateMap
[29 PASSED  0.50/0.50] 
[30 PASSED  0.50/0.50] 
[31 PASSED  0.50/0.50] 
[32 PASSED  0.50/0.50] 
[33 PASSED  0.50/0.50] 
[34 PASSED  0.50/0.50] 
[35 PASSED  0.50/0.50] 
[36 FAILED  0.00/0.50] testRemoveFileSuccessFromDateMapDeleteEntry
 */
public class FileSystemTest {
    @Test
    public void testConstructor() {
        // make via defalut
        FileSystem fs = new FileSystem();
        // make wtih file to add from input.txt
        fs = makeInputFileSystem();
        FileData output = fs.findFile("mySample.txt", "/home");
        assertEquals("mySample.txt", output.name);
    }

    @Test
    public void TestDateMapAddOne() {
        FileSystem fs = makeInputFileSystem();
        ArrayList<FileData> l = fs.findFilesByDate("02/01/2021");
        //System.out.println(l);
       // System.out.println(fs.dateMap.keys());
        assertFalse(l.isEmpty());
    }
    @Test
    public void testSameDate() {
        //TODO:
        //still failing
        FileSystem fs = makeInputFileSystem();
        fs.add("test","fuckall","02/01/2021");
        ArrayList<FileData> l = fs.findFilesByDate("02/01/2021");
       // System.out.println(l);
        assertFalse(l.isEmpty());
    }

    @Test
    public void testSameFilenameSameDirectory() {
        //TODO:
        FileSystem fs = makeInputFileSystem();
        fs.add("mySample.txt","/home","02/04/2021");
        FileData output = fs.findFile("mySample.txt", "/home");
        assertNotNull(output);
       // System.out.print(output.toString());
        assertTrue( output.lastModifiedDate.equals("02/04/2021"));
    }


    @Test
    public void testRemoveByName() {
        FileSystem fs = makeInputFileSystem();
        assertFalse(fs.removeByName("doesntExist"));



    }
    @Test
    public void testRemoveFile() {
        FileSystem fs = makeInputFileSystem();
        boolean temp = fs.removeFile("mySample.txt", "/home");
        System.out.println(fs.findFilesByName("mySample.txt"));
        System.out.println("~~~~~~~~~~");
        System.out.println(fs.findFilesByDate("02/01/2021"));
        assertTrue(temp);
    }    



    @Test
    public void testAdd() {
        // FileData f1 = new FileData("name1","dir1","1/1/1");
        FileSystem fs = new FileSystem();
        fs.add("name1", "dir1", "1/1/1");

    }

    public FileSystem makeInputFileSystem() {
        /**
         * 
         * [mySample.txt, /home, 02/01/2021]
         * [mySample.txt, /root, 02/01/2021]
         * [mySample.txt, /user, 02/06/2021]
         */
        return new FileSystem(
                "C:\\Users\\adria\\Desktop\\Coding Stuff\\CSE 12\\cse12-pa6-HashMap\\pa6-starter\\src\\input.txt");
    }
}
