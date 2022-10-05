package com.showcase.project.mapper;

import com.showcase.project.domain.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO `Project` (`PNAME`, `TAGLINE`, `TECHNOLOGIES`, `INTRODUCTION`, `OWNER`, `DATE`, `COVERIMAGE`) VALUES (#{pname}, #{tagline}, #{technologies}, #{introduction}, #{owner}, #{date}, NULL)")
    int insertProject(String pname, String tagline, String technologies, String introduction, String owner, Date date);
    @Select("select * from `Project` order by `ID` limit ${start}, ${end}")
    List<Project> getProjects(int start, int end);
    @Select("select * from `Project` where ID = #{ID}")
    Project getProject(String ID);
    @Select("select * from `Project` where OWNER = #{ID}")
    List<Project> getProjectByUser(String ID);
}
