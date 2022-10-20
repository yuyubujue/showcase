package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class project_photo implements Serializable {
    @Id
    @Column(name = "PHOTOID")
    private int photo_id;
    @Column(name ="PID")
    private int pid;
    @Column(name = "PHOTO")
    private byte[] photo;
    @Column(name = "UPLOADTIME")
    private long upload_time;

    public project_photo(){}
    public project_photo(int photo_id,int pid,byte[] photo,long upload_time){
        this.photo_id = photo_id;
        this.pid = pid;
        this.photo = photo;
        this.upload_time = upload_time;
    }

    public int getPid() {
        return pid;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public long getUpload_time() {
        return upload_time;
    }
}
