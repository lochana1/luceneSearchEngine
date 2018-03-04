


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.en.EnglishMinimalStemFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardFilter;


public class CustomAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer source = new ClassicTokenizer();
	    TokenStream filter = new LowerCaseFilter(source);
	    filter = new KStemFilter(filter);
	    filter = new EnglishMinimalStemFilter(filter);
	    filter = new EnglishPossessiveFilter(filter);
	  
	    filter = new PorterStemFilter(filter);
	    filter = new StandardFilter(filter);
	    CharArraySet stopWord = CharArraySet.copy(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	    filter = new StopFilter(filter, stopWord);

	     
		
		return new TokenStreamComponents(source, filter);
	}


	
	

}
