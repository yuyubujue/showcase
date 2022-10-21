
package com.showcase.project;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;



public class ProjectApplicationTests {
    private static final String WEB_SERVICE_URI = "http://localhost:8080";
    private Client client;

    //测试方法启动前触发事件
    @Before
    public void init() {

    }

    //测试方法启动后触发事件
    @After
    public void tearDown() {
        //关闭浏览器端
        client.close();
        client = null;
    }


    //测试/project/like 接口
    @Test
    public void like() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form = new Form();
        form.param("pid", "11");
        Response response = client.target(WEB_SERVICE_URI + "/project/like")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/unlike 接口
    @Test
    public void unlike() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form = new Form();
        form.param("pid", "11");
        Response response = client.target(WEB_SERVICE_URI + "/project/unlike")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectLikeByID 接口
    @Test
    public void getProjectLikeByID() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectLikeByID?pid=" + 1)
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/GetProjectByLike 接口
    @Test
    public void GetProjectByLike() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/GetProjectByLike/1")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/ShowMyLike 接口
    @Test
    public void ShowMyLike() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/ShowMyLike")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getlikestatus 接口
    @Test
    public void getlikestatus() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/getlikestatus?pid=" + 1)
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/removeUser接口(管理员登录)
    @Test
    public void removeUser() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form = new Form();
        form.param("id", "44add6d0-4d07-4ee6-9de7-ca90cbc8c40a");
        Response response = client.target(WEB_SERVICE_URI + "/user/removeUser")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //管理员登录,并且返回cookie
    public static NewCookie loginAdmin(Client client) {
        //设置表单提交
        Form form = new Form();
        //cxk Cxk111    testa www847343
        form.param("username", "admin");
        form.param("password", "test123");
        Response response = client.target(WEB_SERVICE_URI + "/user/login")
                .request().buildPost(Entity.form(form)).invoke();
        //String results = response.readEntity(String.class);
        Map<String, NewCookie> cookies = response.getCookies();
        NewCookie auth = cookies.get("Auth");
        return auth;
    }

    //用户登录,并且返回cookie
    public static NewCookie loginUser(Client client) {
        //设置表单提交
        Form form = new Form();
        //cxk Cxk111    testa www847343
        form.param("username", "testa");
        form.param("password", "www847343");
        Response response = client.target(WEB_SERVICE_URI + "/user/login")
                .request().buildPost(Entity.form(form)).invoke();
        Map<String, NewCookie> cookies = response.getCookies();
        NewCookie auth = cookies.get("Auth");
        return auth;
    }

    //测试/user/uploadPic接口
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Test
//    public void uploadPic() {
//        client = ClientBuilder.newClient();
//        NewCookie cookie = loginUser(client);
//        File imgFile=new File("File\\image\\1.jpg");
//        FileItem fileItem = this.createFileItem(imgFile);
//
//        Form form=new Form();
//        form.param("file","File\\image\\1.jpg");
//        Response response = client.target(WEB_SERVICE_URI + "/user/uploadPic")
//                .request().header("enctype","multipart/form-data").cookie(cookie).buildPost(Entity.form(form)).invoke();
//        String results = response.readEntity(String.class);
//        System.out.println(results);
//    }


    //测试/project/getAllTeam 接口
    @Test
    public void getAllTeam() {
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getAllTeam")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getTeamByUID 接口
    @Test
    public void getTeamByUID() {
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getTeamByUID?uid=" + "b95c138a-75eb-49a2-b1d5-46f207198d3e")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/inviteTeammate 接口
    @Test
    public void inviteTeammate() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/inviteTeammate?pid=" + "12" + "&uname=" + "cxk")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }


    //测试/project/writeComment 接口
    @Test
    public void writeComment() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form = new Form();
        form.param("pid", "11");
        form.param("comment", "testComment");
        Response response = client.target(WEB_SERVICE_URI + "/project/writeComment")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getComment 接口
    @Test
    public void getComment() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/getComment/1?pid=" + 12)
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/removeComment 接口
    @Test
    public void removeComment() {
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form = new Form();
        form.param("pid", "7");
        form.param("cid", "7");
        Response response = client.target(WEB_SERVICE_URI + "/project/removeComment")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }
}


    
   
