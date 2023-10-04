<%--
  Created by IntelliJ IDEA.
  User: 한욱
  Date: 2023-10-03
  Time: 오후 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<h1>북마크 그룹</h1>

<a href="hello-servlet">홈</a> | <a href="/location_history">위치 히스토리 목록</a> | <a href="/getOpenApiWifiInfo">Open API 와이파이 정보 가져오기</a> | <a href="/bookMark">북마크 보기</a> | <a href="/bookMarkManagement">북마크 그룹 관리</a>
</br>

<button type="button" id="addBookMark" name="addBookMark" onclick="location.href='addBookMark'">북마크 그룹 이름 추가</button>

<table id="customers">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="6" ><div align="center">북마크 그룹을 추가해 주세요.</div></td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="bean" items="${list}">
                <tr>
                    <td>${bean.ID}</td>
                    <td>${bean.NAME}</td>
                    <td>${bean.NUM}</td>
                    <td>${bean.reg_date}</td>
                    <td>${bean.upd_date}</td>
                    <td>
                    <form id="bookMarkForm${bean.ID}" name="bookMarkForm${bean.ID}" method="post">
                        <input type="hidden" id="id${bean.ID}" name="id" value="${bean.ID}">
                    </form>
                    <input type="button" value="수정" onclick="updateBookmark(${bean.ID});"> <input type="button" value="삭제" onclick="deleteBookmark(${bean.ID});"></td>

                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</table>
<script>
    function updateBookmark(key){
        console.log('bookMarkForm'+key);
        var sdf = document.getElementById('bookMarkForm'+key);
        sdf.action = "updateBookmark";
        sdf.submit();
    }

    function deleteBookmark(key){
        var sdf = document.getElementById('bookMarkForm'+key);
        sdf.action = "bookMarkManagement";
        sdf.submit();
    }
</script>
</body>
</html>
