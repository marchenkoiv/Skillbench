package ru.skillbench.tasks.text;

import java.io.PrintStream;
import java.util.*;

public class WordCounterImpl implements WordCounter{
    private String text;

    public WordCounterImpl(){ text = null;}

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Map<String, Long> getWordCounts() {
        if (text == null){
            throw new IllegalStateException("Illegal State Exception");
        }
        Map<String, Long> map = new HashMap<>();
        String countableText = text.toLowerCase();
        for (String t: countableText.split("\\s+")){
            if(!(t.startsWith("<") && t.endsWith(">") || t.equals("[]"))){
                Long pt = map.get(t);
                if (pt == null){
                    map.put(t, 1L);
                } else{
                    map.put(t, pt + 1);
                }
            }
        }
        return map;
    }

    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        Map<String, Long> map = new TreeMap<String, Long>(this.getWordCounts());
        List<Map.Entry<String, Long>> list = sort(map, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                int a = o2.getValue().compareTo(o1.getValue());
                if (a == 0){
                    a = o1.getKey().compareTo(o2.getKey());
                }
                return a;
            }
        });
        return list;
    }

    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        List<Map.Entry<K, V>> list = new ArrayList(map.entrySet());
        Collections.sort(list, comparator);
        return list;
    }


    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        for(Map.Entry e: entries){
            String s = (String) e.getKey();
            ps.println(s.toLowerCase() + " " + e.getValue());
        }
    }

    public static void main(String[] args){
        WordCounter w = new WordCounterImpl();
        w.setText("Sjgjh        sjgjh kyt kyt   kyt a a  <a> \n \n<[]>");
        System.out.println(w.getWordCountsSorted());
        w.print(w.getWordCountsSorted(), System.out);
    }
}
