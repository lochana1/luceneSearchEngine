import java.io.File;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

public class LuceneSearchMain {
	
	public static void main(String args[]) throws IOException, ParseException {
		
		Logger logger = Logger.getLogger(LuceneSearchMain.class);
		BasicConfigurator.configure();
		
		String usage = "java CranFiles Folder" + " [-cran Cran_File]\n\n"
				+ "This parse the Cran files and Indexes the at INDEX_PATH "
				+ "and in INDEX_PATH that can be searched with SearchFiles";
		String cranFolder = null;

		for (int i = 0; i < args.length; i++) {
			if ("-cran".equals(args[i])) {
				cranFolder = args[i + 1].trim();
				i++;
			}
		}
		
		if (cranFolder == null) {
			System.err.println("Usage: " + usage);
			System.exit(1);
		}
		String fileName = null;
		if (cranFolder.endsWith("/")) {
			fileName=cranFolder.substring(0,cranFolder.length()-1);
		}
		else {
			fileName=cranFolder;
		}
		Parser cf;
		File folder = new File(fileName);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		for(int i = 0; i<listOfFiles.length; i++) {

			if ("cran.all.1400".equals(listOfFiles[i].getName())) {
				logger.info("Parsing the file : " + listOfFiles[i].getName());
				cf = new Parser();
				cf.createfile(listOfFiles[i].toString());
			}
			else if("cran.qry".equals(listOfFiles[i].getName())){
				logger.info("Parsing the file : " + listOfFiles[i].getName());
				cf = new Parser();
				cf.createfile(listOfFiles[i].toString());
			}
		}
		logger.info("Indexing ...... ");
		IndexFiles.callIndex("ParsedDocs", "INDEX_PATH");
		logger.info("Searching ...... ");
		File f = Search.search("INDEX_PATH");
		logger.info("Result File Location: "+ f.getAbsolutePath());
	}

}
