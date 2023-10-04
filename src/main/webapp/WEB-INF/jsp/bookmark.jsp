<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 한욱
  Date: 2023-10-04
  Time: 오전 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
    <title>Title</title>
</head>
<body>
<h1>북마크 목록</h1>

<a href="hello-servlet">홈</a> | <a href="/location_history">위치 히스토리 목록</a> | <a href="/getOpenApiWifiInfo">Open API 와이파이 정보 가져오기</a> | <a href="/bookMark">북마크 보기</a> | <a href="/bookMarkManagement">북마크 그룹 관리</a>
</br>

<table id="customers">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="5" ><div align="center">추가된 북마크가 존재하지 않습니다.</div></td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="bean" items="${list}">
                <tr>
                    <td>${bean.ID}</td>
                    <td>${bean.NAME}</td>
                    <td>${bean.x_SWIFI_MAIN_NM}</td>
                    <td>${bean.detailreg_date}</td>

                    <td>
                        <form id="deleteForm" name="deleteForm" method="post" action="/deletebookmarkpage">
                            <input type="hidden" id="deleteFormId" name="deleteFormId" value="${bean.ID}">
                            <button type="submit">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</table>

</body>
</html>
