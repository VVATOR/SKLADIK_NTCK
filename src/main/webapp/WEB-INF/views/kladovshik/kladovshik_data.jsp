<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page session="false" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/style.css"/>

<html>
<head>
  <title>Кладовщик</title>

</head>
<body>
<a href="/weew/">Back to main menu</a>
<h1>Кладовщик</h1>

	<p>${kladovshik }</p>>
	<a href="/weew/sklad/sklad_kladovschik/${kladovshik.id }"> sklad_kladovschik </a>

</body>
</html>