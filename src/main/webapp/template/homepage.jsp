<%--
  Created by IntelliJ IDEA.
  User: vanti
  Date: 8/21/2021
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <%for (int i = 0; i < 10; i++) {%>
    <h1>Vào trang chủ rồi nè mày!</h1>
    <p>username: ${account.username}</p>
    <p>password: ${account.password}</p>
    <%}%>
</body>
</html>
