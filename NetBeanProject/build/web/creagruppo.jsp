<%-- 
    Document   : creagruppo
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <title>Crea gruppo</title>
    </head>
    <body>
        <div class="container" style="margin: 20px">
            
        <div class="col-lg-1">
            <button class="btn btn-default" onclick="location.href='Home'" style="margin-right: 4px"> <span class="glyphicon glyphicon-chevron-left"/>Home</button>
        </div>
          
            <div class="col-lg-6 col-lg-offset-2" style="background-color: #F0F0F0; border-radius: 8px; padding-bottom: 20px">
                
                <h1 align="center" style="padding-top: 0">Crea un nuovo gruppo</h1>
                <form role="form" action="InserisciGruppo" method="POST">
                    <div class="form-group">
                        <label>Nome gruppo:</label>
                        <input name="groupname"  maxlength="30" pattern=".{4,}" title="Minimo 4 caratteri"  required="required" type="text" placeholder="inserisci il nome del gruppo qui" class="form-control">
                    </div>

                    <label>Privacy Gruppo:</label>
                    <div class="radio">
                        <label>
                            <input type="radio" id="optionsRadios1" name="privacy" value="privato" checked>
                            Privato
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" id="optionsRadios2" name="privacy" value="pubblico">
                            Pubblico
                        </label>
                    </div>

                    <label>Invita utenti:</label>
                    <c:forEach var="utente" items="${utenti}">
                        <c:if test="${utente.id != sessionScope.utente.id}">
                            <div class="checkbox">
                                <label><input type="checkbox" name="${utente.id}"> ${utente.username} </label>
                            </div>
                        </c:if>
                    </c:forEach>
                    <center><button type="submit" class="btn btn-success">Crea Gruppo</button></center>
                </form>
            </div>
        </div>
    </body>
</html>