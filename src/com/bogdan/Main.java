package com.bogdan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String url = "urls.txt";
        urlReader(url);
        final Map<String, Integer> urlResult = sortByValue(map);

        //Output top 10 domain
        int i = 1;
        for (String key : urlResult.keySet()) {
            System.out.println(i + ". " + key + " = " + urlResult.get(key));
            i++;
        }
    }

    //Method for correct read the file without spaces
    private static void urlReader(String url) throws Exception {
        FileReader fileReader = new FileReader(url);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String domain;

        while((domain = bufferedReader.readLine()) != null) {

            domain = domain.replaceAll(" ", "").trim();

            if (domain.contains("/")) {
                domain = domain.split("/")[0];
            }
            inMap(map, domain);
        }
        fileReader.close();
    }

    //Method for input domain in map with value
    private static void inMap(Map<String, Integer> map, String str) {
        if (!map.containsKey(str)) {
            map.put(str, 1);
        } else {
            map.put(str, map.get(str) + 1);
        }
    }

    //Method for output top 10 domain
    private static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}