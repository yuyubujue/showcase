package com.eureka.ProjectDto;

public class introDto {
    public String PID;
    public String PIMG;
    public String PINTRO;


    public introDto() {
    }

    public introDto(String PID,String PIMG,String PINTRO){
        this.PID = PID;
        this.PIMG = PIMG;
        this.PINTRO = PINTRO;
    }

    public String getPID() {
        return PID;
    }
    public String getPIMG(){
        return PIMG;
    }
    public String getPINTRO(){
        return PINTRO;
    }
}

