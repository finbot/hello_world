<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>

<body>
<form action="table.do">
<div style="width: 400px; font-family: Arial; font-size: 9pt;">
	<table>
		<tr>
			<td colspan="2" style="padding-bottom: 10px;">
				<h2>Hello World - Data Base Web Application</h2>
				<h3>Site: <b>Table</b></h3>
			</td>
		</tr>		
		<tr>
			<td colspan="2">
			
				<table border="1px;" cellpadding="0px;" cellspacing="0px;" style="width: 300px;">
					<thead>
						<tr>
							<td>Id</td>
							<td>Name</td>
							<td>Update</td>
							<td>Delete</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.simpleEntityList}" var="simpleEntity">
							<tr>					 
								<td>${simpleEntity._id}</td>
								<td>${simpleEntity.name}</td>
								<td> <a href="table.do?submit=Edit&id=${simpleEntity._id}">Edit</a> </td>
								<td> <a href="table.do?submit=Delete&id=${simpleEntity._id}">Delete</a> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			
			</td>
		</tr>		
		<tr>
			<td colspan="2"><input type="submit" id="create" name="submit" value="Create"/></td>
		</tr>		
	</table>
</div>
</form>
</body>

</html>