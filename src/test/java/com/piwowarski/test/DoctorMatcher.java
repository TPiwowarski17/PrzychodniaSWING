package com.piwowarski.test;

import com.piwowarski.classes.Doctor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Piwowarski Tomasz on 11.04.2017.
 */
public class DoctorMatcher extends TypeSafeMatcher<Doctor>
{
    private String nameRegex;
    private String surnameRegex;
    private String specializationRegex;
    private int experienceMin;
    private int experienceMax;
    private int visitPriceMin;
    private int visitPriceMax;

    private List<String> errorList = new ArrayList<>();

    public DoctorMatcher(String nameRegex, String surnameRegex, String specializationRegex, int experienceMin, int experienceMax,
                         int visitPriceMin, int visitPriceMax)
    {
        this.nameRegex = nameRegex;
        this.surnameRegex = surnameRegex;
        this.specializationRegex = specializationRegex;
        this.experienceMin = experienceMin;
        this.experienceMax = experienceMax;
        this.visitPriceMin = visitPriceMin;
        this.visitPriceMax = visitPriceMax;
    }

    @Override
    protected boolean matchesSafely(Doctor doctor)
    {
        if(!doctor.getName().matches(nameRegex))
        {
            errorList.add("NAME ERROR");
        }
        if(!doctor.getSurname().matches(surnameRegex))
        {
            errorList.add("SURNAME ERROR");
        }
        if(!doctor.getSpecialization().matches(specializationRegex))
        {
            errorList.add("SPECIALIZATION ERROR");
        }
        if(doctor.getExperience() < experienceMin || doctor.getExperience()>experienceMax)
        {
            errorList.add("EXPERIENCE ERROR");
        }
        if(doctor.getVisitPrice() < visitPriceMin || doctor.getVisitPrice() > visitPriceMax)
        {
            errorList.add("VISIT PRICE ERROR");
        }
        return errorList.isEmpty();
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendText(errorList.stream().collect(Collectors.joining("\n")));
    }

    public static DoctorMatcher customDoctorMatcher(String nameRegex, String surnameRegex,
                   String specializationRegex, int experienceMin, int experienceMax, int visitPriceMin, int visitPriceMax)
    {
        return new DoctorMatcher(nameRegex,surnameRegex,specializationRegex,experienceMin,experienceMax,visitPriceMin,visitPriceMax);
    }

}
