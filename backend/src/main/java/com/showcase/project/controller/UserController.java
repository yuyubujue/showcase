package com.showcase.project.controller;
import com.alibaba.fastjson2.JSON;
import com.showcase.project.domain.User;
import com.showcase.project.dto.UserDTO;
import com.showcase.project.service.UserService;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService UserService;
    ServletContext context;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("email") String email){
        if(username.equals("") || password.equals("")||email.equals("")){
            return "Username, password and email can't be empty";
        }
        User checkEmail = UserService.findUserByEmail(email);
        User checkUsername = UserService.findUserByName(username);
        if(checkEmail != null){
            return "Email is existed";
        }
        else if(checkUsername != null){
            return "Name is existed";
        }
        else if(!UserService.checkPassword(password)){
            return "The password should contain at least one number and one letter and between 6 and 16 characters.";
        }
        else {
            byte[] data= new byte[2];
            try{
                ClassPathResource classPathResource = new ClassPathResource("static/null.png");
                InputStream ins = classPathResource.getInputStream();
                byte[] buffer=new byte[2];
                int len=0;
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                while((len=ins.read(buffer))!=-1){
                    bos.write(buffer,0,len);
                }
                bos.flush();
                data = bos.toByteArray();
            }catch (IOException e){
                e.printStackTrace();
            }
            if(UserService.register(UUID.randomUUID().toString(), username, UserService.md5Encode(password), email, "user",data) == 1) {
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
        User user = UserService.login(username, UserService.md5Encode(password));
        if(user == null){
            return "failed";
        }
        else {
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("Auth", uuid);
            cookie.setMaxAge(7*24*60*60);
            cookie.setPath("/");
            if(UserService.updateCookie(user.getUsername(), uuid) == 1) {
                response.addCookie(cookie);
                return user.getAuthority();
            }else{
                return "failed";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getusers")
    @ResponseBody
    public String getUsers(@CookieValue(name = "Auth") String cookie){
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(user.getAuthority().equals("admin")){
                return JSON.toJSONString(UserService.getUsers());
            }else {
                return "unauthorized";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getown")
    @ResponseBody
    public String getOwnUser(@CookieValue(name = "Auth") String cookie){
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(user != null){
                UserDTO userdto = UserService.findOwnUser(cookie);
                return JSON.toJSONString(userdto);
            }else {
                return "Not found";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getuser/{id}")
    @ResponseBody
    public String getUser(@PathVariable String id){
        UserDTO user = UserService.findUser(id);
        if(user != null){
            return JSON.toJSONString(user);
        }else {
            return "Not found";
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/setAuthority")
    @ResponseBody
    public String setAuthority(@CookieValue(name = "Auth") String cookie, @RequestParam("username") String username, @RequestParam("authority") String authority){
        if(!authority.equals("teacher") & !authority.equals("user") & !authority.equals("admin")){
            return "Illegal type";
        }
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(user.getAuthority().equals("admin")){
                if(UserService.setAuthority(username, authority) == 1){
                    return "succeed";
                }else{
                    return "failed";
                }
            }else {
                return "unauthorized";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/setPassword")
    @ResponseBody
    public String setPassword(@CookieValue(name = "Auth") String cookie, @RequestParam("password") String password){
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else if(!UserService.checkPassword(password)){
            return "The password should contain at least one number and one letter and between 6 and 16 characters.";
        }
        else{
            if(UserService.setPassword(cookie, UserService.md5Encode(password)) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/uploadPic")
    @ResponseBody
    public String uploadImage(@RequestParam MultipartFile file, @CookieValue(name = "Auth") String cookie) {
        User user = UserService.authorityAndLoginJudge(cookie);
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
            UserService.setImg(cookie, data);
            return "succeed";
        } catch (IOException e) {
            return "failed";
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getimg/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImg(@PathVariable String id, HttpServletResponse response){
        byte[] imageContent = UserService.getImg(id).getImg();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateIntroduction")
    @ResponseBody
    public String updateIntroduction(@CookieValue(name = "Auth") String cookie, @RequestParam("introduction") String introduction) {
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(UserService.setIntroduction(cookie, introduction) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateSkill")
    @ResponseBody
    public String updateSkill(@CookieValue(name = "Auth") String cookie, @RequestParam("skill") String skill) {
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(UserService.setSkill(cookie, skill) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateInterest")
    @ResponseBody
    public String updateInterest(@CookieValue(name = "Auth") String cookie, @RequestParam("interest") String interest) {
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(UserService.setInterest(cookie, interest) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/removeUser")
    @ResponseBody
    public String removeUser(@CookieValue(name = "Auth") String cookie, @RequestParam("id") String id){
        User user = UserService.authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(user.getAuthority().equals("admin")){
                if(UserService.removeUser(id) == 1){
                    return "succeed";
                }else{
                    return "failed";
                }
            }else {
                return "unauthorized";
            }
        }
    }
}