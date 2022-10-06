package com.showcase.project.mapper;

import com.showcase.project.domain.Project;
import com.showcase.project.dto.ProjectDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO `Project` (`PNAME`, `TAGLINE`, `TECHNOLOGIES`, `INTRODUCTION`, `OWNER`, `DATE`, `COVERIMAGE`) VALUES (#{pname}, #{tagline}, #{technologies}, #{introduction}, #{owner}, #{date}, #{img})")
    int insertProject(String pname, String tagline, String technologies, String introduction, String owner, Date date, byte[] img);
    @Select("select * from `Project` order by `ID` limit ${start}, ${end}")
    List<ProjectDTO> getProjects(int start, int end);
    @Select("select * from `Project` where ID = #{ID}")
    ProjectDTO getProject(String ID);
    @Select("select * from `Project` where OWNER = #{ID}")
    List<ProjectDTO> getProjectByUser(String ID);
    @Update("update User SET COVERIMAGE = #{file} WHERE ID = #{id}")
    int uploadProjectImg(String id, byte[] file);
    @Select("select COVERIMAGE from `Project` where ID = #{ID}")
    Project getCoverImage(String ID);
}
