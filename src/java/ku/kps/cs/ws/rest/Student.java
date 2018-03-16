package ku.kps.cs.ws.rest;

public class Student {

    private String studentID;
    private String studentName;
    private String major;
    private String mobile;

    public String getMobile() {
        return mobile;
    } 

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getMajor() {
        return major;
    }

    public void setStudentID(String id) {
        this.studentID = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
