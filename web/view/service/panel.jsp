<%-- 
    Document   : panel
    Created on : Sep 20, 2012, 9:51:33 PM
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
                <h2>Painel Serviço<small></small></h2>
            </div>

            <s:if test="%{!message.isEmpty()}">
                <s:property escape="false" value="%{message}"/>
            </s:if>

            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
                <li class=""><a href="#profile" data-toggle="tab">Serviços</a></li>               
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade active in" id="home">
                    <div class="page-header header-title">
                        <h2><small>Dados do Serviço </small></h2>
                    </div>
                    <s:if test="%{user == null || user.id == 0}" >
                        <form class="form-horizontal" action="sign" id="form_sign" method="post">
                        </s:if>
                        <s:else>
                            <form class="form-horizontal" action="edit" id="form_sign" method="post">
                            </s:else>

                            <div class="control-group">
                                <label class="control-label" >Servico *</label>
                                <div class="controls">
                                    <input class="input-xlarge focused required" name="service.name" id="user_name" value="<s:property value="%{service.name.trim()}"/>" maxlength="60" value="" type="text">

                                    <s:hidden value="%{user.id}" name="user.id" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Descrição *</label>
                                <div class="controls">
                                    <textarea maxlength="200" style="width: 417px; height: 117px;" name="service.description" class="required"><s:property value="%{service.description.trim()}"/></textarea>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Valor *</label>
                                <div class="controls">
                                    <input class="span2 focused required" maxlength="14" name="service.price" value="<s:property value="%{service.price}"/>" type="text">
                                </div>
                            </div>
 
                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">Salvar</button>
                                <button class="btn">Cancelar</button>
                            </div>
                        </form> 
                </div>
                <div class="tab-pane fade" id="profile">
                    <div id="grid_view_service">
                    </div>
                </div>              
            </div>   
            <hr>
            <footer>
                <p>&copy; Company 2012</p>
            </footer>
        </div> 
    </body>    
    <script>
        <s:include value="../../view/service/service.js" />  
    </script>
</html>