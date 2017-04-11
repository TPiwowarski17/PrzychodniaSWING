package com.piwowarski.classes;

/**
 * Created by Piwowarski Tomasz on 17.03.2017.
 */
public class User
{
    private int id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private int age;
    private String userName;
    private String userPassword;

    public User(int id, String name, String surname, String email, String city, int age, String userName, String userPassword)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.age = age;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        User user = (User) o;

        if (id != user.id)
        {
            return false;
        }
        if (age != user.age)
        {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null)
        {
            return false;
        }
        if (surname != null ? !surname.equals(user.surname) : user.surname != null)
        {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null)
        {
            return false;
        }
        if (city != null ? !city.equals(user.city) : user.city != null)
        {
            return false;
        }
        if (userName != null ? !userName.equals(user.userName) : user.userName != null)
        {
            return false;
        }
        return userPassword != null ? userPassword.equals(user.userPassword) : user.userPassword == null;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
