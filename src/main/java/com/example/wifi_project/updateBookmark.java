package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateBookmark", value = "/updateBookmark")
public class updateBookmark extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String asd = request.getParameter("id");
        System.out.println(asd);
        bookMarkDto bean = new bookMarkDto();
        wifiDao mdao = new wifiDao();
        bookMarkDto data = mdao.selectOneBookmark(Integer.parseInt(asd));
        request.setAttribute("data",data);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/updateBookmark.jsp"); // WebContent 안의 경로
        rd.forward(request, response);
    }
}
