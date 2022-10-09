package com.showcase.project.dto;

public class TeacherCommentDTO {
    private int pid;
    private String teacher_comment;
    private String teacher_name;

    public TeacherCommentDTO(){}
    public TeacherCommentDTO(int pid,String teacher_comment,String teacher_name){
        this.pid = pid;
        this.teacher_comment = teacher_comment;
        this.teacher_name = teacher_name;
    }

    public String getTeacher_comment() {
        return teacher_comment;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public int getPid() {
        return pid;
    }
}
