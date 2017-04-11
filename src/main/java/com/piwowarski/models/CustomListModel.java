package com.piwowarski.models;

import javax.swing.*;
import java.util.List;

/**
 * Created by Piwowarski Tomasz on 10.03.2017.
 */
public class CustomListModel<T> extends AbstractListModel<T>
{
    private List<T> items;

    public CustomListModel(List<T> items)
    {
        this.items = items;
    }
    public void update(List<T> items)
    {
        this.items=items;
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
