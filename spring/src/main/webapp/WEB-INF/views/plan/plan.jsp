<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�����ȹ¥��</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
	 $(function(){
		 $("#ibtn").click(function(){
			 alert("�Լ� �۵�");
			 $(location).attr("href","../plan/planwrite");
			 //../�ּҺ��ʹ� ��Ʈ�ѷ��� ���� �� �����ؾ� �Ѵ�..
		 })
		 
	 })
 </script>
</head>
 
<body>
<h3>���� ���� ������</h3>
<form>
<table border="1">
	<tr>
		<th>������¥</th><th>	�������	</th><th>		�����̸�	</th>
	</tr>
	<tr>
		<c:if test="${planno eq 0}">
			<td>
				������ ������ּ���!
			</td>
		</c:if>
		<td>
			��2
		</td>
	</tr>
	<tr>
		<td>
			��3
		</td>
	</tr>
</table>
</form>
<table border="1" class="right">
	<tbody>
		<tr class="right">
			<td><input type="button" id="ibtn" name="ibtn" value="����¥��"></td>
			<td><input type="button" id="dbtn" name="dbtn" value="���������ϱ�"/></td>
		</tr>
	</tbody>
</table>
</body>
</html>