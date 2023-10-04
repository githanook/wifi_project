<%--
  Created by IntelliJ IDEA.
  User: 한욱
  Date: 2023-09-24
  Time: 오후 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<div style="text-align: center; font-size: x-large; font-weight: bolder"><%request.getParameter("totalCount");%>개의 WIFI 정보를 정상적으로 저장하였습니다.</div>--%>
<div style="text-align: center; font-size: x-large; font-weight: bolder">${totalCount}개의 WIFI 정보를 정상적으로 저장하였습니다.</div>
</br>
<a href="/hello-servlet"><div style="text-align: center">홈으로 가기</div></a>

</body>
</html>
