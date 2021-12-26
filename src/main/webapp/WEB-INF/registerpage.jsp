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
                                <div>
                                    <label class="col-sm-1 col-form-label" for="firstname">Fornavn</label>

                                    <input id="firstname" class="form-control" type="text" name="firstname"
                                           value="${param.email}" placeholder="Indtast fornavn">

                                </div>
                                <div>
                                    <label class="col-sm-1 col-form-label" for="lastname">Efternavn</label>
                                        <%--                    <div class="col-sm-4">--%>
                                    <input id="lastname" class="form-control" type="text" name="lastname"
                                           value="${param.password1}" placeholder="Indtast efternavn">
                                        <%--                    </div>--%>
                                </div>

                                <div>
                                    <label class="col-sm-1 col-form-label" for="email">Email</label>

                                    <input id="email" class="form-control" type="text" name="email"
                                           value="${param.email}" placeholder="Indtast email">

                                </div>

                                <div>
                                    <label class="col-sm-1 col-form-label" for="phone">Telefonnummer</label>
                                        <%--                    <div class="col-sm-4">--%>
                                    <input id="phone" class="form-control" type="phone" name="phone"
                                           value="${param.password2}" placeholder="Indtast dit telefonnummer">
                                        <%--                    </div>--%>
                                    <br>
                                </div>
                                <button type="submit" class="btn btn-primary btn btn-block">Opret bruger</button>
                            </div>
                        </div>
                    </div>


                    <div class="col-md-4">
                        <div>
                            <div>
                                <label class="col-sm-1 col-form-label" for="address">Adresse</label>
                                    <%--                    <div class="col-sm-4">--%>
                                <input id="address" class="form-control" type="address" name="address"
                                       value="${param.password2}" placeholder="Indtast adresse">
                                    <%--                    </div>--%>
                                    <%--                            <input class="btn btn-primary" type="submit" type="submit" value="Submit">--%>
                            </div>

                            <div>
                                <label class="col-sm-1 col-form-label" for="zip">Postnummer</label>
                                    <%--                    <div class="col-sm-4">--%>
                                <input id="zip" class="form-control" type="zip" name="zip"
                                       value="${param.password1}"
                                       placeholder="indtast postnummer">
                                    <%--                    </div>--%>
                            </div>


                            <div>
                                <label class="col-sm-1 col-form-label" for="password1">Kode</label>
                                    <%--                    <div class="col-sm-4">--%>
                                <input id="password1" class="form-control" type="password" name="password1"
                                       value="${param.password1}" placeholder="Indtast kode">
                                    <%--                    </div>--%>
                            </div>
                            <div>
                                <label class="col-sm-1 col-form-label" for="password2">Password</label>
                                    <%--                    <div class="col-sm-4">--%>
                                <input id="password2" class="form-control" type="password" name="password2"
                                       value="${param.password2}" placeholder="Gentag kode">
                                    <%--                    </div>--%>
                                    <%--                                <input class="btn btn-primary" type="submit" type="submit" value="Submit">--%>
                                <br>

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


