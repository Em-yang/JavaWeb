package com.yang.servlet;

import com.yang.entity.Admin;
import com.yang.entity.Book;
import com.yang.entity.Borrow;
import com.yang.entity.Reader;
import com.yang.service.BookService;
import com.yang.service.LoginService;
import com.yang.service.impl.BookServiceImpl;
import com.yang.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();
    private BookService bookService = new BookServiceImpl();
    /**
     *  处理登录的业务逻辑
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type= request.getParameter("type");
        Object object = loginService.login(username,password,type);
        if (object != null){
            HttpSession session = request.getSession();
            switch (type){
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader",reader);
                    //调转到读者的首页
                    response.sendRedirect("/book?page=1");
                    break;
                case "admin":
                    Admin admin = (Admin)object;
                    session.setAttribute("admin",admin);
                    //跳转到管理员的首页
                    response.sendRedirect("/admin?method=findAllBorrow&page=1");
                    break;
            }
        }else {
            response.sendRedirect("login.jsp");
        }
    }

}
