<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>internationalization</title>
</head>
<body>
	<%-- 加载资源文件，根据客户端浏览器的区域和语言来自动选择资源包 --%>
	<fmt:bundle basename="a">
		<fmt:message key="greeting" />
	</fmt:bundle>
</body>
</html>

