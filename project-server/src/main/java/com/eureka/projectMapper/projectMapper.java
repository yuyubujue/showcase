package com.eureka.projectMapper;

import com.eureka.ProjectDAO.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface projectMapper {
    @Insert("insert into project_info(PID,PNAME,PLANGUAGE,pteamid,puploaddate) values (#{pid},#{pname},#{planguage},#{pteamid},#{puploaddate})")
    int InsertProject(String pid,String pname,String planguage,String pteamid,String puploaddate);

    @Update("update User SET pname = #{new_pname} WHERE pid = #{pid}")
    int ChangeNameByid(String new_pname,String pid);
    @Select("select * from project_info where PNAME = #{pname}")
    Project FindProjectByName(String pname);

    @Select("select * from project_info")
    List<Project> Getallproject();

}
