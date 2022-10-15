package com.showcase.project.service;

import com.showcase.project.alogrithm.TIDgenerator;
import com.showcase.project.alogrithm.VerificationCodeGenerator;
import com.showcase.project.domain.*;
import com.showcase.project.dto.*;
import com.showcase.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProjectService")
public class ProjectService{
    @Autowired(required=false)
    private ProjectMapper projectMapper;

    public List<ProjectDTO> getProjectsByCreateTime(String page) {

        if(page.equals("1")) {
            return projectMapper.getProjectsByCreateTime(0,10);
        }else{
            return projectMapper.getProjectsByCreateTime(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }
    public List<ProjectDTO> getProjectsByCreateTimeAsc(String page) {

        if(page.equals("1")) {
            return projectMapper.getProjectsByCreateTimeAsc(0,10);
        }else{
            return projectMapper.getProjectsByCreateTimeAsc(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }

    public List<ProjectDTO> getProjectsByUpdateTime(String page) {

        if(page.equals("1")) {
            return projectMapper.getProjectsByUpdateTime(0,10);
        }else{
            return projectMapper.getProjectsByUpdateTime(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }

    public List<ProjectDTO> getProjectsByUpdateTimeAsc(String page) {

        if(page.equals("1")) {
            return projectMapper.getProjectsByUpdateTimeAsc(0,10);
        }else{
            return projectMapper.getProjectsByUpdateTimeAsc(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }

    public int getProjectLikeByID(int pid){
        return projectMapper.GetProjectLikeByID(pid);
    }


    public int uploadProject(String pname, String tagline,  String introduction, String owner, String img) {
        return projectMapper.insertProject(pname, tagline,  introduction, owner, img, VerificationCodeGenerator.getRandomVCode());
    }
    public List<Project> getAllProject(){return projectMapper.getAllProject();}
    public ProjectDTO getProject(int id) {
        return projectMapper.getProject(id);
    }

    public List<ProjectDTO> getProjectByUser(String UID){return projectMapper.getProjectByUser(UID);}

    public int updateProjectCover(int id, String file){return projectMapper.updateProjectCover(id, file);}
    public int uploadProjectImg(int id, byte[] file){return projectMapper.uploadProjectImg(id, file);}

    public List<project_photo> getAllPhotoBypid(int pid){return projectMapper.getAllPhotoBypid(pid);}

    public int removeProjectPhotoByPhotoid(int photoid){return projectMapper.removeProjectPhotoByPhotoid(photoid);}

    public Project_like getlikestatus(int pid,String uid){return projectMapper.getlikestatus(pid,uid);}
    public Project getCoverImage(int id) {
        return projectMapper.getCoverImage(id);
    }

    public Project getNewOne(){return projectMapper.getNewOne();}

    public int setNewPname(int pid,String newName){return projectMapper.setNewPname(pid,newName);}

/*
    public int GenerateTeam(String tid,boolean ownership,String uid,int pid,String tname,String uname) {return projectMapper.GenerateTeam(tid,ownership,uid,pid,tname,uname);}

    public List<Team> getTeamByTID(String tid){return projectMapper.getTeamByTID(tid);}
    public Team getTeamOwnerByTID(String tid){return projectMapper.getTeamOwnerByTID(tid);}

    public List<Team> getTeamByUID(String uid){return projectMapper.getTeamByUID(uid);}

    public Team checkTeam(String tid,String uname){return projectMapper.CheckTeam(tid,uname);}
    public List<Team> getAllTeam(){return projectMapper.getAllTeam();}

    public int deleteTeam(String tid){return projectMapper.deleteTeam(tid);}
    */

    public int setNewTagLine(int pid,String newTag){return projectMapper.setNewTagLine(pid,newTag);}
    public int LikeProject(int pid,String uid,String uname){return projectMapper.LikeProject(pid,uid,uname);}
    public int UnLikeProject(int pid,String uid){return projectMapper.UnLikeProject(pid,uid);}
    public Project_like CheckLike(int pid,String uid){return projectMapper.CheckLike(pid,uid);}

    public int WriteComment(int pid,String uid,String comment,String uname){return projectMapper.WriteComment(pid,uid,comment,uname);}
    public List<Project_comment> CheckComment(int pid,String uid,int cid){return projectMapper.checkComment(pid,uid,cid);}
    public int DeleteComment(int cid){return projectMapper.DeleteComment(cid);}
    public List<Project_comment> GetComment(int pid,String page){
        if(page.equals("1")) {
            return projectMapper.getComments(pid,0,10);
        }else{
            return projectMapper.getComments(pid,Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }

    public int AddInviteCode(String uname,String VerCode,int pid,String tid,String tname){
        return projectMapper.AddInviteCode(uname,VerCode,pid,tid,tname);
    }

    public Verification VerifyInvite(String uname,String vercode){
        return projectMapper.VerifyInvite(uname,vercode);
    }

    public int DeleteCode(String vercode){
        return projectMapper.DeleteCode(vercode);
    }
    public List<likeDTO> GetProjectByLike(String page){
        if(page.equals("1")) {
            return projectMapper.GetProjectByLike(0,10);
        }else{
            return projectMapper.GetProjectByLike(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }

    public List<awardDTO> GetAwardedProject(String page){
        if(page.equals("1")) {
            return projectMapper.GetAwardedProject(0,10);
        }else{
            return projectMapper.GetAwardedProject(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }
    public int addAward(int pid,String uid,String comment,String username){return projectMapper.addAward(pid,uid,comment,username);}

    public List<TeacherCommentDTO> GetAwardCommentByID(int pid){return projectMapper.GetAwardCommentByID(pid);}
    public int DeleteAward(int pid,String uid){return projectMapper.DeleteAward(pid,uid);}

    public ProjectDTOFull getProjectPageByPid(int pid){return projectMapper.getProjectPageByPid(pid);}

    public Project getFullProjectByPid(int pid){return projectMapper.getFullProjectByPid(pid);}

    public int DeleteAwardAdmin(int pid){return projectMapper.DeleteAwardAdmin(pid);}

    public teacher_award checkAward(int pid, String uid){return projectMapper.checkAward(pid,uid);}

    public Project projectChecker(int pid,String uid){return projectMapper.projectChecker(pid,uid);}

    public int setNewPIntro(int pid,String newintro){return projectMapper.setNewPIntro(pid,newintro);}

    public int GetAwardAmountById(int pid){return projectMapper.GetAwardAmountById(pid);}

    public int UploadProjectSkill(int pid,String skill){return projectMapper.UploadProjectSkill(pid,skill);}

    public int RemoveProject(int pid){return projectMapper.RemoveProject(pid);}

    public List<Integer> GetProjectIdBySkills(String skill){return projectMapper.GetProjectIdBySkills(skill);}

    public int RemoveSkill(int pid,String skill) {return projectMapper.RemoveSkill(pid,skill);}

    public List<String> getProjectSkills(int pid){return projectMapper.getProjectSkills(pid);}

    public List<Project_like> ShowMyLike(String uid){return projectMapper.ShowMyLike(uid);}

    public int joinTeam(String invitecode, String cookie){
        return projectMapper.joinTeam(invitecode, cookie);
    }

    public List<UserDTO> getTeammateByPID(int pid){
        return projectMapper.getTeammateByPID(pid);
    }

    public int removeTeammate(int pid, String uid){
        return projectMapper.removeTeammate(pid, uid);
    }

    public int generateNewInviteCode(int pid, String invitecode){
        return projectMapper.generateNewInviteCode(pid, invitecode);
    }

    public int getPidByCode(String invitecode){
        return projectMapper.getPidByCode(invitecode);
    }

    public int RemoveAllSkill(int pid) {return projectMapper.RemoveAllSkill(pid);}
/*
    public String getTidbyPid(int pid) {
        return projectMapper.getTidbyPid(pid);
    }
    public String getTnamebyPid(int pid) {
        return projectMapper.getTnamebyPid(pid);
    }
 */
}


