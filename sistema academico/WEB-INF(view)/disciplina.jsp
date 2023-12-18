<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Disciplina" %>
<% List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Disciplinas</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }
    #container {
        width: 80%;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }
    h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #666;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    input[type="submit"] {
        background-color: #0066cc;
        color: #fff;
        border: none;
        padding: 8px 12px;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
        background-color: #004080;
    }
</style>
</head>
<body>
    <div id="container">
        <h1>Disciplinas</h1>
        
        <!-- Tabela de Disciplinas -->
        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                </tr>
            </thead>
            <tbody>
                <% for (Disciplina disciplina : disciplinas) { %>
                    <tr>
                        <td><%= disciplina.getCodigo() %></td>
                        <td><%= disciplina.getNome() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        
        <!-- Formulário para adicionar uma disciplina -->
        <h2>Adicionar Disciplina</h2>
        <form action="disciplinas" method="post">
            <input type="hidden" name="action" value="addDisciplina">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome">
            <input type="submit" value="Adicionar Disciplina">
        </form>
    </div>
</body>
</html>
