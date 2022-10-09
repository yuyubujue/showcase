package com.showcase.project.mapper;

import com.showcase.project.controller.ProjectController;
import com.showcase.project.domain.Project;
import com.showcase.project.domain.Project_comment;
import com.showcase.project.domain.Project_like;
import com.showcase.project.domain.teacher_award;
import com.showcase.project.dto.ProjectDTO;
import com.showcase.project.dto.TeacherCommentDTO;
import org.apache.ibatis.annotations.*;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO `Project` (`PNAME`, `TAGLINE`, `TECHNOLOGIES`, `INTRODUCTION`, `OWNER`, `TIMESTAP`, `COVERIMAGE`) VALUES (#{pname}, #{tagline}, #{technologies}, #{introduction}, #{owner}, #{date}, #{img})")
    int insertProject(String pname, String tagline, String introduction, String owner, long timestamp, byte[] img);
    @Select("select * from `Project` order by timestamp limit ${start}, ${end}")
    List<ProjectDTO> getProjects(int start, int end);

    @Select("Select * from `Project_comment` order by comment_time limit ${start}, ${end}")
    List<Project_comment> getComments(int pid,int start,int end);
    @Select("Select * from `Project_comment` where pid=#{pid} and uid = #{uid} and comment_id = #{comment_id}")
    List<Project_comment> checkComment(int pid,String uid,int comment_id);
    @Delete("Delete from `Project_comment` where comment_id = #{comment_id}")
    int DeleteComment(int comment_id);
//    @Select("select * from `Project` order by like limit ${start}, ${end}")
//    List<ProjectDTO> getProjectsByLike(int start, int end);
    @Select("select * from `Project` order by timestamp")
    List<ProjectDTO> getAllProject();
    @Select("select * from `Project` where ID = #{ID}")
    ProjectDTO getProject(int ID);
    @Select("select * from `Project` where OWNER = #{UID}")
    List<ProjectDTO> getProjectByUser(String UID);
    @Update("update User SET COVERIMAGE = #{file} WHERE ID = #{id}")
    int uploadProjectCover(int id, byte[] file);
    @Insert("Insert into `project_photo` (`pid`,`photo`) values (#{pid},#{file})")
    int uploadProjectImg(int pid,byte[] file);

    @Select("select COVERIMAGE from `Project` where ID = #{ID}")
    Project getCoverImage(int ID);
    @Select("select * from Project order by ID desc limit 1")
    Project getNewOne();
    @Update("Update Project SET PNAME = #{newname} where ID = #{id}")
    int setNewPname(int id,String newname);

    @Update("Update Project SET TAGLINE = #{newtag} where ID = #{id}")
    int setNewTagLine(int id,String tag);

    @Select("select sum(project_like.`like_amount`)\n" +
            "from project_like\n" +
            "where pid = #{id}")
    int GetProjectLikeByID(int id);

    @Insert("Insert into `project_like` (`pid`,`uid`,`like_amount`) values (#{pid},#{uid})")
    int LikeProject(int pid,String uid);

    @Delete("delete from project_like\n" +
            "where pid = #{pid}\n" +
            "and uid = #{uid}")
    int UnLikeProject(int pid,String uid);

    @Select("Select * from project_like where pid=#{pid} and uid=#{uid} limit 1")
    Project_like CheckLike(int pid,String uid);

    @Insert("Insert into `project_comment` (`pid`,`comment`,`uid`) values (#{pid},#{comment},#{uid})")
    int WriteComment(int pid,String uid,String comment);
    @Insert("Insert into `teacher_award` (`pid`,`teacher_id`,`teacher_comment`,`teacher_name`) values (#{pid},#{comment},#{uid},#{teacher_name})")
    int addAward(int pid,String uid,String comment,String teacher_name);

    @Select("select sum(`award`)\n" +
            "from `teacher_award`\n" +
            "where pid = #{id}")
    int getAward(int pid);

    @Delete("Delete from `teacher_award` where pid=#{pid} and uid=#{uid}")
    int DeleteAward(int pid,String uid);

    @Select("Select * from `teacher_award` where pid=#{pid}")
    List<TeacherCommentDTO> GetAwardCommentByID(int pid);
    @Select("Select * from `teacher_award` where pid=#{pid} and uid={uid} limit 1")
    teacher_award checkAward(int pid,String uid);

}
