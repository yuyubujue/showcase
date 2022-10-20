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

public class test {
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

    //测试/user/register接口
    @Test
    public void register(){
        client = ClientBuilder.newClient();
        Form form=new Form();
        form.param("username","ssssssqweqw");
        form.param("password","1234567q");
        form.param("email","73445swq566@qq.com");
        Response response = client.target(WEB_SERVICE_URI + "/user/register")
                .request().buildPost(Entity.form(form)).invoke();
        String results=response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/getusers接口(管理员登录)
    @Test
    public void getusers(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Response response2 = client.target(WEB_SERVICE_URI + "/user/getusers")
                .request().cookie(cookie).get();
        String results = response2.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/getown接口
    @Test
    public void getown(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Response response2 = client.target(WEB_SERVICE_URI + "/user/getown")
                .request().cookie(cookie).get();
        String results = response2.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/getuser/{id}接口
    @Test
    public void getuserById(){
        client = ClientBuilder.newClient();
        String results = client.target(WEB_SERVICE_URI + "/user/getuser/44add6d0-4d07-4ee6-9de7-ca90cbc8c40a")
                .request().get().readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/setAuthority接口
    @Test
    public void setAuthority(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("username","qbao775");
        form.param("authority","teacher");
        Response response = client.target(WEB_SERVICE_URI + "/user/setAuthority")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/setPassword接口
    @Test
    public void setPassword(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("password","Cxk111");
        Response response = client.target(WEB_SERVICE_URI + "/user/setPassword")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
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

    //测试/user/getimg/{id}接口
    @Test
    public void getimg(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/user/getimg/63c88ea1-f131-4168-a300-09cbf0726f92")
                .request().get();
        String results=response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/updateIntroduction接口
    @Test
    public void updateIntroduction(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("introduction","<p>Hello all producers!</p><p>I am Cai Xukun, a personal trainee who has been practicing for two and a half years.</p><p>Like singing, dancing, rap, basketball</p><p>music</p><p>Chicken you are so beautiful! Baby!~ chicken you are so beautiful! baby</p><p>Chicken you are so beautiful! Baby!~ chicken you are so beautiful! baby</p><p>You're making me so excited</p><p>I've never had this feeling</p><p>Cause I got a crush on you who you</p><p>in future shows</p><p>I also prepared a lot of myself</p><p>Original work of lyrics, composition, and choreography</p><p>If you look forward to it, please vote for me moressss</p>");
        Response response = client.target(WEB_SERVICE_URI + "/user/updateIntroduction")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/updateSkill接口
    @Test
    public void updateSkill(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("skill","sing, dance, rap, basketball");
        Response response = client.target(WEB_SERVICE_URI + "/user/updateSkill")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/updateInterest接口
    @Test
    public void updateInterest(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("interest","Mysql,Python");
        Response response = client.target(WEB_SERVICE_URI + "/user/updateInterest")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/user/removeUser接口(管理员登录)
    @Test
    public void removeUser(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("id","44add6d0-4d07-4ee6-9de7-ca90cbc8c40a");
        Response response = client.target(WEB_SERVICE_URI + "/user/removeUser")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }


    //测试/project/updateProjectIntro接口
    @Test
    public void updateProjectIntro(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("NewPIntro","sssssss");
        form.param("Pid","1");
        Response response = client.target(WEB_SERVICE_URI + "/project/updateProjectIntro")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/updateProjectName接口
    @Test
    public void updateProjectName(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("NewPname","hhhh");
        form.param("Pid","1");
        Response response = client.target(WEB_SERVICE_URI + "/project/updateProjectName")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/updateProjectTagLine接口
    @Test
    public void updateProjectTagLine(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("NewTagLine","xxxxxxx");
        form.param("Pid","1");
        Response response = client.target(WEB_SERVICE_URI + "/project/updateProjectTagLine")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getAllprojects接口
    @Test
    public void getAllprojects(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getAllprojects")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectsByUpdateTime/{page}接口
    @Test
    public void getProjectsByUpdateTime(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectsByUpdateTime/1")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectsByUploadTime/{page}接口
    @Test
    public void getProjectsByUploadTime(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectsByUploadTime/1")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectsByUploadTimeAsc/{page}接口
    @Test
    public void getProjectsByUploadTimeAsc(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectsByUploadTimeAsc/1")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectsByUpdateTimeAsc/{page}接口
    @Test
    public void getProjectsByUpdateTimeAsc(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectsByUpdateTimeAsc/1")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectsByUser接口
    @Test
    public void getProjectsByUser(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectsByUser?id="+"b95c138a-75eb-49a2-b1d5-46f207198d3e")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectPageByPid接口
    @Test
    public void getProjectPageByPid(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectPageByPid?pid="+"4")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/removeProject接口
    @Test
    public void removeProject(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("pid","1");
        Response response = client.target(WEB_SERVICE_URI + "/project/removeProject")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/GenerateTeam 接口
    @Test
    public void GenerateTeam(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("pid","2");
        form.param("tname","test");
        Response response = client.target(WEB_SERVICE_URI + "/project/GenerateTeam")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getAllTeammateByTid 接口
    @Test
    public void getAllTeammateByTid(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getAllTeammateByTid?tid="+"iu0tdFkDHY")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getTeamOwnerBytid 接口
    @Test
    public void getTeamOwnerBytid(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getTeamOwnerBytid?tid="+"iu0tdFkDHY")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getAllTeam 接口
    @Test
    public void getAllTeam(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getAllTeam")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getTeamByUID 接口
    @Test
    public void getTeamByUID(){
        client = ClientBuilder.newClient();
        Response response = client.target(WEB_SERVICE_URI + "/project/getTeamByUID?uid="+"b95c138a-75eb-49a2-b1d5-46f207198d3e")
                .request().get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/inviteTeammate 接口
    @Test
    public void inviteTeammate(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/inviteTeammate?pid="+"12"+"&uname="+"cxk")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/acceptInvitation 接口
    @Test
    public void acceptInvitation(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/acceptInvitation?vercode="+"1")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/deleteTeam 接口
    @Test
    public void deleteTeam(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("tid","LP4vMW6Ac1");
        Response response = client.target(WEB_SERVICE_URI + "/project/deleteTeam")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/addAward 接口
    @Test
    public void addAward(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("pid","11");
        form.param("comment","ssssaqq");
        Response response = client.target(WEB_SERVICE_URI + "/project/addAward")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/DeleteAward 接口
    @Test
    public void DeleteAward(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("pid","11");
        Response response = client.target(WEB_SERVICE_URI + "/project/DeleteAward")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/GetAwardCommentByID 接口
    @Test
    public void GetAwardCommentByID(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/GetAwardCommentByID?pid="+"11")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/ShowAwardedProject 接口
    @Test
    public void ShowAwardedProject(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/ShowAwardedProject/1")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/GetAwardAmountById 接口
    @Test
    public void GetAwardAmountById(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/GetAwardAmountById?pid="+"11")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/writeComment 接口
    @Test
    public void writeComment(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Form form=new Form();
        form.param("pid","11");
        form.param("comment","testComment");
        Response response = client.target(WEB_SERVICE_URI + "/project/writeComment")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getComment 接口
    @Test
    public void getComment(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/getComment/1?pid="+12)
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/removeComment 接口
    @Test
    public void removeComment(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("pid","7");
        form.param("cid","7");
        Response response = client.target(WEB_SERVICE_URI + "/project/removeComment")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/like 接口
    @Test
    public void like(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("pid","11");
        Response response = client.target(WEB_SERVICE_URI + "/project/like")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/unlike 接口
    @Test
    public void unlike(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("pid","11");
        Response response = client.target(WEB_SERVICE_URI + "/project/unlike")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getProjectLikeByID 接口
    @Test
    public void getProjectLikeByID(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/getProjectLikeByID?pid="+1)
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/GetProjectByLike 接口
    @Test
    public void GetProjectByLike(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/GetProjectByLike/1")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/ShowMyLike 接口
    @Test
    public void ShowMyLike(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/ShowMyLike")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/getlikestatus 接口
    @Test
    public void getlikestatus(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/getlikestatus?pid="+1)
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/GetProjectBySkills 接口
    @Test
    public void GetProjectBySkills(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/GetProjectBySkills/1?skills="+"python,java")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/GetProjectSkills 接口
    @Test
    public void GetProjectSkills(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginUser(client);
        Response response = client.target(WEB_SERVICE_URI + "/project/GetProjectSkills/?pid="+"12")
                .request().cookie(cookie).get();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/UploadNewSkill 接口
    @Test
    public void UploadNewSkill(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("pid","11");
        form.param("skill","english");
        Response response = client.target(WEB_SERVICE_URI + "/project/UploadNewSkill")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //测试/project/RemoveSkill 接口
    @Test
    public void RemoveSkill(){
        client = ClientBuilder.newClient();
        NewCookie cookie = loginAdmin(client);
        Form form=new Form();
        form.param("pid","11");
        form.param("skills","english");
        Response response = client.target(WEB_SERVICE_URI + "/project/RemoveSkill")
                .request().cookie(cookie).buildPost(Entity.form(form)).invoke();
        String results = response.readEntity(String.class);
        System.out.println(results);
    }

    //管理员登录,并且返回cookie
    public static NewCookie loginAdmin(Client client){
        //设置表单提交
        Form form=new Form();
        //cxk Cxk111    testa www847343
        form.param("username","cxk");
        form.param("password","Cxk111");
        Response response = client.target(WEB_SERVICE_URI + "/user/login")
                .request().buildPost(Entity.form(form)).invoke();
        //String results = response.readEntity(String.class);
        Map<String, NewCookie> cookies = response.getCookies();
        NewCookie auth = cookies.get("Auth");
        return auth;
    }

    //用户登录,并且返回cookie
    public static NewCookie loginUser(Client client){
        //设置表单提交
        Form form=new Form();
        //cxk Cxk111    testa www847343
        form.param("username","testa");
        form.param("password","www847343");
        Response response = client.target(WEB_SERVICE_URI + "/user/login")
                .request().buildPost(Entity.form(form)).invoke();
        Map<String, NewCookie> cookies = response.getCookies();
        NewCookie auth = cookies.get("Auth");
        return auth;
    }


    //转换File
    public FileItem createFileItem(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem("textField", "multipart/form-data", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

}
