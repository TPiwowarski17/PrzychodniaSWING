package com.piwowarski.classes;

import java.time.LocalDate;

/**
 * Created by Piwowarski Tomasz on 17.02.2017.
 */
public class Visit
{
    private int id;
    private int idDoctor;
    private int idPatient;
    private LocalDate date;
    private String city;

    public Visit(int id, int idDoctor, int idPatient, LocalDate date, String city)
    {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPatient = idPatient;
        this.date = date;
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

    public int getIdDoctor()
    {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor)
    {
        this.idDoctor = idDoctor;
    }

    public int getIdPatient()
    {
        return idPatient;
    }

    public void setIdPatient(int idPatient)
    {
        this.idPatient = idPatient;
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

        Visit visit = (Visit) o;

        if (id != visit.id)
        {
            return false;
        }
        if (idDoctor != visit.idDoctor)
        {
            return false;
        }
        if (idPatient != visit.idPatient)
        {
            return false;
        }
        if (date != null ? !date.equals(visit.date) : visit.date != null)
        {
            return false;
        }
        return city != null ? city.equals(visit.city) : visit.city == null;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + idDoctor;
        result = 31 * result + idPatient;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Visit{" +
                "id=" + id +
                ", idDoctor=" + idDoctor +
                ", idPatient=" + idPatient +
                ", date=" + date +
                ", city='" + city + '\'' +
                '}';
    }
}
