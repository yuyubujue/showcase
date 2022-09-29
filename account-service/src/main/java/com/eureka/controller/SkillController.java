package com.eureka.controller;
import com.alibaba.fastjson2.JSON;
import com.eureka.domain.User;
import com.eureka.domain.Skill;
import com.eureka.service.SkillService;
import com.eureka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired(required = false)
    private SkillService skillService;
    private UserService userService;

    @PostMapping("/addskill")
    @ResponseBody
    public String add_Skill(@CookieValue(name = "Auth") String cookie, String skill) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null) {
            return "unauthorized";
        }else if(!user.getAuthority().equals("admin")){
            return "unauthorized";
        }else{
            if(skillService.addSkill(skill) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @PostMapping("/deleteskill")
    @ResponseBody
    public String delete_Skill(@CookieValue(name = "Auth") String cookie, String skillID) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null) {
            return "unauthorized";
        }else if(!user.getAuthority().equals("admin")){
            return "unauthorized";
        }else{
            if(skillService.deleteSkill(skillID) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @GetMapping("/getskills")
    @ResponseBody
    public String get_skills() {
        return JSON.toJSONString(skillService.getSkills());
    }

    @PostMapping("/updateskill")
    @ResponseBody
    public String update_skill(@CookieValue(name = "Auth") String cookie,String id, String skill) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null) {
            return "unauthorized";
        }else if(!user.getAuthority().equals("admin")){
            return "unauthorized";
        }else{
            if(skillService.updateSkill(id, skill) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @PostMapping("/adduserskill")
    @ResponseBody
    public String add_User_Skill(@CookieValue(name = "Auth") String cookie, String userID, String skillID) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null) {
            return "unauthorized";
        }else{
            if(skillService.addUserSkill(user.getId(), skillID) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @PostMapping("/getuserskills")
    @ResponseBody
    public String get_user_skills(String userID) {
        return JSON.toJSONString(skillService.getUserSkills(userID));
    }

    @PostMapping("/deleteuserskill")
    @ResponseBody
    public String delete_user_skill(@CookieValue(name = "Auth") String cookie, String skillID) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null) {
            return "unauthorized";
        }else {
            return JSON.toJSONString(skillService.deleteUserSkill(user.getId(),skillID));
        }
    }

    @PostMapping("/searchskill")
    @ResponseBody
    public String search_skill(String word) {
        return JSON.toJSONString(skillService.searchSkills(word));
    }
}