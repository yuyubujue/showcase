package com.showcase.project.service;

import com.showcase.project.domain.Project;
import com.showcase.project.domain.Project_comment;
import com.showcase.project.domain.Project_like;
import com.showcase.project.domain.teacher_award;
import com.showcase.project.dto.ProjectDTO;
import com.showcase.project.dto.TeacherCommentDTO;
import com.showcase.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ProjectService")
public class ProjectService{
    @Autowired(required=false)
    private ProjectMapper projectMapper;

    public List<ProjectDTO> getProjects(String page) {

        if(page.equals("1")) {
            return projectMapper.getProjects(0,10);
        }else{
            return projectMapper.getProjects(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }

//    public List<ProjectDTO> getProjectsByLike(String page) {
//
//        if(page.equals("1")) {
//            return projectMapper.getProjectsByLike(0,10);
//        }else{
//            return projectMapper.getProjectsByLike(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
//        }
//    }

    public int getProjectLikeByID(int pid){
        return projectMapper.GetProjectLikeByID(pid);
    }


    public int uploadProject(String pname, String tagline,  String introduction, String owner, byte[] img) {
        return projectMapper.insertProject(pname, tagline,  introduction, owner, System.currentTimeMillis(), img);
    }
    public List<ProjectDTO> getAllProject(){return projectMapper.getAllProject();}
    public ProjectDTO getProject(int id) {
        return projectMapper.getProject(id);
    }

    public List<ProjectDTO> getProjectByUser(String UID){return projectMapper.getProjectByUser(UID);}

    public int uploadProjectCover(int id, byte[] file){return projectMapper.uploadProjectCover(id, file);}
    public int uploadProjectImg(int id, byte[] file){return projectMapper.uploadProjectImg(id, file);}

    public Project getCoverImage(int id) {
        return projectMapper.getCoverImage(id);
    }

    public Project getNewOne(){return projectMapper.getNewOne();}

    public int setNewPname(int pid,String newName){return projectMapper.setNewPname(pid,newName);}

    public int setNewTagLine(int pid,String newTag){return projectMapper.setNewTagLine(pid,newTag);}
    public int LikeProject(int pid,String uid){return projectMapper.LikeProject(pid,uid);}
    public int UnLikeProject(int pid,String uid){return projectMapper.UnLikeProject(pid,uid);}
    public Project_like CheckLike(int pid,String uid){return projectMapper.CheckLike(pid,uid);}

    public int WriteComment(int pid,String uid,String comment){return projectMapper.WriteComment(pid,uid,comment);}
    public List<Project_comment> CheckComment(int pid,String uid,int comment_id){return projectMapper.checkComment(pid,uid,comment_id);}
    public int DeleteComment(int comment_id){return projectMapper.DeleteComment(comment_id);}
    public List<Project_comment> GetComment(int pid,String page){
        if(page.equals("1")) {
            return projectMapper.getComments(pid,0,10);
        }else{
            return projectMapper.getComments(pid,Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }
    public int addAward(int pid,String uid,String comment,String username){return projectMapper.addAward(pid,uid,comment,username);}

    public List<TeacherCommentDTO> GetAwardCommentByID(int pid){return projectMapper.GetAwardCommentByID(pid);}
    public int DeleteAward(int pid,String uid){return projectMapper.DeleteAward(pid,uid);}

    public teacher_award checkAward(int pid, String uid){return projectMapper.checkAward(pid,uid);}
}
