package kz.attractor.java;


import kz.attractor.java.lesson44.*;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        try {
            new Lesson46Server("localhost", 9889).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
