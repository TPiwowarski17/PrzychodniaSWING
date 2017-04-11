package com.piwowarski.models;

import com.piwowarski.classes.DoctorPatient;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Piwowarski Tomasz on 03.03.2017.
 */
public class CustomTableModel extends AbstractTableModel
{
    private List<DoctorPatient> list;
    private List<String> headers;

    public CustomTableModel(List<DoctorPatient> list, List<String> headers)
    {
        this.list = list;
        this.headers = headers;
    }

    public void update(List<DoctorPatient> list)
    {
        this.list = list;
    }

    @Override
    public int getRowCount()
    {
        return list.size();
    }

    @Override
    public int getColumnCount()
    {
        return headers.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        DoctorPatient doctorPatient = list.get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return doctorPatient.getId();
            case 1:
                return doctorPatient.getDoctorName();
            case 2:
                return doctorPatient.getDoctorSurname();
            case 3:
                return doctorPatient.getSpecialization();
            case 4:
                return doctorPatient.getExperience();
            case 5:
                return doctorPatient.getVisitPrice();
            case 6:
                return doctorPatient.getPatientName();
            case 7:
                return doctorPatient.getPatientSurname();
            case 8:
                return doctorPatient.getAge();
            case 9:
                return doctorPatient.getIllness();
            case 10:
                return doctorPatient.getDate();
            default:
                return doctorPatient.getCity();

        }
    }

    @Override
    public String getColumnName(int column)
    {
        return headers.get(column);
    }
}
