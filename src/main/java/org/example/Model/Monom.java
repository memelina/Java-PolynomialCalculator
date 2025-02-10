package org.example.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monom {

    private int degree;
    private double coefficient;

    public Monom(int degree, double coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }



    @Override
    public String toString() {
        return "Monom{" +
                "degree=" + degree +
                ", coefficient=" + coefficient +
                '}';
    }
}



