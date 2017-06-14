<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>pay.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/pay.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("img").click(function() {
		$("#" + $(this).attr("name")).attr("checked", true);
	});
});
</script>
  </head>
  
  <body>
<div class="divContent">
	<span class="spanPrice">支付金额：</span><span class="price_t">&yen;203.5</span>
	<span class="spanOid">编号：E3A1EB6D0543489F9729B2B5BC5DB365</span>
</div>
<form action="<c:url value='/OrderServlet'/>" method="post" id="form1" target="_top">
<input type="hidden" name="method" value=""/>
<input type="hidden" name="oid" value=""/>
<div class="divBank">

	<div style="margin: 40px;">
		<a href="javascript:alert('支付成功！');" class="linkNext">下一步</a>
	</div>
</div>
</form>
  </body>
</html>
