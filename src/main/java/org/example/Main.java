package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;

import javax.imageio.spi.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository=new StudentRepository();

//        studentRepository.createStudent(
//                new Student("rav",34,"rav@gmail.com"));

        studentRepository.updateStudent(1 , new Student("seeya" , 32 , "siya@gmail.com"));

//        studentRepository.deleteStudent(8);


//        studentRepository.getStudentById(4);

//        studentRepository.getAllStudent();

//        studentRepository.allCrud();
     }
}