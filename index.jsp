<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Student</title>
		<style type="text/css">
		</style>
	</head>
	<body>
		<div>
			<form action="/Student/main" method="post" id="infomationForm">
				<input type="hidden" id="id" name="id">
				<input type="hidden" id="active" name="active">
				<table>
					<tr>
						<td colspan="2">
							<table>
								<tr>
									<td><input type="button" value="search" onclick="btn_onclick(this);"></td>
									<td><input type="button" value="add" onclick="btn_onclick(this);"></td>
									<td><input type="button" value="update" onclick="btn_onclick(this);"></td>
									<td><input type="button" value="delete" onclick="btn_onclick(this);"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th>name:</th>
						<td><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<th>weight:</th>
						<td><input type="text" id="weight" name="weight"></td>
					</tr>
					<tr>
						<th>height:</th>
						<td><input type="text" id="height" name="height"></td>
					</tr>
					<tr>
						<th>color:</th>
						<td><input type="text" id="color" name="color"></td>
					</tr>
					<tr>
						<th>gpa:</th>
						<td><input type="text" id="gpa" name="gpa"></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="mainDiv" style="width:800px;height:500px;border: 1px solid;text-align: center;vertical-align: middle;">
		<%
			List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("rs");
			Map<String, Object> map;
			if(list != null && list.size() > 0){
				for(int i=0;i<list.size();i++){
					map = (Map<String, Object>)list.get(i);%>
					<div id="student<%=map.get("id")%>" style="width:<%=map.get("weight")%>px;height:<%=map.get("height") %>px;border:1px solid;float:left;" onclick="div_onclick(this);">
						<label style="color:<%=map.get("color") %>;"><b><%=map.get("name") %></b></label><br>
						<label><b><%=map.get("gpa") %></b></label>
					</div>
					<input type="hidden" id="hid_id<%=map.get("id")%>" name="hid_id<%=map.get("id")%>" value="<%=map.get("id")%>">
					<input type="hidden" id="hid_name<%=map.get("id")%>" name="hid_name<%=map.get("id")%>" value="<%=map.get("name")%>">
					<input type="hidden" id="hid_weight<%=map.get("id")%>" name="hid_weight<%=map.get("id")%>" value="<%=map.get("weight")%>">
					<input type="hidden" id="hid_height<%=map.get("id")%>" name="hid_height<%=map.get("id")%>" value="<%=map.get("height")%>">
					<input type="hidden" id="hid_color<%=map.get("id")%>" name="hid_color<%=map.get("id")%>" value="<%=map.get("color")%>">
					<input type="hidden" id="hid_gpa<%=map.get("id")%>" name="hid_gpa<%=map.get("id")%>" value="<%=map.get("gpa")%>">
		<%	
				}
			}
		%>
		</div>
	</body>
	<script type="text/javascript">
		window.onload = function () {
		};
		
		function btn_onclick(obj){
			document.getElementById("active").value = obj.value;
			document.getElementById("infomationForm").submit();
		}
		
		function div_onclick(obj){
			var no = obj.id.substr(7);
			document.getElementById("id").value = document.getElementById("hid_id"+no).value;
			document.getElementById("name").value = document.getElementById("hid_name"+no).value;
			document.getElementById("weight").value = document.getElementById("hid_weight"+no).value;
			document.getElementById("height").value = document.getElementById("hid_height"+no).value;
			document.getElementById("color").value = document.getElementById("hid_color"+no).value;
			document.getElementById("gpa").value = document.getElementById("hid_gpa"+no).value;
		}
	</script>
</html>