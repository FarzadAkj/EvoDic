package com.evoteam.android.dictionary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckedWordsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    //DataBase
    DataBaseManager dbManager;

    //list of the words which the user has checked
    ListView checkedWordsListView;

    ArrayList<Dictionary> historyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_words);

        init();
        setCheckedList();
        checkedWordsListView.setOnItemClickListener(this);
        checkedWordsListView.setOnItemLongClickListener(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("History");
        }
    }

    private void init() {
        checkedWordsListView = (ListView) findViewById(R.id.checkedWordsListView);
        dbManager = new DataBaseManager(this);
        historyArrayList = new ArrayList<>();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCheckedList() {
        historyArrayList = dbManager.getHistory();
        ArrayAdapter<Dictionary> adapter =
                new ArrayAdapter<Dictionary>(this, android.R.layout.simple_list_item_1, historyArrayList);

        if (historyArrayList.size() == 0){
            checkedWordsListView.setVisibility(View.INVISIBLE);
        }else{
            checkedWordsListView.setVisibility(View.VISIBLE);
            checkedWordsListView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Dictionary currentWord = historyArrayList.get(position);

        Intent intent = new Intent(CheckedWordsActivity.this, TranslatedWord.class);
        intent.putExtra("word", currentWord.getWord());
        intent.putExtra("translate", currentWord.getTranslate());
        intent.putExtra("picture", currentWord.getPicture());

        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder .setTitle("Delete Dialog")
                .setMessage("Do you want to delete this word from the list?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.deleteHistory(historyArrayList.get(position));
                        setCheckedList();
                        Toast.makeText(CheckedWordsActivity.this, "Item has been removed from the List", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null);

        builder.show();
        return true;
    }
}
