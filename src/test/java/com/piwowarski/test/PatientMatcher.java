package com.piwowarski.test;

import com.piwowarski.classes.Patient;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Piwowarski Tomasz on 11.04.2017.
 */
public class PatientMatcher extends TypeSafeMatcher<Patient>
{
    private String nameRegex;
    private String surnameRegex;
    private String illnessRegex;
    int ageMin;
    int ageMax;

    private List<String> errorList = new ArrayList<>();

    public PatientMatcher(String nameRegex, String surnameRegex, String illnessRegex, int ageMin, int ageMax)
    {
        this.nameRegex = nameRegex;
        this.surnameRegex = surnameRegex;
        this.illnessRegex = illnessRegex;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
    }

    @Override
    protected boolean matchesSafely(Patient patient)
    {
        if(!patient.getName().matches(nameRegex))
        {
            errorList.add("NAME ERROR");
        }
        if(!patient.getSurname().matches(surnameRegex))
        {
            errorList.add("SURNAME ERROR");
        }
        if(!patient.getIllness().matches(illnessRegex))
        {
            errorList.add("ILLNESS ERROR");
        }
        if(patient.getAge() < ageMin || patient.getAge() > ageMax)
        {
            errorList.add("AGE ERROR");
        }
        return errorList.isEmpty();
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendText(errorList.stream().collect(Collectors.joining("\n")));
    }

    public static PatientMatcher customPatientMatcher(String nameRegex, String surnameRegex,
                                                      String illnessRegex, int ageMin, int ageMax)
    {
        return new PatientMatcher(nameRegex,surnameRegex,illnessRegex,ageMin,ageMax);
    }
}
