package com.codecool.chessopen;

import java.io.*;
import java.util.*;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<String> list = new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        HashMap<String, Integer> competitorMap = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            String str = "";
            String name = "";
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                String[] st = str.split(",");
                for (int i = 0; i < st.length; i++) {
                    if (i == 0) {
                        name = st[i];
                    } else {
                        results.add(Integer.parseInt(st[i])*100);
                    }
                }
                int avg = 0;
                for (Integer result : results) {
                    avg = avg + result;
                }
                avg = avg / results.size();
                competitorMap.put(name, avg);
                results.clear();
            }
            competitorMap = sortByValue(competitorMap);
            Set<String> set = competitorMap.keySet();
            for (int i = 0; i < set.size(); i++) {
                list = List.copyOf(set);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return list;
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hm.entrySet());

        list.sort((o1, o2) -> (o1.getValue()).compareTo(o2.getValue()) * -1);

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
