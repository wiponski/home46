package kz.attractor.java.lesson44;

import java.time.LocalDate;
import java.util.Objects;

public class UserData {
    private String mail;
    private String name;
    private String pass;

    private  String hashNum ;


    public UserData(String mail, String name, String pass) {
        this.mail = mail;
        this.name = name;
        this.pass = pass;
        this.hashNum=String.valueOf(Objects.hash(mail, name, pass));
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getHashNum() {
        return hashNum;
    }

    public void setHashNum(String hashNum) {
        this.hashNum = hashNum;
    }
}
