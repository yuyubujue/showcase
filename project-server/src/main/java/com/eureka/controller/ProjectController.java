package com.eureka.controller;

import com.alibaba.fastjson2.JSON;
import com.eureka.ProjectDAO.project_info;
import com.eureka.ProjectService.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.util.List;
import java.util.UUID;

@Api(tags = {"IProject"})
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired(required=false)
    private ProjectService ProjectService;

    @ResponseBody
    @PostMapping(value = "/uploadProject")
    public String upload(@RequestParam("pname") String pname,@RequestParam("planguage") String planguage,@RequestParam("pteamid") String pteamid) {
        try {
            project_info ifdupName = ProjectService.findpbyname(pname);
            if (ifdupName != null) {
                return "Duplicated project Name";
            } else {
                if (ProjectService.UploadProject(UUID.randomUUID().toString(), pname, planguage, pteamid, String.valueOf(System.currentTimeMillis())) == 1) {
                    return "Upload Success";
                } else {
                    return "Upload Failed";
                }
            }
        }catch (Exception e){
            return "UploadProjects!";
        }
    }
    @GetMapping(value="/get10Pagebydate")
    @ResponseBody
    public String get10Pagebydate(){
        // 暂未实现，10.10日前submit
        return "10recentproject.";
    }

    @GetMapping(value ="/get10Pagebylike")
    @ResponseBody
    public String get10Pagebylike(){
        // 暂未实现，10.10日前submit
        return "10toplikedproject.";
    }
    @GetMapping(value = "/changePname")
    @ResponseBody
    public String changename(@RequestParam("pid") String pid,@RequestParam("pname") String rpname){
        try {
            if (ProjectService.ChangeNameByid(rpname, pid) == 1) {
                return "Change Success!";
            } else {
                return "Change Failed!";
            }
        }catch (Exception e){
            return "change project name!";
        }
    }

    @GetMapping(value = "/getAllProject")
    @ResponseBody
    public String getallproject(){
        try {
            List<project_info> projects = ProjectService.Getallproject();
            if (projects == null){
                return "no projects!";
            }
            return JSON.toJSONString(projects);
        }
        catch (Exception e){
            return "get all projects!";
        }
    }

    @PostMapping(value = "/SetProjectIntro")
    @ResponseBody
    public String SetProjectIntro(@RequestParam("curTid") String tid,@RequestParam("img") Blob img,@RequestParam("intro") String intro){
        return "SetProjectIntro";
    }

    @GetMapping(value="/GetProjectimg")
    @ResponseBody
    public String GetPIMG(@RequestParam("curTid")String tid){
        return "getallIMGbyteamid";
    }

    @GetMapping(value="/GetProjectintro")
    @ResponseBody
    public String GetPINTRO(@RequestParam("curTid")String tid){
        return "getINtroductionbyteamid";
    }

    @PostMapping(value = "/UpdateProjectintro")
    @ResponseBody
    public String UpdatePINTRO(@RequestParam("curTid")String tid,@RequestParam("newIntro")String newintro){
        return "updateINtroductionbyteamid";
    }
    @PostMapping(value="/newimg")
    @ResponseBody
    public String Uploadnewimg(@RequestParam("curTid")String tid,@RequestParam("newimg")Blob newimg){
        return "upload new img by team";
    }
    @DeleteMapping(value="/deleteimg")
    @ResponseBody
    public String Deleteimg(@RequestParam("curTid")String tid,@RequestParam("delimg")String imgid){
        return "delete img by tid and imgid";
    }
    @GetMapping(value="/like")
    @ResponseBody
    public String userlike(@RequestParam("curUid")String uid,@RequestParam("selectedproject")String pid){
        return "add one like to a project as user(no duplicated)";
    }

    @GetMapping(value = "/comment")
    @ResponseBody
    public String usercomment(@RequestParam("curUid")String uid,@RequestParam("comment")String comment){
        return "add one user comment";
    }
    @GetMapping(value = "/test")
    @ResponseBody
    public  String test(){
        return "test!";
    }

}

