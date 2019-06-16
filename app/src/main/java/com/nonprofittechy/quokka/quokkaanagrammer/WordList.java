package com.nonprofittechy.quokka.quokkaanagrammer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordList {
    private final Map<String, Set<String>> anagrams;
    private final long numUniqueWords;

    public WordList(InputStream input) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        Map<String, Set<String>> amap = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            String word = line.trim().toUpperCase();
            String sortedChars = sortChars(word);
            if (!amap.containsKey(sortedChars)) {
                amap.put(sortedChars, new HashSet<String>());
            }
            amap.get(sortedChars).add(word);
        }
        long nwords = 0;
        for (String key : amap.keySet()) {
            nwords += amap.get(key).size();
        }
        this.anagrams = amap;
        this.numUniqueWords = nwords;
    }

    public Set<String> getAnagrams(String s) {
        String sortedChars = sortChars(s.toUpperCase());
        Set<String> result = this.anagrams.get(sortedChars);
        if (result != null) {
            return result;
        }
        return Collections.emptySet();
    }

    public long numberOfUniqueWords() {
        return numUniqueWords;
    }

    private static String sortChars(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
