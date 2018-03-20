/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.kps.cs.rest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ku.kps.cs.ws.rest.Student;

/**
 *
 * @author MA-Aunchalee
 */
public class StudentDB {

    public static Connection getDBConnection() throws ClassNotFoundException, SQLDataException, SQLException {
        String dbUrl = "jdbc:mysql://158.108.244.20/test";
        String dbClass = "com.mysql.jdbc.Driver";
        String userName = "root", password = "PASSWORD";
        Class.forName(dbClass);
        Connection con = DriverManager.getConnection(dbUrl, userName, password);
        return con;

    }

    public static List<Student> getStudent(String studentId) {
        List<Student> studentList = new ArrayList<Student>();
        String query = "Select * FROM students where studentID='" + studentId + "'";
        try {
            Student student = new Student();
            Connection con = getDBConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                student.setStudentID(rs.getString("StudentID"));
                student.setMajor(rs.getString("StudentName"));
                student.setStudentName(rs.getString("StudentName"));
                student.setMobile(rs.getString("Mobile"));
            }
            studentList.add(student);
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return studentList;
        }
    }
    public static int insertStudent(Student student){
        int res = 0;
        try{
            String id = student.getStudentID();
            String name =student.getStudentName();
            String major =student.getMajor();
            String mobile =student.getMobile();
            
            String query= "INSERT INTO students(StudentID,StudentName,Major,Mobile) VALUES(?,?,?,?)";
            Connection conn =getDBConnection();
            PreparedStatement prepareStmt= conn.prepareStatement(query);
            prepareStmt.setString(1, id);
            prepareStmt.setString(2, name);
            prepareStmt.setString(3, major);
            prepareStmt.setString(4, mobile);
            res =prepareStmt.executeUpdate();
            conn.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return  res;
    }
    public static void main (String[] args){
        List<Student> studentList =StudentDB.getStudent("5721602457");
        for(Student std : studentList){
            System.out.println(std.getStudentID()+"-"+std.getStudentName());
        }
    }
}


