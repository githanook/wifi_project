package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "deletebookmarkpage", value = "/deletebookmarkpage")
public class deletebookmarkpage extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {

        String key = request.getParameter("deleteFormId");
        System.out.println(key);

        wifiDao mdao = new wifiDao();
        bookmarkjoinDto data = mdao.deletebookmarkdetail(Integer.parseInt(key));
        request.setAttribute("data",data);

        RequestDispatcher rr = request.getRequestDispatcher("/WEB-INF/jsp/deletebookmarkpage.jsp"); // WebContent 안의 경로
        rr.forward(request, response);
    }
}
