package com.doaip.api;

import com.main.account.Account;
import com.main.service.AccountService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/dang-nhap", "/trang-chu"})
public class HomeControlerServlet extends HttpServlet {

    @Inject
    private AccountService accountService;

    private static final long serialVersionUID = 2686801510274002166L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURL().toString());
        System.out.println(request.getContextPath());
        if (request.getRequestURL().toString().contains("/trang-chu")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            request.setAttribute("account", account);
            RequestDispatcher dispatcher;
            if (accountService.findOne(account)) {
                dispatcher = request.getRequestDispatcher("/template/homepage.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("#");
            }
            dispatcher.forward(request,response);
        } else {
            request.setAttribute("account", accountService.findAll());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/template/Login_v16/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
