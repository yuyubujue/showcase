package com.showcase.project.mapper;

import com.showcase.project.controller.ProjectController;
import com.showcase.project.domain.Project;
import com.showcase.project.domain.Project_comment;
import com.showcase.project.domain.Project_like;
import com.showcase.project.domain.teacher_award;
import com.showcase.project.dto.ProjectDTO;
import com.showcase.project.dto.TeacherCommentDTO;
import com.showcase.project.dto.awardDTO;
import com.showcase.project.dto.likeDTO;
import org.apache.ibatis.annotations.*;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO `project` (`PNAME`, `TAGLINE`,  `INTRODUCTION`, `OWNER`, `COVERIMAGE`) VALUES (#{pname}, #{tagline}, #{introduction}, #{owner}, #{img})")
    int insertProject(String pname, String tagline, String introduction, String owner, byte[] img);
    @Select("select * from `project` order by TIMESTAMP desc limit ${start}, ${end}")
    List<ProjectDTO> getProjectsByCreateTime(int start, int end);
    @Select("select * from `project` order by TIMESTAMP limit ${start}, ${end}")
    List<ProjectDTO> getProjectsByCreateTimeAsc(int start, int end);
    @Select("select * from `project` order by UPDATETIME desc limit ${start}, ${end}")
    List<ProjectDTO> getProjectsByUpdateTime(int start, int end);
    @Select("select * from `project` order by UPDATETIME limit ${start}, ${end}")
    List<ProjectDTO> getProjectsByUpdateTimeAsc(int start, int end);

    @Select("Select * from `project_comment` where PID = #{pid} order by time limit ${start}, ${end}")
    List<Project_comment> getComments(int pid,int start,int end);
    @Select("Select * from `project_comment` where PID=#{pid} and UID = #{uid} and CID = #{cid}")
    List<Project_comment> checkComment(int pid,String uid,int cid);
    @Delete("Delete from `project_comment` where CID = #{cid}")
    int DeleteComment(int cid);
//    @Select("select * from `Project` order by like limit ${start}, ${end}")
//    List<ProjectDTO> getProjectsByLike(int start, int end);
    @Select("select * from `project` order by TIMESTAMP")
    List<Project> getAllProject();
    @Select("select * from `project` where ID = #{ID}")
    ProjectDTO getProject(int ID);
    @Select("select * from `project` where OWNER = #{UID}")
    List<ProjectDTO> getProjectByUser(String UID);
    @Update("update user SET COVERIMAGE = #{file} WHERE ID = #{id}")
    int uploadProjectCover(int id, byte[] file);
    @Insert("Insert into `project_photo` (`PID`,`PHOTO`) values (#{pid},#{file})")
    int uploadProjectImg(int pid,byte[] file);

    @Select("select COVERIMAGE from `project` where ID = #{ID}")
    Project getCoverImage(int ID);
    @Select("select * from project order by ID desc limit 1")
    Project getNewOne();
    @Update("Update project SET PNAME = #{newname} where ID = #{id}")
    int setNewPname(int id,String newname);

    @Update("Update project SET TAGLINE = #{tag} where ID = #{id}")
    int setNewTagLine(int id,String tag);

    @Select("select ifnull(sum(project_like.LIKEAMOUNT),0)\n" +
            "from project_like\n" +
            "where PID = #{id}")
    int GetProjectLikeByID(int id);
    @Select("select project.ID,project.PNAME,project.OWNER,project.TIMESTAMP,sum(project_like.LIKEAMOUNT) as likeamount from project left join project_like on project_like.PID = project.ID group by project.ID order by sum(LIKEAMOUNT) desc limit #{start},#{end}")
    List<likeDTO> GetProjectByLike(int start,int end);

    @Select("select project.ID,project.PNAME,project.OWNER,project.TIMESTAMP,sum(teacher_award.AWARD) as award_times from project right join teacher_award on teacher_award.PID = project.ID  group by teacher_award.PID  order by sum(AWARD) desc limit #{start}, #{end}")
    List<awardDTO> GetAwardedProject(int start, int end);

    @Insert("Insert into `project_like` (`PID`,`UID`,`UNAME`) values (#{pid},#{uid},#{uname})")
    int LikeProject(int pid,String uid,String uname);

    @Delete("delete from project_like\n" +
            "where PID = #{pid}\n" +
            "and UID = #{uid}")
    int UnLikeProject(int pid,String uid);

    @Select("Select * from project_like where UID = #{uid}")
    List<Project_like> ShowMyLike(String uid);

    @Select("Select * from project_like where PID=#{pid} and UID=#{uid} limit 1")
    Project_like CheckLike(int pid,String uid);

    @Insert("Insert into `project_comment` (`PID`,`COMMENT`,`UID`,`UNAME`) values (#{pid},#{comment},#{uid},#{uname})")
    int WriteComment(int pid,String uid,String comment,String uname);
    @Insert("Insert into `teacher_award` (`PID`,`UID`,`TEACHERCOMMENT`,`TEACHERNAME`) values (#{pid},#{uid},#{comment},#{teacher_name})")
    int addAward(int pid,String uid,String comment,String teacher_name);
    @Select("Select sum(AWARD) from `teacher_award` where PID=#{pid}")
    int GetAwardAmountById(int pid);



    @Delete("Delete from `teacher_award` where PID=#{pid} and UID=#{uid}")
    int DeleteAward(int pid,String uid);

    @Select("Select * from `teacher_award` where PID=#{pid}")
    List<TeacherCommentDTO> GetAwardCommentByID(int pid);
    @Select("Select * from `teacher_award` where PID=#{pid} and UID=#{uid} limit 1")
    teacher_award checkAward(int pid,String uid);

    @Select("Select * from `project` where ID=#{pid} and OWNER=#{uid}")
    Project projectChecker(int pid,String uid);
    @Update("Update project set INTRODUCTION = #{newIntro} where ID = #{pid}")
    int setNewPIntro(int pid,String newIntro);

    @Insert("Insert into `project_skill` (`PID`,`SKILLS`) values (#{pid},#{skill})")
    int UploadProjectSkill(int pid,String skill);

    @Delete("Delete from `project` where ID = #{pid}")
    int RemoveProject(int pid);
    @Select("Select PID from project_skill where SKILLS = #{skill}")
    List<Integer> GetProjectIdBySkills(String skill);

    @Delete("Delete from `project_skill` where PID = #{pid} and SKILLS = #{skill}")
    int RemoveSkill(int pid,String skill);

    @Select("Select SKILLS from project_skill where PID =#{pid}")
    List<String> getProjectSkills(int pid);
}
