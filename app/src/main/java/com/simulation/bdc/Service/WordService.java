package com.simulation.bdc.Service;

import android.util.Log;

import com.simulation.bdc.enitity.Review;
import com.simulation.bdc.enitity.Word;
import com.simulation.bdc.util.ConnecteURL;
import com.simulation.bdc.util.ParseJson;
import com.simulation.bdc.util.RequestURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;

public class WordService {

    private static final String TAG = "WordService";
    private ConnecteURL connecteURL = new ConnecteURL();

    private RequestURL requestUrl = new RequestURL();

    /**
     * 通过单词拼写模糊查找单词信息
     * @param word
     * @return
     */
    public List<Word> queryWordByName(String word) {
        String url = requestUrl.queryWordByWordName(null,word);
        String wordJson = connecteURL.connecteUrl(url);
        List<Word> wordList = ParseJson.parseWords(wordJson);
        return wordList;
    }

    /**
     * 通过单词拼写精确查找单词信息
     * @param word
     * @return
     */
    public Word queryWordByWordName(String word){
        String url = requestUrl.queryWordByWordName(word);
        String wordJson = connecteURL.connecteUrl(url);
        Log.d(TAG, "queryWordByWordName: " + url);
        Log.d(TAG, "queryWordByWordName: " + wordJson);
        List<Word> wordList = ParseJson.parseWords(wordJson);
        if(wordList != null && !wordList.isEmpty()){
            return wordList.get(0);
        }else{
            return null;
        }
    }

//    /**
//     * 从通过单词Id本地查找单词信息
//     * @param wordId
//     * @return
//     */
//    public Word queryWordByWordIdFromLocal(int wordId){
//        return DataSupport.where("wordId=?",wordId +"").findFirst(Word.class);
//    }
    /**
     * 通过单元Id查找单词
     * @param unitId
     * @return
     */
    public List<Word> queryWordByUnitId(int unitId,int userId){
        List<Word> wordList = null;
        String url = requestUrl.queryWordByUnitId(unitId, userId);
        Log.d(TAG, "queryWordByUnitId: " + url);
        Log.d(TAG, "queryWordByUnitId: " + connecteURL.connecteUrl(url));
        wordList = ParseJson.parseWords(connecteURL.connecteUrl(url));
        return wordList;
    }

    /**
     * 添加用户已完成单词
     * @param userId
     * @param wordId
     * @return
     */
    public  boolean addUserCompleteWord(int userId,int wordId){
        String url = requestUrl.addUserCompleteWord(userId,wordId);
        Log.d(TAG, "addUserCompleteWord: " + url);
        String responseData = connecteURL.connecteUrl(url);
        if(ParseJson.parseResult(responseData) != 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加用户生词表
     * @param userId
     * @param wordId
     * @return
     */
    public boolean addNewWord(int userId,int wordId){
        String url = requestUrl.addNewWord(userId,wordId);
        Log.d(TAG, "addNewWord: " + url);
        String responseData = connecteURL.connecteUrl(url);
        if(ParseJson.parseResult(responseData) != 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加 用户复习表单词
     * @param userId
     * @param wordId
     * @return
     */
    public boolean addReviewWord(int userId, int wordId){
        String url = requestUrl.addReview(userId, wordId);

        Log.d(TAG, "addReviewWord: " + url);

        String responseData = connecteURL.connecteUrl(url);

        Log.d(TAG, "addReviewWord+responseData: " + responseData);
        if(ParseJson.parseResult(responseData) != 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 修改用户复习表
     * @param review
     * @return
     */
    public boolean updateReview(Review review){
        boolean result = false;
        if(review != null) {
            String url = requestUrl.updateReview(review.getReviewId(), review.getReviewTime());
            String responseData = connecteURL.connecteUrl(url);

            Log.d(TAG, "updateReview: " + responseData + url);

            if (ParseJson.parseResult(responseData) != 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 获取用户复习表
     * @param userId
     * @return
     */
    public List<Review> queryReview(int userId){
        String responseData = connecteURL.connecteUrl(requestUrl.queryReview(userId));
        Log.d(TAG, "queryReview: " + responseData);
        List<Review> reviews = ParseJson.parseReview(responseData);
        DataSupport.deleteAll(Review.class,"userId=?",userId + "");
        DataSupport.saveAll(reviews);
        return reviews;
    }

    /**
     * 获取本地复习表信息
     * @param userId
     * @return
     */
    public List<Review> queryReviewFromLocal(int userId){
        List<Review> reviews = DataSupport.where("userId=? and reviewTime < 4",userId + "").find(Review.class);
        return reviews;
    }
    /**
     * 通过单词Id查找单词
     * @param wordId
     * @return
     */
    public Word queryWordByWordId(int wordId){
        String url = RequestURL.URL + "/word/query_by_word_info?wordId=" + wordId;
        String responseData = connecteURL.connecteUrl(url);
        Word word = null;
        Log.d(TAG, "queryWordByWordId + word : " + responseData + wordId);
        try {
            word = ParseJson.parseWord(new JSONArray(responseData).getJSONObject(0));
        }catch (JSONException e){
            Log.d(TAG, "queryWordByWordId: " + e);
        }
        return word;
    }

}
