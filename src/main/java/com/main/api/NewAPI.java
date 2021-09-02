package com.main.api;

import com.main.account.Account;
import com.main.service.IAccountService;
import com.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/api-admin"})
public class NewAPI extends HttpServlet {

    @Inject
    private IAccountService accountService;


    //GET-POST-DELETE-PUT

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //Lấy
//        super.doGet(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Thêm
        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Account account = HttpUtil.of(req.getReader()).toModel(Account.class);
        accountService.save(account);
        accountService.findAll();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Xóa
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Sửa
        super.doPut(req, resp);
    }
}
