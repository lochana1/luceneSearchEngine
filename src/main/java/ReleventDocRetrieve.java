
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReleventDocRetrieve {
	
	public static int releventDoc(String queryNum) throws IOException, FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader("cran/cranqrel"));
		String line=reader.readLine();
		int counter = 0;
		while (line != null) { 
			
			String a[] = line.split(" ");
			if (queryNum.equals(a[0])) {
				counter++;
			}
			
			line=reader.readLine();
		}
		reader.close();
		return counter;
		
	}

}
