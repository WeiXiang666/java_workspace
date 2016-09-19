<%-- 编码必须是utf-8，而不能采用特定某个国家和语言的编码 --%>
<%@page import="tool.I18ntool" pageEncoding="utf-8"%>
<html>
<head>
<!-- internationalization这个英语单词一共20个字母，书写麻烦，用第一个字母和
最后一个字母，中间加上18个字母，构成了i18n这个词汇，表示国际化 -->
<title>internationalization</title>
</head>
<body>
	<%-- 从客户端浏览器提交的Header数据中获得国家区域和语言信息 --%>
	<%=I18ntool.getString("greeting", "a",
					request.getHeader("accept-language"))%>
</body>
</html>
