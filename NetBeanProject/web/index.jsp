<%-- 
    Document   : index
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <style>
            .body{
                background-color: white;
            }
            .header{
                height: 220px;
                width: 98%;
                background-color: #3276B1;
                border-radius: 8px;
                color: #FFF2C1;
                margin-top: 30px;
                text-align: center;
                padding-top: 30px;
                font-family: "Trebuchet MS", Helvetica, sans-serif;
            }
        </style>
        <title>Login</title>
    </head>
    
    
    
    <body class="body">  
        
        <div class="container">
            <div class="header" style="font-size: 60px;"> STUDENT's CHAT 
                <div style="font-size: 20px;"> Munarini Stefano e Bonadiman Gabriele</div></div>
        
                
        <div class="row">
            <form class="form-horizontal" role="form" name="loginforum" action="Login" method='POST'>
                
                <div class="form-group" style="margin-top:40px">
                  <label for="username" class="col-sm-5  control-label">Username</label>
                  <div class="col-sm-3">
                    <input name="username" type="text" class="form-control" required="required" id="Username" placeholder="Username">
                  </div>
                </div>
                
                <div class="form-group" style="margin-top:20px">
                  <label for="password" class="col-sm-5  control-label">Password</label>
                  <div class="col-sm-3">
                      <input name="password" type="password" class="form-control" required="required" id="password" placeholder="Password">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="col-sm-offset-6 col-sm-2">
                    <button type="submit" class="btn btn-primary">Accedi</button>
                  </div>
                </div>
            </form>
                
            
            
            
            
            <div class="col-sm-offset-2" style="margin-top:40px">
                <button onclick="location.href='registrazione.jsp' " class="btn btn-success">Registrati</button>
            </div>
            <div class="col-sm-offset-2" style="margin-top:10px">
                <button onclick="location.href='recuperaPassword.jsp' " class="btn btn-danger">Recupera Password</button>
            </div>
            <div class="col-sm-offset-2" style="margin-top:10px">
                <button onclick="location.href='GruppiPubblici' " class="btn btn-default">Gruppi Pubblici</button>
            </div>
 
        </div>

        </div>

    </body>
</html>