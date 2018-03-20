/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.kps.cs.ws.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ku.kps.cs.rest.db.StudentDB;

@Path("/studentinfo")
public class StudentResource {

    @GET
    @Path("/plaintext/{studentId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStudentNamtPlainText(@PathParam("studentId") String studentId) {
        if (studentId.trim().length() == 0) {
            return Response.serverError().entity("Student ID is Blank").build();
        }
        String result = "";
        List<Student> studentList = StudentDB.getStudent(studentId);
        for (Student std : studentList) {
            result = "ID :" + std.getStudentID()
                    + "Name: " + std.getStudentName()
                    + "Major:" + std.getMajor();
          // result =result+std.getStudentName();
        }
        return Response.status(200).entity(result).build();
    }

    private List<Student> getStudentList(String studentId) {
        List<Student> studentList = new ArrayList<Student>();
        if (studentId.equals("5821601234")) {
            Student Student1 = new Student();
            Student1.setStudentID(studentId);
            Student1.setStudentName("Mongkol L");
            Student1.setMajor("Computer Science");
            studentList.add(Student1);

        } else if (studentId.equals("5821605678")) {
            Student Student2 = new Student();
            Student2.setStudentID(studentId);
            Student2.setStudentName("Thanabodee");
            Student2.setMajor("Computer Science");
            studentList.add(Student2);
        }
        return studentList;
    }
    @POST
    @Path("/insert")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertStudent(@FormParam("id") String id,@FormParam("name") String name,@FormParam("mobile") String mobile,@FormParam("major") String major){
        Student student = new Student();
        student.setStudentID(id);
        student.setStudentID(name);
        student.setMajor(major);
        student.setMobile(mobile);
        int res =StudentDB.insertStudent(student);
        String result ="Add Student id =" +id+"(status:" +res+")";
        
        return Response.status(Response.Status.OK).entity(result).build();
    }

}
