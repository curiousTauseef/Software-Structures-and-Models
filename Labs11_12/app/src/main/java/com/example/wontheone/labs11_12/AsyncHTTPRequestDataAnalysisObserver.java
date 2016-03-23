package com.example.wontheone.labs11_12;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-22.
 */
public interface AsyncHTTPRequestDataAnalysisObserver {
    void updateNumLines(int num);
    void setTotalSize(int num);
    void setLongestLineLength(int num);
}
