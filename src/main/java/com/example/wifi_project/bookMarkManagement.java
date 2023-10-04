package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "bookMarkManagement", value = "/bookMarkManagement")
public class bookMarkManagement extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        request.setCharacterEncoding("UTF-8");
        //북마크 추가할 때
        String bookMarkName = request.getParameter("bookMarkName");
        String bookMarkOrder = request.getParameter("bookMarkOrder");
        wifiDao mdao = new wifiDao();
        if(bookMarkName != null){
            System.out.println(bookMarkName);
            System.out.println(bookMarkOrder);
            bookMarkDto bean = new bookMarkDto();
            bean.setNAME(bookMarkName);
            bean.setNUM(Integer.parseInt(bookMarkOrder));
            mdao.bookMarkInsert(bean);
        }

        //북마크 수정할 때
        String updateBookMarkName = request.getParameter("updateBookMarkName");
        String updateBookMarkNum = request.getParameter("updateBookMarkNum");
        String updateBookMarkId = request.getParameter("updateBookMarkId");
        if(updateBookMarkName != null){
            bookMarkDto bean = new bookMarkDto();
            bean.setID(Integer.parseInt(updateBookMarkId));
            bean.setNAME(updateBookMarkName);
            bean.setNUM(Integer.parseInt(updateBookMarkNum));
            int a = mdao.updateBookMark(bean);
            if(a > 0){
                System.out.println("업데이트 성공!");
            }else{
                System.out.println("업데이트 실패");
            }
        }

        String deleteBookmarkId = request.getParameter("id");
        System.out.println(deleteBookmarkId);
        if(deleteBookmarkId != null){
            bookMarkDto bean = new bookMarkDto();
            bean.setID(Integer.parseInt(deleteBookmarkId));
            mdao.bookmarkDelete(bean);
        }

        ArrayList<bookMarkDto> list = mdao.getBookMarkList();
        request.setAttribute("list",list);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/bookMarkManagement.jsp"); // WebContent 안의 경로
        rd.forward(request, response);
    }
}
