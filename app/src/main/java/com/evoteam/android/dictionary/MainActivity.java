package com.evoteam.android.dictionary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //DataBase
    public DataBaseManager dbManager;

    Button toIran;
    Button toEngland;
    Button toIraq;
    Button fromIran;
    Button fromEngland;
    Button fromIraq;
    EditText editText;
    TextView fromTextView;
    TextView toTextView;
    ArrayList<Dictionary> recommendedWords;
    DicPullParser dpp;
    ListView recommendedList;
    public static String[] translateTo;
    public static String[] translateFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();     //initializing the views
        clicks();   //handling the clicks

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("EvoDic  B-)");
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text = s.toString().toLowerCase();
                setList(text);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString().toLowerCase();
                setList(text);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().toLowerCase();
                setList(text);
            }

        });

    }

    private void clicks(){
        fromEngland.setOnClickListener(this);
        fromIran.setOnClickListener(this);
        fromIraq.setOnClickListener(this);
        toIran.setOnClickListener(this);
        toIraq.setOnClickListener(this);
        toEngland.setOnClickListener(this);
        toTextView.setOnClickListener(this);

        recommendedList.setOnItemClickListener(this);
    }

    private void init() {   //initializing views in here

        //Injo male mostafa
        dbManager = new DataBaseManager(this);

        toEngland = (Button) findViewById(R.id.toengland);
        toIran = (Button) findViewById(R.id.toiran);
        toIraq = (Button) findViewById(R.id.toiraq);
        fromIraq = (Button) findViewById(R.id.fromiraq);
        fromIran = (Button) findViewById(R.id.fromiran);
        fromEngland = (Button) findViewById(R.id.fromengland);
        editText = (EditText) findViewById(R.id.editText);
        toTextView = (TextView) findViewById(R.id.toTextView);
        fromTextView = (TextView) findViewById(R.id.fromTextView);
        recommendedList = (ListView) findViewById(R.id.recommendedList);

        recommendedWords = new ArrayList<Dictionary>();
        translateFrom = new String[]{""};
        translateTo = new String[]{""};

        dpp = new DicPullParser();

    }

    @Override
    //animations and controlling the clicks
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.toiran:
                if (translateTo[0] == ""){
                    Toast.makeText(this, R.string.persianChecked, Toast.LENGTH_SHORT).show();
                    //v.animate().alpha(1f).setDuration(800);
                    v.animate().rotationYBy(20f).setDuration(500);
                    toEngland.animate().alpha(0.2f).setDuration(500);
                    toIraq.animate().alpha(0.2f).setDuration(500);
                    translateTo[0] = "iran";

                }else if(translateTo[0] != "iran"){
                    Toast.makeText(this, R.string.persianChecked, Toast.LENGTH_SHORT).show();
                    v.animate().alpha(1f).setDuration(800);
                    toEngland.animate().alpha(0.2f).setDuration(500);
                    toIraq.animate().alpha(0.2f).setDuration(500);
                    v.animate().rotationYBy(20f).setDuration(500);


                    if (translateTo[0] == "england"){
                        toEngland.animate().rotationYBy(-20f).setDuration(500);
                    }else if (translateTo[0] == "iraq"){
                        toIraq.animate().rotationYBy(-20f).setDuration(500);
                    }

                    translateTo[0] = "iran";
                }

                break;

            case R.id.toengland:

                if (translateTo[0] == ""){
                    Toast.makeText(this, R.string.englishChecked, Toast.LENGTH_SHORT).show();
                    v.animate().alpha(1f).setDuration(800);
                    v.animate().rotationYBy(20f).setDuration(500);
                    toIran.animate().alpha(0.2f).setDuration(500);
                    toIraq.animate().alpha(0.2f).setDuration(500);
                    translateTo[0] = "england";

                }else if(translateTo[0] != "england"){
                    Toast.makeText(this, R.string.englishChecked, Toast.LENGTH_SHORT).show();
                    v.animate().alpha(1f).setDuration(800);
                    toIran.animate().alpha(0.2f).setDuration(500);
                    toIraq.animate().alpha(0.2f).setDuration(500);
                    v.animate().rotationYBy(20f).setDuration(500);


                    if (translateTo[0] == "iran"){
                        toIran.animate().rotationYBy(-20f).setDuration(500);
                    }else if (translateTo[0] == "iraq"){
                        toIraq.animate().rotationYBy(-20f).setDuration(500);
                    }

                    translateTo[0] = "england";
                }

                break;

            case R.id.toiraq:
                if (translateTo[0] == ""){
                    Toast.makeText(this, R.string.arabicChecked, Toast.LENGTH_SHORT).show();
                    v.animate().alpha(1f).setDuration(800);
                    v.animate().rotationYBy(20f).setDuration(500);
                    toEngland.animate().alpha(0.2f).setDuration(500);
                    toIran.animate().alpha(0.2f).setDuration(500);
                    translateTo[0] = "iraq";

                }else if(translateTo[0] != "iraq"){
                    Toast.makeText(this, R.string.arabicChecked, Toast.LENGTH_SHORT).show();
                    v.animate().alpha(1f).setDuration(800);
                    toEngland.animate().alpha(0.2f).setDuration(500);
                    toIran.animate().alpha(0.2f).setDuration(500);
                    v.animate().rotationYBy(20f).setDuration(500);


                    if (translateTo[0] == "iran"){
                        toIran.animate().rotationYBy(-20f).setDuration(500);
                    }else if (translateTo[0] == "england"){
                        toEngland.animate().rotationYBy(-20f).setDuration(500);
                    }

                    translateTo[0] = "iraq";
                }else{
                    return;
                }

                break;

            case R.id.toTextView:
                switch (translateTo[0]){
                    case "":
                        translateTo[0] = "";
                        toIran.animate().alpha(1f).setDuration(500);
                        toEngland.animate().alpha(1f).setDuration(500);
                        toIraq.animate().alpha(1f).setDuration(500);
                        break;
                    case "iran":
                        translateTo[0] = "";
                        toIran.animate().alpha(1f).setDuration(500);
                        toEngland.animate().alpha(1f).setDuration(500);
                        toIraq.animate().alpha(1f).setDuration(500);
                        toIran.animate().rotationYBy(-20f).setDuration(500);
                        Toast.makeText(this, R.string.pickPersian, Toast.LENGTH_SHORT).show();
                        break;
                    case "iraq":
                        translateTo[0] = "";
                        toIran.animate().alpha(1f).setDuration(500);
                        toEngland.animate().alpha(1f).setDuration(500);
                        toIraq.animate().alpha(1f).setDuration(500);
                        toIraq.animate().rotationYBy(-20f).setDuration(500);
                        Toast.makeText(this, R.string.pickَArabic, Toast.LENGTH_SHORT).show();
                        break;
                    case "england":
                        translateTo[0] = "";
                        toIran.animate().alpha(1f).setDuration(500);
                        toEngland.animate().alpha(1f).setDuration(500);
                        toIraq.animate().alpha(1f).setDuration(500);
                        toEngland.animate().rotationYBy(-20f).setDuration(500);
                        Toast.makeText(this, R.string.pickEnglish, Toast.LENGTH_SHORT).show();
                        break;
                }

                break;

            case R.id.fromiran:
                if (translateFrom[0] == ""){
                    v.animate().rotationYBy(20f).setDuration(500);
                    fromEngland.animate().alpha(0.2f).setDuration(500);
                    fromIraq.animate().alpha(0.2f).setDuration(500);
                    translateFrom[0] = "iran";
                    editText.setHint(R.string.persianPicked);
                    editText.setGravity(View.FOCUS_RIGHT);

                }else if(translateFrom[0] != "iran"){
                    v.animate().alpha(1f).setDuration(800);
                    fromEngland.animate().alpha(0.2f).setDuration(500);
                    fromIraq.animate().alpha(0.2f).setDuration(500);
                    v.animate().rotationYBy(20f).setDuration(500);
                    editText.setHint(R.string.persianPicked);
                    editText.setGravity(View.FOCUS_RIGHT);

                    if (translateFrom[0] == "england"){
                        fromEngland.animate().rotationYBy(-20f).setDuration(500);
                    }else if (translateFrom[0] == "iraq"){
                        fromIraq.animate().rotationYBy(-20f).setDuration(500);
                    }

                    translateFrom[0] = "iran";
                }

                break;

            case R.id.fromengland:

                if (translateFrom[0] == ""){
                    v.animate().alpha(1f).setDuration(800);
                    v.animate().rotationYBy(20f).setDuration(500);
                    fromIran.animate().alpha(0.2f).setDuration(500);
                    fromIraq.animate().alpha(0.2f).setDuration(500);
                    translateFrom[0] = "england";
                    editText.setHint(R.string.englishPicked);
                    editText.setGravity(View.SCROLL_INDICATOR_LEFT);

                }else if(translateFrom[0] != "england"){
                    v.animate().alpha(1f).setDuration(800);
                    fromIran.animate().alpha(0.2f).setDuration(500);
                    fromIraq.animate().alpha(0.2f).setDuration(500);
                    v.animate().rotationYBy(20f).setDuration(500);
                    editText.setHint(R.string.englishPicked);
                    editText.setGravity(View.SCROLL_INDICATOR_LEFT);

                    if (translateFrom[0] == "iran"){
                        fromIran.animate().rotationYBy(-20f).setDuration(500);
                    }else if (translateFrom[0] == "iraq"){
                        fromIraq.animate().rotationYBy(-20f).setDuration(500);
                    }

                    translateFrom[0] = "england";
                }

                break;

            case R.id.fromiraq:
                if (translateFrom[0] == ""){
                    v.animate().alpha(1f).setDuration(800);
                    v.animate().rotationYBy(20f).setDuration(500);
                    fromEngland.animate().alpha(0.2f).setDuration(500);
                    fromIran.animate().alpha(0.2f).setDuration(500);
                    translateFrom[0] = "iraq";
                    editText.setHint(R.string.arabicPicked);
                    editText.setGravity(View.FOCUS_RIGHT);

                }else if(translateFrom[0] != "iraq"){
                    v.animate().alpha(1f).setDuration(800);
                    fromEngland.animate().alpha(0.2f).setDuration(500);
                    fromIran.animate().alpha(0.2f).setDuration(500);
                    v.animate().rotationYBy(20f).setDuration(500);
                    editText.setHint(R.string.arabicPicked);
                    editText.setGravity(View.FOCUS_RIGHT);

                    if (translateFrom[0] == "iran"){
                        fromIran.animate().rotationYBy(-20f).setDuration(500);
                    }else if (translateFrom[0] == "england"){
                        fromEngland.animate().rotationYBy(-20f).setDuration(500);
                    }

                    translateFrom[0] = "iraq";
                }else{
                    return;
                }

                break;


            default:
                break;
        }


    }

    public void setList(String text){
        if(text.isEmpty()) {
            invisibleTheList();
        }else if(MainActivity.translateFrom[0] == ""
                || MainActivity.translateTo[0] == ""){
            invisibleTheList();
        }else{
            recommendedList.setVisibility(View.VISIBLE);
            recommendedWords = dpp.parseXml(this, text);
            ArrayAdapter<Dictionary> adapter =
                    new ArrayAdapter<Dictionary>(this, android.R.layout.simple_list_item_1, recommendedWords);
            recommendedList.setAdapter(adapter);
        }
    }

    private void invisibleTheList() {
        recommendedList.setVisibility(View.INVISIBLE);
    }

    //Clicking on list items
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProgressDialog pdialog = new ProgressDialog(this);
        pdialog.show();

        Dictionary currentWord = recommendedWords.get(position);

        addToDataBase(currentWord);

        Intent intent = new Intent(this, TranslatedWord.class);
        intent.putExtra("word", currentWord.getWord());
        intent.putExtra("translate", currentWord.getTranslate());
        intent.putExtra("picture", currentWord.getPicture());

        startActivity(intent);
        pdialog.dismiss();
    }

    private void addToDataBase(Dictionary currentWord) {

        int a = dbManager.getHistory().size();
        //controlling the length of
        //if(a == 50){
            //delete from dataBase

        //}
        dbManager.addHistory(currentWord);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ProgressDialog pdialog = new ProgressDialog(this);
        pdialog.show();

        switch (item.getItemId()) {
            case (R.id.history):
                startActivity(new Intent(MainActivity.this, CheckedWordsActivity.class));
                break;
            case (R.id.favoritewords):
                startActivity(new Intent(MainActivity.this, FavoriteWordsActivity.class));
                break;
        }
        pdialog.cancel();
        return super.onOptionsItemSelected(item);
    }
}
