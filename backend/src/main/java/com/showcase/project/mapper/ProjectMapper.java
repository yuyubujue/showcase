package com.showcase.project.mapper;

import com.showcase.project.domain.*;
import com.showcase.project.dto.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO `project` (`PNAME`, `TAGLINE`, `INTRODUCTION`, `OWNER`, `COVERIMAGE`, `INVITECODE`) VALUES (#{pname}, #{tagline}, #{introduction}, #{owner}, #{img}, #{code})")
    int insertProject(String pname, String tagline, String introduction, String owner, String img, String code);
    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID group by p.ID order by p.TIMESTAMP desc limit ${start}, ${end}")
    List<ProjectLikeCommentDTO> getProjectsByCreateTime(int start, int end);
    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID group by p.ID order by p.TIMESTAMP limit ${start}, ${end}")
    List<ProjectLikeCommentDTO> getProjectsByCreateTimeAsc(int start, int end);
    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID group by p.ID order by p.UPDATETIME desc limit ${start}, ${end}")
    List<ProjectLikeCommentDTO> getProjectsByUpdateTime(int start, int end);
    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID group by p.ID order by p.UPDATETIME limit #{start}, #{end}")
    List<ProjectLikeCommentDTO> getProjectsByUpdateTimeAsc(int start, int end);

    @Select("Select * from `project_comment` where PID = #{pid} order by time limit ${start}, ${end}")
    List<Project_comment> getComments(int pid,int start,int end);
    @Select("Select * from `project_comment` where PID=#{pid} and UID = #{uid} and CID = #{cid}")
    List<Project_comment> checkComment(int pid,String uid,int cid);
    @Delete("Delete from `project_comment` where CID = #{cid}")
    int DeleteComment(int cid);

    @Select("select * from `project` order by TIMESTAMP")
    List<Project> getAllProject();
    @Select("select * from `project` where ID = #{ID}")
    ProjectDTO getProject(int ID);
    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID where p.OWNER = #{UID}")
    List<ProjectLikeCommentDTO> getProjectByUser(String UID);
    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID where p.PNAME like CONCAT('%',#{pname},'%')")
    List<ProjectLikeCommentDTO> getProjectByPname(String pname);

    @Update("update project SET COVERIMAGE = #{file} WHERE ID = #{id}")
    int updateProjectCover(int id, String file);
    @Insert("Insert into `project_photo` (`PID`,`PHOTO`) values (#{pid},#{file})")
    int uploadProjectImg(int pid,byte[] file);
    @Select("Select * from `project_photo` where PID = #{pid}")
    List<project_photo> getAllPhotoBypid(int pid);
    @Delete("Delete from `project_photo` where PHOTOID = #{photoid}")
    int removeProjectPhotoByPhotoid(int photoid);
    @Select("select COVERIMAGE from `project` where ID = #{ID}")
    Project getCoverImage(int ID);
    @Select("select * from project order by ID desc limit 1")
    Project getNewOne();
    @Update("Update project SET PNAME = #{newname} where ID = #{id}")
    int setNewPname(int id,String newname);


    @Delete("Delete from `project_skill` where PID = #{pid}")
    int RemoveAllSkill(int pid);

    @Update("Update project SET TAGLINE = #{tag} where ID = #{id}")
    int setNewTagLine(int id,String tag);

    @Select("select ifnull(sum(project_like.LIKEAMOUNT),0)\n" +
            "from project_like\n" +
            "where PID = #{id}")
    int GetProjectLikeByID(int id);



    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID group by p.ID order by LIKEAMOUNT desc,p.UPDATETIME limit #{start},#{end}")
    List<ProjectLikeCommentDTO> GetProjectByLike(int start,int end);



    @Select("select * from `project_like` where PID = #{pid} and UID = #{uid} limit 1")
    Project_like getlikestatus(int pid,String uid);
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

    @Delete("Delete from `teacher_award` where PID=#{pid}")
    int DeleteAwardAdmin(int pid);

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

    @Select("select p.*,ifnull(pl.LIKEAMOUNT,0) as LIKEAMOUNT,ifnull(ta.AWARD,0) AS AWARD,ifnull(pc.COMMENT,0) AS COMMENT,ifnull(User.USERNAME,0) AS USERNAME  from project p\n" +
            "\n" +
            "        left join (select project_like.PID,sum(project_like.LIKEAMOUNT) AS LIKEAMOUNT from project_like group by project_like.PID) as pl on p.Id = pl.PID\n" +
            "            left join User on p.OWNER = User.ID\n" +
            "                left join (select teacher_award.PID,sum(teacher_award.AWARD) as AWARD from teacher_award group by teacher_award.PID) as ta on p.ID = ta.PID\n" +
            "                    left join (select project_comment.PID,count(project_comment.comment) AS COMMENT from project_comment group by project_comment.PID) as pc on p.ID = pc.PID where p.ID = #{pid}")
    ProjectLikeCommentDTO getProjectPageByPid(int pid);

    @Insert("INSERT INTO `team`(`UID`, `PID`) VALUES ((SELECT ID from User WHERE COOKIE = #{cookie}),(SELECT ID from project WHERE INVITECODE = #{invitecode}))")
    int joinTeam(String invitecode, String cookie);

    @Select("SELECT * FROM `team` t LEFT JOIN `User` u ON t.UID = u.ID WHERE t.pid = ${pid}")
    List<UserDTO> getTeammateByPID(int pid);

    @Delete("Delete from `team` where UID = #{uid} AND PID = ${pid}")
    int removeTeammate(int pid, String uid);

    @Update("Update project SET INVITECODE = #{invitecode} where ID = #{pid}")
    int generateNewInviteCode(int pid, String invitecode);

    @Select("Select ID from project where INVITECODE = #{invitecode}")
    int getPidByCode(String invitecode);

    @Select("Select * from project where ID = #{pid}")
    Project getFullProjectByPid(int pid);
}
