package com.yang.servlet;

import com.yang.entity.Book;
import com.yang.entity.Borrow;
import com.yang.entity.Reader;
import com.yang.service.BookService;
import com.yang.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 加载图书信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null){
            method = "findAll";
        }
        HttpSession session = request.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        //流程控制
        switch (method){
            case "findAll":
                String pageStr = request.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> list = bookService.findAll(page);
                request.setAttribute("dataPrePage",6);
                request.setAttribute("currentPage",page);
                request.setAttribute("pages",bookService.getPages());
                request.setAttribute("list",list);
                request.getRequestDispatcher("index.jsp").forward(request,response);
                break;
            case "addBorrow":
                String bookidStr = request.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                //添加借书请求
                bookService.addBorrow(bookid,reader.getId());
                response.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
            case "findAllBorrow":
                //展示当前用户的所有借书记录
                pageStr = request.getParameter("page");
                page = Integer.parseInt(pageStr);
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(),page);
                request.setAttribute("list",borrowList);
                request.setAttribute("dataPrePage",6);
                request.setAttribute("currentPage",page);
                request.setAttribute("pages",bookService.getBorrowPages(reader.getId()));
                request.getRequestDispatcher("borrow.jsp").forward(request,response);
                break;
        }
    }
}
