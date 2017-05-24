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

public class FavoriteWordsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    //DataBase
    DataBaseManager dbManager;

    ListView favoriteList;

    ArrayList<Dictionary> favoriteArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_words);

        init();
        setListView();

        favoriteList.setOnItemClickListener(this);
        favoriteList.setOnItemLongClickListener(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Favorite");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListView() {
        if (dbManager.getFavorite().size() == 0){
            favoriteList.setVisibility(View.INVISIBLE);
        }else{
            favoriteList.setVisibility(View.VISIBLE);
            favoriteArrayList = dbManager.getFavorite();
            ArrayAdapter<Dictionary> adapter =
                    new ArrayAdapter<Dictionary>(this, android.R.layout.simple_list_item_1, favoriteArrayList);
            favoriteList.setAdapter(adapter);
        }
    }

    private void init() {
        favoriteArrayList = new ArrayList<>();
        dbManager = new DataBaseManager(this);
        favoriteList = (ListView) findViewById(R.id.favoriteList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Dictionary currentWord = favoriteArrayList.get(position);

        Intent intent = new Intent(FavoriteWordsActivity.this, TranslatedWord.class);
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
                        dbManager.deleteFavorite(favoriteArrayList.get(position));
                        setListView();
                        Toast.makeText(FavoriteWordsActivity.this, "Item has been removed from the List", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null);

        builder.show();
        return true;
    }
}
