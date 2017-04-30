package com.piwowarski.panels;


import com.piwowarski.classes.Doctor;
import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.models.CustomComboboxModel;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Piwowarski Tomasz on 03.02.2017.
 */
public class PanelStatystyka extends JPanel
{
    private JLabel lStat1 = new JLabel("Lekarz o największej pensji");
    private JLabel lStat2 = new JLabel("Zestawienie specializacji lekarzy");
    private JLabel lStat3 = new JLabel("Najczestsza choroba");
    private JLabel lStat4 = new JLabel("Zestawienie dat wizyt");
    private JLabel lStat5 = new JLabel("Zestawienie miejscowości");

    private JTextField tfStat1 = new JTextField(33);
    private JTextField tfStat2 = new JTextField(20);
    private JTextField tfStat3 = new JTextField(33);
    private JTextField tfStat4 = new JTextField(20);
    private JTextField tfStat5 = new JTextField(20);

    CustomComboboxModel<String> cbmodSpecialization;
    CustomComboboxModel<LocalDate> cbmodVisitDate;
    CustomComboboxModel<String> cbmodCities;

    JComboBox<String> cbxSpecialization;
    JComboBox<LocalDate> cbxVisitDate;
    JComboBox<String> cbxCities;

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();
    private void setMyFont(Font f)
    {
        lStat1.setFont(f);
        lStat2.setFont(f);
        lStat3.setFont(f);
        lStat4.setFont(f);
        lStat5.setFont(f);
        tfStat1.setFont(f);
        tfStat1.setHorizontalAlignment(JTextField.CENTER);
        tfStat2.setFont(f);
        tfStat2.setHorizontalAlignment(JTextField.CENTER);
        tfStat3.setFont(f);
        tfStat3.setHorizontalAlignment(JTextField.CENTER);
        tfStat4.setFont(f);
        tfStat4.setHorizontalAlignment(JTextField.CENTER);
        tfStat5.setFont(f);
        tfStat5.setHorizontalAlignment(JTextField.CENTER);

    }
    private void getBestEranDoctor()
    {
        java.util.List<Doctor> doctorList= database.selectDoctor();
        java.util.List<Integer> earnList = new ArrayList<>();
        doctorList.forEach( x -> {
            earnList.add(x.getVisitPrice() * database.countPatientInDoctor(x));
        });
        int idx =0;
        for (int i = 1; i < earnList.size(); i++)
        {
            if(earnList.get(i) > earnList.get(idx))
            {
                idx = i;
            }
        }
        tfStat1.setText("Lekarz: " + doctorList.get(idx).getName() + " " + doctorList.get(idx).getSurname() + " zarobił "
                + earnList.get(idx));
    }
    private void getMostCommonIllness()
    {

        java.util.List<String> illnessList = database.selectAllIllness();
        if(illnessList.size()>0)
        {
            java.util.List<Integer> quantityList = new ArrayList<>();
            illnessList.forEach(x ->
            {
                quantityList.add(database.countPatientIllOn(x));
            });
            int idx = 0;
            for (int i = 1; i < quantityList.size(); i++)
            {
                if (quantityList.get(i) > quantityList.get(idx))
                {
                    idx = i;
                }
            }
            tfStat3.setText(illnessList.get(idx) + " liczba zachorowań " + quantityList.get(idx));
        }

    }
    public PanelStatystyka()
    {
        super(new GridBagLayout());

        setMyFont(new Font("consolas",Font.PLAIN,20));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lStat1,gbc);

        gbc.ipady = 25;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth =2;
        add(tfStat1,gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lStat2,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        cbmodSpecialization = new CustomComboboxModel<>(database.selectNameInnerJoin("D.specialization"));
        cbxSpecialization = new JComboBox<>(cbmodSpecialization);
        cbxSpecialization.setFont(new Font("consolas",Font.PLAIN,20));
        cbxSpecialization.addActionListener(e->{
            tfStat2.setText(String.valueOf(database.countSpecialization("Doctor","specialization",
                    (String)cbxSpecialization.getSelectedItem())));
        });
        add(cbxSpecialization,gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;

        tfStat2.setText(String.valueOf(database.countSpecialization("Doctor","specialization",
                (String)cbxSpecialization.getSelectedItem())));
        add(tfStat2,gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lStat3,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(tfStat3,gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lStat4,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        cbmodVisitDate = new CustomComboboxModel<>(database.selectDateInnerJoin());
        cbxVisitDate = new JComboBox<>(cbmodVisitDate);
        cbxVisitDate.setFont(new Font("consolas",Font.PLAIN,20));
        cbxVisitDate.addActionListener(e->{
            tfStat4.setText(String.valueOf(database.counDate((LocalDate)cbxVisitDate.getSelectedItem())));
        });
        add(cbxVisitDate,gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        tfStat4.setText(String.valueOf(database.counDate((LocalDate)cbxVisitDate.getSelectedItem())));
        add(tfStat4,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lStat5,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        cbmodCities = new CustomComboboxModel<>(database.selectNameInnerJoin("V.city"));
        cbxCities = new JComboBox<>(cbmodCities);
        cbxCities.setFont(new Font("consolas",Font.PLAIN,20));
        cbxCities.addActionListener(e->{
            tfStat5.setText(String.valueOf((database.countSpecialization("Visit","city",
                    (String)cbxCities.getSelectedItem()))));
        });
        add(cbxCities,gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        tfStat5.setText(String.valueOf((database.countSpecialization("Visit","city",
                (String)cbxCities.getSelectedItem()))));
        add(tfStat5,gbc);
        getBestEranDoctor();
        getMostCommonIllness();
    }
}
