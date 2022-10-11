package com.showcase.project.controller;

import com.alibaba.fastjson2.JSON;
import com.showcase.project.domain.*;
import com.showcase.project.dto.ProjectDTO;
import com.showcase.project.dto.TeacherCommentDTO;
import com.showcase.project.service.UserService;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.showcase.project.service.ProjectService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired(required = false)
    private ProjectService projectService;

    @Autowired(required = false)
    private UserService userService;

    //  Project
    @PostMapping(value = "/uploadProject")
    @ResponseBody
    public String upload(@RequestParam String pname, @RequestParam String shortTagline, @RequestParam String introduction, @RequestParam String skills, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }

        byte[] data = new byte[2];
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/null.png");
            InputStream ins = classPathResource.getInputStream();
            byte[] buffer = new byte[2];
            int len = 0;
            org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = ins.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            data = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (projectService.uploadProject(pname, shortTagline, introduction, user.getId(), data) == 1) {
            int pid = projectService.getNewOne().getID();
            skills = skills.trim();
            String skill[] = skills.split(",");
            for (int i = 0; i < skill.length; i++) {
                projectService.UploadProjectSkill(pid, skill[i]);
            }
            return String.valueOf(projectService.getNewOne().getID());

        } else {
            return "failed";
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


    @GetMapping(value = "/getProject/{pid}")
    @ResponseBody
    public String getProject(@PathVariable("pid") int pid) {
        return JSON.toJSONString(projectService.getProject(pid));
    }

    @PostMapping(value = "/removeProject")
    @ResponseBody
    public String removeProject(@RequestParam("pid") int pid, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        String uid = user.getId();
        Project checker = projectService.projectChecker(pid, uid);
        if (checker == null) {
            return "not you projects!";
        }
        if (projectService.RemoveProject(pid) == 1) {
            return "success";
        }
        return "remove fail";
    }

    @GetMapping(value = "/getInviteLink")
    @ResponseBody
    public String getInviteLink(@RequestParam("pid") String pid) {
        return "http://xxxxx.com/project/invite/?id=88c348f2-c0af-3b77-d956-acc85591f6ca";
    }

    @GetMapping(value = "/invite/{id}")
    @ResponseBody
    public String invite(@PathVariable("id") String id) {
        return "success";
    }

    @PostMapping(value = "/uploadProjectCover/{id}")
    @ResponseBody
    public String uploadProjectCover(@PathVariable("id") int id, @RequestParam MultipartFile file, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        byte[] data = new byte[1024];
        try {
            InputStream ins = file.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = ins.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            data = bos.toByteArray();
            projectService.uploadProjectCover(id, data);
            return "succeed";
        } catch (IOException e) {
            return "failed";
        }
    }

    @PostMapping(value = "/uploadProjectImg/{id}")
    @ResponseBody
    public String uploadProjectImg(@PathVariable("id") int pid, @RequestParam MultipartFile file, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if (user == null) {
            return "unauthorized";
        }
        byte[] data = new byte[1024];
        try {
            InputStream ins = file.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = ins.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            data = bos.toByteArray();
            projectService.uploadProjectImg(pid, data);
            return "succeed";
        } catch (IOException e) {
            return "failed";
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getCover/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getCover(@PathVariable int id, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        try {
            byte[] imageContent = projectService.getCoverImage(id).getCoverImage();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/removeProjectImg")
    @ResponseBody
    public String removeProjectImg(@RequestBody String project) {
        return "./File/projectimg/null.png";
    }


    //  team
    @GetMapping(value = "/getTeammate/{pid}")
    @ResponseBody
    public String getTeammate(@PathVariable("pid") String pid) {
        return "[{\"id\":\"a37f46fc-5ebe-4fdc-b92c-911ac12c7df6\",\"username\":\"testacc\",\"img\":\"./File/image/null.png\"},{\"id\":\"a37f46fc-5ebe-4fdc-b92c-911ac12c7df6\",\"username\":\"testacc\",\"img\":\"./File/image/null.png\"},{\"id\":\"a37f46fc-5ebe-4fdc-b92c-911ac12c7df6\",\"username\":\"https://icp.sojson.com\",\"img\":\"./File/image/null.png\"}]";
    }

    @PostMapping(value = "/removeTeammate/")
    @ResponseBody
    public String removeTeammate(@RequestParam String project) {
        return "success";
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
    public String writeComment(@RequestParam int pid, String comment, @CookieValue(name = "Auth") String cookie) {
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

    //skill
    @GetMapping(value = "/GetProjectBySkills/{page}")
    @ResponseBody
    public String getProjectBySkills(@RequestParam String skills, @PathVariable("page") String page) {
        skills = skills.trim();
        String skill[] = skills.split(",");
        List<Integer> result_set = new ArrayList<Integer>();
        List<ProjectDTO> result_project = new ArrayList<ProjectDTO>();
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
            result_project.add(projectService.getProject(pid));
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

