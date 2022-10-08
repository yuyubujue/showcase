package com.showcase.project.service;

import com.showcase.project.domain.Project;
import com.showcase.project.dto.ProjectDTO;
import com.showcase.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ProjectService")
public class ProjectService{
    @Autowired(required=false)
    private ProjectMapper projectMapper;

    public List<ProjectDTO> getProjects(String page) {

        if(page.equals("1")) {
            return projectMapper.getProjects(0,10);
        }else{
            return projectMapper.getProjects(Integer.parseInt(page) * 10, Integer.parseInt(page) * 10 + 10);
        }
    }

    public int uploadProject(String pname, String tagline, String technologies, String introduction, String owner, byte[] img) {
        return projectMapper.insertProject(pname, tagline, technologies, introduction, owner, new Date(), img);
    }

    public ProjectDTO getProject(String id) {
        return projectMapper.getProject(id);
    }

    public List<ProjectDTO> getProjectByUser(String ID){return projectMapper.getProjectByUser(ID);}

    public int uploadProjectImg(String id, byte[] file){return projectMapper.uploadProjectImg(id, file);}

    public Project getCoverImage(String id) {
        return projectMapper.getCoverImage(id);
    }

    public Project getNewOne(){return projectMapper.getNewOne();}
}
