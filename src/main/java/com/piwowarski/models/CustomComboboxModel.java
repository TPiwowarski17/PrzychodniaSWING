package com.piwowarski.models;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * Created by Piwowarski Tomasz on 03.02.2017.
 */
public class CustomComboboxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T>
{
    private List<T> items;
    private T selectedItem;

    public CustomComboboxModel(List<T> items)
    {
        update(items);
    }

    public void update(List<T> items)
    {
        this.items = items;
        if (items != null && !items.isEmpty())
        {
            this.selectedItem = items.get(0);
        }
    }

    @Override
    public void setSelectedItem(Object anItem)
    {
        selectedItem = (T)anItem;
    }

    @Override
    public Object getSelectedItem()
    {
        return selectedItem;
    }

    @Override
    public int getSize()
    {
        return items.size();
    }

    @Override
    public T getElementAt(int index)
    {
        return items.get(index);
    }
}
