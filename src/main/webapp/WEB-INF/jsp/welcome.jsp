<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="en">

<jsp:include page="fragments/staticFiles.jsp"/>

<body>

<div class="container">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <h2><fmt:message key="welcome"/></h2>
    <h2>Capgemini DevOps CDIF CoE</h2>
    <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
    <img src="${petsImage}"/>
	<p id="demo"></p>
	<script>
	document.getElementById("demo").innerHTML = Date(); 
	</script>
    <jsp:include page="fragments/footer.jsp"/>

</div>
</body>

</html>
