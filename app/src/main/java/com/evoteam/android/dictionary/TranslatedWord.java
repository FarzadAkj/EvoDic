package com.evoteam.android.dictionary;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class TranslatedWord extends AppCompatActivity{

    //DataBase
    DataBaseManager dbManager;

    TextView word;
    TextView translate;
    ImageView imageView;

    static Dictionary currentWord;



    private static String wordName       = null;
    private static String wordTranslate  = null;
    private static String image          = null;
    private static int resId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translated_word);

        init();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if(extras.containsKey("word")){
                wordName = extras.getString("word");
            }if(extras.containsKey("translate")){
                wordTranslate = extras.getString("translate");
            }if(extras.containsKey("picture")){
                image = extras.getString("picture");
                String imageFile = image.substring(0, image.indexOf('.'));
                resId = getResources().getIdentifier(
                        getApplicationContext().getPackageName() + ":drawable/" + imageFile, null, null);
            }
        }
        currentWord = new Dictionary(wordName, wordTranslate, image);


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        finishingTheTranslation();
    }

    private void init() {
        dbManager = new DataBaseManager(this);
        word = (TextView) findViewById(R.id.word);
        translate = (TextView) findViewById(R.id.translate);
        imageView = (ImageView) findViewById(R.id.imageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addtofavorite, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*
        got a problem here -__-
         */

        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }else{
            //Managing the dataBase
            int a = dbManager.getFavorite().size();
            if(a == 75){
                Dictionary deletingWord = DataBaseManager.favoriteList.get(74);
                dbManager.deleteFavorite(deletingWord);
            }
            dbManager.addFavorite(currentWord);
        }

        return super.onOptionsItemSelected(item);
    }

    private void finishingTheTranslation() {
        word.setText(wordName);
        translate.setText(wordTranslate);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }


}
