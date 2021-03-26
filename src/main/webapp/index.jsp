<%--
  Created by IntelliJ IDEA.
  User: 77524
  Date: 2021/3/26
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${userList != null}">
    <h2>用户列表</h2>
    <c:forEach items="${userList}" var="user">
        ${user.name} ${user.gender} ${user.birthday}
    </c:forEach>
</c:if>
<c:if test="${userList == null}">
    <h2>未查询到指定用户</h2>
</c:if>
</body>
</html>
