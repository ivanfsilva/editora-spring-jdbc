<%--
  Created by IntelliJ IDEA.
  User: ivanf
  Date: 16/11/2020
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset=UTF-8>
    <title>Cargos</title>
</head>
<body>

<c:import url="menu.jsp" />

<c:url var="save" value="/cargo/save" />
<form:form modelAttribute="cargo" action="${save}" method="post">
    <form:hidden path="idCargo" />
    <fieldset style="width: 500px; margin: 0 auto;">
        <legend>Cargo</legend>
        <div>
            <form:label path="cargo">Cargo</form:label><br>
            <form:input path="cargo" type="text" name="cargo" required="true" />
        </div>
        <br>
        <div>
            <form:label path="departamento">Departamento</form:label><br>
            <form:select path="departamento" name="departamento" required="true" >
                <form:option value="" label="--- Select ---" />
                <form:options items="${departamentos}"
                              itemValue="idDepartamento"
                              itemLabel="departamento" />
            </form:select>
        </div>
        <br>
        <div>
            <input type="submit" value="Salvar">
            <input type="reset" value="Limpar">
        </div>
    </fieldset>
</form:form>

<fieldset style="width: 500px; margin: 0 auto;">
    <legend>Cargos</legend>
    <table style="width: 490px;">
        <tr>
            <th>Código</th>
            <th>Descrição</th>
            <th>Departamento</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="c" items="${ cargos }" varStatus="i">
            <tr bgcolor="${ i.count % 2 != 0 ? '#f1f1f1' : 'white' }">
                <td>${ c.idCargo }</td>
                <td>${ c.cargo }</td>
                <td>${ c.departamento.departamento }</td>
                <td>
                    <c:url var="update" value="/cargo/update/${ c.idCargo }" />
                    <a href="${ update }" title="Ver/Editar">&#9445;</a>
                    <c:url var="delete" value="/cargo/delete/${ c.idCargo }" />
                    <a href="${ delete }" title="Delete">&#9447</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</fieldset>
</body>
</html>
