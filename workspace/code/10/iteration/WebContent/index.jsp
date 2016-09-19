<%@ page import="java.util.*,mytags.*" pageEncoding="GBK" %>
<%@ taglib uri="http://www.abc.com/ns/mytags" prefix="it"%>

<HTML>
<HEAD>
<TITLE>Iteration Tag Example</TITLE>
</HEAD>
<BODY BGCOLOR="#FFFFFF">
	<%-- 为了演示程序，创建了一个集合，添加了3个对象元素。--%>
	<%
		Vector students = new Vector();
		students.add(new Student("001", "zs", 19));
		students.add(new Student("002", "ls", 20));
		students.add(new Student("003", "ww", 19));
	%>

	<table border="1">
		<tr>
			<th align="center">ID</th>
			<th align="center">Name</th>
			<th aligh="center">Age</th>
		</tr>
		<it:iterator var="student" type="mytags.Student"
			list="<%=students%>">
			<tr>
				<%--尽管没有使用jsp:useBean来声明这个Student Bean对象，--%>
				<%--但是由自定义脚本变量形式来创建一个Student Bean对象--%>
				<td><jsp:getProperty name="student" property="id" /></td>
				<td><jsp:getProperty name="student" property="name" /></td>
				<td><jsp:getProperty name="student" property="age" /></td>
			</tr>
		</it:iterator>
	</table>
</BODY>
</HTML>
