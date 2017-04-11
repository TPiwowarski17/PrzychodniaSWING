package com.piwowarski.classes;

/**
 * Created by Piwowarski Tomasz on 03.02.2017.
 */

public class Patient
{
    private int id;
    private String name;
    private String surname;
    private int age;
    private String illness;

    public Patient(int id,String name, String surname, int age, String illness)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.illness = illness;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
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
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Patient patient = (Patient) o;

        if (age != patient.age)
        {
            return false;
        }
        if (name != null ? !name.equals(patient.name) : patient.name != null)
        {
            return false;
        }
        if (surname != null ? !surname.equals(patient.surname) : patient.surname != null)
        {
            return false;
        }
        return illness != null ? illness.equals(patient.illness) : patient.illness == null;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (illness != null ? illness.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", illness='" + illness + '\'' +
                '}';
    }
}
