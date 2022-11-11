public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    // TODO
    public FileData(String name, String directory, String modifiedDate) {
    	this.name = name;
    	this.dir = directory;
    	this.lastModifiedDate = modifiedDate;
    }

    // TODO
    public String toString() {
    	return "{Name: " +name+ ", Directory: " +dir+ ", Modified Date: " +lastModifiedDate+ "}";

    }
    
    public boolean equals(FileData other) {
    	return this.name.equals(other.name) && this.dir.equals(other.dir) && this.lastModifiedDate.equals(other.lastModifiedDate);
    }
}
