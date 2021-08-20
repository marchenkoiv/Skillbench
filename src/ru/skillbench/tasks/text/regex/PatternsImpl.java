package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsImpl implements Patterns{

    public Pattern getSQLIdentifierPattern() {
        Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]{0,29}");
        return p;
    }

    public Pattern getEmailPattern() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.-]{0,20}[a-zA-Z0-9]@([a-zA-Z0-9][a-zA-Z0-9_]*[a-zA-Z0-9].)+(ru|com|net|org)");
        return p;
    }

    public Pattern getHrefTagPattern() {
        Pattern p = Pattern.compile("(?i)<(\\s)*a(\\s)+href(\\s)*=(\\s)*((\"(.*)\")|([^\" ]*))(\\s)*(\\\\)?>");
        return p;
    }

    public List<String> findAll(String input, Pattern pattern) {
        Matcher m = pattern.matcher(input);
        List<String> list = new ArrayList<>();
        while (m.find()){
            list.add(input.substring(m.start(),m.end()));
        }
        return list;
    }

    public int countMatches(String input, String regex) {
        regex = "(?i)" + regex;
        List<String> l = this.findAll(input, Pattern.compile(regex));
        return l.size();
    }

    public static void main(String[] args) {
        Patterns p = new PatternsImpl();
        System.out.println(p.findAll("<a   href=\"www.google.com\">", p.getHrefTagPattern()));
        System.out.println(p.countMatches("tyv ufc r45 3gf", "[a-zA-Z][a-zA-Z0-9_]{0,29}"));
    }
}
