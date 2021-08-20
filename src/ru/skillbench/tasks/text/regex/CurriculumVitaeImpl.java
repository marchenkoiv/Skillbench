package ru.skillbench.tasks.text.regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImpl implements CurriculumVitae{
    private String text;
    private HashMap<Integer, String> hiddenStr = new HashMap<>();
    
    public void setText(String text){
        this.hiddenStr.clear();
        this.text = text;
    }
    
    public String getText(){
        if (text == null){
            throw new IllegalStateException("Illegal State Exception");
        }
        return text;
    }
    
    public List<Phone> getPhones(){
        String text = getText();
        List<Phone> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(text);        
        while (matcher.find()){
            int a = -1, b = -1;
            if (matcher.group(2) != null){
                a = Integer.parseInt(matcher.group(2));
            }
            if (matcher.group(7) != null){
                b = Integer.parseInt(matcher.group(7));
            }
            Phone currentPhone = new Phone(matcher.group(0), a, b);
            list.add(currentPhone);
        }
        return list;
    }

    public String getFullName(){
        String text = getText();
        String fullName = null;
        Pattern pattern = Pattern.compile("[A-Z][a-z]*[a-z.] [A-Z][a-z]*[a-z.]( [A-Z][a-z]*[a-z.])?");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            fullName = text.substring(matcher.start(), matcher.end());
        }
        if (fullName == null){
            throw new NoSuchElementException("No Such Element Exception");
        }
        return fullName;
    }

    public String getFirstName(){
        String fullName = getFullName();
        return fullName.substring(0, fullName.indexOf(' '));
    }

    public String getMiddleName(){
        String fullName = getFullName();
        String middleName;
        if (fullName.indexOf(' ') == fullName.lastIndexOf(' ')){
            middleName = null;
        }
        else {
            middleName = fullName.substring(fullName.indexOf(' ')+1, fullName.lastIndexOf(' '));
        }
        return middleName;
    }

    public String getLastName(){
        String fullName = getFullName();
        return fullName.substring(fullName.lastIndexOf(' ')+1);
    }

    public void updateLastName(String newLastName){
        String oldLastName = getLastName();
        text = text.replaceFirst(oldLastName, newLastName);
    }

    public void updatePhone(Phone oldPhone, Phone newPhone){
        String oldStrPhone = oldPhone.getNumber();
        String newStrPhone = newPhone.getNumber();
        getText();
        if (!text.contains(oldStrPhone)){
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        text = text.replace(oldStrPhone, newStrPhone);
    }

    public void hide(String piece){
        getText();
        if (!text.contains(piece)){
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        hiddenStr.put(text.indexOf(piece), piece);
        String newPiece = piece.replaceAll("[^ .@]", "X");
        text = text.replace(piece, newPiece);
    }

    public void hidePhone(String phone){
        this.getText();
        if (!text.contains(phone)){
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        hiddenStr.put(text.indexOf(phone), phone);
        String newPiece = phone.replaceAll("[0-9]", "X");
        text = text.replace(phone, newPiece);
    }

    public int unhideAll(){
        getText();
        int i = 0;
        //int beginSec;
        Iterator<Map.Entry<Integer, String>> entries = hiddenStr.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, String> entry = entries.next();
            text = text.substring(0, entry.getKey()) + entry.getValue() + text.substring(entry.getKey()+entry.getValue().length());
            i++;
        }
        hiddenStr.clear();
        return i;
    }

    public static void main(String[] args) {
        CurriculumVitae object = new CurriculumVitaeImpl();
        //object.getText();
        object.setText("u Iyy Po Tu. hgfjk. @ (916)125-4171, 495 926-93-47 ext.1846, 800 250 0890    Tu. hgfjk. @");
        System.out.println(object.getText());
        System.out.println(object.getFullName());
        System.out.println(object.getFirstName());
        System.out.println(object.getMiddleName());
        System.out.println(object.getLastName());
        System.out.println(object.getPhones());
        System.out.println(Math.sqrt(-1));
        //Phone ph1 = new Phone("495 926-93-47 ext.1846");
        //Phone ph2 = new Phone("(916)125-4171");
        //object.updatePhone(ph1, ph2);
        System.out.println(object.getText());
        object.hide("Tu. hgfjk. @");
        object.hide("0890");
        System.out.println(object.getText());
        System.out.println(object.unhideAll());
        System.out.println(object.getText());
        object.hidePhone("(916)125-4171");
        System.out.println(object.getText());
    }
}
