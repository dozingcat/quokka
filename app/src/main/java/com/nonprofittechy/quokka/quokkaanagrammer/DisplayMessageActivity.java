package com.nonprofittechy.quokka.quokkaanagrammer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        boolean hasBlank = intent.getBooleanExtra(MainActivity.EXTRA_BLANK, false);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);

//        Permutation permutation = new Permutation();
//        int n = message.length();

/*        HashSet<String> results = this.getAnagrams(message);
*/
        // HashSet<String> results = ["A","B"]; // FIXME
        // HashSet<String> results = permutation.permute(message, 0, n-1, new HashSet<String>());
        WordList wordList = MainActivity.wordList;
        Set<String> anagrams;
        if (hasBlank) {
            anagrams = new HashSet<String>();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                anagrams.addAll(wordList.getAnagrams(message + ch));
            }
        }
        else {
            anagrams = wordList.getAnagrams(message);
        }

        List<String> sortedAnagrams = new ArrayList<String>();
        sortedAnagrams.addAll(anagrams);
        Collections.sort(sortedAnagrams);

        StringBuilder sb = new StringBuilder();

        for (String s: sortedAnagrams) {
            sb.append(s);
            sb.append("\r\n");
        }

        textView.setText(sb.toString());
        textView.setMovementMethod(new ScrollingMovementMethod());

    }
/*
    public HashSet<String> getAnagrams(String word) {
        char[] charArray;
        charArray = word.toCharArray();

        HashSet<String> results = new HashSet<String>();

        return _makeAnagrams("", word, results);

    }
*/
/*
    private HashSet<String> _makeAnagrams(String perm, String word, HashSet<String> res) {
        if (word.isEmpty()) {
            res.add(perm + word);
        } else {
            for (int i = 0; i < word.length(); i++) {
                _makeAnagrams(perm + word.charAt(i), word.substring(0, i)
                        + word.substring(i + 1, word.length()), res);
            }
        }

        return res;
    }
*/

/*    public HashSet<String> _makeAnagrams(String str1,String str2){
        if(str2.length() > 1){
            char ch = str2.charAt(0);
            for(int i = 0; i <= str1.length();i++)
                permut(str1.substring(0,i) + ch + str1.substring(i,str1.length()),
                        str2.substring(1,str2.length()));
        }else{
            char ch = str2.charAt(0);
            for(int i = 0; i <= str1.length();i++)
                System.out.println(str1.substring(0,i) + ch +    str1.substring(i,str1.length()),
                        str2.substring(1,str2.length()));
        }
    }*/
}
