package com.simulation.bdc.util;

import android.util.Log;

import com.simulation.bdc.enitity.DailySentence;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {

    private static final String TAG = "ParseJson";

    /**
     * 解析java服务端传过来的结果
     *
     * @param responseData
     * @return
     */
    public static int parseResult(String responseData) {
        int result = 0;
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            result = jsonObject.getInt("result");
        } catch (JSONException e) {
            Log.e(TAG, "parseResult: 解析json数据错误", e);
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

    public static List<Word> parseWord(String jsonString) {
        List<Word> wordList = null;
        try {
            JSONArray wordsJson = new JSONArray(jsonString);
            wordList = new ArrayList<Word>();
            for(int i = 0;i < wordsJson.length();i++){
                JSONObject jsonObject = wordsJson.getJSONObject(i);
                Word word = new Word();
                word.setMeans(parseMean(jsonObject.getString("means")));
                word.setWordId(jsonObject.getInt("wordId"));
                word.setPhUk(jsonObject.getString("phUk"));
                word.setPhUsa(jsonObject.getString("phUsa"));
                word.setProUk(jsonObject.getString("proUk"));
                word.setProUsa(jsonObject.getString("proUsa"));
                word.setSentence(parseSentence(jsonObject.getString("sentences")));
                word.setWordName(jsonObject.getString("wordName"));
                wordList.add(word);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wordList;
    }

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

}
