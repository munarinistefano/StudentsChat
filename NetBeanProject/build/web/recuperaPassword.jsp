<%-- 
    Document   : recuperoPassword
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <title>Recupera Password</title>
    </head>
    <body>
        
        <div class="container" style="margin: 20px">
            <div class="col-lg-1">
                <button class="btn btn-default" onclick="location.href='Home'" style="margin-right: 4px"> <span class="glyphicon glyphicon-chevron-left"/>  Login</button>
            </div>
            <div class="col-lg-6 col-lg-offset-3" style="background-color: #F0F0F0; border-radius: 8px; padding-bottom: 20px">
                <h3 align="center"> Recupera la tua password</h3>
                <div class="col-lg-6 col-lg-offset-3" >
                        <form  method="POST" action="RecuperaPassword">
                            <div class="form-group">
                              <label>Email</label>
                              <input type="email" name="email" maxlength="50">
                            </div>
                            <center><button type="submit" value="Recupera" class="btn btn-success">Conferma</button></center>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
