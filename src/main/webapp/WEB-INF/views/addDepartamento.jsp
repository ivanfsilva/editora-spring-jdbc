<%--
  Created by IntelliJ IDEA.
  User: ivanf
  Date: 16/11/2020
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Departamentos</title>
</head>
<body>
    <form:form modelAttribute="" action="" method="post">
        <fieldset style="width: 300px; margin: 0 auto;">
            <legend>Departamento</legend>
            <div>
                <form:label path="departamento">Departamento</form:label><br>
                <form:input path="departamento" type="text" name="departamento" />
            </div>
            <br>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpar">
            </div>
        </fieldset>
    </form:form>

    <fieldset style="width: 300px; margin: 0 auto;">
        <legend>Departamentos</legend>
        <table style="width: 300px;">
            <tr>
                <th>Código</th>
                <th>Descrição</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="d" items="departamentos" varStatus="i">
            <tr>
                <td>Id</td>
                <td>Departamento</td>
                <td>
                    <a href="#" title="Ver/Editar">&#9445;</a>
                    <a href=""# title="Delete">&#9447</a>
                </td>
            </tr>
        </table>
        </c:forEach>
    </fieldset>
</body>
</html>
