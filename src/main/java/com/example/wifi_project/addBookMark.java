package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addBookMark", value = "/addBookMark")
public class addBookMark extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {

        RequestDispatcher rr = request.getRequestDispatcher("/WEB-INF/jsp/addBookMark.jsp"); // WebContent 안의 경로
        rr.forward(request, response);

    }
}
