<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Aluno" %>
<%@ page import="model.Disciplina" %>
<% List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos"); %>
<% List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alunos</title>
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
        <h1>Alunos</h1>
        
        <!-- Tabela de Alunos -->
        <table>
            <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Nome</th>
                </tr>
            </thead>
            <tbody>
                <% for (Aluno aluno : alunos) { %>
                    <tr>
                        <td><%= aluno.getMatricula() %></td>
                        <td><%= aluno.getNome() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        
        <!-- Formulário para adicionar um aluno a uma turma -->
        <h2>Adicionar Aluno a uma Turma</h2>
        <form action="alunos" method="post">
            <input type="hidden" name="action" value="addAluno">
            <label for="matricula">Matrícula:</label>
            <input type="number" id="matricula" name="matricula">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome">
            <label for="disciplina">Disciplina:</label>
    		<select name="disciplina" id="disciplina">
		        <% for (Disciplina disciplina : disciplinas) { %>
		            <option value="<%= disciplina.getCodigo() %>"><%= disciplina.getNome() %></option>
		        <% } %>
    		</select>
            <input type="submit" value="Adicionar Aluno">
        </form>
    </div>
</body>
</html>
