package com.piwowarski.datbase;

import com.piwowarski.classes.*;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Piwowarski Tomasz on 24.03.2017.
 */
public class PrzychodniaDaoImpl implements PrzychodniaDao
{
    private final static String DRV = "org.sqlite.JDBC";
    private final static String DB = "jdbc:sqlite:Przychodnia.db";

    private Statement stat;
    private Connection conn;

    private static PrzychodniaDaoImpl przychodniaDao;
    private PrzychodniaDaoImpl(){
        try
        {

            Class.forName(DRV);
            SQLiteConfig conf = new SQLiteConfig();
            conf.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB, conf.toProperties());
            stat = conn.createStatement();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static PrzychodniaDaoImpl getInstance()
    {
        if (przychodniaDao == null)
        {
            przychodniaDao = new PrzychodniaDaoImpl();
        }
        return przychodniaDao;
    }
    @Override
    public void insertDoctor(Doctor d)
    {
        try
        {

            String sql = "INSERT INTO Doctor (name,surname,specialization,experience,visitPrice) VALUES " +
                    "(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getName());
            ps.setString(2, d.getSurname());
            ps.setString(3, d.getSpecialization());
            ps.setInt(4, d.getExperience());
            ps.setInt(5, d.getVisitPrice());
            ps.execute();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public void insertPatient(Patient p)
    {
        try
        {
            String sql = "INSERT INTO Patient (name,surname,age,illness) VALUES" +
                    "(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,p.getName());
            ps.setString(2,p.getSurname());
            ps.setInt(3,p.getAge());
            ps.setString(4,p.getIllness());
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }


    }
    @Override
    public void insertVisit(Visit v)
    {
        try
        {
            String sql = "INSERT INTO Visit (idDoctor,idPatient,date,city) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,v.getIdDoctor());
            ps.setInt(2,v.getIdPatient());
            ps.setString(4,v.getCity());
            ps.setDate(3,Date.valueOf(v.getDate()));
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void insertUser(User u)
    {
        String sql = "INSERT INTO User(name,surname,email,city,age,userName,userPassword) VALUES" +
                "(?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,u.getName());
            ps.setString(2,u.getSurname());
            ps.setString(3,u.getEmail());
            ps.setString(4,u.getCity());
            ps.setInt(5,u.getAge());
            ps.setString(6,u.getUserName());
            ps.setString(7,u.getUserPassword());
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public Optional<User> isCorect(String userName, String userPassword)
    {
        Optional<User> user = Optional.empty();
        String sql = "SELECT * FROM User WHERE userName=? AND userPassword = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,userPassword);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String email = rs.getString(4);
                String city = rs.getString(5);
                int age = rs.getInt(6);
                String userName1 = rs.getString(7);
                String userPassword1 = rs.getString(8);
                user = Optional.of(new User(id,name,surname,email,city,age,userName1,userPassword1));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void deleteDoctor(int id)
    {

        try
        {
            String sql = "DELETE FROM Doctor WHERE id=(?)";
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void deletePatient(int id)
    {

        try
        {
            String sql = "DELETE FROM Patient WHERE id=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);;
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteVisit(int id)
    {
        try
        {
            String sql = "DELETE FROM Visit WHERE id = (?)";
            PreparedStatement ps = conn.prepareStatement(sql);;
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updateDoctor(Doctor d)
    {
        try
        {
            String sql = "UPDATE Doctor SET name = (?), surname = (?), specialization = (?), experience = (?), visitPrice = (?)" +
                    "WHERE id = (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,d.getName());
            ps.setString(2,d.getSurname());
            ps.setString(3,d.getSpecialization());
            ps.setInt(4,d.getExperience());
            ps.setInt(5,d.getVisitPrice());
            ps.setInt(6,d.getId());
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updatePatient(Patient p)
    {
        try
        {
            String sql = "UPDATE Patient SET name = (?), surname = (?), age = (?), illness = (?) WHERE id = (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,p.getName());
            ps.setString(2,p.getSurname());
            ps.setInt(3,p.getAge());
            ps.setString(4,p.getIllness());
            ps.setInt(5,p.getId());
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void updateVisit(Visit v)
    {
        try
        {
            String sql = "UPDATE Visit SET idDoctor=(?),idPatient(?),date=(?),city=(?) WHERE id=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,v.getIdDoctor());
            ps.setInt(2,v.getIdPatient());
            ps.setDate(3,Date.valueOf(v.getDate()));
            ps.setString(4,v.getCity());
            ps.setInt(5,v.getId());
            ps.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public boolean isUserExist(String userName)
    {
        String sql = "SELECT userName FROM USER WHERE userName = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Doctor> selectDoctor()
    {
        List<Doctor> list = new ArrayList<>();
        try
        {
            String sql = "SELECT * FROM Doctor";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String specialization = rs.getString("specialization");
                int experience = rs.getInt("experience");
                int visitPrice = rs.getInt("visitPrice");
                list.add(new Doctor(id,name,surname,specialization,experience,visitPrice));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Patient> selectPatient()
    {
        List<Patient> list = new ArrayList<>();
        try
        {
            String sql = "SELECT * FROM Patient";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                int age = rs.getInt(4);
                String illness = rs.getString(5);
                list.add(new Patient(id,name,surname,age,illness));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Visit> selectVisit()
    {
        List<Visit> list = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM Visit";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt(1);
                int idDoctor = rs.getInt(2);
                int idPatient = rs.getInt(3);
                LocalDate date = rs.getDate(4).toLocalDate();
                String city = rs.getString(5);
                list.add(new Visit(id,idDoctor,idPatient,date,city));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public Optional<Doctor> selectDoctorById(int id)
    {
        Optional<Doctor> dop = Optional.ofNullable(null);
        try
        {
            String sql = "SELECT * FROM Doctor WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int id1 = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String specialization = rs.getString(4);
                int experience = rs.getInt(5);
                int visitPrice = rs.getInt(6);
                dop = Optional.of(new Doctor(id1,name,surname,specialization,experience,visitPrice));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dop;

    }
    @Override
    public List<DoctorPatient> selectInnerJoin()
    {
        List<DoctorPatient> list = null;

        String sql = "SELECT V.id, D.name, D.surname, D.specialization,D.experience, D.visitPrice, P.name, P.surname, P.age, " +
                "P.illness,  V.date, V.city " +
                "FROM Doctor D INNER JOIN Visit V ON D.id = V.idDoctor INNER JOIN Patient P ON P.id = V.idPatient";
        list =getRows(sql);

        return list;
    }
    @Override
    public Optional<Patient> selectPatientById(int id)
    {
        Optional<Patient> patientOptional = Optional.ofNullable(null);
        try
        {
            String sql = "SELECT * FROM Patient WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                String name = rs.getString(2);
                String surname = rs.getString(3);
                int age = rs.getInt(4);
                String illness = rs.getString(5);
                patientOptional = Optional.of(new Patient(id,name,surname,age,illness));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return patientOptional;
    }
    @Override
    public Optional<Visit> selectVisitById(int id)
    {
        Optional<Visit> visitOptional = Optional.ofNullable(null);
        String sql = "SELECT * FROM Visit WHERE id=?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int idDoctor = rs.getInt(2);
                int idPatient = rs.getInt(3);
                LocalDate date = rs.getDate(4).toLocalDate();
                String city = rs.getString(5);
                visitOptional = Optional.of(new Visit(id,idDoctor,idPatient,date,city));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return visitOptional;
    }
    @Override
    public int countSpecialization(String tableName,String columnName,String name)
    {
        String sql = "SELECT COUNT(id) FROM " + tableName +" WHERE " + columnName + " = ?";
        int quantity = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            quantity = rs.getInt(1);


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return quantity;
    }
    @Override
    public int counDate(LocalDate ldate)
    {
        String sql = "SELECT COUNT(id) FROM Visit WHERE date = ?";
        int quantity = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,Date.valueOf(ldate));
            ResultSet rs = ps.executeQuery();
            quantity = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return quantity;
    }
    @Override
    public List<LocalDate> selectDateInnerJoin()
    {
        List<LocalDate> list = null;

        String sql = "SELECT DISTINCT  V.date " +
                "FROM Doctor D INNER JOIN Visit V ON D.id = V.idDoctor INNER JOIN Patient P ON P.id = V.idPatient";
        try
        {
            list = new ArrayList<>();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next())
            {
                list.add(rs.getDate(1).toLocalDate());
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<String> selectNameInnerJoin(String columnName)
    {
        List<String> list = null;

        String sql = "SELECT DISTINCT " + columnName + " " +
                "FROM Doctor D INNER JOIN Visit V ON D.id = V.idDoctor INNER JOIN Patient P ON P.id = V.idPatient";
        try
        {
            list = new ArrayList<>();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next())
            {
                list.add(rs.getString(1));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<DoctorPatient> filter(
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
    )
    {
        List<DoctorPatient> list = null;

        String sql = "SELECT V.id, D.name, D.surname, D.specialization,D.experience, D.visitPrice, P.name, P.surname, P.age, " +
                "P.illness,  V.date, V.city " +
                "FROM Doctor D INNER JOIN Visit V ON D.id = V.idDoctor INNER JOIN Patient P ON P.id = V.idPatient " +
                "WHERE 1 = 1 ";

        if(doctorNameActive)
        {
            String namesSql = " AND D.name IN ('" + String.join("','" , doctorNames) + "') ";
            sql += namesSql;
        }

        if(doctorSurnameActive)
        {
            String surnameSql = " AND D.surname IN ('" + String.join("','",doctorSurnames) + "') ";
            sql += surnameSql;
        }

        if(doctorSpecializationActive)
        {
            String specializationSql = " AND D.specialization IN ('" + String.join("','",doctorSpecializations) + "') ";
            sql += specializationSql;
        }
        if(visitPriceActive)
        {
            sql += " AND D.visitPrice BETWEEN " + visitPriceFrom + " AND " + visitPriceTo + " ";
        }

        sql += ";";

        list = getRows(sql);

        if(visitDateActive)
        {
            list = list.stream().filter(x -> x.getDate().compareTo(visitDateFrom) >= 0 && x.getDate().compareTo(visitDateTo) <= 0).collect(Collectors.toList());
        }

        return list;
    }
    @Override
    public List<DoctorPatient> getRows(String sql)
    {
        List<DoctorPatient> list = null;
        try
        {
            ResultSet rs = stat.executeQuery(sql);
            list = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt(1);
                String doctorName = rs.getString(2);
                String doctorSurname = rs.getString(3);
                String specialization = rs.getString(4);
                int experience = rs.getInt(5);
                int visitPrice = rs.getInt(6);
                String patientName = rs.getString(7);
                String patientSurname = rs.getString(8);
                int age = rs.getInt(9);
                String illness = rs.getString(10);
                LocalDate date = rs.getDate(11).toLocalDate();
                String city = rs.getString(12);
                list.add(new DoctorPatient(id, doctorName, doctorSurname, specialization, experience, visitPrice, patientName, patientSurname, age, illness, date, city));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list;
    }
    @Override
    public List<Integer> getIds(String tableName)
    {
        String sql = "SELECT id FROM " + tableName;
        List<Integer> list = new ArrayList<>();
        try
        {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next())
            {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countPatientInDoctor(Doctor d)
    {
        String sql = "SELECT COUNT(id) FROM Visit WHERE idDoctor = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,d.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countPatientIllOn(String illness)
    {
        String sql = "SELECT COUNT(id) FROM Patient WHERE illness = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,illness);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
