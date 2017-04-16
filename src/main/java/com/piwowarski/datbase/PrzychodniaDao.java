package com.piwowarski.datbase;

import com.piwowarski.classes.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by Piwowarski Tomasz on 24.03.2017.
 */
public interface PrzychodniaDao
{
    void createTabel();
    void insertDoctor(Doctor d);
    void insertPatient(Patient p);
    void insertVisit(Visit v);
    void insertUser(User u);
    void insertMedicalHistory(Patient p);
    Optional<User> isCorect(String userName, String userPassword);
    void deleteDoctor(int id);
    void deletePatient(int id);
    void deleteVisit(int id);
    void updateDoctor(Doctor d);
    void updatePatient(Patient p);
    void updateVisit(Visit v);
    boolean isUserExist(String userName);
    List<Doctor> selectDoctor();
    List<Patient> selectPatient();
    List<Visit> selectVisit();
    Optional<Doctor> selectDoctorById(int id);
    List<DoctorPatient> selectInnerJoin();
    Optional<Patient> selectPatientById(int id);
    Optional<Visit> selectVisitById(int id);
    Optional<Doctor> selectDoctorByName(String namesurname);
    Optional<Patient> selectPatientByName(String namesurname);
    int countSpecialization(String tableName,String columnName,String name);
    int counDate(LocalDate ldate);
    List<LocalDate> selectDateInnerJoin();
    List<String> selectNameInnerJoin(String columnName);
    List<DoctorPatient> filter(
            boolean doctorNameActive,
            boolean doctorSurnameActive,
            boolean doctorSpecializationActive,
            boolean visitDateActive,
            boolean visitPriceActive,
            List<String> doctorNames,
            List<String> doctorSurnames,
            List<String> doctorSpecializations,
            LocalDate visitDateFrom,
            LocalDate visitDateTo,
            Integer visitPriceFrom,
            Integer visitPriceTo
    );
    List<DoctorPatient> getRows(String sql);
    List<Integer> getIds(String tableName);
    int countPatientInDoctor(Doctor d);
    int countPatientIllOn(String illness);
    List<String> getNameSurname(String tabel);
    int selectId(String tabel,String namesurname);
    List<String> selectAllIllness();
    List<String> selectMedicalHistory(String namesurname);

}
