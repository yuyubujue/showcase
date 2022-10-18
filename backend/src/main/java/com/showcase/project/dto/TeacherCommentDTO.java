package com.showcase.project.dto;

public class TeacherCommentDTO {
    private int pid;
    private String uid;
    private String teachercomment;
    private String teachername;

    public TeacherCommentDTO(){}
    public TeacherCommentDTO(int pid,String teachercomment,String teachername,String uid){
        this.pid = pid;
        this.teachercomment = teachercomment;
        this.uid = uid;
        this.teachername = teachername;
    }

    public String getTeachercomment() {
        return teachercomment;
    }

    public String getTeachername() {
        return teachername;
    }

    public String getUid() {
        return uid;
    }

    public int getPid() {
        return pid;
    }
}