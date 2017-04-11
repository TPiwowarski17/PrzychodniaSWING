package com.piwowarski.classes;

import java.time.LocalDate;

/**
 * Created by Piwowarski Tomasz on 24.02.2017.
 */
public class DoctorPatient
{
    private int id;
    private String doctorName;
    private String doctorSurname;
    private String specialization;
    private int experience;
    private int visitPrice;
    private String patientName;
    private String patientSurname;
    private int age;
    private String illness;
    private LocalDate date;
    private String city;

    public DoctorPatient(int id, String doctorName, String doctorSurname, String specialization, int experience, int visitPrice, String patientName, String patientSurname, int age, String illness, LocalDate date, String city)
    {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.specialization = specialization;
        this.experience = experience;
        this.visitPrice = visitPrice;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.age = age;
        this.illness = illness;
        this.date = date;
        this.city = city;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDoctorName()
    {
        return doctorName;
    }

    public void setDoctorName(String doctorName)
    {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname()
    {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname)
    {
        this.doctorSurname = doctorSurname;
    }

    public String getSpecialization()
    {
        return specialization;
    }

    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
    }

    public int getExperience()
    {
        return experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public int getVisitPrice()
    {
        return visitPrice;
    }

    public void setVisitPrice(int visitPrice)
    {
        this.visitPrice = visitPrice;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getPatientSurname()
    {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname)
    {
        this.patientSurname = patientSurname;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getIllness()
    {
        return illness;
    }

    public void setIllness(String illness)
    {
        this.illness = illness;
    }

    @Override
    public String toString()
    {
        return "DoctorPatient{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSurname='" + doctorSurname + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience=" + experience +
                ", visitPrice=" + visitPrice +
                ", patientName='" + patientName + '\'' +
                ", patientSurname='" + patientSurname + '\'' +
                ", age=" + age +
                ", illness='" + illness + '\'' +
                '}';
    }
}
