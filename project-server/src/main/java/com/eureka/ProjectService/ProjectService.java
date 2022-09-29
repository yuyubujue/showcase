package com.eureka.ProjectService;

import com.eureka.ProjectDAO.project_info;
import com.eureka.projectMapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired(required = false)
    private ProjectMapper projectMapper;

    public int UploadProject(String pid, String pname, String planguage, String pteamid, String puploaddate) {
        return projectMapper.InsertProject(pid,pname,planguage,pteamid,puploaddate);
    }

    public project_info findpbyname(String pname){
        return projectMapper.FindProjectByName(pname);
    }

    public int ChangeNameByid(String new_pname,String id) {return projectMapper.ChangeNameByid(new_pname, id);}

    public List<project_info> Getallproject(){return projectMapper.Getallproject();}
}
