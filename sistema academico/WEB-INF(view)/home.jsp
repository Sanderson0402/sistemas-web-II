<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu - SIGA</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        #menu-container {
            width: 300px;
            margin: 100px auto;
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
        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        li {
            margin-bottom: 10px;
        }
        a {
            text-decoration: none;
            color: #0066cc;
            transition: color 0.3s ease;
        }
        a:hover {
            color: #004080;
        }
    </style>
</head>
<body>
    <div id="menu-container">
        <h1>SIGA</h1>
        <h2>Version 1.0</h2>
        <h3>Menu:</h3>
        <ul>
            <li><a href="<%= request.getContextPath() %>/aulas">Gerenciar Aulas</a></li>
            <li><a href="<%= request.getContextPath() %>/professores">Gerenciar Professores</a></li>
            <li><a href="<%= request.getContextPath() %>/disciplinas">Gerenciar Disciplinas</a></li>
            <li><a href="<%= request.getContextPath() %>/alunos">Gerenciar Alunos</a></li>
        </ul>
    </div>
</body>
</html>

