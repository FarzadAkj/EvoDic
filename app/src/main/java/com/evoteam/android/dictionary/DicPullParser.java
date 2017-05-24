package com.evoteam.android.dictionary;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DicPullParser {

    MainActivity main = new MainActivity();

    //
    private static final String TAG_STARTED      = "word";
    private static final String TAG_WORD         = "name";
    private static final String TAG_TRANSLATE    = "translate";
    private static final String TAG_PICTURE      = "picture";
    //
    private InputStream inputStream;
    private String currentTag = null;
    private Dictionary currentWord;
    ArrayList<Dictionary> dictionary;

    public ArrayList<Dictionary> parseXml(Context context, String text){
        dictionary= new ArrayList<Dictionary>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            XmlPullParser xpp = factory.newPullParser();

            //deciding which source to parse

            if(MainActivity.translateTo[0] == "iran"){
                if(MainActivity.translateFrom[0] == "england"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }else if(MainActivity.translateFrom[0] == "iraq"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }else if(MainActivity.translateFrom[0] == "iran"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }
            }else if(MainActivity.translateTo[0] == "england"){
                if(MainActivity.translateFrom[0] == "england"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }else if(MainActivity.translateFrom[0] == "iraq"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }else if(MainActivity.translateFrom[0] == "iran"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }
            }else if(MainActivity.translateTo[0] == "iraq"){
                if(MainActivity.translateFrom[0] == "england"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }else if(MainActivity.translateFrom[0] == "iraq"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }else if(MainActivity.translateFrom[0] == "iran"){
                    inputStream = context.getResources().openRawResource(R.raw.dic);
                }
            }
            xpp.setInput(inputStream, null);

            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                if (eventType == XmlPullParser.START_TAG){
                    handleStartTag(xpp.getName());
                }else if (eventType == XmlPullParser.TEXT) {
                    handleTextTag(xpp.getText(), text);
                }else if (eventType == XmlPullParser.END_TAG){
                    currentTag = null;
                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    private void handleStartTag(String name) {
        if(name.equals("word")){
            currentTag = TAG_STARTED;
            currentWord = new Dictionary();
        }else if(name.equals("name")){
            currentTag = TAG_WORD;
        }else if(name.equals("translate")){
            currentTag = TAG_TRANSLATE;
        }else if(name.equals("picture")){
            currentTag = TAG_PICTURE;
        }
    }

    private void handleTextTag(String name, String text) {
        String xmlText = name;
        if(currentWord != null && currentTag != null){
            if(currentTag == TAG_WORD) {
                if (xmlText.startsWith(text)) {
                    dictionary.add(currentWord);
                    currentWord.setWord(xmlText);
                }
            }else if(currentTag == TAG_TRANSLATE && dictionary.contains(currentWord)){
                currentWord.setTranslate(xmlText);
            }else if(currentTag == TAG_PICTURE && dictionary.contains(currentWord)){
                currentWord.setPicture(xmlText);
            }
        }
    }
}
