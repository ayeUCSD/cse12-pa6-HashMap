import static org.junit.Assert.*;

import org.junit.*;

public class FileDataTest {

	
	
	@Test
	public void testConstruction() {
		try {
			FileData t = new FileData("amongus.txt", "./local/", "1/1/2021");
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	
	@Test
	public void testToString() {
		FileData t = new FileData("amongus.txt", "./local/", "1/1/2021");
		assertEquals("{Name: amongus.txt, Directory: ./local/, Modified Date: 1/1/2021}", t.toString());
	}
	
}
