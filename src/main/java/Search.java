import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class Search {

	
	public static File search(String indexPath) throws IOException, ParseException {
		PrintWriter writer = null;
		File file = new File("Result.txt");
		writer = new PrintWriter(new FileWriter(file));
		String field = "contents";
		

		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
		IndexSearcher searcher = new IndexSearcher(reader);
		searcher.setSimilarity(new BM25Similarity());
//		searcher.setSimilarity(new ClassicSimilarity());
//		searcher.setSimilarity(new TFIDFSimilarity() {
//			
//			@Override
//			public float tf(float arg0) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//			
//			@Override
//			public float sloppyFreq(int arg0) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//			
//			@Override
//			public float scorePayload(int arg0, int arg1, int arg2, BytesRef arg3) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//			
//			@Override
//			public float lengthNorm(int arg0) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//			
//			@Override
//			public float idf(long arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		});

//		Analyzer analyzer = new StandardAnalyzer();
		Analyzer analyzer = new EnglishAnalyzer();
//		Analyzer analyzer = new CustomAnalyzer();
		QueryParser parser = new QueryParser(field, analyzer);
		
		parser.setAllowLeadingWildcard(true);
		BufferedReader r = new BufferedReader(new FileReader("Query/QueryList"));
		String querys = r.readLine();
		String questionaire[] = new String[225];
		String qNo = "";
		String que = "";
		int counter = 0;
		while (querys != null) {
			qNo = querys.substring(0, 3);
			que = querys.substring(4);
			questionaire[counter] = que;
			counter++;
			querys = r.readLine();
		}
		int questionparsed = 0;
		for (int i = 0; i < questionaire.length; i++) {
			ReleventDocRetrieve.releventDoc(Integer.toString(i + 1));
			Query query = parser.parse(questionaire[i]);
			BoostQuery q = new BoostQuery(query, 1.5f);
			TopDocs results = searcher.search(q, 40);
			ScoreDoc[] hits = results.scoreDocs;
			for (int j = 0; j < hits.length; j++) {
				String output = i + 1 + " " + "Q0" +" " + searcher.doc(hits[j].doc).get("id") + " " + (j + 1) + " "
						+ hits[j].score + " " + "STANDARD";
				
				writer.println(output);

			}
			questionparsed++;
		}
		writer.close();
			return file;
	}

}
