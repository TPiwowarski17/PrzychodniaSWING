package com.piwowarski.panels;

import com.piwowarski.classes.User;
import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.encryption.Encryption;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * Created by Piwowarski Tomasz on 16.02.2017.
 */
public class PanelLogin extends JPanel
{
    private JLabel lID = new JLabel("ID");
    private JLabel lPassword = new JLabel("Pass:");

    private JTextField tfID = new JTextField(20);
    private JPasswordField tfPassword = new JPasswordField(20);

    private JButton bLogin = new JButton("Login");
    private JButton bCancel = new JButton("Cancel");
    private JButton bRegistration = new JButton("Registration");

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();

    private Font font = new Font("consolas",Font.PLAIN,24);

    private void setMyFont(Font f)
    {
        lID.setFont(f);
        lPassword.setFont(f);
        bLogin.setFont(f);
        bCancel.setFont(f);
        bRegistration.setFont(f);
    }
    private void addToPanel(JPanel p,JComponent c,GridBagConstraints gbc,int x,int y)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        p.add(c,gbc);
    }
    private void addToPanel(JPanel p,JComponent c,GridBagConstraints gbc,int x,int y,int ipadx)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.ipadx = ipadx;
        p.add(c,gbc);
    }
    private void createRegistratin()
    {
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelRejestracja panel = new PanelRejestracja();
        panel.setVisible(true);
        frame.setContentPane(panel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth/3,screenHeight/4);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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

        frame.pack();
        frame.setVisible(true);
    }
    public Optional<User> isCorectLogin()
    {
        String userName = tfID.getText();
        String password = String.valueOf(tfPassword.getPassword());
        Encryption enc = new Encryption(password);
        enc.encrypt();
        password = enc.getWordEncrypt();
       return database.isCorect(userName,password);
    }
    public PanelLogin()
    {
        super(new GridBagLayout());
        GridBagConstraints gbcLog = new GridBagConstraints();

        lID.setFont(font);
        addToPanel(this,lID,gbcLog,0,0);
        gbcLog.gridwidth = 2;
        tfID.setFont(font);
        addToPanel(this,tfID,gbcLog,1,0);
        gbcLog.gridwidth = 1;

        lPassword.setFont(font);
        addToPanel(this,lPassword,gbcLog,0,1);
        gbcLog.gridwidth = 2;
        tfPassword.setFont(font);
        addToPanel(this,tfPassword,gbcLog,1,1);
        gbcLog.gridwidth = 1;

        bLogin.setFont(font);
        bLogin.addActionListener(e->{
            Optional<User> user = isCorectLogin();
            if(user.isPresent())
            {
                MainPanel mp = new MainPanel();
                JFrame frame =(JFrame)getRootPane().getParent();
                frame.setContentPane(mp);
                frame.setJMenuBar(mp.createMenuBar());
                frame.setLocation(0,0);
                frame.pack();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Nie zalogowano");
                tfID.setText("");
                tfPassword.setText("");
            }
        });
        addToPanel(this,bLogin,gbcLog,0,2);

        bRegistration.setFont(font);
        bRegistration.addActionListener(e->{
            createRegistratin();
        });
        addToPanel(this,bRegistration,gbcLog,1,2);

        bCancel.setFont(font);
        bCancel.addActionListener( e->{
            ((JFrame)getRootPane().getParent()).dispose();
        });
        addToPanel(this,bCancel,gbcLog,2,2);
    }
}
