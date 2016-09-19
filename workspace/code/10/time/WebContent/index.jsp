<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.abc.com/ns/mytags" prefix="t"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>show time</title>
</head>
<body>
	<%-- ÔÚJSP³ÌÐòÖÐ£¬Í¨¹ýÊôÐÔÏò±ê¼Ç´¦ÀíÆ÷¶ÔÏó´«µÝÊý¾Ý  --%>
	<t:time color="red" format="yyyy-MM-dd HH:mm:ss" />
	<br>
	<%--Ã»ÓÐ´«µÝcolorÊôÐÔ£¬ÕâÊÇ¿ÉÒÔµÄ£¬ÒòÎªcolorÊôÐÔ²»ÊÇ±ØÐëµÄÊôÐÔ--%>
	<t:time format="yy-MM-dd" />
</body>
</html>
