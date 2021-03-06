package com.simulation.bdc.util;

import android.util.Log;

import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.DailySentence;
import com.simulation.bdc.enitity.Grade;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Publisher;
import com.simulation.bdc.enitity.Review;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.Unit;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.enitity.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 解析后台传过来的json数据
 */
public class ParseJson {

    private static final String TAG = "ParseJson";
    private static String url = "http://123.206.29.55:8080/lab.bdc/";

    /**
     * 解析返回结果
     * @param responseData
     * @return
     */
    public static int parseResult(String responseData) {
        int result = 0;
        if(responseData != null) {
            try {
                JSONObject jsonObject = new JSONObject(responseData);
                result = jsonObject.getInt("result");
            } catch (JSONException e) {
                Log.e(TAG, "parseResult: 解析json数据错误", e);
            }
        }
        return result;
    }

    /**
     * 解析含有用户信息的json数据
     *
     * @param jsonString
     * @return
     */
    public static User parseUserJson(String jsonString) {
        User user = null;
        if (jsonString == null || "null".equals(jsonString)) {
            return user;
        } else {
            try {
                JSONObject userJson = new JSONObject(jsonString);
                user = new User();
                user.setAccount(userJson.getString("account"));
                user.setAge(userJson.getInt("age"));
                user.setBirthday(DateConverter.convert(userJson.getString("birthday")));
                user.setEmail(userJson.getString("email"));
                user.setGender(userJson.getString("gender"));
                user.setNickname(userJson.getString("nickname"));
                user.setPassword(userJson.getString("password"));
                user.setPhoneNo(userJson.getString("phoneNo"));
                user.setPic(userJson.getString("pic"));
                user.setRegisterTime(DateConverter.convert(userJson.getString("registerTime")));
                user.setUserId(userJson.getInt("userId"));
                user.setPlans(parseUserPlan(userJson.getString("plans")));
            } catch (JSONException e) {
                Log.e(TAG, "parseUserJson: " + e);
            }
        }
        return user;
    }

    /**
     * 解析服务端传过来的每日一句的信息
     *
     * @param jsonString
     * @return
     */
    public static DailySentence parseDailySentenceJson(String jsonString) {
        DailySentence dailySentence = null;
        if (jsonString == null || "参数不合法".equals(jsonString)) {
            return dailySentence;
        } else {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                dailySentence = new DailySentence();
                dailySentence.setSid(jsonObject.getInt("sid"));
                dailySentence.setTts(jsonObject.getString("tts"));
                dailySentence.setContent(jsonObject.getString("content"));
                dailySentence.setNote(jsonObject.getString("note"));
                dailySentence.setLove(jsonObject.getInt("love"));
                dailySentence.setTranslation(jsonObject.getString("translation"));
                dailySentence.setPicture(jsonObject.getString("picture"));
                dailySentence.setPicture2(jsonObject.getString("picture2"));
                dailySentence.setCaption(jsonObject.getString("caption"));
                dailySentence.setDateLine(jsonObject.getString("dateline"));
                dailySentence.setS_pv(jsonObject.getInt("s_pv"));
                dailySentence.setSp_pv(jsonObject.getInt("sp_pv"));
                dailySentence.setTags(jsonObject.getString("tags"));
                dailySentence.setFenxiang_img(jsonObject.getString("fenxiang_img"));
            } catch (JSONException e) {
                Log.e(TAG, "parseDailySentenceJson: " + e);
            }
        }
        return dailySentence;
    }

    /**
     * 解析单词信息
     * @param jsonString
     * @return
     */
    public static List<Word> parseWords(String jsonString) {
        List<Word> wordList = null;
        try {
            JSONArray wordsJson = new JSONArray(jsonString);
            wordList = new ArrayList<Word>();
            for(int i = 0;i < wordsJson.length();i++){
                JSONObject jsonObject = wordsJson.getJSONObject(i);
                Word word = parseWord(jsonObject);
                wordList.add(word);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public static Word parseWord(JSONObject jsonObject){
        Word word = null;
        try {
            word = new Word();
            word.setMeans(parseMean(jsonObject.getString("means")));
            word.setWordId(jsonObject.getInt("wordId"));
            word.setPhUk(jsonObject.getString("phUk"));
            word.setPhUsa(jsonObject.getString("phUsa"));
            word.setProUk(jsonObject.getString("proUk"));
            word.setProUsa(jsonObject.getString("proUsa"));
            word.setSentence(parseSentence(jsonObject.getString("sentences")));
            word.setWordName(jsonObject.getString("wordName"));
            word.setAlikeWord(parseString(jsonObject.getJSONArray("alikeWord")));
            word.setSameTypeWord(parseString(jsonObject.getJSONArray("sameTypeWord")));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return word;
    }

    /**
     * 解析单词释义
     * @param jsonString
     * @return
     */
    public static List<Mean> parseMean(String jsonString){
        List<Mean> meanList = null;
        try{
            JSONArray meanJson = new JSONArray(jsonString);
            meanList = new ArrayList<Mean>();
            for(int i = 0;i < meanJson.length();i++){
                JSONObject jsonObject = meanJson.getJSONObject(i);
                Mean mean = new Mean();
                mean.setMean(jsonObject.getString("mean"));
                mean.setPart(jsonObject.getJSONObject("part").getString("part"));
                meanList.add(mean);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return meanList;
    }

    /**
     * 解析例句信息
     * @param jsonString
     * @return
     */
    public static List<Sentence> parseSentence(String jsonString){
        List<Sentence> sentenceList = null;
        try{
            JSONArray sentenceJson = new JSONArray(jsonString);
            sentenceList = new ArrayList<Sentence>();
            for(int i = 0;i < sentenceJson.length();i++){
                JSONObject jsonObject = sentenceJson.getJSONObject(i);
                Sentence sentence = new Sentence();
                sentence.setText(jsonObject.getString("text"));
                sentence.setTranslation(jsonObject.getString("translation"));
                sentenceList.add(sentence);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return sentenceList;
    }

    /**
     * 解析 用户计划
     * @param jsonString
     * @return
     */
    public static List<UserPlan> parseUserPlan(String jsonString){
        List<UserPlan> userPlans = null;
        try{
            JSONArray planJson = new JSONArray(jsonString);
            userPlans = new ArrayList<UserPlan>();
            for(int i = 0;i < planJson.length();i++){
                JSONObject jsonObject = planJson.getJSONObject(i);
                UserPlan userPlan = new UserPlan();
                userPlan.setHasDone(jsonObject.getInt("hasDone"));
                userPlan.setIsDoing(jsonObject.getInt("isDoing"));
                userPlan.setPlanId(jsonObject.getInt("planId"));
                userPlan.setUnitId(jsonObject.getInt("unitId"));
                userPlan.setWordId(jsonObject.getInt("wordId"));
                userPlan.setWordNumber(jsonObject.getInt("wordNumber"));
                userPlan.setBook(parseBook(jsonObject.getJSONObject("book")));
                userPlan.setUserId(jsonObject.getInt("userId"));
                userPlans.add(userPlan);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return userPlans;
    }

    /**
     * 解析包含字符串的json数组
     * @param jsonArray
     * @return
     */
    public static List<String> parseString(JSONArray jsonArray){
        List<String> strings = null;
        if(jsonArray != null){
            try {
                strings = new ArrayList<String>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    strings.add(jsonArray.getString(i));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return strings;
    }
    /**
     * 解析教材数组的信息
     */
    public static List<Book> parseBooks(String jsonString){
        List<Book> books = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            if(jsonArray != null && jsonArray.length() > 0){
                books = new ArrayList<Book>();
                for(int i = 0;i < jsonArray.length();i++){
                    books.add(parseBook(jsonArray.getJSONObject(i)));
                }
            }
        }catch (JSONException e){
            Log.d(TAG, "parseBooks: " + e);
        }
        return books;
    }
    /**
     * 解析教材信息
     * @param bookJson
     * @return
     */
    public static Book parseBook(JSONObject bookJson){
        Book book = null;
        try {
            if(bookJson != null){
                book = new Book();
                book.setBookId(bookJson.getInt("bookId"));
                book.setBookName(bookJson.getString("bookName"));
                book.setCoverPicture(bookJson.getString("coverPicture"));
                book.setGrade(parseGrade(bookJson.getJSONObject("grade")));
                book.setPublisher(parsePublisher(bookJson.getJSONObject("publisher")));
                book.setWordNumber(bookJson.getInt("wordNumber"));
                book.setUnits(parseUnits(bookJson.getString("units")));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 解析 年级信息
     * @param jsonObject
     * @return
     */
    public static Grade parseGrade(JSONObject jsonObject){
        Grade grade = null;
        try {
            if(jsonObject != null){
                grade = new Grade();
                grade.setGradeId(jsonObject.getInt("gradeId"));
                grade.setGrandeName(jsonObject.getString("gradeName"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return grade;
    }

    /**
     * 解析 出版商信息
     * @param jsonObject
     * @return
     */
    public static Publisher parsePublisher(JSONObject jsonObject){
        Publisher publisher = null;
        try {
            if(jsonObject != null){
                publisher = new Publisher();
                publisher.setPublisherId(jsonObject.getInt("publisherId"));
                publisher.setPublisherName(jsonObject.getString("publisherName"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return publisher;
    }

    /**
     * 解析教材单元信心
     * @param jsonString
     * @return
     */
    public static List<Unit> parseUnits(String jsonString){
        List<Unit> unitList = null;
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            if(jsonArray != null){
                unitList = new ArrayList<Unit>();
                for(int i = 0;i < jsonArray.length();i++){
                    Unit unit = new Unit();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    unit.setUnitId(jsonObject.getInt("unitId"));
                    unit.setUnitName(jsonObject.getString("unitName"));
                    unit.setBookId(jsonObject.getInt("bookId"));
                    unitList.add(unit);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return unitList;
    }

    public static List<Review> parseReview(String jsonString){
        List<Review> reviews = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            if(jsonArray != null){
                reviews = new ArrayList<Review>();
                for(int i = 0; i < jsonArray.length();i++){
                    Review review = new Review();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    review.setReviewId(jsonObject.getInt("reviewId"));
//                    review.setAddTime(new Date(jsonObject.getString("addTime")));
                    review.setUserId(jsonObject.getInt("userId"));
                    review.setReviewTime(jsonObject.getInt("reviewTime"));
                    review.setWordId(jsonObject.getInt("wordId"));
                    reviews.add(review);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return reviews;
    }

}
