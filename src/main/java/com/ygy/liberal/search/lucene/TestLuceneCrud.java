package com.ygy.liberal.search.lucene;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 适用lucene 增删改查
 *
 * @author guoyao
 * @date 2020-07-18
 */
public class TestLuceneCrud {


    public static void main(String[] args) throws Exception {
        testSave();
        testSingleQueryAnalyzer();
        testMultiFieldQueryAnalyzer();
        testItemQuery();
        testBooleanQuery();
        //testDel();

    }


    //布尔搜索
    private static void testBooleanQuery() {
        TermQuery query1 = new TermQuery(new Term("content", "股市"));
        TermQuery query2 = new TermQuery(new Term("content", "暴跌"));
        BooleanQuery query = new BooleanQuery.Builder()
                .add(new BooleanClause(query1, BooleanClause.Occur.MUST))
                .add(new BooleanClause(query2, BooleanClause.Occur.MUST_NOT)).build();
        testQuery(query);
    }

    //词项搜索
    private static void testItemQuery() {
        TermQuery termQuery = new TermQuery(new Term("content", "股市"));
        testQuery(termQuery);
    }

    private static void testSingleQueryAnalyzer() throws Exception {
        QueryParser queryParser = new QueryParser("content", new SmartChineseAnalyzer());
        testQueryParse(queryParser, "股市暴涨");
    }

    //多字段搜索
    private static void testMultiFieldQueryAnalyzer() throws Exception {
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(new String[]{"title", "content"}, new SmartChineseAnalyzer());
        testQueryParse(queryParser, "股市");
    }


    private static void testQueryParse(QueryParser queryParser, String keyWord) throws Exception {
        queryParser.setDefaultOperator(QueryParser.Operator.AND);
        Query query = queryParser.parse(keyWord);
        testQuery(query);
    }

    private static void testQuery(Query query) {
        try {
            System.out.println(" query : " + query);
            DirectoryReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs topDocs = searcher.search(query, 10);
            for (ScoreDoc sDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(sDoc.doc);
                System.out.println("documentId:" + sDoc.doc);
                System.out.println(doc.get("id"));
                System.out.println(doc.get("title"));
                System.out.println(doc.get("content"));
            }
            System.out.println("-----------------------------");
        } catch (Exception ex) {

        }
    }


    private static void testDel() throws Exception {
        IndexWriter indexWriter = getIndexWriter();
        indexWriter.deleteDocuments(new Term("content", "股市暴涨"));
        indexWriter.commit();
        indexWriter.close();
    }


    private static void testSave() throws Exception {

        FieldType idType = new FieldType();
        idType.setIndexOptions(IndexOptions.DOCS);
        //存储
        idType.setStored(true);

        Article article = getArticle();
        Document firstDoc = new Document();
        firstDoc.add(new Field("id", String.valueOf(article.id), idType));
        firstDoc.add(new Field("title", String.valueOf(article.title), getTitleType()));
        firstDoc.add(new Field("content", String.valueOf(article.content), getContentType()));
        firstDoc.add(new IntPoint("relayTimes", article.relayTimes));
        firstDoc.add(new IntPoint("relayTimesDisplay", article.relayTimes));

        //分词器 index打开方式 :先清除后创建
        IndexWriter writer = getIndexWriter();
        writer.addDocument(firstDoc);
        writer.commit();
        writer.close();
    }

    private static IndexWriter getIndexWriter() throws IOException {
        IndexWriterConfig writerConfig = new IndexWriterConfig(new SmartChineseAnalyzer())
                .setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        Directory directory = FSDirectory.open(Paths.get("index"));

        return new IndexWriter(directory, writerConfig);
    }

    private static FieldType getTitleType() {
        FieldType titleType = new FieldType();
        titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        titleType.setStored(true);
        titleType.setTokenized(true);
        return titleType;
    }

    private static FieldType getContentType() {
        FieldType contentType = new FieldType();
        //索引信息
        contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        contentType.setStored(true);
        //词条化
        contentType.setTokenized(true);
        //词向量
        contentType.setStoreTermVectors(true);
        //词偏移信息
        contentType.setStoreTermVectorOffsets(true);
        //词位置信息
        contentType.setStoreTermVectorPositions(true);
        contentType.setStoreTermVectorPayloads(true);
        return contentType;
    }

    private static Article getArticle() {
        Article article = new Article();
        article.setId(241);
        article.setTitle("股市从此站起来了");
        article.setContent("股市连续多天暴涨,不断有各种韭菜追涨杀跌,机构乐此不疲的割韭菜");
        article.setRelayTimes(100);
        return article;
    }


    private static class Article {

        private int id;

        private String title;

        private String content;

        /**
         * 转发次数
         */
        private int relayTimes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRelayTimes() {
            return relayTimes;
        }

        public void setRelayTimes(int relayTimes) {
            this.relayTimes = relayTimes;
        }
    }

}
