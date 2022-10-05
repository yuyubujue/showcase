package com.showcase.project.controller;

import com.alibaba.fastjson2.JSON;
import com.showcase.project.domain.User;
import com.showcase.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.showcase.project.service.ProjectService;
import com.showcase.project.domain.Project;

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
    public String upload(@RequestParam String pname, @RequestParam String shortTagline,@RequestParam String planguage,@RequestParam String introduction, @CookieValue(name = "Auth") String cookie) {
        User user = userService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        if (projectService.uploadProject(pname,shortTagline,planguage,introduction,user.getId()) == 1){
            return "succeed";
        }else{
            return "failed";
        }
    }

    @PostMapping(value = "/updateProject")
    @ResponseBody
    public String update(@RequestBody String project){
        return "success";
    }

    @PostMapping(value = "/updateProjectThumbnail")
    @ResponseBody
    public String updateProjectThumbnail(@RequestParam("pid") String pid, @RequestParam("thumbnail") Blob thumbnail){
        return "success";
    }

    @GetMapping(value = "/getProjectByDate/{page}")
    @ResponseBody
    public String getProjectByDate(@PathVariable("page") String page){
        if(page.equals("1")) {
            return "[{\"pid\":\"123\",\"pname\":\"test1\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"124\",\"pname\":\"test2\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"124\",\"pname\":\"test3\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"125\",\"pname\":\"test4\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"126\",\"pname\":\"test5\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"127\",\"pname\":\"test6\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"128\",\"pname\":\"test7\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"129\",\"pname\":\"test8\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"130\",\"pname\":\"test9\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"131\",\"pname\":\"test10\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"}]";
        }else {
            return "[{\"pid\":\"132\",\"pname\":\"test11\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"133\",\"pname\":\"test12\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"134\",\"pname\":\"test13\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"135\",\"pname\":\"test14\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"136\",\"pname\":\"test15\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"137\",\"pname\":\"test16\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"138\",\"pname\":\"test17\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"}]";
        }
    }

    @GetMapping(value = "/getProjectByLike/{page}")
    @ResponseBody
    public String getProjectByLike(@PathVariable("page") String page){
        if(page.equals("1")) {
            return "[{\"pid\":\"123\",\"pname\":\"test1\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"124\",\"pname\":\"test2\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"124\",\"pname\":\"test3\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"125\",\"pname\":\"test4\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"126\",\"pname\":\"test5\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"127\",\"pname\":\"test6\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"128\",\"pname\":\"test7\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"129\",\"pname\":\"test8\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"130\",\"pname\":\"test9\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"131\",\"pname\":\"test10\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"}]";
        }else {
            return "[{\"pid\":\"132\",\"pname\":\"test11\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"133\",\"pname\":\"test12\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"134\",\"pname\":\"test13\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"135\",\"pname\":\"test14\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"136\",\"pname\":\"test15\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"137\",\"pname\":\"test16\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"},{\"pid\":\"138\",\"pname\":\"test17\",\"shortTagline\":\"Test short tagline\",\"thumbnail\":\"./File/thumbnail/null.png\"}]";
        }
    }

    @GetMapping(value = "/getProjects/{page}")
    @ResponseBody
    public String getProjects(@PathVariable("page") String page){
        return JSON.toJSONString(projectService.getProjects(page));
    }

    @GetMapping(value = "/getProjectsByUser")
    @ResponseBody
    public String getProjectsByUser(@RequestParam("id") String id){
        return JSON.toJSONString(projectService.getProjectByUser(id));
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
    public String getProject(@PathVariable("pid") String pid) {
        return JSON.toJSONString(projectService.getProject(pid));
    }

    @GetMapping(value = "/removeProject/{pid}")
    @ResponseBody
    public String removeProject(@PathVariable("pid") String pid) {
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

    @PostMapping(value = "/uploadProjectImg")
    @ResponseBody
    public String uploadProjectImg(@RequestParam("pid") String pid, @RequestParam MultipartFile img){
        return "./File/projectimg/null.png";
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
    public String removeTeammate(@RequestBody String project) {
        return "success";
    }

    @PostMapping(value = "/addAwardBadges")
    @ResponseBody
    public String addAwardBadges (@RequestBody String project){
        return "success";
    }

    @PostMapping(value = "/writeComment")
    @ResponseBody
    public String writeComment(@RequestBody String project){
        return "success";
    }

    @PostMapping(value = "/getCommit")
    @ResponseBody
    public String getComment(@RequestBody String project){
        return "[{\"commentID\":\"1\",\"id\":\"a37f46fc-5ebe-4fdc-b92c-911ac12c7df6\",\"comment\":\"test comment\"},{\"commentID\":\"2\",\"id\":\"a37f46fc-5ebe-4fdc-b92c-911ac12c7df6\",\"comment\":\"test comment\"}]";
    }

    @PostMapping(value = "/removeCommit")
    @ResponseBody
    public String removeCommit(@RequestBody String project){
        return "[{\"commentID\":\"1\",\"id\":\"a37f46fc-5ebe-4fdc-b92c-911ac12c7df6\",\"comment\":\"test comment\"},{\"commentID\":\"2\",\"id\":\"a37f46fc-5ebe-4fdc-b92c-911ac12c7df6\",\"comment\":\"test comment\"}]";
    }

    @PostMapping(value = "/like")
    @ResponseBody
    public String like(@RequestBody String project) {
        return "success";
    }

    @PostMapping(value = "/unlike")
    @ResponseBody
    public String unlike(@RequestBody String project){
        return "success";
    }

    @PostMapping(value = "/posttest")
    @ResponseBody
    public String posttest(@RequestParam String pname, @RequestParam String shortTagline,@RequestParam String planguage,@RequestParam String introduction){
        return pname;
    }
}

