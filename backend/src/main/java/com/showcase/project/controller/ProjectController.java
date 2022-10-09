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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired(required = false)
    private ProjectService projectService;

    @Autowired(required = false)
    private UserService userService;


    @PostMapping(value = "/uploadProject")
    @ResponseBody
    public String upload(@RequestParam String pname, @RequestParam String shortTagline,@RequestParam String introduction, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        byte[] data= new byte[2];
        try{
            ClassPathResource classPathResource = new ClassPathResource("static/null.png");
            InputStream ins = classPathResource.getInputStream();
            byte[] buffer=new byte[2];
            int len=0;
            org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream bos=new ByteArrayOutputStream();
            while((len=ins.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
            bos.flush();
            data = bos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (projectService.uploadProject(pname,shortTagline,introduction,user.getId(),data) == 1){
            return String.valueOf(projectService.getNewOne().getID());
        }else{
            return "failed";
        }
    }

    @PostMapping(value = "/updateProjectName")
    @ResponseBody
    public String updateProjectName(@RequestParam String NewPname,@RequestParam int Pid,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        int resp;
        resp = projectService.setNewPname(Pid,NewPname);
        if(resp==1){
            return "success";
        }
        return "failed!";
    }

    @PostMapping(value = "/updateProjectTagLine")
    @ResponseBody
    public String updateProjectTagLine(@RequestParam String NewTagLine,@RequestParam int Pid,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        int resp;
        resp = projectService.setNewTagLine(Pid,NewTagLine);
        if(resp==1){
            return "success";
        }
        return "failed!";
    }


    @GetMapping(value="/getAllprojects")
    @ResponseBody
    public List<ProjectDTO> getAllprojects(){
        return projectService.getAllProject();
    }

    @GetMapping(value = "/getProjectLikeByID")
    @ResponseBody
    public String getProjectLikeById(int pid){
        return JSON.toJSONString(projectService.getProjectLikeByID(pid));
    }

    @GetMapping(value = "/getProjects/{page}")
    @ResponseBody
    public String getProjects(@PathVariable("page") String page){
        return JSON.toJSONString(projectService.getProjects(page));
    }

    @GetMapping(value = "/getProjectsByUser")
    @ResponseBody
    public String getProjectsByUser(@RequestParam("id") String uid){
        return JSON.toJSONString(projectService.getProjectByUser(uid));
    }

    @GetMapping(value = "/getProjectsAwardBadges/{page}")
    @ResponseBody
    public String getProjectsAwardBadges(@PathVariable("page") String page){
        if(page.equals("1")) {
            return "[{\"pid\":\"123\",\"pname\":\"test1\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"124\",\"pname\":\"test2\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"124\",\"pname\":\"test3\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"125\",\"pname\":\"test4\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"126\",\"pname\":\"test5\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"127\",\"pname\":\"test6\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"128\",\"pname\":\"test7\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"129\",\"pname\":\"test8\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"130\",\"pname\":\"test9\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"131\",\"pname\":\"test10\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"}]";
        }else {
            return "[{\"pid\":\"132\",\"pname\":\"test11\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"133\",\"pname\":\"test12\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"134\",\"pname\":\"test13\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"135\",\"pname\":\"test14\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"136\",\"pname\":\"test15\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"137\",\"pname\":\"test16\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"138\",\"pname\":\"test17\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"}]";
        }
    }

    @GetMapping(value = "/getProject/{pid}")
    @ResponseBody
    public String getProject(@PathVariable("pid") int pid) {
        return JSON.toJSONString(projectService.getProject(pid));
    }

    @GetMapping(value = "/removeProject/{pid}")
    @ResponseBody
    public String removeProject(@PathVariable("pid") int pid) {
        return "success";
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
    public String uploadProjectCover(@PathVariable("id") int id, @RequestParam MultipartFile file, @CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        byte[] data= new byte[1024];
        try {
            InputStream ins = file.getInputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            while((len=ins.read(buffer))!=-1){
                bos.write(buffer,0,len);
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
    public String uploadProjectImg(@PathVariable("id") int pid, @RequestParam MultipartFile file, @CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        byte[] data= new byte[1024];
        try {
            InputStream ins = file.getInputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            while((len=ins.read(buffer))!=-1){
                bos.write(buffer,0,len);
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
    public ResponseEntity<byte[]> getCover(@PathVariable int id, HttpServletResponse response){
        HttpHeaders headers = new HttpHeaders();
        try{
            byte[] imageContent = projectService.getCoverImage(id).getCoverImage();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/removeProjectImg")
    @ResponseBody
    public String removeProjectImg(@RequestBody String project){
        return "./File/projectimg/null.png";
    }

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

    @PostMapping(value = "/addAward")
    @ResponseBody
    public String addAward (@RequestParam int pid,@RequestParam String comment,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        } else if (user.getAuthority()!="teacher") {
            return "only teacher can award!";
        }else {
            String uid = user.getId();
            teacher_award checker = projectService.checkAward(pid,uid);
            if(checker != null){
                return "award exist!";
            }
            String teacher_name = user.getUsername();
            int resp = projectService.addAward(pid,uid,comment,teacher_name);
            if(resp == 1){
                return "award success";
            }else{
                return "award fail";
            }
        }
    }

    @PostMapping(value="/DeleteAward")
    @ResponseBody
    public String DeleteAward(@RequestParam int pid,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        } else if (user.getAuthority()!="teacher") {
            return "only teacher can remove award!";
        }else {
            String uid = user.getId();
            teacher_award checker = projectService.checkAward(pid, uid);
            if (checker == null) {
                return "award exist!";
            }
            int resp = projectService.DeleteAward(pid, uid);
            if (resp == 1) {
                return "delete success";
            } else {
                return "delete fail";
            }
        }
    }

    @GetMapping(value = "GetAwardCommentByTeacher")
    @ResponseBody
    public List<TeacherCommentDTO> GetAwardCommentByID(@RequestParam int pid){
        return projectService.GetAwardCommentByID(pid);
    }

    @PostMapping(value = "/writeComment")
    @ResponseBody
    public String writeComment(@RequestParam int pid,String comment,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        String uid = user.getId();
        int checker = projectService.WriteComment(pid,uid,comment);
        if(checker==1) {
            return "comment success";
        }else{
            return "comment fail";
        }
    }

    @GetMapping(value = "/getComment/{page}")
    @ResponseBody
    public String getComment(@RequestParam int pid,@PathVariable("page") String page){
        return JSON.toJSONString(projectService.GetComment(pid,page));
    }

    @PostMapping(value = "/removeComment")
    @ResponseBody
    public String removeComment(@RequestParam int pid,@RequestParam int comment_id,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        String uid = user.getId();
        List<Project_comment> checker = projectService.CheckComment(pid,uid,comment_id);
        if (checker == null){
            return "no such comment";
        }else{
            int resp = projectService.DeleteComment(comment_id);
            if(resp==1){
                return "delete success";
            }else {
                return "delete fail";
            }
        }
    }

    @PostMapping(value = "/like")
    @ResponseBody
    public String like(@RequestParam int pid,@CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        String uid = user.getId();
        Project_like checker = projectService.CheckLike(pid,uid);
        if (checker == null){
            int resp = projectService.LikeProject(pid,uid);
            if(resp==1) {
                return "like success";
            }else{
                return "like fail";
            }
        }else{
            return "repeated like";
        }
    }

    @PostMapping(value = "/unlike")
    @ResponseBody
    public String unlike(@RequestParam int pid,@CookieValue(name = "Auth") String cookie){
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        String uid = user.getId();


        int resp = projectService.UnLikeProject(pid,uid);
        if(resp==1) {
            return "unlike success";
        }else{
            return "no such like";
        }



    }


}

