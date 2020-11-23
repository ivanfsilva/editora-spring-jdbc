<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Funcionários</title>
    <style>
        .master {
            width: 960px; margin: 0 auto;
        }

        .campo {
            margin-bottom: 1em;
        }

        .campo input:focus, .campo select:focus {
            background: #f8f8f8;
        }

        fieldset.grupo .campo {
            float: left;
            margin-right: 2em;
        }
    </style>
</head>
<body>

<c:import url="menu.jsp"/>

<fieldset class='master'>
    <c:url var="save" value="/funcionario/save"/>
    <form:form modelAttribute="funcionario" action="${save}" method="post" >
        <form:hidden path="idFuncionario"/>
        <fieldset class='grupo'>
            <legend> Funcionário </legend>
            <div class='campo'>
                <form:label path="nome">Nome</form:label><br>
                <form:input path="nome" type="text" size='40'/>
            </div>
            <div class='campo'>
                <form:label path="salario">Salario</form:label><br>
                <form:input path="salario" type="text" size='20'/>
            </div>
            <div class='campo'>
                <form:label path="dataEntrada">Data de Entrada</form:label><br>
                <form:input path="dataEntrada" type="date"/>
            </div>
            <div class='campo'>
                <form:label path="dataSaida">Data de Saída</form:label><br>
                <form:input path="dataSaida" type="date"/>
            </div>

            <fieldset class='grupo'>
                <legend> Cargo </legend>
                <div class='campo'>
                    <form:label path="cargo">Cargo</form:label><br>
                    <form:select path="cargo" required="true">
                        <form:option value="" label="--- Select ---"/>
                        <form:options items="${cargos}"
                                      itemValue="idCargo"
                                      itemLabel="cargo"/>
                    </form:select>
                </div>
            </fieldset>
            <br>
            <fieldset class="grupo">
                <form:hidden path="endereco.idEndereco"/>
                <legend> Endereço </legend>
                <div class='campo'>
                    <form:label path="endereco.logradouro">Logradouro</form:label><br>
                    <form:input path="endereco.logradouro" type="text" size='30'/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.numero">Número</form:label><br>
                    <form:input path="endereco.numero" type="text" size='30'/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.complemento">Complemento</form:label><br>
                    <form:input path="endereco.complemento" type="text" size='30'/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.bairro">Bairro</form:label><br>
                    <form:input path="endereco.bairro" type="text" size='30'/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.cidade">Cidade</form:label><br>
                    <form:input path="endereco.cidade" type="text" size='30'/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.estado">Estado</form:label><br>
                    <form:input path="endereco.estado" type="text" size='30'/>
                </div>
                <div class='campo'>
                    <form:label path="endereco.cep">Cep</form:label><br>
                    <form:input path="endereco.cep" type="text" size='30'/>
                </div>
            </fieldset>
            <br>
            <div>
                <input type="submit" value="Salvar">
                <input type="reset" value="Limpa">
            </div>
        </fieldset>
    </form:form>
</fieldset>

<fieldset class='master'>
    <legend>Funcionários</legend>
    <table style="width: 960px;">
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Salário</th>
            <th>Data de Entrada</th>
            <th>Data de Saída</th>
            <th>Cargo</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="f" items="${funcionarios}" varStatus="i">
            <tr bgcolor='${i.count % 2 != 0 ? '#f1f1f1' : 'white'}'>
                <td> ${f.idFuncionario} </td>
                <td> ${f.nome}</td>
                <td> ${f.salario}</td>
                <td> ${f.dataEntrada}</td>
                <td> ${f.dataSaida}</td>
                <td> ${f.cargo.cargo}</td>
                <td>
                    <c:url var="update" value="/funcionario/update/${f.idFuncionario}"></c:url>
                    <a href="${update}" title="Ver/Editar">&#9445;</a>
                    |
                    <c:url var="delete" value="/funcionario/delete/${f.idFuncionario}"></c:url>
                    <a href="${delete}" title="Delete">&#9447;</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</fieldset>
</body>
</html>