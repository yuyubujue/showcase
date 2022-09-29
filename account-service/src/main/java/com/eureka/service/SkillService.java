package com.eureka.service;

import com.eureka.domain.Skill;
import com.eureka.mapper.SkillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SkillService")
public class SkillService{
    @Autowired(required=false)
    private SkillMapper skillMapper;

    public int addSkill(String skill){
        return skillMapper.addSkill(skill);
    }
    public int deleteSkill(String id){return skillMapper.deleteSkill(id);}
    public List<Skill> getSkills(){return skillMapper.getSkills();}
    public int updateSkill(String id, String skill){return skillMapper.updateSkill(id, skill);}
    public int addUserSkill(String userID, String skillID){return skillMapper.addUserSkill(userID, skillID);}
    public List<Skill> getUserSkills(String userID){return skillMapper.getUserSkills(userID);}
    public int deleteUserSkill(String userID, String skillID){return skillMapper.deleteUserSkill(userID, skillID);}
    public List<Skill> searchSkills(String word){return skillMapper.searchSkills(word);}
}
