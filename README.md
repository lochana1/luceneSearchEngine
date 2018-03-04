

USING LUCENE FOR TEXT SEARCH IN CRANFIELD


Execute: java -jar luceneSearch-0.0.1-SNAPSHOT.jar -cran cran

These commands will run the JAR and a result text will be generated. This result text is then used with TREC EVAL library to get the evaluation result.

Analyzers
I have used 3 different analyzers:
1. Standard Analyzer: It has all the basic 
2. English Analyzer
3. Custom Analyzer
The custom analyzer is made of following filters:

 

Scoring Models
I have used 3 different scoring models:

1. TFIDF: This ranking function is implemented as Classic Similarity. It is based upon the term weighing using vector space model.

2. BM25 Similarity: BM25 is a bag-of-words retrieval function that ranks a set of documents based on the query terms appearing in each document, regardless of the inter-relationship between the query terms within a document.
(https://en.wikipedia.org/wiki/Okapi_BM25)

3. LMDirichlet Similarity : It is a generative statistical model that allows sets of observations to be explained by unobserved groups that explain why some parts of the data are similar. (https://en.wikipedia.org/wiki/Latent_Dirichlet_allocation)

Observations using MAP and Recall Precision
Analyzer	Similarity	MAP	Recall Precision
Custom	TFIDF	0.3873	0.3854
Custom	BM25	0.3885	0.3855
English	BM25	0.3885	0.3855
Standard	BM25	0.3885	0.3855
English	LMDirichlet	0.2975	0.3048
English	TFIDF	0.3816	0.3788



 

Using combinations of different analyzers and scoring it can be seen that 
1. Effect of using different analyzers is minimal
2. BM25 performs a little better than TFIDF 
3. LMDirichlet doesn't give good performance.
