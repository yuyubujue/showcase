package com.eureka.mapper;

import com.eureka.domain.User;
import com.eureka.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into User(ID,USERNAME,PASSWORD,EMAIL,AUTHORITY,IMG,INTRODUCTION) values(#{userid},#{username},#{passsword},#{email},#{authority},#{img},\'Nothing yet~\')")
    int insertUser(String userid, String username, String passsword, String email, String authority, String img);
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
    @Select("select ID,USERNAME,PASSWORD,EMAIL,IMG from User")
    List<UserDTO> getUsers();
    @Update("update User SET AUTHORITY = #{authority} WHERE USERNAME = #{username} OR EMAIL = #{username}")
    int setAuthority(String username, String authority);
    @Update("update User SET PASSWORD = #{password} WHERE COOKIE = #{cookie}")
    int setPassword(String cookie, String password);
    @Select("select ID,USERNAME,PASSWORD,EMAIL,IMG,INTRODUCTION from User where ID = #{ID}")
    UserDTO findUser(String ID);
    @Update("update User SET IMG = #{img} WHERE COOKIE = #{cookie}")
    int setImg(String cookie, String img);
    @Select("select IMG from User where ID = #{id}")
    String getImg(String id);
    @Update("update User SET INTRODUCTION = #{introduction} WHERE COOKIE = #{cookie}")
    int setIntroduction(String cookie, String introduction);
}
