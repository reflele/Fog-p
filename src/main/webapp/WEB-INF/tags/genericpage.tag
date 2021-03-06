<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <meta name="theme-color" content="#7952b3">
   <style>
       .custom{
           width:300px !important;
       }
       .countwidth{
           width:50px !important
       }
       .hide{
            display: none;
       }

       .mobu {
           color: blue;
           text-decoration: underline;
           padding: 0;
           border: none;
           background: none;
       }

       .stretch{
           width: 100%;
           /*min-height: 100%;*/
       }

       .btn-primary, .btn-primary:hover, .btn-primary:active, .btn-primary:visited {
           background-color: #22528c !important;
       }



   </style>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

 <img src="${pageContext.request.contextPath}/images/test.jpg" class="img-fluid mb-0"/>


<header class="d-flex flex-column flex-md-row align-items-center p-3 pb-0 px-md-4 mb-4 bg-white border-bottom shadow-sm">
    <div class="h5 my-0 me-md-auto fw-normal">
        <p style="font-size: larger">
            <jsp:invoke fragment="header"/>
        </p>
    </div>
    <nav class="my-2 my-md-0 me-md-3">
        <c:if test="${addHomeLink == null }">
            <c:if test="${sessionScope.role == null}">
            <a class="p-2 text-dark" href="<%=request.getContextPath()%>">Forside</a>
        </c:if>
        </c:if>
<%--            <a class="p-2 text-dark" href="<%=request.getContextPath()%>">Home</a>--%>

        <c:if test="${sessionScope.role == 'customer'}">
        <a class="p-2 text-dark" href="customerpage">Forside</a>
        </c:if>
        <c:if test="${sessionScope.role == 'employee'}">
            <a class="p-2 text-dark" href="employeepage">Forside</a>
        </c:if>
<%--        <a class="p-2 text-dark" href="#">Profile</a>--%>
<%--        <a class="p-2 text-dark" href="#">About</a>--%>
    </nav>

    <div>

        <c:if test="${sessionScope.user != null }">
            ${sessionScope.user.email}
        </c:if>

        <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
        <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
        <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

        <c:if test="${isNotLoginPage && isNotRegisterPage}">
            <c:if test="${sessionScope.user != null }">
                <a type="button" class="btn btn-sm  btn-outline-secondary"
                href="${pageContext.request.contextPath}/fc/logoutcommand">Logout</a>
            </c:if>
            <c:if test="${sessionScope.user == null }">
                <a type="button" class="btn btn-sm  btn-outline-secondary"
                   href="${pageContext.request.contextPath}/fc/loginpage">Log på</a>
                <a type="button" class="btn btn-sm  btn-outline-secondary"
                   href="${pageContext.request.contextPath}/fc/registerpage">Opret bruger</a>
            </c:if>
    </div>
    </c:if>
</header>

<div id="body" class="container" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container">
    <br>
<%--    <hr>--%>
    <br><br><br>
<%--    <jsp:invoke fragment="footer"/>--%>

</div>
 <a href="https://www.johannesfog.dk/">
     <img src="${pageContext.request.contextPath}/images/fog_sidefod.png" class="img-fluid mb-0 stretch"/>
 </a>
</body>

</html>
