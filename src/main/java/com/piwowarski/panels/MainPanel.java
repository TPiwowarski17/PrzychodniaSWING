package com.piwowarski.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Piwowarski Tomasz on 10.02.2017.
 */
public class MainPanel extends JPanel
{
    private PanelWizyta panelWizyta = new PanelWizyta();
    private PanelStatystyka panelStatystyka = new PanelStatystyka();
    private PanelPacjent panelPacjent = new PanelPacjent();
    private PanelLekarz panelLekarz = new PanelLekarz();
    private PanelFilter panelFilter = new PanelFilter();

    private final String PANEL_WIZYTA = "PANEL_WIZYTA";
    private final String PANEL_STATYSTYK = "PANEL_STATYSTYK";
    private final String PANEL_LEKARZ = "PANEL_LEKARZ";
    private final String PANEL_PACJENT = "PANEL_PACJENT";
    private final String PANEL_FILTER = "PANEL_FILTER";

    private void createWindow(JPanel panel)
    {
        JFrame frame = new JFrame("Filter");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setContentPane(panel);
        panelFilter.setVisible(true);
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (InstantiationException ex)
        {
            ex.printStackTrace();
        } catch (IllegalAccessException ex)
        {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex)
        {
            ex.printStackTrace();
        }

        frame.pack();
        frame.setVisible(true);
    }
    public MainPanel()
    {
        super(new CardLayout());
        add(panelStatystyka,PANEL_STATYSTYK);
        add(panelLekarz,PANEL_LEKARZ);
        add(panelPacjent,PANEL_PACJENT);
    }
    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("PANELS");

        JMenuItem menuPanelStatystyk = new JMenuItem(PANEL_STATYSTYK);
        menuPanelStatystyk.addActionListener(e->{
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this,PANEL_STATYSTYK);
        });

        JMenuItem menuPanelLekarz = new JMenuItem(PANEL_LEKARZ);
        menuPanelLekarz.addActionListener(e->{
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this,PANEL_LEKARZ);
        });

        JMenuItem menuPanelPacjent = new JMenuItem(PANEL_PACJENT);
        menuPanelPacjent.addActionListener(e->{
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this,PANEL_PACJENT);
        });
        JMenuItem menuPanelFilter = new JMenuItem(PANEL_FILTER);
        menuPanelFilter.addActionListener(e->{
            createWindow(panelFilter);
        });
        JMenuItem menuPanelWyswietlen = new JMenuItem(PANEL_WIZYTA);
        menuPanelWyswietlen.addActionListener(e->{
           createWindow(panelWizyta);
        });
        menu.add(menuPanelLekarz);
        menu.add(menuPanelPacjent);
        menu.add(menuPanelStatystyk);
        menu.add(menuPanelWyswietlen);
        menu.add(menuPanelFilter);

        menuBar.add(menu);

        return menuBar;
    }
}
