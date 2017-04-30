package com.piwowarski.panels;

import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.models.CustomComboboxModel;
import com.piwowarski.models.CustomListModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Piwowarski Tomasz on 16.04.2017.
 */
public class PanelHistoria extends JPanel
{
    private CustomComboboxModel<String> cbMedicalHistoryModel;
    private JComboBox<String> cbMedicalHistory;

    private CustomListModel<String> listIllnessModel;
    private JList<String> listIllness;

    private JLabel lPatient = new JLabel("Patient:");

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();

    private void setMyFont(Font f)
    {
        cbMedicalHistory.setFont(f);
        lPatient.setFont(f);
        listIllness.setFont(f);
    }

    public PanelHistoria()
    {

        super(new BorderLayout());

        cbMedicalHistoryModel = new CustomComboboxModel<>(database.getNameSurname("Patient"));
        cbMedicalHistory = new JComboBox<>(cbMedicalHistoryModel);
        cbMedicalHistory.addActionListener(e->{
            listIllnessModel.update(database.selectMedicalHistory((String)cbMedicalHistory.getSelectedItem()));
            repaint();
        });

        listIllnessModel = new CustomListModel<>(database.selectMedicalHistory((String)cbMedicalHistory.getSelectedItem()));
        listIllness = new JList<>(listIllnessModel);

        setMyFont(new Font("consolas",Font.PLAIN,24));
        JPanel panelName = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        panelName.add(lPatient,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelName.add(cbMedicalHistory,gbc);

        /*DefaultListCellRenderer renderer =  (DefaultListCellRenderer)listIllness.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);*/

        add(panelName,BorderLayout.NORTH);
        add(new JScrollPane(listIllness),BorderLayout.CENTER);
    }
}
