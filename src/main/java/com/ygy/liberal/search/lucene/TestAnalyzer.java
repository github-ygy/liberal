package com.ygy.liberal.search.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * 测试分词器
 *
 * @author guoyao
 * @date 2020-07-18
 */
public class TestAnalyzer {

    public static void main(String[] args) throws Exception {
        String keyWord = "股市连续多天暴涨,不断有各种韭菜追涨杀跌,机构乐此不疲的割韭菜";
        testAnalyzer(new StandardAnalyzer(), keyWord);
        testAnalyzer(new WhitespaceAnalyzer(), keyWord);
        testAnalyzer(new SimpleAnalyzer(), keyWord);
        testAnalyzer(new CJKAnalyzer(), keyWord);
        testAnalyzer(new KeywordAnalyzer(), keyWord);
        testAnalyzer(new StopAnalyzer(), keyWord);
        testAnalyzer(new SmartChineseAnalyzer(), keyWord);

    }


    /**
     * 标准分词器
     * @param keyWord
     * @throws Exception
     */
    private static void testAnalyzer(Analyzer analyzer,String keyWord) throws Exception {
        TokenStream tokenStream = analyzer.tokenStream(keyWord, keyWord);
        tokenStream.reset();
        CharTermAttribute termAttribute = tokenStream.getAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()) {
            System.out.print(termAttribute.toString() + " ||  ");
        }
        System.out.println();
        analyzer.close();
    }
}
