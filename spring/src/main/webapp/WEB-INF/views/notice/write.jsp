<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<style type="text/css">
	table {
		margin: 0 auto;
	}

	thead {
		font-size: xx-large;
		font-weight: bold;
	}
	
	#nTitle {
		width: 370px;
	}
</style>
</head>
<body>
	<form action="/notice/writeProc" method="post">
		<table class="table table-borderless">
			<thead>
				<tr>
					<th colspan="2">공지사항 작성</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="text-center">공지제목</th>
					<td class="text-center">
						<input type="text" required="required" name="nTitle" id="nTitle">
					</td>
				</tr>
				<tr>
					<th class="text-center">공지내용</th>
					<td class="text-center">
						<textarea required="required" name="nContent" id="nContent" cols="50" rows="10"></textarea>
					</td>
				</tr>
			</tbody>
			<tr>
				<td class="text-center" colspan="2">
					<input type="submit" class="btn btn-primary" value="작성">
					<input type="reset" class="btn btn-secondary" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>