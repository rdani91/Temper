package hu.daniel.rozsa.logic.entity;

import java.io.Serializable;

public class User implements Serializable{


    public String name;
    public int age;
    public String e_mail;
    public Gender gender;
    public String location;
    public int profileResId;
    public LoginType loginType;


    public enum Gender{
        MALE, FEMALE
    }

    public enum LoginType{
        EMAIL, FACEBOOK
    }
}
