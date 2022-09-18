package com.eureka.controller;
import com.alibaba.fastjson2.JSON;
import com.eureka.domain.User;
import com.eureka.dto.UserDTO;
import com.eureka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService UserService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("email") String email){
        User checkEmail = UserService.findUserByEmail(email);
        User checkUsername = UserService.findUserByName(username);
        if(checkEmail != null){
            return "Email is existed";
        }
        else if(checkUsername != null){
            return "Name is existed";
        }
        else {
            if(UserService.register(UUID.randomUUID().toString(), username, password, email, "user","null.png") == 1) {
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
        User user = UserService.login(username, password);
        if(user == null){
            return "failed";
        }
        else {
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("Auth", uuid);
            if(UserService.updateCookie(user.getUsername(), uuid) == 1) {
                response.addCookie(cookie);
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @GetMapping("/getusers")
    @ResponseBody
    public String getUsers(@CookieValue(name = "Auth") String cookie){
        User user = authorityAndLoginJudge(cookie);
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

    @PostMapping("/setAuthority")
    @ResponseBody
    public String setAuthority(@CookieValue(name = "Auth") String cookie, @RequestParam("username") String username, @RequestParam("authority") String authority){
        if(!authority.equals("teacher")  & !authority.equals("student") & !authority.equals("user")){
            return "Illegal type";
        }
        User user = authorityAndLoginJudge(cookie);
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

    @PostMapping("/setPassword")
    @ResponseBody
    public String setPassword(@CookieValue(name = "Auth") String cookie, @RequestParam("password") String password){
        User user = authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }else{
            if(UserService.setPassword(cookie, password) == 1){
                return "succeed";
            }else{
                return "failed";
            }
        }
    }

    @PostMapping("/uploadPic")
    @ResponseBody
    public String uploadImage(@RequestParam("image") MultipartFile image, @CookieValue(name = "Auth") String cookie) {
        User user = authorityAndLoginJudge(cookie);
        if(user == null){
            return "unauthorized";
        }
        try {
            String name = UUID.randomUUID().toString();
            InputStream inputStream = image.getInputStream();
            Path directory = Paths.get("File/image");
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            Files.copy(inputStream, directory.resolve(name));
            UserService.setImg(cookie, name);
            return "succeed";

        } catch (Exception e) {
            return "failed";
        }
    }

    @GetMapping("/getimg/{id}")
    @ResponseBody
    public String getImg(@PathVariable String id, HttpServletResponse response){
        String img = UserService.getImg(id);
        if(img != null){
            try {
                response.setContentType("image/jpeg;charset=utf-8");
                response.setHeader("Content-Disposition", "inline; filename=girls.png");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(Files.readAllBytes(Paths.get("File/image").resolve(img)));
                outputStream.flush();
                outputStream.close();
                return null;
            } catch (Exception e) {
                return "Error";
            }
        }
        return "Not found";

    }

    @PostMapping("/updateIntroduction")
    @ResponseBody
    public String updateIntroduction(@CookieValue(name = "Auth") String cookie, String introduction) {
        User user = authorityAndLoginJudge(cookie);
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

    public User authorityAndLoginJudge(String cookie){
        if(cookie.equals(null) || cookie.equals("")){
            return null;
        }
        return UserService.findUserByCookie(cookie);
    }
}
