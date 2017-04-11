package com.piwowarski.panels;

import com.piwowarski.classes.Doctor;
import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Optional;

/**
 * Created by Piwowarski Tomasz on 03.02.2017.
 */
public class PanelLekarz extends JPanel
{
    private JLabel lNameDoctor = new JLabel("Name");
    private JLabel lSurnameDoctor = new JLabel("Surname");
    private JLabel lSpecialization =new JLabel("Specialization");
    private JLabel lExperience = new JLabel("Experience");
    private JLabel lVisitPrice = new JLabel("Visit price");

    private JTextField tfNameDoctor = new JTextField(20);
    private JTextField tfSurnameDoctor = new JTextField(20);
    private JTextField tfSpecialization = new JTextField(20);
    private JTextField tfExperience = new JTextField(20);
    private JTextField tfVisitPrice = new JTextField(20);

    private JTextField tfId = new JTextField(5);

    private JButton btnLeft = new JButton("<<<");
    private JButton btnRight = new JButton(">>>");
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();

    private java.util.List<Integer> listId = database.getIds("Doctor");
    private int idx=0;

    private void setMyFont(Font f)
    {
        lNameDoctor.setFont(f);
        lSurnameDoctor.setFont(f);
        lSpecialization.setFont(f);
        lExperience.setFont(f);
        lVisitPrice.setFont(f);
        tfNameDoctor.setFont(f);
        tfSurnameDoctor.setFont(f);
        tfSpecialization.setFont(f);
        tfExperience.setFont(f);
        tfVisitPrice.setFont(f);
        tfId.setFont(f);
        btnLeft.setFont(f);
        btnRight.setFont(f);
        btnInsert.setFont(f);
        btnUpdate.setFont(f);
        btnDelete.setFont(f);
    }
    private Doctor getDataDoctor()
    {
        int id = Integer.parseInt(tfId.getText());
        String name = tfNameDoctor.getText();
        String surname = tfSurnameDoctor.getText();
        String specialization = tfSpecialization.getText();
        int experience = Integer.parseInt(tfExperience.getText());
        int visitPrice = Integer.parseInt(tfVisitPrice.getText());
        return new Doctor(id,name,surname,specialization,experience,visitPrice);
    }
    private void setDataDoctor(Optional<Doctor> d)
    {
        if(d.isPresent())
        {
            this.tfNameDoctor.setText(d.get().getName());
            tfSurnameDoctor.setText(d.get().getSurname());
            tfSpecialization.setText(d.get().getSpecialization());
            tfExperience.setText(Integer.toString(d.get().getExperience()));
            tfVisitPrice.setText(Integer.toString(d.get().getVisitPrice()));
        }
        else
        {
            tfNameDoctor.setText("");
            tfSurnameDoctor.setText("");
            tfSpecialization.setText("");
            tfExperience.setText("");
            tfVisitPrice.setText("");
        }
    }

    public PanelLekarz()
    {
        super(new GridBagLayout());
        setMyFont(new Font("consolas",Font.PLAIN,24));
        JPanel panelNavigationButtons = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelNavigationButtons = new GridBagConstraints();
        gbcPanelNavigationButtons.gridx = 0;
        gbcPanelNavigationButtons.gridy = 0;
        gbcPanelNavigationButtons.insets = new Insets(0, 0, 10, 10);
        btnLeft.setFont(new Font("ComicSans", Font.BOLD, 20));
        btnLeft.setBackground(new Color(100, 20, 100));
        btnLeft.addActionListener(e->{
            if(idx > 0)
            {
                idx--;
                int nextId = listId.get(idx);
                this.tfId.setText(Integer.toString(nextId));
                setDataDoctor(database.selectDoctorById(nextId));
            }
        });
        panelNavigationButtons.add(btnLeft, gbcPanelNavigationButtons);

        gbcPanelNavigationButtons.gridx = 1;
        gbcPanelNavigationButtons.gridy = 0;
        gbcPanelNavigationButtons.insets = new Insets(0, 0, 10, 10);
        tfId.setText(String.valueOf(listId.get(idx)));
        tfId.setHorizontalAlignment(JTextField.CENTER);
        panelNavigationButtons.add(tfId, gbcPanelNavigationButtons);

        gbcPanelNavigationButtons.gridx = 2;
        gbcPanelNavigationButtons.gridy = 0;
        gbcPanelNavigationButtons.insets = new Insets(0, 0, 10, 0);
        btnRight.setFont(new Font("ComicSans", Font.BOLD, 20));
        btnRight.setBackground(new Color(100, 20, 100));
        btnRight.addActionListener(e->{
            if(idx < listId.size()-1)
            {
                idx++;
                int nextId = listId.get(idx);
                this.tfId.setText(Integer.toString(nextId));
                Optional<Doctor> d = database.selectDoctorById(nextId);
                setDataDoctor(d);
            }

        });
        panelNavigationButtons.add(btnRight, gbcPanelNavigationButtons);

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelLekarz = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "Doctor",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Consolas", Font.BOLD, 25),
                Color.BLUE
        ));

        gbcPanelLekarz.gridx = 0;
        gbcPanelLekarz.gridy = 0;
        panelFields.add(lNameDoctor,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 1;
        gbcPanelLekarz.gridy = 0;
        panelFields.add(tfNameDoctor,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 0;
        gbcPanelLekarz.gridy = 1;
        panelFields.add(lSurnameDoctor,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 1;
        gbcPanelLekarz.gridy = 1;
        panelFields.add(tfSurnameDoctor,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 0;
        gbcPanelLekarz.gridy = 2;
        panelFields.add(lSpecialization,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 1;
        gbcPanelLekarz.gridy = 2;
        panelFields.add(tfSpecialization,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 0;
        gbcPanelLekarz.gridy = 3;
        panelFields.add(lExperience,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 1;
        gbcPanelLekarz.gridy = 3;
        panelFields.add(tfExperience,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 0;
        gbcPanelLekarz.gridy = 4;
        panelFields.add(lVisitPrice,gbcPanelLekarz);

        gbcPanelLekarz.gridx = 1;
        gbcPanelLekarz.gridy = 4;
        panelFields.add(tfVisitPrice,gbcPanelLekarz);

        JPanel panelOperationsButton = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperationsButton = new GridBagConstraints();

        btnInsert.addActionListener( e->{
            database.insertDoctor(getDataDoctor());
            listId = database.getIds("Doctor");
            JOptionPane.showMessageDialog(null,"Dodano lekarza");
        });
        gbcPanelOperationsButton.gridx = 0;
        gbcPanelOperationsButton.gridy = 0;
        panelOperationsButton.add(btnInsert, gbcPanelOperationsButton);

        btnUpdate.addActionListener(e->{
            database.updateDoctor(getDataDoctor());
            JOptionPane.showMessageDialog(null,"Zaktualizowano lekarza");
        });
        gbcPanelOperationsButton.gridx = 1;
        gbcPanelOperationsButton.gridy = 0;
        panelOperationsButton.add(btnUpdate, gbcPanelOperationsButton);


        btnDelete.addActionListener( e->{
            database.deleteDoctor(Integer.parseInt(tfId.getText()));
            listId = database.getIds("Doctor");
            JOptionPane.showMessageDialog(null,"Usunięto lekarza nr " + tfId.getText());
        });
        gbcPanelOperationsButton.gridx = 2;
        gbcPanelOperationsButton.gridy = 0;
        panelOperationsButton.add(btnDelete, gbcPanelOperationsButton);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panelNavigationButtons, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(panelFields, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(panelOperationsButton, gbc);
        setDataDoctor(database.selectDoctorById(Integer.parseInt(tfId.getText())));
    }
}
