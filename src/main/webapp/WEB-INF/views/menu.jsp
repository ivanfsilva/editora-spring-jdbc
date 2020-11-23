<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul style="list-style-type: none;">
    <c:url var="depAdd" value="/departamento/add"/>
    <li>
        <a href="${depAdd}" title="Departamentos">
            <img alt="" src="<c:url value="/files/livros.png" />" width="15px" height="15px">
            Departamentos</a>
    </li>

    <c:url var="cargoAdd" value="/cargo/add"/>
    <li>
        <a href="${cargoAdd}" title="Cargos">
            <img alt="" src="<c:url value="/img/livros.png" />" width="15px" height="15px">
            Cargos</a>
    </li>

    <c:url var="funcAdd" value="/funcionario/add"/>
    <li>
        <a href="${funcAdd}" title="Funcionários">
            <img alt="" src="<c:url value="/images/livros.png" />" width="15px" height="15px">
            Funcionários</a>
    </li>
</ul>