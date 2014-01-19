<%-- 
    Document   : mdoeratore
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html> 

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <title>Pagina Moderatore</title>
    </head>
    <body>
        <c:set var="ctx" value="${pageContext.request.contextPath}" />
        
        <div class="jumbotron" style="padding: 5px">
            <div class="row">
                <div class="col-lg-1">
                    <c:if test="${utente.avatar != null}">
                        <img src="${ctx}/UploadedAvatar/${sessionScope.utente.avatar}" alt="avatar" class="img-rounded" style="height: 100px; width: 100px"/>
                    </c:if>
                </div>
                <div class="col-lg-7" >            
                    <h4> Ciao ${sessionScope.utente.username}! </h4>
                    <h5><a href="${ctx}/modificaDati.jsp">Modifica profilo</a></h5>
                    <c:if test="${utente.date_time != null}">
                        <h5> Il tuo ultimo accesso e' stato il <fmt:formatDate type="date" dateStyle="long" value="${sessionScope.utente.date_time}"/> alle <fmt:formatDate type="time" timeStyle="short" value="${sessionScope.utente.date_time}"/></h5>
                    </c:if>
                </div>
                <div class="col-lg-1 col-lg-offset-3">
                    <button class="btn btn-danger" onclick="location.href='Logout'">Logout</button>
                </div>
            </div>  
        </div>
        
        <div class="container">
            
            <div class="btn-group btn-group-justified" style="padding: 0">
                <a class="btn btn-default btn-info" role="button" href="${ctx}/Home">Home</a>
                <a class="btn btn-default btn-warning" role="button" href="${ctx}/Gruppi">Miei Gruppi</a>
            </div>
            
            <h1 align="center">Gruppi</h1>
        
            <table id="example" class="table table-striped" style="margin-top: 20px">
                <thead>
                <tr>
                    <th class="site_name"></th>
                    <th>Nome Gruppo</th>
                    <th>Amministratore</th>
                    <th>Data Creazione</th>
                    <th>Numero Post</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>


            <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
            <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
            <script>

                $(document).ready(function() {

                    var json = ${gruppiMod};

                    var arrayStato = new Array();
                    var arrayNome = new Array();
                    var arrayID = new Array();
                    var arrayNomeProprietario = new Array();
                    var arrayData = new Array();
                    var arrayNumeroPost = new Array();

                    for (var i=0;i<Object.keys(json).length;i++){
                        arrayNome.push(json[i].nome);
                        arrayStato.push(json[i].stato);
                        arrayID.push(json[i].ID);
                        arrayNomeProprietario.push(json[i].nome_proprietario);
                        arrayData.push(json[i].data_creazione);
                        arrayNumeroPost.push(json[i].numero_post);
                    }

                    $("#example").dataTable({
                        "aaData":[
                        ],
                        "aoColumnDefs":[{
                              "sTitle":"Stato"
                            , "aTargets": [ "site_name" ]
                        },{
                              "aTargets": [ 1 ]
                            , "bSortable": true
                            , "mRender": function ( url, type, full )  {
                                return  '<a href="${ctx}/Gruppo?id='+url[0]+'">' + url[1] + '</a>';
                            }
                        },{
                              "aTargets":[ 3 ]
                            , "sType": ""
                        }]
                    });  

                    // Contatore
                    var contatore = 0;

                    function fnClickAddRow(stato,nome,id,nome_proprietario,data,numero_post) {
                        var arrayIdNome = new Array();
                        arrayIdNome.push(id);
                        arrayIdNome.push(nome);
                        $('#example').dataTable().fnAddData( [
                            [stato,arrayIdNome,nome_proprietario,data,numero_post]
                        ]);
                        contatore++;
                    }

                    for (var i=0;i<arrayStato.length;i++){
                        if (arrayStato[i] == 0)
                            fnClickAddRow("Aperto",arrayNome[i],arrayID[i],arrayNomeProprietario[i],arrayData[i],arrayNumeroPost[i]);
                        else
                            fnClickAddRow("Chiuso",arrayNome[i],arrayID[i],arrayNomeProprietario[i],arrayData[i],arrayNumeroPost[i]);
                    }
                });

            </script>
        
        </div>
        
    </body>
</html>
