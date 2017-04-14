package com.piwowarski.panels;

import com.piwowarski.classes.User;
import com.piwowarski.datbase.PrzychodniaDao;
import com.piwowarski.datbase.PrzychodniaDaoImpl;
import com.piwowarski.encryption.Encryption;

import javax.management.BadAttributeValueExpException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;


/**
 * Created by Piwowarski Tomasz on 03.02.2017.
 */
public class PanelRejestracja extends JPanel
{
    private JLabel lUserName = new JLabel("Name");
    private JLabel lUserSurname = new JLabel("Surname");
    private JLabel lUserEmail = new JLabel("Email");
    private JLabel lUserCity= new JLabel("City");
    private JLabel lUserAge = new JLabel("Age");
    private JLabel lAccountName = new JLabel("Account Name");
    private JLabel lPassword = new JLabel("Password");
    private JLabel lRePassword = new JLabel("Repeat Password");

    private JTextField tfUserName = new JTextField(20);
    private JTextField tfUserSurname = new JTextField(20);
    private JTextField tfUserEmail = new JTextField(20);
    private JTextField tfUserCity = new JTextField(20);
    private JTextField tfUserAge = new JTextField(20);
    private JTextField tfAccountName = new JTextField(20);
    private JPasswordField tfPassword = new JPasswordField(20);
    private JPasswordField tfRePassword = new JPasswordField(20);

    private JButton bAdd = new JButton("Add");
    private JButton bCancel = new JButton("Cancel");

    private PrzychodniaDao database = PrzychodniaDaoImpl.getInstance();

    private Font font = new Font("consolas",Font.BOLD,24);

    private User getDataUser() throws BadAttributeValueExpException
    {
        String name = tfUserName.getText();
        if(!name.matches("[A-Z][a-z]+"))
        {
            throw new BadAttributeValueExpException("Złe imie");
        }
        String surname = tfUserSurname.getText();
        if(!surname.matches("[A-Z][a-z]+"))
        {
            throw new BadAttributeValueExpException("Złe nazwisko");
        }
        String email = tfUserEmail.getText();
        if(!email.matches("([a-z]|[0-9]|.)+@(gmail|onet|interia).(pl|com)"))
        {
            throw new BadAttributeValueExpException("Zły email");
        }
        String city = tfUserEmail.getText();
        int age = Integer.parseInt(tfUserAge.getText());
        if(age < 0 || age > 99)
        {
            throw new BadAttributeValueExpException("Zły wiek");
        }
        String userName = tfAccountName.getText();
        String password = String.valueOf(tfPassword.getPassword());
        Encryption e = new Encryption(password);
        e.encrypt();
        password = e.getWordEncrypt();
        return new User(0,name,surname,email,city,age,userName,password);
    }
    private boolean isPasswordCorect(String password)
    {
        int tab[] = {0,0,0};
        for (int i = 0; i < password.length(); i++)
        {
            char c = password.charAt(i);
            if(String.valueOf(c).matches("[A-Z]"))
            {
                tab[0]++;
            }
            else if(String.valueOf(c).matches("[0-9]"))
            {
                tab[1]++;
            }
            else if(String.valueOf(c).matches("[`~!@#$%^&*()-_=+]"))
            {
                tab[2]++;
            }
            else if(String.valueOf(c).matches("[    ]"))
            {
                return false;
            }
        }
        if(password.length() > 10 && tab[0]>1 && tab[1]>1 && tab[2]>0)
        {
            return true;
        }
        return false;
    }

    private void addToPanel(JPanel p,JComponent c,GridBagConstraints gbc,int x,int y,int ipadx)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.ipadx = ipadx;
        p.add(c,gbc);
    }
    private void addTFBackgroundColor(JTextField tf, String regex)
    {
        tf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                if(!tf.getText().matches(regex))
                {
                    tf.setBackground(Color.RED);
                }
                else
                {
                    tf.setBackground(Color.GREEN);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                if(!tf.getText().matches(regex))
                {
                    tf.setBackground(Color.RED);
                }
                else
                {
                    tf.setBackground(Color.GREEN);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {

            }
        });
    }
    private void setAllTFBackground()
    {
        addTFBackgroundColor(tfUserName,"[A-Z][a-z]+");
        addTFBackgroundColor(tfUserSurname,"[A-Z][a-z]+");
        addTFBackgroundColor(tfUserEmail,"([a-z]|[0-9]|.)+@(gmail|onet|interia).(pl|com)");
        addTFBackgroundColor(tfUserCity,"[A-Z]([A-Z]| )+");
        addTFBackgroundColor(tfUserAge,"[1-9][0-9]{0,1}");
        addTFBackgroundColor(tfAccountName,"([a-z]|[0-9])+");
        tfPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                if(!isPasswordCorect(String.valueOf(tfPassword.getPassword())))
                {
                    tfPassword.setBackground(Color.RED);
                }
                else
                {
                    tfPassword.setBackground(Color.GREEN);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                if(!isPasswordCorect(String.valueOf(tfPassword.getPassword())))
                {
                    tfPassword.setBackground(Color.RED);
                }
                else
                {
                    tfPassword.setBackground(Color.GREEN);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {

            }
        });
    }
    public PanelRejestracja()
    {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setAllTFBackground();
        tfUserName.setToolTipText("Pierwsza duża litera potem małe");
        tfUserName.setFont(font);
        lUserName.setFont(font);
        addToPanel(this,lUserName,gbc,0,0,20);
        addToPanel(this,tfUserName,gbc,1,0,20);

        tfUserSurname.setToolTipText("Pierwsza duża litera potem małe");
        tfUserSurname.setFont(font);
        lUserSurname.setFont(font);
        addToPanel(this,lUserSurname,gbc,0,1,20);
        addToPanel(this,tfUserSurname,gbc,1,1,20);

        tfUserEmail.setToolTipText("małe znaki + @ + domena");

        tfUserEmail.setFont(font);
        lUserEmail.setFont(font);
        addToPanel(this,lUserEmail,gbc,0,2,20);
        addToPanel(this,tfUserEmail,gbc,1,2,20);

        tfUserCity.setToolTipText("Tylko duże litery i znak spacji");
        tfUserCity.addActionListener(e->{
            if(!tfUserCity.getText().matches("[A-Z]([A-Z]| )+"))
            {
                tfUserCity.setBackground(Color.RED);
            }
            else
            {
                tfUserCity.setBackground(Color.WHITE);
            }
        });
        tfUserCity.setFont(font);
        lUserCity.setFont(font);
        addToPanel(this,lUserCity,gbc,0,3,20);
        addToPanel(this,tfUserCity,gbc,1,3,20);

        tfUserAge.setToolTipText("Liczba załkowita");
        tfUserAge.setFont(font);
        lUserAge.setFont(font);
        addToPanel(this,lUserAge,gbc,0,4,20);
        addToPanel(this,tfUserAge,gbc,1,4,20);

        tfAccountName.setToolTipText("Nazwa pod którą bedziesz się logować");
        tfAccountName.setFont(font);
        lAccountName.setFont(font);
        addToPanel(this,lAccountName,gbc,0,5,20);
        addToPanel(this,tfAccountName,gbc,1,5,20);

        tfPassword.setToolTipText("Hasło powino spełniać kryteria:\n" +
                "-dłuższe niż 10 znaków\n-conajmniej 2 duze litery\n-conajmniej 2 cyfry\n-conajmniej jeden znak specialny");
        tfPassword.setFont(font);
        lPassword.setFont(font);
        addToPanel(this,lPassword,gbc,0,6,20);
        addToPanel(this,tfPassword,gbc,1,6,20);

        tfRePassword.setToolTipText("Ponownie hasło");
        tfRePassword.setFont(font);
        lRePassword.setFont(font);
        addToPanel(this,lRePassword,gbc,0,7,20);
        addToPanel(this,tfRePassword,gbc,1,7,20);

        bAdd.addActionListener(e->{
            if(isPasswordCorect(String.valueOf(tfPassword.getPassword()))
                    && String.valueOf(tfPassword.getPassword()).equals(String.valueOf(tfRePassword.getPassword()))
                    && !database.isUserExist(tfAccountName.getText()))
            {

                try
                {
                    database.insertUser(getDataUser());
                    JOptionPane.showMessageDialog(null, "Dodano użytkownika");
                    ((JFrame) getRootPane().getParent()).dispose();
                } catch (BadAttributeValueExpException e1)
                {
                    JOptionPane.showMessageDialog(null, "Błędne dane");
                    e1.printStackTrace();
                }


            }
            else if(database.isUserExist(tfAccountName.getText()))
            {
                JOptionPane.showMessageDialog(null,"Nazwa użytkownika już istnieje!");
            }
            else if(!String.valueOf(tfPassword.getPassword()).equals(String.valueOf(tfRePassword.getPassword())))
            {
                JOptionPane.showMessageDialog(null,"Różne hasła!");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Hasło nie spełnia kryteriów:\n" +
                        "-dłuższe niż 10 znaków\n-conajmniej 2 duze litery\n-conajmniej 2 cyfry\n-conajmniej jeden znak specialny");
            }
        });
        bCancel.addActionListener(e -> {
            ((JFrame)getRootPane().getParent()).dispose();
        });
        bAdd.setFont(font);
        bCancel.setFont(font);
        addToPanel(this,bAdd,gbc,0,8,20);
        addToPanel(this,bCancel,gbc,1,8,20);
    }
}
