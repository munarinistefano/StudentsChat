<%-- 
    Document   : pageNotFound
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Errore</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
    </head>
    <body>
        <c:set var="ctx" value="${pageContext.request.contextPath}" />
        <div class="container" style="padding: 50px">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="alert alert-danger">
                    <p align="center"><strong>Pagina non trovata</strong></p>
                </div>
                <a href="${ctx}/Home"><p align="center">Torna alla home</p></a>
            </div>
        </div>
        
    </body>
</html>
