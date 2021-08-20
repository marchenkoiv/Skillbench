package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

import static java.lang.Math.abs;

public class ComplexNumberImpl implements ComplexNumber{
    private double re = 0;
    private double im = 0;

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public boolean isReal() {
        return im == 0;
    }

    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public void set(String value) throws NumberFormatException {
        String[] s = value.split("[+-]");
        if(value.indexOf("-")==0){
            s[1] = "-"+s[1];
        }
        if(value.lastIndexOf("-")!=0 && value.lastIndexOf("-")!=-1){
            if(s.length == 3) {
                s[2] = "-" + s[2];
            } else {s[1] = "-" + s[1];}
        }
        if(s[0]==""){
            String[]s1 = new String[s.length-1];
            s1[0] = s[1];
            if(s.length == 3){
                s1[1] = s[2];
            }
            s = s1;
        }
        if (s.length == 2 && value.endsWith("+i")){
            re = Double.parseDouble(s[0]);
            im = 1;
        }else if (s.length == 2 && value.endsWith("-i")){
            re = Double.parseDouble(s[0]);
            im = -1;
        }else if (s.length == 1 && value.endsWith("i")){
            if(s[0].equals("i")){
                im = 1;
                re = 0;
            }else if(s[0].equals("-i")){
                im = -1;
                re = 0;
            }else{
            im = Double.parseDouble(s[0].substring(0, s[0].length()-1));
            re = 0;}
        }else if (s.length == 1){
            re = Double.parseDouble(s[0]);
            im = 0;
        }else if(s.length == 2 && value.endsWith("i")){
            re = Double.parseDouble(s[0]);
            im = Double.parseDouble(s[1].substring(0, s[1].length()-1));
        } else{throw new NumberFormatException("Format");}

    }

    @Override
    public ComplexNumber copy() {
        ComplexNumber cn = new ComplexNumberImpl();
        cn.set(re, im);
        return cn;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        ComplexNumber cn = (ComplexNumber) super.clone();
        cn.set(re, im);
        return cn;
    }

    @Override
    public String toString(){
        if(im == 0){
            return Double.toString(re);
        }
        if(re == 0){
            return im+"i";
        }
        if(im > 0){
            return re+"+"+im+"i";
        }
        return re+"-"+abs(im)+"i";
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(other == null || this.getClass().getInterfaces()[0] != other.getClass().getInterfaces()[0]){
            return false;
        }
        ComplexNumber o = (ComplexNumber) other;
        return this.re == o.getRe() && this.im == o.getIm();
    }

    public int compareTo(ComplexNumber other) {
        double comp = (this.re*this.re+this.im*this.im)-(other.getRe()*other.getRe()+other.getIm()*other.getIm());
        if(comp < 0)
            return -1;
        if(comp > 0)
            return 1;
        return 0;
    }

    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    public ComplexNumber negate() {
        this.set(this.re*(-1), this.im*(-1));
        return this;
    }

    public ComplexNumber add(ComplexNumber arg2) {
        this.set(this.re+arg2.getRe(), this.im+arg2.getIm());
        return this;
    }

    public ComplexNumber multiply(ComplexNumber arg2) {
        this.set(this.re*arg2.getRe()-this.im*arg2.getIm(), this.im*arg2.getRe()+this.re*arg2.getIm());
        return this;
    }

    public static void main(String[] args) {
        ComplexNumber c = new ComplexNumberImpl();
        c.set(27, 4);
        ComplexNumber m = c.add(c);
        m = c.copy();
        try {
            m = c.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(m.negate().toString());
        m.set("-4+2.5i");
        m.set("-5-10i");
        m.set("+1.465i");
        m.set("-i");
        m = c.copy();
        boolean b = c.equals(c);
        b = m.equals(c);
        //m.set("55*i");
        //m.set("6j");
    }
}
