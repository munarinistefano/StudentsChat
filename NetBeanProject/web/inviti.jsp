<%-- 
    Document   : inviti
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inviti</title>
    </head>
    <body>
        <h1>Inviti:</h1>
        <c:if test="${fn:length(inviti) == 0 }">
            <p>Nessuna richiesta d'iscrizione a gruppi</p>
        </c:if>
        <c:forEach var="invito" items="${inviti}">
            <!--<c:if test="${invito.stato == 0 }">-->
                <p>${invito.nome_gruppo}</p>
                <form name="input" action="ModificaStatoInviti" method="POST">
                    <input name="${invito.id_gruppo}" type="submit" value="accept">
                    <input name="${invito.id_gruppo}" type="submit" value="refuse">
                </form>
            <!--</c:if>-->
        </c:forEach>
    </body>
</html>
