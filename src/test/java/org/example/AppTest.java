package org.example;

import junit.framework.TestCase;
import org.example.Model.Monom;
import org.example.Model.Polinom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class App  {
    @Test

    void shouldShowSimpleAssertionAdd() {

        Polinom p1= new Polinom();
        p1.addMonom(new Monom(2,3.0));
        Polinom p2= new Polinom();
        p2.addMonom(new Monom(2,2.0));
        Polinom rez = new Polinom();
        rez.addMonom(new Monom(2,5.0));

        Polinom rezDorit= Polinom.add(p1,p2);
        Assertions.assertEquals(rez.getMonomial().get(2).getCoefficient(),rezDorit.getMonomial().get(2).getCoefficient());
        Assertions.assertEquals(rez.getMonomial().get(2).getDegree(),rezDorit.getMonomial().get(2).getDegree());

    }
    @Test
    void shouldShowSimpleAssertionSub()
    {
        Polinom p1= new Polinom();
        p1.addMonom(new Monom(2,3.0));
        Polinom p2= new Polinom();
        p2.addMonom(new Monom(2,2.0));
        Polinom rez = new Polinom();
        rez.addMonom(new Monom(2,1.0));
        Polinom rezDorit=Polinom.sub(p1,p2);
        Assertions.assertEquals(rez.getMonomial().get(2).getCoefficient(),rezDorit.getMonomial().get(2).getCoefficient());
        Assertions.assertEquals(rez.getMonomial().get(2).getDegree(),rezDorit.getMonomial().get(2).getDegree());

    }
    @Test

    void shouldShowSimpleAssertionMul() {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(2, 3.0));
        Polinom p2 = new Polinom();
        p2.addMonom(new Monom(2, 2.0));
        Polinom rez = new Polinom();
        rez.addMonom(new Monom(4, 6));
        Polinom rezDorit = Polinom.mul(p1, p2);
        Assertions.assertEquals(rez.getMonomial().get(4).getCoefficient(), rezDorit.getMonomial().get(4).getCoefficient());
        Assertions.assertEquals(rez.getMonomial().get(4).getDegree(), rezDorit.getMonomial().get(4).getDegree());
    }

@Test
    void shouldShowSimpleAssertionDerivare() {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(2, 3.0));
        Polinom p2 = new Polinom();
        p2.addMonom(new Monom(2, 2.0));
        Polinom rez = new Polinom();
        rez.addMonom(new Monom(1, 6.0));
        Polinom rezDorit = Polinom.derivare(p1);
        Assertions.assertEquals(rez.getMonomial().get(1).getCoefficient(), rezDorit.getMonomial().get(1).getCoefficient());
        Assertions.assertEquals(rez.getMonomial().get(1).getDegree(), rezDorit.getMonomial().get(1).getDegree());
    }
    @Test

    void shouldShowSimpleAssertionIntegrare() {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(2, 3.0));
        Polinom p2 = new Polinom();
        p2.addMonom(new Monom(2, 2.0));
        Polinom rez = new Polinom();
        rez.addMonom(new Monom(3, 1.0));
         Polinom rezDorit = Polinom.integrare(p1);
        Assertions.assertEquals(rez.getMonomial().get(3).getCoefficient(), rezDorit.getMonomial().get(3).getCoefficient());
        Assertions.assertEquals(rez.getMonomial().get(3).getDegree(), rezDorit.getMonomial().get(3).getDegree());
    }
}