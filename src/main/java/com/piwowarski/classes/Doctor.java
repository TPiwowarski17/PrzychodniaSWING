package com.piwowarski.classes;

/**
 * Created by Piwowarski Tomasz on 02.02.2017.
 */
public class Doctor
{
    private int id;
    private String name;
    private String surname;
    private String specialization;
    private int experience;
    private int visitPrice;

    public Doctor(int id, String name, String surname, String specjalizacja, int experience, int visitPrice)
    {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.specialization = specjalizacja;
        this.experience = experience;
        this.visitPrice = visitPrice;
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

        Doctor doctor = (Doctor) o;

        if (experience != doctor.experience)
        {
            return false;
        }
        if (visitPrice != doctor.visitPrice)
        {
            return false;
        }
        if (name != null ? !name.equals(doctor.name) : doctor.name != null)
        {
            return false;
        }
        if (surname != null ? !surname.equals(doctor.surname) : doctor.surname != null)
        {
            return false;
        }
        return specialization != null ? specialization.equals(doctor.specialization) : doctor.specialization == null;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + visitPrice;
        return result;
    }

    @Override
    public String
    toString()
    {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience=" + experience +
                ", visitPrice=" + visitPrice +
                '}';
    }
}

