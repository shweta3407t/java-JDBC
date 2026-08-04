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
        //statement.executeUpdate(sql);   //create /update/ delete    //type-int
        try{
            Connection connection= DriverManager.getConnection(url,password,username);


             String sql="insert into students (name,age ,email) values(? , ? , ? ) ";




             PreparedStatement preparedStatement=connection.prepareStatement(sql);

             preparedStatement.setString(1 , student.getName());
            preparedStatement.setInt(2 , student.getAge());
            preparedStatement.setString(3 , student.getEmail());


            int result=preparedStatement.executeUpdate(sql);

            System.out.println(result);

            if(result == 1){
                System.out.println("Student  created successfully");
            }else {
                System.out.println("creation failed");
            }

            connection.close();;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public  void  updateStudent(){
        try{
            Connection connection= DriverManager.getConnection(url,password,username);
            Statement statement=connection.createStatement();
            String sql="update students set email='raj@gmail.com'  where id=2 ";

            int result=statement.executeUpdate(sql);//create /update/ delete

            if(result == 1){
                System.out.println("Student  updated successfully");
            }else {
                System.out.println("updation failed");
            }

            connection.close();;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void  deleteStudent(){
        try{
            Connection connection= DriverManager.getConnection(url,password,username);
            Statement statement=connection.createStatement();
            String sql="delete from students where id=3";

            int result=statement.executeUpdate(sql);//create /update/ delete

            if(result == 1){
                System.out.println("Student  deleted successfully");
            }else {
                System.out.println("deleted failed");
            }

            connection.close();;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










    //statement.executeQuery(sql);  //select    //type-ReseltSet
    public  void  getStudentById(long id){
        try{
            Connection connection= DriverManager.getConnection(url,password,username);

            Statement statement=connection.createStatement();

            String sql=" " +
                    "select id , name , age , email from students where id="+id;


            ResultSet resultSet=statement.executeQuery(sql);   //create /update/ delete
            resultSet.next();
            Student student=mapToStudent(resultSet);
            System.out.println(student);



            connection.close();;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void  getAllStudent(){
        try{
            Connection connection= DriverManager.getConnection(url,password,username);

            Statement statement=connection.createStatement();

            String sql=" " +
                    "select id , name , age , email from students";


            ResultSet resultSet=statement.executeQuery(sql);

            List<Student> studentList =mapToStudentList(resultSet);
            for(Student s : studentList) {
                System.out.println(s);
            }



            connection.close();;

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
        try{
            Connection connection=DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();

            String sql="select * from students";

            boolean result=statement.execute(sql);

            if(result){
                ResultSet resultSet=statement.getResultSet();

                List<Student > studentList=mapToStudentList(resultSet);

                for(Student s : studentList){
                    System.out.println(s);
                }
            }else {
                statement.getUpdateCount();
            }

            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
