package com.eureka.controller;

import com.alibaba.fastjson2.JSON;
import com.eureka.ProjectDAO.Project;
import com.eureka.ProjectService.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Project ifdupName = ProjectService.findpbyname(pname);
        if (ifdupName != null){
            return "Duplicated project Name";
        }else{
            if (ProjectService.UploadProject(UUID.randomUUID().toString(),pname,planguage,pteamid, String.valueOf(System.currentTimeMillis()))==1){
                return "Upload Success";
            }else{
                return "Upload Failed";
            }
        }
    }

    @GetMapping(value = "/changePname")
    @ResponseBody
    public String changename(@RequestParam("pid") String pid,@RequestParam("pname") String rpname){

        if (ProjectService.ChangeNameByid(rpname,pid) == 1){
            return "Change Success!";
        }else{
            return "Change Failed!";
        }
    }

    @GetMapping(value = "/getAllProject")
    @ResponseBody
    public String getallproject(){
        try {

            List<Project> projects = ProjectService.Getallproject();
            if (projects == null){
                return "no projects!";
            }
            return JSON.toJSONString(projects);
        }
        catch (Exception e){
            List<Project> projects = ProjectService.Getallproject();
            return JSON.toJSONString(projects);
        }
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public  String test(){
        return "test!";
    }

}

