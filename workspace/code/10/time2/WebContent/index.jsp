<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.abc.com/ns/mytags" prefix="t"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>show time</title>
</head>
<body>
	<p>
		<%-- ´Ë´¦Ìá¹©ÁË½Å±¾±äÁ¿µÄÃû³ÆºÍÀàÐÍ  --%>
		<t:time color="red" format="yyyy-MM-dd HH:mm:ss" var="d"
			type="java.util.Date" />
	<p>
		<%-- ·ÃÎÊ¸Ã½Å±¾±äÁ¿£¬Êä³öÆäÖµ --%>
		<%= d %>  
		
		<%-- Õâ¸ö½Å±¾±äÁ¿»á³ö´íÎóÌáÊ¾£¬¿ÉÒÔÔÚPreferences->Validation->JSP Syntax ValidationÖÐ½«ÑéÖ¤¹´Ñ¡µô£¬²»½øÐÐÑéÖ¤Ôò¿ÉÒÔºöÂÔµôÕâ¸ö´íÎó¼ì²é --%>
</body>
</html>
</html>