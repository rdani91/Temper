package hu.daniel.rozsa.logic.entity;

public class User {


    public String name;
    public int age;
    public Gender gender;
    public String location;


    public enum Gender{
        MALE, FEMALE;
    }
}
