package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector{
    private double[] items;

    public void set(double... elements){
        this.items = new double[elements.length];
        this.items = elements;
    }

    public double[] get(){
        return this.items;
    }

    public ArrayVector clone(){
        ArrayVectorImpl a = new ArrayVectorImpl();
        a.set(this.items.clone());
        return a;
    }

    public int getSize(){
        return this.items.length;
    }

    public void set(int index, double value){
        if (items.length > index && index >= 0)
            this.items[index] = value;
        else if (index >= 0){
            double[] a = new double[index + 1];
            for (int i = 0; i < items.length; i++){
                a[i] = this.items[i];
            }
            a[index] = value;
            this.items = a;
        }
    }

    public double get(int index) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index >= this.getSize())
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException");
        return this.items[index];
    }

    public double getMax(){
        double max = this.items[0];
        for (double current : this.items){
            if (current > max){
                max = current;
            }
        }
        return max;
    }

    public double getMin(){
        double min = this.items[0];
        for (double current : this.items){
            if (current < min){
                min = current;
            }
        }
        return min;
    }

    public void sortAscending(){
        Arrays.sort(this.items);
    }

    public void mult(double factor){
        for (int i = 0; i < this.getSize(); i++){
            this.items[i] *= factor;
        }
    }

    public ArrayVector sum(ArrayVector anotherVector){
        int i = 0;
        while (i < this.getSize() && i < anotherVector.getSize()){
            this.items[i] += anotherVector.get(i);
            i++;
        }
        return this;
    }

    public double scalarMult(ArrayVector anotherVector){
        double result = 0;
        int i = 0;
        while (i < this.getSize() && i < anotherVector.getSize()){
            result += this.items[i] * anotherVector.get(i);
            i++;
        }
        return result;
    }

    public double getNorm(){
        double result = scalarMult(this);
        return Math.sqrt(result);
    }

    public static void main(String[] args) {
        ArrayVector object = new ArrayVectorImpl();
        object.set(1, 7, 4, 8);
        System.out.println(object.get(1));
        System.out.println(object.getNorm());
        System.out.println(object.getMax());
        object.set(1, 0.2);
        System.out.println(object.get(1));
        System.out.println(object.getSize());
        object.set(10, 0.2);
        System.out.println(object.get(10));
        System.out.println(object.get(9));
    }

}
