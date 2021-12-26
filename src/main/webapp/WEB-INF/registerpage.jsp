<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Register as new User
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div>
            <form class="ff" name="login" action="${pageContext.request.contextPath}/fc/registercommand" method="POST">

                <div class="row">
                    <div class="col-md-4">

                        <div class="container">
                            <div>


                                <div class="form-floating">
                                    <input value="${param.password1}" name="firstname" type="text" class="form-control" id="firstname" placeholder="Fornavn">
                                    <label for="firstname">Fornavn</label>
                                </div>
<br>
                                <div class="form-floating">
                                    <input value="${param.password1}" name="lastname" type="text" class="form-control" id="lastname" placeholder="Efternavn">
                                    <label for="lastname">Efternavn</label>
                                </div>
                                <br>
                                <div class="form-floating">
                                    <input name="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                                    <label for="floatingInput">Email address</label>
                                </div>

                                <br>
                                <div class="form-floating">
                                    <input value="${param.password1}" name="phone" type="text" class="form-control" id="phone" placeholder="Telefonnummer">
                                    <label for="phone">Telefonnummer</label>
                                </div>
                                <br>

                                <button type="submit" class="btn btn-primary btn btn-block">Opret bruger</button>
                            </div>
                        </div>
                    </div>


                    <div class="col-md-4">
                        <div>

                            <div class="form-floating">
                                <input value="${param.password1}" name="address" type="text" class="form-control" id="address" placeholder="Adresse">
                                <label for="address">Adresse</label>
                            </div>
<br>

                            <div class="form-floating">
                                <input value="${param.password1}" name="zip" type="text" class="form-control" id="zip" placeholder="Password">
                                <label for="zip">Postnummer</label>
                            </div>

<br>
                            <div class="form-floating">
                                <input value="${param.password1}" name="password1" type="password" class="form-control" id="password1" placeholder="Password">
                                <label for="password1">Password</label>
                            </div>
                            <br>
                            <div class="form-floating">
                                <input value="${param.password2}" name="password2" type="password" class="form-control" id="password2" placeholder="Password">
                                <label for="password2">Password</label>
                            </div>



                        </div>

                    </div>
                    <div class="col-md-4">
                        <img src="${pageContext.request.contextPath}/images/registerpagepic.jpg"
                             class="img-fluid mb-0"/>

                    </div>

                </div>


            </form>
        </div>

        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>
        </div>
    </jsp:body>
</t:genericpage>


