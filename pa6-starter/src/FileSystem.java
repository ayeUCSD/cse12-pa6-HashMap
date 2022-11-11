import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

	MyHashMap<String, ArrayList<FileData>> nameMap;
	MyHashMap<String, ArrayList<FileData>> dateMap;

	public FileSystem() {
		nameMap = new MyHashMap<String, ArrayList<FileData>>();
		dateMap = new MyHashMap<String, ArrayList<FileData>>();
	}

	// TODO
	public FileSystem(String inputFile) {
		nameMap = new MyHashMap<String, ArrayList<FileData>>();
		dateMap = new MyHashMap<String, ArrayList<FileData>>();
		// STILL NEED TODO HERE FINISH OTHER METHODS FIRST
//////////////////////////////////////////////////////

		try {
			File f = new File(inputFile);
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String[] data = sc.nextLine().split(", ");
				System.out.println(Arrays.toString(data));
				add(data[0], data[1], data[2]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

	}

	/**
	 * adds a a new FileData entry to both nameMap and dateMap
	 * 
	 * @param fileName
	 * @param directory
	 * @param modifiedDate
	 * @return
	 */
	public boolean add(String fileName, String directory, String modifiedDate) {
		FileData entry = new FileData(fileName, directory, modifiedDate);
		ArrayList<FileData> l = nameMap.get(fileName);

		// ADD TO nameMap
		// if its the first one to be added, make a new array for the file!
		if (l == null) {
			l = new ArrayList<FileData>();
			l.add(entry);
			nameMap.put(fileName, l);
		} // otherwise, just add it to the list (hope that pointers work the way you
			// thought)
		else {
			l.add(entry);
		}

		ArrayList<FileData> l2 = nameMap.get(modifiedDate);

		// ADD TO dateMap
		// if its the first one to be added, make a new array for the file!
		if (l2 == null) {
			l2 = new ArrayList<FileData>();
			l2.add(entry);
			nameMap.put(fileName, l2);
		} // otherwise, just add it to the list (hope that pointers work the way you
			// thought)
		else {
			l2.add(entry);
		}

		return true;
	}

	/**
	 * returns file with mathcing name and directory otherwise it returns null
	 * 
	 * @param name
	 * @param directory
	 * @return
	 */
	public FileData findFile(String name, String directory) {
		// get the list of entries
		ArrayList<FileData> l = nameMap.get(name);
		if (l == null) {
			return null;
		}

		for (FileData e : l) {
			if (e.name.equals(name) && e.dir.equals(directory)) {
				return e;
			}
		}
		return null;

	}

	// returns all the keys in fileMap, which should be the names of files!
	public ArrayList<String> findAllFilesName() {

		List<String> l = nameMap.keys();
		return (ArrayList<String>) l;
	}

	//
	public ArrayList<FileData> findFilesByName(String name) {
		ArrayList<FileData> l = nameMap.get(name);
		if (l != null) {
			return l;
		} else {
			return new ArrayList<FileData>();
		}
	}

	//
	public ArrayList<FileData> findFilesByDate(String modifiedDate) {
		ArrayList<FileData> l = nameMap.get(modifiedDate);
		if (l != null) {
			return l;
		} else {
			return new ArrayList<FileData>();
		}
	}

	// TODO
	public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {
		ArrayList<FileData> l = dateMap.get(modifiedDate);
		for (FileData e : l) {
			for (FileData e2 : l) {
				if (!e.equals(e2)) {
					return l;
				}
			}

		}
		return new ArrayList<FileData>();
	}

	// TODO
	// should also remove the files from the dateMap
	public boolean removeByName(String name) {
		ArrayList<FileData> names = nameMap.get(name);
		ArrayList<String> dates = new ArrayList<String>();
		for (FileData e : names) {
			dates.add(e.lastModifiedDate);
		}

		// remove names from dates list
		for (String date : dates) {
			ArrayList<FileData> temp = findFilesByDate(date);
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).name.equals(name)) {
					temp.remove(i);
					i--;

				}
			}
		}

		nameMap.set(name, new ArrayList<FileData>());

		return true;
	}

	// TODO
	public boolean removeFile(String name, String directory) {

		FileData toRemove = null;
		ArrayList<FileData> nList = nameMap.get(name);
		for (int i = 0; i < nList.size(); i++) {
			FileData e = nList.get(i);
			if (e.name.equals(name) && e.dir.equals(directory)) {
				toRemove = e;
				nList.remove(i);
				break;
			}
		}
		if (toRemove == null) {
			return false;
		}

		nList = dateMap.get(toRemove.lastModifiedDate);

		for (int i = 0; i < nList.size(); i++) {
			FileData e = nList.get(i);
			if (e.name.equals(name) && e.dir.equals(directory)) {
				toRemove = e;
				nList.remove(i);
				return true;
			}
		}

		return false;

	}

}
