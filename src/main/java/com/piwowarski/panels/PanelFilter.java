package com.piwowarski.panels;


import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.models.CustomComboboxModel;
import com.piwowarski.models.CustomListModel;
import com.piwowarski.models.CustomTableModel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Piwowarski Tomasz on 03.03.2017.
 */
public class PanelFilter extends JPanel
{
    private JCheckBox chbName = new JCheckBox("Name");
    private JCheckBox chbSurname = new JCheckBox("Surname");
    private JCheckBox chbSpecialization = new JCheckBox("Specialization");
    private JCheckBox chbPrice = new JCheckBox("Price");
    private JCheckBox chbCalendar = new JCheckBox("Date");

    private JLabel lFromPrice = new JLabel(" from ");
    private JLabel lToPrice = new JLabel(" to ");
    private JLabel lFromDate = new JLabel(" from ");
    private JLabel lToDate = new JLabel(" to ");

    private JDateChooser dchFromDate = new JDateChooser(new Date(0,0,1));
    private JDateChooser dchToDate = new JDateChooser(new Date(200,11,31));

    private CustomListModel<String> clName;
    private CustomListModel<String> clSurname;
    private CustomListModel<String> clSpecialization;

    private JList<String> listName;
    private JList<String> listSurname;
    private JList<String> listSpecialization;

    private CustomComboboxModel<Integer> cbLowerPriceModel;
    private CustomComboboxModel<Integer> cbUpperPriceModel;
    private JComboBox<Integer> cbLowerPrice;
    private JComboBox<Integer> cbUpperPrice;

    private JButton bFilter = new JButton("Filter");
    private JButton bReset = new JButton("Reset");

    private CustomTableModel customTableModel;
    private JTable table;

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();

    private void setMyFont(Font f)
    {
        chbName.setFont(f);
        chbSurname.setFont(f);
        chbSpecialization.setFont(f);
        chbPrice.setFont(f);
        chbCalendar.setFont(f);
        lFromPrice.setFont(f);
        lToPrice.setFont(f);
        lFromDate.setFont(f);
        lToDate.setFont(f);
        dchFromDate.setFont(f);
        dchToDate.setFont(f);
        bFilter.setFont(f);
        bReset.setFont(f);
    }

    public PanelFilter()
    {
        super(new BorderLayout());

        setMyFont(new Font("consolas",Font.PLAIN,20));
        //Panela Table
        List<String> headers = new ArrayList<>();
        Collections.addAll(headers,"id","doctorName","doctorSurname","specialization","experience","visitPrice",
                "patientName","patientSurname","age","illness","date","city");
        customTableModel = new CustomTableModel(database.selectInnerJoin(), headers);
        table = new JTable(customTableModel);
        table.setFont(new Font("consolas",Font.PLAIN,14));


        JPanel panelCheckBox = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCheckBox = new GridBagConstraints();

        gbcCheckBox.gridx = 0;
        gbcCheckBox.gridy = 0;
        panelCheckBox.add(chbName,gbcCheckBox);

        gbcCheckBox.gridx = 0;
        gbcCheckBox.gridy = 1;
        clName = new CustomListModel<>(database.selectNameInnerJoin("D.name"));
        listName = new JList<>(clName);
        listName.setFont(new Font("consolas",Font.PLAIN,14));
        panelCheckBox.add(new JScrollPane(listName),gbcCheckBox);

        gbcCheckBox.gridx = 1;
        gbcCheckBox.gridy = 0;

        panelCheckBox.add(chbSurname,gbcCheckBox);

        gbcCheckBox.gridx = 1;
        gbcCheckBox.gridy = 1;
        clSurname = new CustomListModel<>(database.selectNameInnerJoin("D.surname"));
        listSurname = new JList<>(clSurname);
        listSurname.setFont(new Font("consolas",Font.PLAIN,14));
        panelCheckBox.add(new JScrollPane(listSurname),gbcCheckBox);

        gbcCheckBox.gridx = 2;
        gbcCheckBox.gridy = 0;
        panelCheckBox.add(chbSpecialization,gbcCheckBox);

        gbcCheckBox.gridx = 2;
        gbcCheckBox.gridy = 1;
        clSpecialization = new CustomListModel<>(database.selectNameInnerJoin("D.specialization"));
        listSpecialization = new JList<>(clSpecialization);
        listSpecialization.setFont(new Font("consolas",Font.PLAIN,14));
        panelCheckBox.add(new JScrollPane(listSpecialization),gbcCheckBox);

        //Panel Cena
        JPanel panelPrice = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPrice = new GridBagConstraints();

        gbcPrice.gridx = 0;
        gbcPrice.gridy = 0;
        panelPrice.add(chbPrice,gbcPrice);

        gbcPrice.gridx = 1;
        gbcPrice.gridy = 0;
        panelPrice.add(lFromPrice,gbcPrice);

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,25,50,75,100,125,150,200,250,300);
        cbLowerPriceModel = new CustomComboboxModel<>(list);
        cbUpperPriceModel = new CustomComboboxModel<>(list);
        cbLowerPrice = new JComboBox<>(cbLowerPriceModel);
        cbUpperPrice = new JComboBox<>(cbUpperPriceModel);
        cbLowerPrice.addActionListener(e->{
            if((int)cbLowerPrice.getSelectedItem() > (int)cbUpperPrice.getSelectedItem())
            {
                int idxUpper = cbUpperPrice.getSelectedIndex();
                cbLowerPrice.setSelectedIndex(idxUpper > 0 ? idxUpper - 1 : idxUpper );
            }
        });
        cbUpperPrice.addActionListener(e->{
            if((int)cbLowerPrice.getSelectedItem() > (int)cbUpperPrice.getSelectedItem())
            {
                int idxLower = cbLowerPrice.getSelectedIndex();
                cbUpperPrice.setSelectedIndex( idxLower < list.size() - 1 ? idxLower+1:idxLower);
            }
        });


        gbcPrice.gridx = 2;
        gbcPrice.gridy = 0;
        panelPrice.add(cbLowerPrice,gbcPrice);

        gbcPrice.gridx = 3;
        gbcPrice.gridy = 0;
        panelPrice.add(lToPrice,gbcPrice);

        gbcPrice.gridx = 4;
        gbcPrice.gridy = 0;
        panelPrice.add(cbUpperPrice,gbcPrice);

        JPanel panelButton = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();

        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        this.bFilter.addActionListener( e -> {
            boolean nameCheckBox = chbName.isSelected();
            boolean surnameCheckBox = chbSurname.isSelected();
            boolean specializationChceckBox = chbSpecialization.isSelected();
            boolean dateCheckBox = chbCalendar.isSelected();
            boolean priceCheckBox = chbPrice.isSelected();
            List<String> listNameStr = listName.getSelectedValuesList();
            List<String> listSurnameStr = listSurname.getSelectedValuesList();
            List<String> listSpecializationStr = listSpecialization.getSelectedValuesList();

            int lowerPrice = (int)cbLowerPrice.getSelectedItem();
            int upperPrice = (int)cbUpperPrice.getSelectedItem();

            LocalDate dateFrom = Instant.ofEpochMilli(this.dchFromDate.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dateTo = Instant.ofEpochMilli(this.dchToDate.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

            this.customTableModel.update(database.filter(nameCheckBox,surnameCheckBox,specializationChceckBox,
            dateCheckBox,priceCheckBox,listNameStr,listSurnameStr,listSpecializationStr,dateFrom,dateTo,lowerPrice,upperPrice));
            this.table.updateUI();

        });
        panelButton.add(bFilter,gbcButton);


        bReset.addActionListener(e->{
            this.customTableModel.update(database.selectInnerJoin());
            this.table.updateUI();
        });
        gbcButton.gridx = 1;
        gbcButton.gridy = 0;
        panelButton.add(bReset,gbcButton);


        //Panel Calendar


        JPanel panelCalendar = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCalendar = new GridBagConstraints();

        dchFromDate.setMaxSelectableDate(dchToDate.getDate());
        dchToDate.setMinSelectableDate(dchFromDate.getDate());

        gbcCalendar.gridx = 0;
        gbcCalendar.gridy = 0;
        panelCalendar.add(chbCalendar,gbcCalendar);

        gbcCalendar.gridx = 1;
        gbcCalendar.gridy = 0;
        panelCalendar.add(lFromDate,gbcCalendar);

        gbcCalendar.gridx = 2;
        gbcCalendar.gridy = 0;
        panelCalendar.add(dchFromDate,gbcCalendar);

        gbcCalendar.gridx = 3;
        gbcCalendar.gridy = 0;
        panelCalendar.add(lToDate,gbcCalendar);

        gbcCalendar.gridx = 4;
        gbcCalendar.gridy = 0;
        panelCalendar.add(dchToDate,gbcCalendar);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelDown = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelDown.add(panelCheckBox,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDown.add(panelPrice,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelDown.add(panelCalendar,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelDown.add(panelButton,gbc);



        add(new JScrollPane(table),BorderLayout.CENTER);
        add(panelDown, BorderLayout.PAGE_END);

    }

}
