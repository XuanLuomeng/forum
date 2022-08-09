package com.example.forum.web.servlet;

import com.example.forum.domain.User;
import com.example.forum.service.UserService;
import com.example.forum.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //为防止乱码设置字符集编码
        req.setCharacterEncoding("utf-8");
        //获取并封装注册日期
        Date regdate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //获取并封装注册用户信息
        User user = new User();
        user.setUserid(req.getParameter("userid"));
        user.setPassword(req.getParameter("password2"));
        user.setEmail(req.getParameter("email"));
        user.setUsername(req.getParameter("username"));
        user.setSex(req.getParameter("sex"));
        user.setTelephone(req.getParameter("telephone"));
        user.setRegdate(simpleDateFormat.format(regdate));
        //调用service进行数据库处理注册
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        //响应是否注册成功
        if(flag){
            //注册成功
        }else {
            //注册失败
        }
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        String json = mapper.writeValueAsString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
