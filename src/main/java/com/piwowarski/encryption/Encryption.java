package com.piwowarski.encryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Piwowarski Tomasz on 15.03.2017.
 */
public class Encryption
{
    private String word;
    private String wordEncrypt;
    private String wordDecipher;
    private int[] tabLetters;
    private static final String pathName = "D:\\Programowanie\\Szyfrowanie\\word.txt";

    public Encryption()
    {
        File file = new File(pathName);
        try
        {
            Scanner sc = new Scanner(file);
            this.word = sc.nextLine();
            tabLetters=new int[word.length()];
            sc.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        for (int i = 0; i < word.length(); i++)
        {
            if(word.charAt(i)>='A' && word.charAt(i)<='Z')
            {
                tabLetters[i]=1;
            }
            else if(word.charAt(i)>='a' && word.charAt(i)<='z')
            {
                tabLetters[i]=2;
            }
            else if(word.charAt(i)>='0' && word.charAt(i)<='9')
            {
                tabLetters[i]=3;
            }
            else
            {
                tabLetters[i]=4;
            }
        }
    }
    public Encryption(String word)
    {
        this.word =  word;
        tabLetters = new int[word.length()];
        for (int i = 0; i < word.length(); i++)
        {
            if(word.charAt(i)>='A' && word.charAt(i)<='Z')
            {
                tabLetters[i]=1;
            }
            else if(word.charAt(i)>='a' && word.charAt(i)<='z')
            {
                tabLetters[i]=2;
            }
            else if(word.charAt(i)>='0' && word.charAt(i)<='9')
            {
                tabLetters[i]=3;
            }
            else
            {
                tabLetters[i]=4;
            }
        }
    }
    private String adjustString(String str)
    {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++)
        {
            for (int j = i + 1; j < sb.length(); j++)
            {
                if (sb.charAt(j) == sb.charAt(i))
                {
                    sb.delete(j, j + 1);
                    j--;
                }
            }
        }
        return sb.toString();
    }
    private String getKey(String filePath)
    {
        String codeKey =null;
        File file = new File("D:\\Programowanie\\Szyfrowanie\\" + filePath);
        try
        {
            Scanner sc = new Scanner(file);
            codeKey = sc.next();
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        if(filePath.equals("duzyKlucz.txt"))
        {
            codeKey = codeKey.toUpperCase();
            codeKey = adjustString(codeKey);
        }
        else if(filePath.equals("malyKlucz.txt"))
        {
            codeKey = codeKey.toLowerCase();
            codeKey = adjustString(codeKey);
        }
        return codeKey;
    }
    public void encrypt()
    {
        String res = "";
        int cezarKey=Integer.parseInt(getKey("cezarKlucz.txt"));
        String bigKey=getKey("duzyKlucz.txt");
        String smallKey = getKey("malyKlucz.txt");

        if(cezarKey%2!=0 || cezarKey<0)
        {
            cezarKey=28;
        }

        for (int i = 0,j = 0,k = 0; i < word.length(); i++)
        {
            if(word.charAt(i)>='A' && word.charAt(i)<='Z')
            {
                res += (char) ((word.charAt(i) + bigKey.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % bigKey.length();
            }
            else if(word.charAt(i)>='a' && word.charAt(i)<='z')
            {
                res += (char) ((word.charAt(i) + smallKey.charAt(k) - 2 * 'a') % 26 + 'a');
                k = ++k % smallKey.length();
            }
            else if(word.charAt(i)>='0' && word.charAt(i)<='9')
            {
                res+= (char)10 - (word.charAt(i) - '0');
            }
            else
            {
                res += (char) (word.charAt(i) + cezarKey);
            }
        }

        this.wordEncrypt = res;
    }

    public void decipher()
    {
        String res = "";
        int cezarKey=Integer.parseInt(getKey("cezarKlucz.txt"));
        String bigKey=getKey("duzyKlucz.txt");
        String smallKey = getKey("malyKlucz.txt");

        if(cezarKey%2!=0 || cezarKey<0)
        {
            cezarKey=28;
        }
        for (int i = 0,j = 0,k = 0; i < wordEncrypt.length(); i++)
        {
            if(tabLetters[i]==1)
            {
                res += (char) ((wordEncrypt.charAt(i) - bigKey.charAt(j) + 26) % 26 + 'A');
                j = ++j % bigKey.length();
            }
            else if(tabLetters[i]==2)
            {
                res += (char) ((wordEncrypt.charAt(i) - smallKey.charAt(k) + 26) % 26 + 'a');
                k = ++k % smallKey.length();
            }
            else if(tabLetters[i]==3)
            {
                res+= (char)10 - (wordEncrypt.charAt(i) - '0');
            }
            else
            {
                res += (char) (wordEncrypt.charAt(i) - cezarKey);
            }
        }
        this.wordDecipher = res;
    }

    public String getWordEncrypt()
    {
        return wordEncrypt;
    }

    public void setWordEncrypt(String wordEncrypt)
    {
        this.wordEncrypt = wordEncrypt;
    }

    public String getWordDecipher()
    {
        return wordDecipher;
    }

    public void setWordDecipher(String wordDecipher)
    {
        this.wordDecipher = wordDecipher;
    }

    @Override
    public String toString()
    {
        return "Code{" +
                "word='" + word + '\n' +
                ", wordEncrypt='" + wordEncrypt + '\n' +
                ", wordDecipher='" + wordDecipher + '\n' +
                ", tabLetters=" + Arrays.toString(tabLetters) +
                '}';
    }
}
