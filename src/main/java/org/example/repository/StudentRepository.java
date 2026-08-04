package org.example.repository;

import org.example.model.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentRepository {


    String url="jdbc:mysql://localhost:3306/javaJDBC";
    String password="root";
    String username="root";



    public  void  createStudent(Student student){
        String sql= """
                     insert into students (name,age ,email) values(? , ? , ? )
                     """;


        try(Connection connection= DriverManager.getConnection(url,password,username);

             PreparedStatement preparedStatement=connection.prepareStatement(sql); ){

             preparedStatement.setString(1 , student.getName());
            preparedStatement.setInt(2 , student.getAge());
            preparedStatement.setString(3 , student.getEmail());


            int rowAffected = preparedStatement.executeUpdate();

            System.out.println(rowAffected);

            if(rowAffected == 1){
                System.out.println("Student  created successfully");
            }else {
                System.out.println("creation failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public  void  updateStudent(  long id ,Student student ){
        String sql= """ 
               update students 
               set name=?,
                   age=?,
                   email=?
               where id =?
                """;

        try(Connection connection= DriverManager.getConnection(url,password,username);
            PreparedStatement preparedStatement=connection.prepareStatement(sql); ){

            preparedStatement.setString(1 , student.getName());
            preparedStatement.setInt(2 , student.getAge());
            preparedStatement.setString(3 , student.getEmail());
            preparedStatement.setLong(4,id);

            int rowAffected=preparedStatement.executeUpdate( );//create /update/ delete

            if(rowAffected == 1){
                System.out.println("Student  updated successfully");
            }else {
                System.out.println("updation failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void  deleteStudent(long id){

        String sql= """ 
               delete from students where id =?
                """;


        try(Connection connection= DriverManager.getConnection(url,password,username);
            PreparedStatement preparedStatement=connection.prepareStatement(sql); ){

            preparedStatement.setLong(1 , id);

            int rowAffected=preparedStatement.executeUpdate( );//create /update/ delete

            if(rowAffected == 1){
                System.out.println("Student  deleted successfully");
            }else {
                System.out.println("deleted failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










    //statement.executeQuery(sql);  //select    //type-ReseltSet
    public  void  getStudentById(long id){
        String sql= """
                select id ,name ,age ,email from students where id=?
                """ ;

        try(Connection connection= DriverManager.getConnection(url,password,username);
            PreparedStatement preparedStatement=connection.prepareStatement(sql);){


            preparedStatement.setLong(1 , id);

            ResultSet resultSet=preparedStatement.executeQuery( );   //create /update/ delete
            resultSet.next();
            Student student=mapToStudent(resultSet);
            System.out.println(student);



            connection.close();;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void  getAllStudent(){

        String sql= """
                select id , name,age, email from students
                """;
        try(Connection connection= DriverManager.getConnection(url,password,username);
            PreparedStatement preparedStatement=connection.prepareStatement(sql); ){


            ResultSet resultSet  = preparedStatement .executeQuery( );

            List<Student> studentList =mapToStudentList(resultSet);
            for(Student s : studentList) {
                System.out.println(s);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException {
        Student student=new Student();

        student. setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        student.setEmail(resultSet.getString("email"));


        return student;

    }


    private List<Student> mapToStudentList(ResultSet resultSet) throws SQLException {
        List<Student> studentList=new LinkedList<>();


        while(resultSet.next()){
            Student student=new Student();

            student. setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setEmail(resultSet.getString("email"));

            studentList.add(student);
        }

        return studentList;
    }
















    //statement.execute()  //boolean
    public void allCrud()  {
        String sql= """
                select * from students
                """;

        try(Connection connection= DriverManager.getConnection(url,password,username);
            PreparedStatement preparedStatement=connection.prepareStatement(sql); ){


            boolean rowAffected=preparedStatement.execute( );

            if(rowAffected){
                ResultSet resultSet=preparedStatement.getResultSet();

                List<Student > studentList=mapToStudentList(resultSet);

                for(Student s : studentList){
                    System.out.println(s);
                }
            }else {
                preparedStatement.getUpdateCount();
            }

         }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
