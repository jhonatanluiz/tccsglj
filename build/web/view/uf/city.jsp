<%-- 
    Document   : city
    Created on : Aug 19, 2012, 12:58:03 AM
    Author     : jhonatan
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<s:select list="citys" cssClass="required" name="user.address[0].city.id" listKey="id" listValue="city" headerKey="" headerValue="-- Selecione --"/>
