package com.eureka.mapper;
import com.eureka.domain.Skill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface SkillMapper {
    @Insert("INSERT INTO `Skill` (`ID`, `SKILL`) VALUES (NULL, #{skill})")
    int addSkill(String skill);
    @Delete("DELETE FROM `Skill` WHERE `Skill`.`ID` = #{id}")
    int deleteSkill(String id);
    @Select("select * from Skill")
    List<Skill> getSkills();
    @Update("UPDATE `Skill` SET `SKILL` = '#{skill}' WHERE `Skill`.`ID` = #{id}")
    int updateSkill(String id, String skill);
    @Insert("INSERT INTO `UserSkill` (`UserID`, `SkillID`) VALUES (#{userID}, #{skillID})")
    int addUserSkill(String userID, String skillID);
    @Select("select * from Skill where ID = (select SkillID from UserSkill where UserID = #{userID})")
    List<Skill> getUserSkills(String userID);
    @Delete("DELETE FROM `UserSkill` WHERE `UserSkill`.`UserID` = #{userID} AND `UserSkill`.`SkillID` = #{skillID}")
    int deleteUserSkill(String userID, String skillID);
    @Select("SELECT * FROM `Skill` WHERE `SKILL` LIKE '%' || #{word} || '%'")
    List<Skill> searchSkills(String word);
}
