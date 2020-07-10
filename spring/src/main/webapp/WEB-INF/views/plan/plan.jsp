<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행계획짜기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
	 $(function(){
		 $("#ibtn").click(function(){
			 alert("함수 작동");
			 $(location).attr("href","../plan/planwrite");
		 })
		$(".checkBtn").click(function(){  
			var str =""
			var tdArr= new Array(); // 배열 선언
			var checkBtn = $(this);
			//클릭된 행의 모든 값
			var tr = checkBtn.parent().parent();
			var td = tr.children();
			
			var time = td.eq(0).text();
			var place = td.eq(1).text();
			var title = td.eq(2).text();
			var cate = td.eq(3).text();
			// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
			td.each(function(i){	
				tdArr.push(td.eq(i).text());
			});
			str +=	" 시간 : <font color='red'> " + time + "</font> " +
			", 여행장소 : <font color='red'>" + place + "</font> " +
			", 일정이름 : <font color='red'>" + title + "</font> " +
			", 여행유형 : <font color='red'>" + cate  + "</font>" +
			""
			//"<input type='hidden' name='titleR' id='titleR' vaiue='"+title+"'/>"
			;	
			
			$("#Result2").html(str);	
		});
		 $("#mobtn").click(function(){
			 var form = document.planlist;
			 form.submit();
				 // $(location).attr("href","../plan/planmodi");
		 })
})

 </script>
</head>

<body>
<div align="center">
<h3>나의 일정 페이지</h3>
${DTOlist}
플랜번호 <br>

 

http://127.0.0.1:9000/plan/plan

<table border="1" width="100%" class="table table-bordered table-hover text-center" id="example-table-2">
	 <tbody>
		<tr>
			<th>일정날짜</th><th>	일정장소	</th><th>	일정이름	</th><th>여행 유형</th>
		</tr>
		<c:if test="${planNo=null}">
			<tr>
				<td>
					일정을 만들어주세요!
				</td>
			</tr>	
		</c:if>
			<c:forEach var="list" items="${DTOlist}"  varStatus="status">
					<tr>
						<td>
							${list.planDate}
						</td>
						<td>
							${list.planTitle} 
						</td>
						<td>
							${list.planTitle}
						</td>
						<td>
							${list.planCate}
						</td>
						<td><input type="button" class="checkBtn" value="선택" /></td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
<form method="get" id="planlist" name="planlist" action="/planmodi_redirect">
	<div class="Result2" id="Result2" >
	</div>
</form>
	<table class="right" width="700" >
		<tbody>
			<tr class="right" align="center">
				<td><input type="button" id="ibtn" name="ibtn" value="일정짜기"></td>
				<td><input type="button" id="mobtn" name="mobtn" value="일정수정하기"/></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
<!-- ${modelData[0][1][key2]}  ${list[status.index][planNo]}-->