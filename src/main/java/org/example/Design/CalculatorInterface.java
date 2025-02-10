package org.example.Design;


import org.example.Model.Monom;
import org.example.Model.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorInterface extends JFrame implements ActionListener {
    private JTextField firstPolynomialField = new JTextField();
    private JTextField secondPolynomialField = new JTextField();
    private JTextField resultField = new JTextField();
    private JLabel titleLabel = new JLabel("Polynomial Calculator", SwingConstants.CENTER);
    private JButton addButton = new JButton("+");
    private JButton subButton = new JButton("-");
    private JButton mulButton = new JButton("x");
    private JButton divButton = new JButton("/");
    private JButton intButton = new JButton("∫f(x)dx");
    private JButton derButton = new JButton("f'(x)");
    private Polinom poli1;
    private Polinom poli2;

    public CalculatorInterface() {

        setTitle("Polynomial Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); //pentru a aranja componentele


        JPanel panel = new JPanel(new GridLayout(0, 2)); //2 coloane si se genereaza automat cate randuri e nevoie
        add(titleLabel, BorderLayout.NORTH); //adaugam titlul in partea de sus a containerului

        panel.add(new JLabel("First Polynomial ="));
        panel.add(firstPolynomialField);;

        panel.add(new JLabel("Second Polynomial ="));
        panel.add(secondPolynomialField);


        addButton.addActionListener(this);
        panel.add(addButton);

        subButton.addActionListener(this);
        panel.add(subButton);

        mulButton.addActionListener(this);
        panel.add(mulButton);

        divButton.addActionListener(this);
        panel.add(divButton);

        intButton.addActionListener(this);
        panel.add(intButton);

        derButton.addActionListener(this);
        panel.add(derButton);

        panel.add(new JLabel("Result:"));
        resultField.setEditable(false); //nu se poate scrie in field
        panel.add(resultField);

        add(panel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(500, 400));
        pack(); //fereastra se adapteaza la continutul sau
        setLocationRelativeTo(null); //plasez fereastra pe centru
        setVisible(true);
    }
    public static boolean  polinomInvalid(String p) {

        String regex = "([+-]?\\d*)(?:\\*x(?:\\^(\\d*))?)?";
        //[+-]? adica + sau - poate aparea optional sau o singura data
        //\d* orice cifra  poate aparea de 0 sau mai multe ori
        // \*x potrivire literala pentru *x ca sa nu putem introduce xx de ex sau cu spatii
        //? adica nu e obligatoriu sa am exponent sau x



        // Verific daca p contine 1 sau mai multe siruri care se potrivesc cu regexul
        return p.matches("^(?:" + regex + ")+$");
        //^-sirul trebuie sa inceapa cu ce urmeaza dupa acest caracter
        //(?: grup de non capturare adica vreau sa vad daca se potriveste fara sa salvez ce se potriveste
        //+ matches regex o data sau de mai multe ori
        //$ trebuie sa se termine cu sirul de inaintea lui
    }

    @Override

        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            String firstPolynomial = firstPolynomialField.getText();
            String secondPolynomial = secondPolynomialField.getText();

        if (!polinomInvalid(firstPolynomial) || !polinomInvalid(secondPolynomial)) {
            resultField.setText("Invalid input!");
            return;
        }

          poli1 = parsePolynomial(firstPolynomial);
          poli2 = parsePolynomial(secondPolynomial);


            Polinom result;
            switch (actionCommand) {
                case "+":
                    result = Polinom.add(poli1,poli2);
                    break;
                case "-":
                    result= Polinom.sub(poli1,poli2);
                    break;
                case "x":
                    result= Polinom.mul(poli1,poli2);
                    break;
                case "∫f(x)dx":
                    result= Polinom.integrare(poli1);
                    break;
                case "f'(x)":
                    result= Polinom.derivare(poli1);
                    break;

                default:
                    result = null;
            }

            if (result != null) {
                String resultText = polynomialToString(result);
                resultField.setText(resultText);
            } else {
                resultField.setText("0");
            }
    }




    public static Polinom parsePolynomial(String polinomText) {
        Polinom polinom = new Polinom();
        Pattern pattern = Pattern.compile("([+-]?\\d*)(?:\\*x(?:\\^(\\d*))?)?");
        Matcher matcher = pattern.matcher(polinomText);

        while (matcher.find()) {
            String potrivire = matcher.group();
            if (potrivire.isEmpty()) { //daca nu se potriveste cu regexul o expresie trecem la urmatoarea potrivire
                continue;
            }

            double coeficient;
            int degree;

            if (matcher.group(1) != null && !matcher.group(1).isEmpty()) {
                try {
                    coeficient = Double.parseDouble(matcher.group(1));
                } catch (NumberFormatException e) {
                    coeficient = potrivire.startsWith("-") ? -1 : 1; // Setez coeficientul implicit la 1 sau -1
                }
            } else {
                coeficient = potrivire.startsWith("-") ? -1 : 1; // Setez coeficientul implicit la 1 sau -1
            }

            if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                degree = Integer.parseInt(matcher.group(2));
            } else {
                degree = potrivire.contains("x") ? 1 : 0; // Setez gradul implicit la 1 sau 0
            }

            polinom.addMonom(new Monom(degree, coeficient));
        }

        return polinom;
    }


    public String polynomialToString(Polinom polinom) {
            StringBuilder sb = new StringBuilder(); //ca sa folosim append()
            for (Monom monom : polinom.getMonomial().values()) {
                double coeficient = monom.getCoefficient();
                int degree = monom.getDegree();

                if (coeficient != 0) {
                    if (coeficient > 0 && sb.length() > 0) {  //ca sa nu avem + la inceputul polinomului
                        sb.append(" + ");
                    }
                    if (coeficient < 0) {
                        sb.append(" - ");
                        coeficient = -coeficient; //ca sa fie pozitiv inainte sa de a fi adaugat in sir
                    }
                    if (coeficient != 1 || degree == 0) {
                        sb.append(coeficient);
                    }
                    if (degree != 0) {
                        sb.append("x");
                        if (degree != 1) {
                            sb.append("^").append(degree);
                        }
                    }
                }
            }
            String result = sb.toString();
        if (result.isEmpty()) {
            return "0";
        } else {
            return result;
        }
        }
    }
