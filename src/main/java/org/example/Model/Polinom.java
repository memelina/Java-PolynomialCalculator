package org.example.Model;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Polinom {
    private Map<Integer, Monom> monomial;


    public Polinom() {
        this.monomial = new HashMap<>();
    }

    public Map<Integer, Monom> getMonomial() {
        return monomial;
    }

    public void addMonom(Monom m) {

        monomial.put(m.getDegree(), m);
    }


    public static Polinom add(Polinom p1, Polinom p2) {
        Polinom rez = new Polinom();

        //adaug in rez fiecare monom din p1
        for (Map.Entry<Integer, Monom> m : p1.monomial.entrySet()) {
            rez.addMonom(m.getValue());

        }

        for (Map.Entry<Integer, Monom> m : p2.monomial.entrySet()) {
            int degree = m.getKey();
            double coeficient = m.getValue().getCoefficient();

            //veific daca in p2 exista un monom cu acelasi grad ca si in p1 si daca da adun coeficienti
            if (rez.monomial.containsKey(degree)) {
                double coeficientExistent = rez.monomial.get(degree).getCoefficient();
                rez.monomial.put(degree, new Monom(degree, coeficientExistent + coeficient));
            } else {
                rez.addMonom(new Monom(degree, coeficient));
            }
        }

        return rez;
    }

    public static Polinom sub(Polinom p1, Polinom p2) {
        Polinom rez = new Polinom();


        for (Map.Entry<Integer, Monom> m : p1.monomial.entrySet()) {
            rez.addMonom(m.getValue());
        }

        for (Map.Entry<Integer, Monom> m : p2.monomial.entrySet()) {
            int degree = m.getKey();
            double coeficient = m.getValue().getCoefficient();

            if (rez.monomial.containsKey(degree)) {
                double coeficientExistent = rez.monomial.get(degree).getCoefficient();
                rez.monomial.put(degree, new Monom(degree, coeficientExistent - coeficient));
            } else {
                rez.addMonom(new Monom(degree, -coeficient));
            }
        }

        return rez;
    }

    public static Polinom mul(Polinom p1, Polinom p2) {
        Polinom rez = new Polinom();


        for (Map.Entry<Integer, Monom> m1 : p1.monomial.entrySet()) {
            for (Map.Entry<Integer, Monom> m2 : p2.monomial.entrySet()) {
                int degree = m1.getValue().getDegree() + m2.getValue().getDegree();
                double coeficient = m1.getValue().getCoefficient() * m2.getValue().getCoefficient();

                if (rez.monomial.containsKey(degree)) {
                    double existingCoefficient = rez.monomial.get(degree).getCoefficient();
                    coeficient += existingCoefficient;
                }
                rez.addMonom(new Monom(degree, coeficient));
            }
        }

        return rez;
    }

    public static Polinom derivare(Polinom polinom) {
        Polinom rez = new Polinom();


        for (Map.Entry<Integer, Monom> m : polinom.monomial.entrySet()) {
            int degree = m.getValue().getDegree() - 1;
            double coeficient = m.getValue().getDegree() * m.getValue().getCoefficient();

            if (degree >= 0) {
                rez.addMonom(new Monom(degree, coeficient));
            }
        }

        return rez;
    }

    public static Polinom integrare(Polinom polinom) {
        Polinom rez = new Polinom();


        for (Map.Entry<Integer, Monom> m : polinom.monomial.entrySet()) {
            int degree = m.getValue().getDegree() + 1;
            double coeficient = m.getValue().getCoefficient() / (degree);

            rez.addMonom(new Monom(degree, coeficient));
        }

        return rez;
    }



    @Override
    public String toString() {
        return "Polinom{" +
                "monomial=" + monomial +
                '}';
    }
}
