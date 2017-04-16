package com.piwowarski.main;


import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.panels.PanelLogin;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Piwowarski Tomasz on 02.02.2017.
 */
public class Main
{
    public static void createWindow()
    {
        JFrame frame = new JFrame("Przychodnia Ostatni Dech :D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelLogin panelLogin = new PanelLogin();
        panelLogin.setVisible(true);
        frame.setContentPane(panelLogin);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth/4,screenHeight/2);
        frame.setLocation(screenWidth/3,screenHeight/4);
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        //frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args)
    {
        //PrzychodniaDao db = PrzychodniaDaoImpl.getInstance();

        javax.swing.SwingUtilities.invokeLater(
                () -> createWindow()
        );
    }

}

