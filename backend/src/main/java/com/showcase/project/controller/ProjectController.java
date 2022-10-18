package com.showcase.project.controller;
import com.showcase.project.dto.ProjectLikeCommentDTO;
import com.showcase.project.dto.UserDTO;
import com.alibaba.fastjson2.JSON;
import com.showcase.project.alogrithm.TIDgenerator;
import com.showcase.project.domain.*;
import com.showcase.project.dto.ProjectDTO;
import com.showcase.project.service.ProjectService;
import com.showcase.project.service.SendMailService;
import com.showcase.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired(required = false)
    private ProjectService projectService;

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private SendMailService sendMailService;

    @Value("${setting.websiteDomain}")
    private String websiteDomain;

    //  Project
    @PostMapping(value = "/uploadProject")
    @ResponseBody
    public String upload(@RequestParam String pname, @RequestParam String shortTagline,@RequestParam String file, @RequestParam String introduction, @RequestParam String skills, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        if (projectService.uploadProject(pname, shortTagline, introduction, user.getId(), file) == 1) {
            int pid = projectService.getNewOne().getID();
            if (skills.contains(",")==false){
                projectService.UploadProjectSkill(pid,skills.trim());
            }else {
                skills = skills.trim();
                String skill[] = skills.split(",");
                for (int i = 0; i < skill.length; i++) {
                    projectService.UploadProjectSkill(pid, skill[i]);
                }
            }
            return String.valueOf(pid);

        } else {
            return "failed";
        }
    }


    @PostMapping(value = "/updateProjectCover")
    @ResponseBody
    public String uploadProjectCover(@RequestParam int pid, @RequestParam String file, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        String uid = user.getId();
        Project checker = projectService.projectChecker(pid, uid);
        if (checker == null) {
            return "not your project!";
        }
        if(projectService.updateProjectCover(pid, file) == 1){
            return "success";
        }else {
            return "failed!";
        }
    }
    @PostMapping(value = "/updateProjectIntro")
    @ResponseBody
    public String updateProjectIntro(@RequestParam String NewPIntro, @RequestParam int Pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }

        String uid = user.getId();
        Project checker = projectService.projectChecker(Pid, uid);
        if (checker == null) {
            return "not your project!";
        }
        int resp;
        resp = projectService.setNewPIntro(Pid, NewPIntro);
        if (resp == 1) {
            return "success";
        }
        return "failed!";
    }

    @PostMapping(value = "/updateProjectName")
    @ResponseBody
    public String updateProjectName(@RequestParam String NewPname, @RequestParam int Pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }

        String uid = user.getId();
        Project checker = projectService.projectChecker(Pid, uid);
        if (checker == null) {
            return "not your project!";
        }
        int resp;
        resp = projectService.setNewPname(Pid, NewPname);
        if (resp == 1) {
            return "success";
        }
        return "failed!";
    }

    @PostMapping(value = "/updateProjectTagLine")
    @ResponseBody
    public String updateProjectTagLine(@RequestParam String NewTagLine, @RequestParam int Pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        String uid = user.getId();
        Project checker = projectService.projectChecker(Pid, uid);
        if (checker == null) {
            return "not your project!";
        }
        int resp;
        resp = projectService.setNewTagLine(Pid, NewTagLine);
        if (resp == 1) {
            return "success";
        }
        return "failed!";
    }


    @GetMapping(value = "/getAllprojects")
    @ResponseBody
    public List<Project> getAllprojects() {
        return projectService.getAllProject();
    }

    @GetMapping(value = "/getProjectsByUpdateTime/{page}")
    @ResponseBody
    public String getProjectsByUpdateTime(@PathVariable("page") String page) {
        return JSON.toJSONString(projectService.getProjectsByUpdateTime(page));
    }

    @GetMapping(value = "/getProjectsByUpdateTimeAsc/{page}")
    @ResponseBody
    public String getProjectsByUpdateTimeAsc(@PathVariable("page") String page) {
        return JSON.toJSONString(projectService.getProjectsByUpdateTimeAsc(page));
    }

    @GetMapping(value = "/getProjectsByUploadTime/{page}")
    @ResponseBody
    public String getProjectsByUploadTime(@PathVariable("page") String page) {
        return JSON.toJSONString(projectService.getProjectsByCreateTime(page));
    }

    @GetMapping(value = "/getProjectsByUploadTimeAsc/{page}")
    @ResponseBody
    public String getProjectsByUploadTimeAsc(@PathVariable("page") String page) {
        return JSON.toJSONString(projectService.getProjectsByCreateTimeAsc(page));
    }

    @GetMapping(value = "/getProjectsByUser")
    @ResponseBody
    public String getProjectsByUser(@RequestParam("id") String uid) {
        return JSON.toJSONString(projectService.getProjectByUser(uid));
    }

    @GetMapping(value = "/getProjectsByPname")
    @ResponseBody
    public String getProjectsByPname(@RequestParam("pname") String pname) {
        return JSON.toJSONString(projectService.getProjectByPname(pname));
    }


//    @GetMapping(value = "/getProject")
//    @ResponseBody
//    public String getProject(@PathVariable("pid") int pid) {
//        return JSON.toJSONString(projectService.getProject(pid));
//    }

    @GetMapping(value = "/getProjectPageByPid")
    @ResponseBody
    public String getProjectPageByPid(@RequestParam("pid") int pid) {
        return JSON.toJSONString(projectService.getProjectPageByPid(pid));
    }


    @PostMapping(value = "/removeProject")
    @ResponseBody
    public String removeProject(@RequestParam("pid") int pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        String uid = user.getId();
        String auth = String.valueOf(user.getAuthority());
        if(auth.equals("admin")){
            int resp = projectService.RemoveProject(pid);

            if (resp == 1) {
                projectService.RemoveAllSkill(pid);
                return "delete success by admin";
            } else {
                return "delete fail by admin";
            }
        }
        Project checker = projectService.projectChecker(pid, uid);
        if (checker == null) {
            return "not you projects!";
        }
        if (projectService.RemoveProject(pid) == 1) {
            projectService.RemoveAllSkill(pid);
            return "success";
        }
        return "remove fail";
    }

    @GetMapping(value = "/getInviteLink/{pid}")
    @ResponseBody
    public String getInviteLink(@PathVariable("pid") int pid, @CookieValue(name = "Auth") String cookie) {
        if(!cookie.equals(UserService.strSpecialFilter(cookie))){
            return "Illegal input";
        }
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        Project project = projectService.getFullProjectByPid(pid);
        if (user.getId().equals(project.getOwner())){
            return websiteDomain.substring(1,websiteDomain.length()-1) + "/invite.html?code=" + project.getInvitecode();
        }else {
            return "unauthorized";
        }
    }

    @GetMapping(value = "/sendInvitation")
    @ResponseBody
    public String sendInvitation(@RequestParam String uname,@RequestParam("pid") int pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        User invited = userService.findUserByName(uname);
        String inv_email = invited.getEmail();
        Project project = projectService.getFullProjectByPid(pid);
        if (user.getId().equals(project.getOwner())){
            sendMailService.sendSimpleMail(inv_email,"invitation from project "+project.getID(),project.getInvitecode());
            return "sent success";
        }else {
            return "unauthorized";
        }
    }

    @PostMapping(value = "/invite")
    @ResponseBody
    public String invite(@RequestParam("invitecode") String invitecode, @CookieValue(name = "Auth") String cookie) {
        if(!cookie.equals(UserService.strSpecialFilter(cookie))||!invitecode.equals(UserService.strSpecialFilter(invitecode))){
            return "Illegal input";
        }
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        int pid = projectService.getPidByCode(invitecode);
        Project pj = projectService.getFullProjectByPid(pid);
        if (pj.getOwner().equals(user.getId())){
            return "failed!";
        }
        for (UserDTO u : projectService.getTeammateByPID(pid)){
            if (u.getId().equals(user.getId())){
                return "failed!";
            }
        }
        if(projectService.joinTeam(invitecode,cookie) == 1){
            return String.valueOf(projectService.getPidByCode(invitecode));
        }else{
            return "failed!";
        }
    }

    @PostMapping(value = "/getTeammateByPID")
    @ResponseBody
    public String getTeammateByPID(@RequestParam("pid") int pid) {
        return JSON.toJSONString(projectService.getTeammateByPID(pid));
    }

    @PostMapping(value = "/removeTeammate")
    @ResponseBody
    public String removeTeammate(@RequestParam("pid") int pid, @RequestParam("uid") String uid, @CookieValue(name = "Auth") String cookie) {
        if(!cookie.equals(UserService.strSpecialFilter(cookie))||!uid.equals(UserService.strSpecialFilter(uid))){
            return "Illegal input";
        }
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectLikeCommentDTO project = projectService.getProjectPageByPid(pid);
        if(project.getOwner().equals(user.getId())){
            if(projectService.removeTeammate(pid,uid) == 1){
                return "success";
            }else {
                return "failed!";
            }
        }else{
            return "unauthorized";
        }
    }

    @GetMapping(value = "/generateNewInviteCode/{pid}")
    @ResponseBody
    public String generateNewInviteCode(@PathVariable("pid") int pid, @CookieValue(name = "Auth") String cookie) {
        if(!cookie.equals(UserService.strSpecialFilter(cookie))){
            return "Illegal input";
        }
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectLikeCommentDTO project = projectService.getProjectPageByPid(pid);
        if(project.getOwner().equals(user.getId())){
            if(projectService.generateNewInviteCode(pid,TIDgenerator.getRandomTID()) == 1){
                return "success";
            }else {
                return "failed!";
            }
        }else{
            return "unauthorized";
        }
    }

        //  award

    @PostMapping(value = "/addAward")
    @ResponseBody
    public String addAward(@RequestParam int pid, @RequestParam String comment, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        User checker1 = userService.checkTeacher("teacher", cookie);
        if (checker1 == null) {
            return "only teacher can award";
        }
        String uid = user.getId();
        teacher_award checker = projectService.checkAward(pid, uid);
        if (checker != null) {
            return "award exist!";
        }
        String teacher_name = user.getUsername();
        int resp = projectService.addAward(pid, uid, comment, teacher_name);
        if (resp == 1) {
            return "award success";
        } else {
            return "award fail";
        }
    }


    @PostMapping(value = "/DeleteAward")
    @ResponseBody
    public String DeleteAward(@RequestParam int pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        User checker1 = userService.checkTeacher("teacher", cookie);
        if (checker1 == null) {
            return "only teacher can award";
        }

        String uid = user.getId();
        String auth = String.valueOf(user.getAuthority());
        if(auth.equals("admin")){
            int resp = projectService.DeleteAwardAdmin(pid);
            if (resp == 1) {
                return "all award removed by admin of "+ pid;
            } else {
                return "delete fail by admin";
            }
        }else{
            teacher_award checker = projectService.checkAward(pid, uid);
            if (checker == null) {
                return "not your award";
            }
            int resp = projectService.DeleteAward(pid, uid);
            if (resp == 1) {
                return "delete success";
            } else {
                return "delete fail";
            }
        }


    }

    @GetMapping(value = "GetAwardCommentByID")
    @ResponseBody
    public String GetAwardCommentByID(@RequestParam int pid) {
        return JSON.toJSONString(projectService.GetAwardCommentByID(pid));
    }

    @GetMapping(value = "/ShowAwardedProject/{page}")
    @ResponseBody
    public String ShowAwardedProject(@PathVariable("page") String page) {
        return JSON.toJSONString(projectService.GetAwardedProject(page));
    }

    @GetMapping(value = "GetAwardAmountById")
    @ResponseBody
    public int GetAwardAmountById(@RequestParam int pid) {
        return projectService.GetAwardAmountById(pid);
    }
//  comment

    @PostMapping(value = "/writeComment")
    @ResponseBody
    public String writeComment(@RequestParam int pid, @RequestParam String comment, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        String uid = user.getId();
        String uname = user.getUsername();
        int checker = projectService.WriteComment(pid, uid, comment, uname);
        if (checker == 1) {
            return "comment success";
        } else {
            return "comment fail";
        }
    }

    @GetMapping(value = "/getComment/{page}")
    @ResponseBody
    public String getComment(@RequestParam int pid, @PathVariable("page") String page) {
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        return JSON.toJSONString(projectService.GetComment(pid, page));
    }

    @PostMapping(value = "/removeComment")
    @ResponseBody
    public String removeComment(@RequestParam int pid, @RequestParam int cid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        String uid = user.getId();
        String auth = String.valueOf(user.getAuthority());

        if (auth.equals("admin")){
            int resp = projectService.DeleteComment(cid);
            if (resp == 1) {
                return "delete success by admin";
            } else {
                return "delete fail by admin";
            }
        }else {
            List<Project_comment> checker = projectService.CheckComment(pid, uid, cid);
            if (checker == null) {
                return "no such comment";
            } else {
                int resp = projectService.DeleteComment(cid);
                if (resp == 1) {
                    return "delete success";
                } else {
                    return "delete fail";
                }
            }
        }
    }

    // like
    @PostMapping(value = "/like")
    @ResponseBody
    public String like(@RequestParam int pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        String uid = user.getId();
        String uname = user.getUsername();
        Project_like checker = projectService.CheckLike(pid, uid);
        if (checker == null) {
            int resp = projectService.LikeProject(pid, uid, uname);
            if (resp == 1) {
                return "like success";
            } else {
                return "like fail";
            }
        } else {
            return "repeated like";
        }
    }

    @PostMapping(value = "/unlike")
    @ResponseBody
    public String unlike(@RequestParam int pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        String uid = user.getId();


        int resp = projectService.UnLikeProject(pid, uid);
        if (resp == 1) {
            return "unlike success";
        } else {
            return "no such like";
        }
    }

    @GetMapping(value = "/getProjectLikeByID")
    @ResponseBody
    public String getProjectLikeById(int pid) {
        return JSON.toJSONString(projectService.getProjectLikeByID(pid));
    }

    @GetMapping(value = "/GetProjectByLike/{page}")
    @ResponseBody
    public String getProjectByLike(@PathVariable("page") String page) {

        return JSON.toJSONString(projectService.GetProjectByLike(page));
    }

    @GetMapping(value = "/ShowMyLike")
    @ResponseBody
    public String ShowMyLike(@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        String uid = user.getId();
        return JSON.toJSONString(projectService.ShowMyLike(uid));
    }

    @GetMapping(value ="/getlikestatus")
    @ResponseBody
    public String getLikeStatus(@RequestParam int pid, @CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        String uid = user.getId();
        if (user == null) {
            return "unauthorized";
        }else{
            Project_like checker = projectService.getlikestatus(pid,uid);
            if (checker==null){
                return "can like!";
            }else{
                return "cannot like!";
            }
        }
    }
    //skill
    @GetMapping(value = "/GetProjectBySkills/{page}")
    @ResponseBody
    public String getProjectBySkills(@RequestParam String skills, @PathVariable("page") String page) {
        skills = skills.trim();
        String skill[] = skills.split(",");
        List<Integer> result_set = new ArrayList<Integer>();
        List<ProjectLikeCommentDTO> result_project = new ArrayList<ProjectLikeCommentDTO>();
        for (int i = 0; i < skill.length; i++) {
            List<Integer> temp_set = projectService.GetProjectIdBySkills(skill[i]);
            for (Integer pid : temp_set) {
                result_set.add(pid);
            }
        }
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(result_set);
        ArrayList<Integer> result = new ArrayList<>(hashSet);
        for (Integer pid : result) {
            if (projectService.getProject(pid) == null) {
                return result.toString() + "some projects not exist";
            }
            result_project.add(projectService.getProjectPageByPid(pid));
        }
        int total_page = (result_project.size()/10) +1;
        int req_page=Integer.parseInt(page);
        try {
            if (req_page == total_page) {
                return JSON.toJSONString(result_project.subList(req_page * 10 -10, result_project.size()));
            } else if (req_page < total_page) {
                return JSON.toJSONString(result_project.subList(req_page * 10 - 10, req_page * 10));
            } else {
                return "no such page!";
            }
        }catch (Exception e){
            return "page error!";
        }

    }

    @GetMapping("GetProjectSkills")
    @ResponseBody
    public String GetProjectSkills(@RequestParam int pid){
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        List<String> res_set = projectService.getProjectSkills(pid);
        if (res_set==null){
            return "no skill in this project!";
        }else{
            return res_set.toString();
        }
    }

    @PostMapping("UploadNewSkill")
    @ResponseBody
    public String UploadNewSkill(@RequestParam int pid,@RequestParam String skill, @CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        String uid = user.getId();
        Project checker1 = projectService.projectChecker(pid,uid);
        if (checker1 == null){
            return "not your project!";
        }
        if(projectService.UploadProjectSkill(pid,skill)==1){
            return "success";
        }else{
            return "upload skill fail";
        }
    }

    @PostMapping("/UpdateSkill")
    @GetMapping
    public String UpdateSkill(@RequestParam int pid,@RequestParam String skills,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        String uid = user.getId();
        Project checker1 = projectService.projectChecker(pid,uid);
        if (checker1 == null){
            return "not your project!";
        }
        try {
            projectService.RemoveAllSkill(pid);
            if (skills.contains(",") == false) {
                projectService.UploadProjectSkill(pid, skills.trim());
            } else {
                skills = skills.trim();
                String skill[] = skills.split(",");
                for (int i = 0; i < skill.length; i++) {
                    projectService.UploadProjectSkill(pid, skill[i]);
                }

            }
            return "delete Success!";
        }catch (Exception e){
            return "delete fail!";
        }
    }

    @PostMapping("/RemoveSkill")
    @ResponseBody
    public String RemoveSkill(@RequestParam int pid, @RequestParam String skills, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        ProjectDTO checker2 = projectService.getProject(pid);
        if (checker2 == null) {
            return "no such project!";
        }
        String uid = user.getId();
        Project checker1 = projectService.projectChecker(pid,uid);
        if (checker1 == null){
            return "not your project!";
        }
        if(projectService.RemoveSkill(pid,skills)==1){
            return "success";
        }else{
            return "fail remove";
        }

    }
}

