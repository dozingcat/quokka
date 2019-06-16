package com.nonprofittechy.quokka.quokkaanagrammer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_BLANK = "has_blank";

    public static WordList wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            wordList = new WordList(getAssets().open("words"));
            Toast.makeText(this, "Read " + wordList.numberOfUniqueWords() + " words", Toast.LENGTH_LONG).show();
        }
        catch (IOException ex) {
            Toast.makeText(this, "Error reading words: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_BLANK, ((CheckBox)findViewById(R.id.blankCheckbox)).isChecked());
        startActivity(intent);
    }
}
