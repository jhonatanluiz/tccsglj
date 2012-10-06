<%-- 
    Document   : login
    Created on : Aug 15, 2012, 10:13:40 PM
    Author     : jhonatan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Bootstrap, from Twitter</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <s:include value="../layout/css.jsp" />
        <s:include value="../layout/javascript.jsp" />  
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
    </head>
    <body>

        <s:include value="../layout/topbar.jsp" />  
        <div class="container">


            <div class="page-header">
                <h1>Redefinir sua senha <small>/Para redefinir sua senha, insira seu email de acesso ao sistema.</small></h1>
            </div>

            <s:if test="%{!message.isEmpty()}">
                <s:property escape="false" value="%{message}"/>
            </s:if>

            <form class="form-horizontal" action="requestPassword" id="form_request_password" method="post">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="focusedInput">Email</label>
                        <div class="controls">
                            <input class="input-xlarge focused required email" name="user.email" type="text">
                        </div>
                    </div>                    
                    <div class="control-group">

                    </div>                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Enviar</button>                               
                    </div>
                </fieldset>
            </form>
            <hr>
            <footer>
                <p>&copy; Company 2012</p>
            </footer>
        </div> 
    </body>    
    <script>
       <s:include value="../../view/authenticate/authenticate.js" />  
    </script>
</html>