<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>User Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
    <style>
    .error 
    {
        color: #ff0000;
        font-weight: bold;
    }
    </style>
  
</head>
<body>
<h1>
	Add User
</h1>

<c:url var="addAction" value="/user/add" ></c:url>

<form:form action="${addAction}" commandName="user"  modelAttribute="user">
<table>

	<c:if test="${!empty user.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message  code="lbl.name" text="name"  />
			</form:label>
		</td>
		<td>
			<form:input path="name" /></td>
			<td><form:errors path="name" cssClass="error" /></td>
		 
	</tr>
	<tr>
		<td>
			<form:label path="address">
				<spring:message code="lbl.address" text="address"/>
			</form:label>
		</td>
		<td>
			<form:input path="address" />
		</td><td><form:errors path="address" cssClass="error" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty user.name}">
				<input type="submit"
					value="<spring:message text="Edit User"/>" />
			</c:if>
			<c:if test="${empty user.name}">
				<input type="submit"
					value="<spring:message text="Add User"/>" onclick="javascript:Add();"/>
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>User List</h3>
<c:if test="${!empty listUsers}">
	<table class="tg">
	<tr>
		<th width="80">User ID</th>
		<th width="120">User Name</th>
		<th width="120">User Address</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listUsers}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.name}</td>
			<td>${user.address}</td>
			<td><a href="<c:url value='/edit/${user.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${user.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
