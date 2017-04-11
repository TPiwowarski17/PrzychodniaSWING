package com.piwowarski.test;

import com.piwowarski.classes.Doctor;
import com.piwowarski.classes.Patient;
import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.encryption.Encryption;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Piwowarski Tomasz on 05.04.2017.
 */
public class MainTest
{
    @Test
    public void test1()
    {
        String str1 = "Tajne haslo 123";
        Encryption e = new Encryption(str1);

        e.encrypt();
        Assert.assertNotEquals("Napisy powinny byc różne",str1,e.getWordEncrypt());

        e.decipher();
        Assert.assertEquals("Napisy powinny być takie same",str1,e.getWordDecipher());
    }
    @Test
    public void test2()
    {
        PrzychodniaDao sql = PrzychodniaDaoImpl.getInstance();
        Doctor d = new Doctor(1,"Jan","Kowalski","Pediatra",10,200);
        sql.insertDoctor(d);
        List<Doctor> doctors= sql.selectDoctor();
        Assert.assertNotNull("Lista powinna mieć conajmniej jeden element",doctors);
        int idx = doctors.size()-1;
        d = doctors.get(idx);
        d.setName("Adam");
        sql.updateDoctor(d);
        Assert.assertEquals("Powinny być takie same",d,sql.selectDoctor().get(idx));
        sql.deleteDoctor(d.getId());

        Patient p = new Patient(1,"Adam","Nowak",25,"Grypa");
        sql.insertPatient(p);
        List<Patient> patients = sql.selectPatient();
        Assert.assertNotNull("Lista powinna mieć conajmniej jeden element",patients);
        idx = patients.size()-1;
        p = patients.get(idx);
        p.setName("Jan");
        sql.updatePatient(p);
        Assert.assertEquals("Powinny być takie same",p,sql.selectPatient().get(idx));
        sql.deletePatient(p.getId());
    }

    @Test
    public void test3()
    {
        Doctor d = new Doctor(1,"Adam","Kowalski","Okulista",1,150);
        Patient p = new Patient(0,"Jan","Nowak",13,"Grypa");

        Assert.assertThat("Doktor powinien mieć wlasności id",d, Matchers.hasProperty("id"));
        Assert.assertThat("Doktor powinien mieć wlasności name",d, Matchers.hasProperty("name"));
        Assert.assertThat("Doktor powinien mieć wlasności surname",d, Matchers.hasProperty("surname"));
        Assert.assertThat("Doktor powinien mieć wlasności specialization",d, Matchers.hasProperty("specialization"));
        Assert.assertThat("Doktor powinien mieć wlasności experience",d, Matchers.hasProperty("experience"));
        Assert.assertThat("Doktor powinien mieć wlasności visitPrice",d, Matchers.hasProperty("visitPrice"));

        Assert.assertThat("Pacjent powinien mieć wlasności id",p, Matchers.hasProperty("id"));
        Assert.assertThat("Pacjent powinien mieć wlasności name",p, Matchers.hasProperty("name"));
        Assert.assertThat("Pacjent powinien mieć wlasności surname",p, Matchers.hasProperty("surname"));
        Assert.assertThat("Pacjent powinien mieć wlasności age",p, Matchers.hasProperty("age"));
        Assert.assertThat("Pacjent powinien mieć wlasności illness",p, Matchers.hasProperty("illness"));
    }

    @Test
    public void test4()
    {
        Doctor d = new Doctor(1,"Adam","Kowalski","Okulista",10,150);
        Assert.assertThat(d,DoctorMatcher.customDoctorMatcher("[A-Z][a-z]+","[A-Z][a-z]+","[A-Z]([a-z]| )+",
                0,50,0,1000));
    }
}
