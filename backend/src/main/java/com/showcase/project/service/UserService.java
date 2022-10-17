package com.showcase.project.service;

import com.showcase.project.domain.User;
import com.showcase.project.dto.UserDTO;
import com.showcase.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("UserService")
public class UserService{
    @Autowired(required=false)
    private UserMapper userMapper;

    public int register(String userid, String username, String password, String email, String authority, byte[] img) {
        return userMapper.insertUser(userid, username, password, email, authority, img);
    }

    public User login(String username,String password) {
        return userMapper.login(username,password);
    }

    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    public User findUserByName(String username){
        return userMapper.findUserByName(username);
    }

    public int updateCookie(String username, String cookie){
        return userMapper.updateCookie(username, cookie);
    }

    public User findUserByCookie(String cookie){
        return userMapper.findUserByCookie(cookie);
    }

    public List<UserDTO> getUsers(){
        return userMapper.getUsers();
    }

    public int setAuthority(String username, String authority){
        return userMapper.setAuthority(authority, username);
    }

    public int setPassword(String cookie, String password){
        return userMapper.setPassword(cookie, password);
    }

    public UserDTO findUser(String ID){
        return userMapper.findUser(ID);
    }

    public int setImg(String cookie, byte[] img){
        return userMapper.setImg(cookie, img);
    }

    public User getImg(String id){
        return userMapper.getImg(id);
    }

    public int setIntroduction(String cookie, String introduction){return userMapper.setIntroduction(cookie, introduction);}

    public int setSkill(String cookie, String skill){return userMapper.setSkill(cookie, skill);}

    public int setInterest(String cookie, String interest){return userMapper.setInterest(cookie, interest);}

    public int removeUser(String id){
        return userMapper.removeUser(id);
    }

    public User checkTeacher(String authority,String cookie){return userMapper.checkTeacher(authority,cookie);}



    public User authorityAndLoginJudge(String cookie){
        if(cookie.equals("")){
            return null;
        }
        return findUserByCookie(cookie);
    }

    public UserDTO findOwnUser(String cookie){
        if(cookie.equals("")){
            return null;
        }
        return userMapper.findOwnByCookie(cookie);
    }

    public boolean checkPassword(String password){
        char[] passwordChar = password.toCharArray();
        boolean pass = false;
        if (password.length() < 6 || password.length() > 16){
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(passwordChar[i])) {
                pass = true;
            }
        }
        if(!pass){
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(passwordChar[i])) {
                return true;
            }
        }
        return false;
    }

    public String md5Encode(String password){
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] passwordArray = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < passwordArray.length; ++i) {
                sb.append(Integer.toHexString((passwordArray[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return "";
    }

    public static String strSpecialFilter(String str) {
        String regEx = "[\\u00A0\\s\"`~!#$%^&*()+=|{}':;'\\[\\]<>/?~！#￥%……&*（）+|{}【】‘；：”“’。、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
