package com.piwowarski.panels;

import com.piwowarski.classes.Doctor;
import com.piwowarski.classes.Patient;
import com.piwowarski.classes.Visit;
import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.models.CustomComboboxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Piwowarski Tomasz on 02.02.2017.
 */
public class PanelWizyta extends JPanel
{
    private JTextField tfid = new JTextField(5);
    private JButton btnLeft = new JButton("<<<");
    private JButton btnRight= new JButton(">>>");
    private JButton btnDelete = new JButton("DELETE");
    private JButton btnInsert = new JButton("INSERT");

    private JLabel lDoctorID = new JLabel("ID Doctor");
    private JComboBox<Integer> cbDoctor;
    private CustomComboboxModel<Integer> cbDoctorModel;
    private JLabel lPatientID = new JLabel("ID Patient");
    private JComboBox<Integer> cbPatient;
    private CustomComboboxModel<Integer> cbPatientModel;

    //Panel Lekarz 1
    private JLabel lNameDoctor1 = new JLabel("Name");
    private JLabel lSurnameDoctor1 = new JLabel("Surname");
    private JLabel lSpecialization1 =new JLabel("Specialization");
    private JLabel lExperience1 = new JLabel("Experience");
    private JLabel lVisitPrice1 = new JLabel("Visit price");

    private JTextField tfNameDoctor1 = new JTextField(5);
    private JTextField tfSurnameDoctor1 = new JTextField(5);
    private JTextField tfSpecialization1 = new JTextField(5);
    private JTextField tfExperience1 = new JTextField(5);
    private JTextField tfVisitPrice1 = new JTextField(5);

    //Panel Lekarz 2
    private JLabel lNameDoctor2 = new JLabel("Name");
    private JLabel lSurnameDoctor2 = new JLabel("Surname");
    private JLabel lSpecialization2 =new JLabel("Specialization");
    private JLabel lExperience2 = new JLabel("Experience");
    private JLabel lVisitPrice2 = new JLabel("Visit price");

    private JTextField tfNameDoctor2 = new JTextField(5);
    private JTextField tfSurnameDoctor2 = new JTextField(5);
    private JTextField tfSpecialization2 = new JTextField(5);
    private JTextField tfExperience2 = new JTextField(5);
    private JTextField tfVisitPrice2 = new JTextField(5);
    //Panel pacjent 1
    private JLabel lNamePatient1 = new JLabel("Name");
    private JLabel lSurnamePatient1 = new JLabel("Surname");
    private JLabel lAgePatient1 = new JLabel("Age");
    private JLabel lIllnessPatient1 = new JLabel("Illness");

    private JTextField tfNamePatient1 = new JTextField(5);
    private JTextField tfSurnamePatient1 = new JTextField(5);
    private JTextField tfAgePatient1 = new JTextField(5);
    private JTextField tfIllnessPatient1 = new JTextField(5);

    //Panel Pacjent 2
    private JLabel lNamePatient2 = new JLabel("Name");
    private JLabel lSurnamePatient2 = new JLabel("Surname");
    private JLabel lAgePatient2 = new JLabel("Age");
    private JLabel lIllnessPatient2 = new JLabel("Illness");

    private JTextField tfNamePatient2 = new JTextField(5);
    private JTextField tfSurnamePatient2 = new JTextField(5);
    private JTextField tfAgePatient2 = new JTextField(5);
    private JTextField tfIllnessPatient2 = new JTextField(5);
    //Panel Date
    private JLabel lDate = new JLabel("Date");
    private JLabel lCity = new JLabel("City");
    private JTextField tfCity = new JTextField(10);
    private JDateChooser dchDate = new JDateChooser(new Date());

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();

    private java.util.List<Integer> listId = database.getIds("Visit");
    private int idx = 0;

    private void setMyFont(Font f)
    {
        tfid.setFont(f);
        btnLeft.setFont(f);
        btnRight.setFont(f);
        btnDelete.setFont(f);
        btnInsert.setFont(f);
        lDoctorID.setFont(f);
        lPatientID.setFont(f);
        lNameDoctor1.setFont(f);
        lSurnameDoctor1.setFont(f);
        lSpecialization1.setFont(f);
        lExperience1.setFont(f);
        lVisitPrice1.setFont(f);
        tfNameDoctor1.setFont(f);
        tfSurnameDoctor1.setFont(f);
        tfSpecialization1.setFont(f);
        tfExperience1.setFont(f);
        tfVisitPrice1.setFont(f);
        lNameDoctor2.setFont(f);
        lSurnameDoctor2.setFont(f);
        lSpecialization2.setFont(f);
        lExperience2.setFont(f);
        lVisitPrice2.setFont(f);
        tfNameDoctor2.setFont(f);
        tfSurnameDoctor2.setFont(f);
        tfSpecialization2.setFont(f);
        tfExperience2.setFont(f);
        tfVisitPrice2.setFont(f);
        lNamePatient1.setFont(f);
        lSurnamePatient1.setFont(f);
        lAgePatient1.setFont(f);
        lIllnessPatient1.setFont(f);
        tfNamePatient1.setFont(f);
        tfSurnamePatient1.setFont(f);
        tfAgePatient1.setFont(f);
        tfIllnessPatient1.setFont(f);
        lNamePatient2.setFont(f);
        lSurnamePatient2.setFont(f);
        lAgePatient2.setFont(f);
        lIllnessPatient2.setFont(f);
        tfNamePatient2.setFont(f);
        tfSurnamePatient2.setFont(f);
        tfAgePatient2.setFont(f);
        tfIllnessPatient2.setFont(f);
        lDate.setFont(f);
        lCity.setFont(f);
        tfCity.setFont(f);
        dchDate.setFont(f);
    }

    private void setDataDoctor(Optional<Doctor> d)
    {
        if(d.isPresent())
        {
            tfNameDoctor2.setText(d.get().getName());
            this.tfSurnameDoctor2.setText(d.get().getSurname());
            tfSpecialization2.setText(d.get().getSpecialization());
            tfExperience2.setText(Integer.toString(d.get().getExperience()));
            tfVisitPrice2.setText(Integer.toString(d.get().getVisitPrice()));
        }
    }
    private void setDataPatient(Optional<Patient> patientOptional)
    {
        if(patientOptional.isPresent())
        {
            this.tfNamePatient2.setText(patientOptional.get().getName());
            this.tfSurnamePatient2.setText(patientOptional.get().getSurname());
            this.tfAgePatient2.setText(Integer.toString(patientOptional.get().getAge()));
            this.tfIllnessPatient2.setText(patientOptional.get().getIllness());
        }
        else
        {
            this.tfNamePatient2.setText("");
            this.tfSurnamePatient2.setText("");
            this.tfAgePatient2.setText("");
            this.tfIllnessPatient2.setText("");
        }
    }
    private Visit getDataVisit()
    {
        int idDoctor = (int)cbDoctor.getSelectedItem();
        int idPatient = (int)cbPatient.getSelectedItem();
        String city = tfCity.getText();
        LocalDate date = Instant.ofEpochMilli(this.dchDate.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return new Visit(1,idDoctor,idPatient,date,city);
    }
    private void setDataVisit(Optional<Visit> visitOptional)
    {
        if(visitOptional.isPresent())
        {
            Optional<Doctor> doctorOptional = database.selectDoctorById(visitOptional.get().getIdDoctor());
            Optional<Patient> patientOptional = database.selectPatientById(visitOptional.get().getIdPatient());
            tfNameDoctor1.setText(doctorOptional.get().getName());
            tfSurnameDoctor1.setText(doctorOptional.get().getSurname());
            tfSpecialization1.setText(doctorOptional.get().getSpecialization());
            tfExperience1.setText(Integer.toString(doctorOptional.get().getExperience()));
            tfVisitPrice1.setText(Integer.toString(doctorOptional.get().getVisitPrice()));

            this.tfNamePatient1.setText(patientOptional.get().getName());
            this.tfSurnamePatient1.setText(patientOptional.get().getSurname());
            this.tfAgePatient1.setText(Integer.toString(patientOptional.get().getAge()));
            this.tfIllnessPatient1.setText(patientOptional.get().getIllness());
        }
        else
        {
            tfNameDoctor1.setText("");
            tfSurnameDoctor1.setText("");
            tfSpecialization1.setText("");
            tfExperience1.setText("");
            tfVisitPrice1.setText("");

            this.tfNamePatient1.setText("");
            this.tfSurnamePatient1.setText("");
            this.tfAgePatient1.setText("");
            this.tfIllnessPatient1.setText("");
        }
    }
    public PanelWizyta()
    {
        super(new GridBagLayout());

        setMyFont(new Font("consolas",Font.PLAIN,18));

        JPanel panelNavigationButtons = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelNavigationButtons = new GridBagConstraints();

        gbcPanelNavigationButtons.gridx=0;
        gbcPanelNavigationButtons.gridy=0;
        gbcPanelNavigationButtons.insets = new Insets(0,10,10,0);
        btnLeft.addActionListener(e->{
            if(idx > 0)
            {
                idx--;
                int nextId = listId.get(idx);
                tfid.setText(Integer.toString(nextId));
                setDataVisit(database.selectVisitById(nextId));
            }

        });
        panelNavigationButtons.add(btnLeft,gbcPanelNavigationButtons);

        gbcPanelNavigationButtons.gridx=1;
        gbcPanelNavigationButtons.gridy=0;
        gbcPanelNavigationButtons.insets = new Insets(0,0,10,0);
        tfid.setText(String.valueOf(listId.get(idx)));
        tfid.setHorizontalAlignment(JTextField.CENTER);
        panelNavigationButtons.add(tfid,gbcPanelNavigationButtons);

        gbcPanelNavigationButtons.gridx=2;
        gbcPanelNavigationButtons.gridy=0;
        gbcPanelNavigationButtons.insets = new Insets(0,0,10,10);
        btnRight.addActionListener(e->{
            if(idx < listId.size() - 1)
            {
                idx++;
                int nextId = listId.get(idx);
                tfid.setText(Integer.toString(nextId));
                setDataVisit(database.selectVisitById(nextId));
            }
        });
        panelNavigationButtons.add(btnRight,gbcPanelNavigationButtons);

        //Panel Doctor 1
        JPanel panelDoctor1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelLekarz1 = new GridBagConstraints();

        panelDoctor1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "Doctor",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Consolas", Font.BOLD, 16),
                Color.BLUE
        ));

        gbcPanelLekarz1.gridx = 0;
        gbcPanelLekarz1.gridy = 0;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(lNameDoctor1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 1;
        gbcPanelLekarz1.gridy = 0;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(tfNameDoctor1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 0;
        gbcPanelLekarz1.gridy = 1;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(lSurnameDoctor1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 1;
        gbcPanelLekarz1.gridy = 1;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(tfSurnameDoctor1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 0;
        gbcPanelLekarz1.gridy = 2;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(lSpecialization1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 1;
        gbcPanelLekarz1.gridy = 2;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(tfSpecialization1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 0;
        gbcPanelLekarz1.gridy = 3;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(lExperience1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 1;
        gbcPanelLekarz1.gridy = 3;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(tfExperience1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 0;
        gbcPanelLekarz1.gridy = 4;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(lVisitPrice1,gbcPanelLekarz1);

        gbcPanelLekarz1.gridx = 1;
        gbcPanelLekarz1.gridy = 4;
        gbcPanelLekarz1.ipadx = 20;
        panelDoctor1.add(tfVisitPrice1,gbcPanelLekarz1);

        //Panel Doctor 2

        JPanel panelDoctor2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelDoctor2 = new GridBagConstraints();

        panelDoctor2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "Doctor",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Consolas", Font.BOLD, 16),
                Color.BLUE
        ));

        gbcPanelDoctor2.gridx = 0;
        gbcPanelDoctor2.gridy = 0;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(lNameDoctor2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 1;
        gbcPanelDoctor2.gridy = 0;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(tfNameDoctor2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 0;
        gbcPanelDoctor2.gridy = 1;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(lSurnameDoctor2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 1;
        gbcPanelDoctor2.gridy = 1;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(tfSurnameDoctor2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 0;
        gbcPanelDoctor2.gridy = 2;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(lSpecialization2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 1;
        gbcPanelDoctor2.gridy = 2;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(tfSpecialization2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 0;
        gbcPanelDoctor2.gridy = 3;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(lExperience2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 1;
        gbcPanelDoctor2.gridy = 3;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(tfExperience2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 0;
        gbcPanelDoctor2.gridy = 4;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(lVisitPrice2,gbcPanelDoctor2);

        gbcPanelDoctor2.gridx = 1;
        gbcPanelDoctor2.gridy = 4;
        gbcPanelDoctor2.ipadx = 20;
        panelDoctor2.add(tfVisitPrice2,gbcPanelDoctor2);

        //Panel Patient 1

        JPanel panelPatient1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelPacjent = new GridBagConstraints();

        panelPatient1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "Patient",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Consolas", Font.BOLD, 16),
                Color.BLUE
        ));

        gbcPanelPacjent.gridx = 0;
        gbcPanelPacjent.gridy = 0;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(lNamePatient1,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 0;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(tfNamePatient1,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 0;
        gbcPanelPacjent.gridy = 1;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(lSurnamePatient1,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 1;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(tfSurnamePatient1,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 0;
        gbcPanelPacjent.gridy = 2;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(lAgePatient1,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 2;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(tfAgePatient1,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 0;
        gbcPanelPacjent.gridy = 3;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(lIllnessPatient1,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 3;
        gbcPanelPacjent.ipadx = 20;
        panelPatient1.add(tfIllnessPatient1,gbcPanelPacjent);


        //Panel Patient 1

        JPanel panelPatient2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelPacjent2 = new GridBagConstraints();

        panelPatient2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "Patient",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Consolas", Font.BOLD, 16),
                Color.BLUE
        ));

        gbcPanelPacjent2.gridx = 0;
        gbcPanelPacjent2.gridy = 0;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(lNamePatient2,gbcPanelPacjent2);

        gbcPanelPacjent2.gridx = 1;
        gbcPanelPacjent2.gridy = 0;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(tfNamePatient2,gbcPanelPacjent2);

        gbcPanelPacjent2.gridx = 0;
        gbcPanelPacjent2.gridy = 1;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(lSurnamePatient2,gbcPanelPacjent2);

        gbcPanelPacjent2.gridx = 1;
        gbcPanelPacjent2.gridy = 1;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(tfSurnamePatient2,gbcPanelPacjent2);

        gbcPanelPacjent2.gridx = 0;
        gbcPanelPacjent2.gridy = 2;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(lAgePatient2,gbcPanelPacjent2);

        gbcPanelPacjent2.gridx = 1;
        gbcPanelPacjent2.gridy = 2;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(tfAgePatient2,gbcPanelPacjent2);

        gbcPanelPacjent2.gridx = 0;
        gbcPanelPacjent2.gridy = 3;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(lIllnessPatient2,gbcPanelPacjent2);

        gbcPanelPacjent2.gridx = 1;
        gbcPanelPacjent2.gridy = 3;
        gbcPanelPacjent2.ipadx = 20;
        panelPatient2.add(tfIllnessPatient2,gbcPanelPacjent2);

        //Panel ID + ComboBox ?!
        JPanel panelID = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelID = new GridBagConstraints();

        gbcPanelID.gridx = 0;
        gbcPanelID.gridy = 0;
        panelID.add(lDoctorID,gbcPanelID);

        gbcPanelID.gridx = 1;
        gbcPanelID.gridy = 0;
        cbDoctorModel = new CustomComboboxModel<>(database.getIds("Doctor"));
        cbDoctor = new JComboBox<>(cbDoctorModel);
        cbDoctor.setFont(new Font("consolas",Font.PLAIN,20));
        cbDoctor.addActionListener(e->{
            setDataDoctor(database.selectDoctorById((int)cbDoctor.getSelectedItem()));
        });
        panelID.add(cbDoctor,gbcPanelID);

        gbcPanelID.gridx = 2;
        gbcPanelID.gridy = 0;
        panelID.add(lPatientID,gbcPanelID);

        gbcPanelID.gridx = 3;
        gbcPanelID.gridy = 0;
        cbPatientModel = new CustomComboboxModel<>(database.getIds("Patient"));
        cbPatient = new JComboBox<>(cbPatientModel);
        cbPatient.setFont(new Font("consolas",Font.PLAIN,20));
        cbPatient.addActionListener(e->{
            setDataPatient(database.selectPatientById((int)cbPatient.getSelectedItem()));
        });
        panelID.add(cbPatient,gbcPanelID);

        JPanel panelDate = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDate = new GridBagConstraints();
        panelDate.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "Visit",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Consolas", Font.BOLD, 16),
                Color.BLUE
        ));

        gbcDate.gridx = 0;
        gbcDate.gridy = 0;
        panelDate.add(lCity,gbcDate);

        gbcDate.gridx = 1;
        gbcDate.gridy = 0;
        panelDate.add(tfCity,gbcDate);

        gbcDate.gridx = 0;
        gbcDate.gridy = 1;
        panelDate.add(lDate,gbcDate);

        gbcDate.gridx = 1;
        gbcDate.gridy = 1;
        panelDate.add(dchDate,gbcDate);

        //Dodawanie koncowe
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(panelNavigationButtons,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(panelDoctor1,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(panelPatient1,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        btnDelete.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null, "DELETE PRESSED");
            database.deleteVisit(Integer.parseInt(tfid.getText()));
            listId = database.getIds("Visit");
            JOptionPane.showMessageDialog(null,"Usunieto wizyte nr " + tfid.getText());
        });
        add(btnDelete,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(panelID,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(panelDoctor2,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(panelDate,gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        add(panelPatient2,gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        btnInsert.addActionListener(e->{
            database.insertVisit(getDataVisit());
            listId = database.getIds("Visit");
            JOptionPane.showMessageDialog(null,"Dodano wizyte");
        });
        add(btnInsert,gbc);
        setDataPatient(database.selectPatientById((int)cbPatient.getSelectedItem()));
        setDataDoctor(database.selectDoctorById((int)cbDoctor.getSelectedItem()));
        setDataVisit(database.selectVisitById(Integer.parseInt(tfid.getText())));

    }
}

