package com.piwowarski.panels;

import com.piwowarski.classes.Patient;

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
public class PanelPacjent extends JPanel
{
    private JLabel lName = new JLabel("Name");
    private JLabel lSurname = new JLabel("Surname");
    private JLabel lAge = new JLabel("Age");
    private JLabel lIllness = new JLabel("Illness");

    private JTextField tfName = new JTextField(20);
    private JTextField tfSurname = new JTextField(20);
    private JTextField tfAge = new JTextField(20);
    private JTextField tfIllness = new JTextField(20);

    private JTextField tfId = new JTextField(5);

    private JButton btnLeft = new JButton("<<<");
    private JButton btnRight = new JButton(">>>");
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();

    private java.util.List<Integer> listId = database.getIds("Patient");
    private int idx=0;

    private void setMyFont(Font f)
    {
        lName.setFont(f);
        lSurname.setFont(f);
        lAge.setFont(f);
        lIllness.setFont(f);
        tfName.setFont(f);
        tfSurname.setFont(f);
        tfAge.setFont(f);
        tfIllness.setFont(f);
        tfId.setFont(f);
        btnLeft.setFont(f);
        btnRight.setFont(f);
        btnInsert.setFont(f);
        btnUpdate.setFont(f);
        btnDelete.setFont(f);
    }

    private Patient getDataPatient()
    {
        int id = Integer.parseInt(tfId.getText());
        String name = tfName.getText();
        String surname = tfSurname.getText();
        int age = Integer.parseInt(tfAge.getText());
        String illness = tfIllness.getText();
        return new Patient(id,name,surname,age,illness);
    }
    private void setDataPatient(Optional<Patient> patientOptional)
    {
        if(patientOptional.isPresent())
        {
            this.tfId.setText(Integer.toString(patientOptional.get().getId()));
            this.tfName.setText(patientOptional.get().getName());
            this.tfSurname.setText(patientOptional.get().getSurname());
            this.tfAge.setText(Integer.toString(patientOptional.get().getAge()));
            this.tfIllness.setText(patientOptional.get().getIllness());
        }
        else
        {
            this.tfId.setText("");
            this.tfName.setText("");
            this.tfSurname.setText("");
            this.tfAge.setText("");
            this.tfIllness.setText("");
        }
    }

    public PanelPacjent()
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
                setDataPatient(database.selectPatientById(nextId));
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
                setDataPatient(database.selectPatientById(nextId));
            }
        });
        panelNavigationButtons.add(btnRight, gbcPanelNavigationButtons);


        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelPacjent = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
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
        panelFields.add(lName,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 0;
        gbcPanelPacjent.ipadx = 20;
        panelFields.add(tfName,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 0;
        gbcPanelPacjent.gridy = 1;
        gbcPanelPacjent.ipadx = 20;
        panelFields.add(lSurname,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 1;
        gbcPanelPacjent.ipadx = 20;
        panelFields.add(tfSurname,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 0;
        gbcPanelPacjent.gridy = 2;
        gbcPanelPacjent.ipadx = 20;
        panelFields.add(lAge,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 2;
        gbcPanelPacjent.ipadx = 20;
        panelFields.add(tfAge,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 0;
        gbcPanelPacjent.gridy = 3;
        gbcPanelPacjent.ipadx = 20;
        panelFields.add(lIllness,gbcPanelPacjent);

        gbcPanelPacjent.gridx = 1;
        gbcPanelPacjent.gridy = 3;
        gbcPanelPacjent.ipadx = 20;
        panelFields.add(tfIllness,gbcPanelPacjent);
        JPanel panelOperationsButton = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperationsButton = new GridBagConstraints();

        gbcPanelOperationsButton.gridx = 0;
        gbcPanelOperationsButton.gridy = 0;
        btnInsert.addActionListener(e->{
            database.insertPatient(getDataPatient());
            listId = database.getIds("Patient");
            JOptionPane.showMessageDialog(null,"Dodano pacjenta");
        });
        panelOperationsButton.add(btnInsert, gbcPanelOperationsButton);

        gbcPanelOperationsButton.gridx = 1;
        gbcPanelOperationsButton.gridy = 0;
        btnInsert.addActionListener(e->{
            database.updatePatient(getDataPatient());
            JOptionPane.showMessageDialog(null,"Zaktualizowano pacjenta");
        });
        panelOperationsButton.add(btnUpdate, gbcPanelOperationsButton);

        gbcPanelOperationsButton.gridx = 2;
        gbcPanelOperationsButton.gridy = 0;
        btnDelete.addActionListener(e->{
            database.deletePatient(Integer.parseInt(tfId.getText()));
            listId = database.getIds("Patient");
            JOptionPane.showMessageDialog(null,"Usunieto pacjenta " + tfId.getText());
        });
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

        setDataPatient(database.selectPatientById(Integer.parseInt(tfId.getText())));
    }
}
