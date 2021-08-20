package ru.skillbench.tasks.javaapi.collections;

import java.util.*;

public class StringFilterImpl implements StringFilter {
    private HashSet<String> set;

    public StringFilterImpl(){
        set = new HashSet();
    }

    public void add(String s) {
        if (s != null){
            s = s.toLowerCase();
        }
        set.add(s);
    }

    public boolean remove(String s) {
        if (s != null){
            s = s.toLowerCase();
        }
        return set.remove(s);
    }

    public void removeAll() {
        set.removeAll(set);
    }

    public Collection<String> getCollection() {
        return set;
    }

    public Iterator<String> getStringsContaining(String chars) {
        if (chars == null || chars.equals("")){
            return set.iterator();
        }
        HashSet <String> subSet = new HashSet();
        for (String s : set) {
            if (s!=null && s.contains(chars.toLowerCase())) {
                subSet.add(s);
            }
        }
        return subSet.iterator();
    }

    public Iterator<String> getStringsStartingWith(String begin) {
        if (begin == null || begin.equals("")){
            return set.iterator();
        }
        HashSet <String> subSet = new HashSet();
        for (String s : set) {
            if (s!=null && s.startsWith(begin.toLowerCase())) {
                subSet.add(s);
            }
        }
        return subSet.iterator();
    }

    public Iterator<String> getStringsByNumberFormat(String format) {
        if (format == null || format.equals("")){
            return set.iterator();
        }
        HashSet <String> subSet = new HashSet();
        for (String s : set) {
            String formS = s.replaceAll("[0-9]", "#");
            if (formS.equals(format)) {
                subSet.add(s);
            }
        }
        return subSet.iterator();
    }

    public Iterator<String> getStringsByPattern(String pattern) {
        if (pattern == null || pattern.equals("")){
            return set.iterator();
        }
        pattern = pattern.replace("*", "(.*)");
        HashSet <String> subSet = new HashSet();
        for (String s : set) {
            if (s!=null && s.matches(pattern.toLowerCase())) {
                subSet.add(s);
            }
        }
        return subSet.iterator();
    }

    public static void main(String[] args) {
        StringFilter s = new StringFilterImpl();
        s.add(null);
        s.add(null);
        s.add("Sum");
        s.add("Int");
        s.add("i25");
        s.add("i25");
        s.getCollection();
        Iterator<String> it = s.getStringsContaining("ad");
        s.getStringsStartingWith("i");
        s.getStringsByNumberFormat("i##");
        s.getStringsByPattern("*i*");
        s.removeAll();
    }
}
