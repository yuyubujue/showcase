package com.showcase.project.mapper;

import com.showcase.project.domain.User;
import com.showcase.project.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into User(ID,USERNAME,PASSWORD,EMAIL,AUTHORITY,IMG,INTRODUCTION) values(#{userid},#{username},#{passsword},#{email},#{authority},#{img},\'Nothing yet~\')")
    int insertUser(String userid, String username, String password, String email, String authority, byte[] img);
    @Select("select * from User where (USERNAME = #{username} OR EMAIL = #{username}) and PASSWORD = #{password}")
    User login(String username,String password);
    @Select("select * from User where email = #{email}")
    User findUserByEmail(String email);
    @Select("select * from User where USERNAME = #{username}")
    User findUserByName(String username);
    @Update("update User SET COOKIE = #{cookie} WHERE USERNAME = #{username} OR EMAIL = #{username}")
    int updateCookie(String username, String cookie);
    @Select("select * from User where COOKIE = #{cookie}")
    User findUserByCookie(String cookie);
    @Select("select * from User where COOKIE = #{cookie}")
    UserDTO findOwnByCookie(String cookie);
    @Select("select * from User")
    List<UserDTO> getUsers();
    @Update("update User SET AUTHORITY = #{authority} WHERE USERNAME = #{username} OR EMAIL = #{username}")
    int setAuthority(String authority, String username);
    @Update("update User SET PASSWORD = #{password} WHERE COOKIE = #{cookie}")
    int setPassword(String cookie, String password);
    @Select("select * from User where ID = #{ID}")
    UserDTO findUser(String ID);
    @Update("update User SET IMG = #{img} WHERE COOKIE = #{cookie}")
    int setImg(String cookie, byte[] img);
    @Select("select IMG from User where ID = #{id}")
    User getImg(String id);
    @Update("update User SET INTRODUCTION = #{introduction} WHERE COOKIE = #{cookie}")
    int setIntroduction(String cookie, String introduction);
    @Update("update User SET SKILL = #{skill} WHERE COOKIE = #{cookie}")
    int setSkill(String cookie, String skill);
    @Update("update User SET INTEREST = #{interest} WHERE COOKIE = #{cookie}")
    int setInterest(String cookie, String interest);
    @Delete("Delete from User where ID = #{id}")
    int removeUser(String id);
    @Select("Select * from User where AUTHORITY=#{authority} and COOKIE = #{cookie}")
    User checkTeacher(String authority,String cookie);

}
