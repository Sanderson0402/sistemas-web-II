<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Aluno" %>
<%@ page import="model.Disciplina" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Aula" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<% List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos"); %>
<% List<Aluno> presentes = (List<Aluno>) request.getAttribute("presentes"); %>
<% List<Aluno> faltantes = (List<Aluno>) request.getAttribute("faltantes"); %>
<% List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas"); %>
<% Map<Aula, Map<Boolean, List<Aluno>>> aulas = (Map<Aula, Map<Boolean, List<Aluno>>>) request.getAttribute("aulas"); %>
<%
HttpSession sessionDisc = request.getSession();
Integer disciplinaCodigoAttr = (Integer) sessionDisc.getAttribute("disciplinaCodigo");
if (disciplinaCodigoAttr != null) {
    int disciplinaCodigo = disciplinaCodigoAttr.intValue();
    // rest of your code
}
%>
<html>
<head>
    <title>Aluno</title>
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
        ul {
		    list-style-type: none;
		    padding: 0;
		}

		ul li {
		    padding: 10px;
		    border-bottom: 1px solid #ddd;
		}

		ul li:last-child {
    		border-bottom: none;
		}
 
    </style>
</head>
<body>
    <div id="container">
        <h1>Aula de Hoje, <%= new SimpleDateFormat("dd/MM/yyyy").format(new Date()) %></h1>
        
        <!-- Dropdown para selecionar disciplina -->
        <form action="aulas" method="post">
            <label for="disciplina">Selecione a Disciplina:</label>
			<select name="disciplina" id="disciplina">
	    		<% for (Disciplina disciplina : disciplinas) { %>
	        	<option value="<%= disciplina.getCodigo() %>"><%= disciplina.getNome() %></option>
	    		<% } %>
			</select>
            <input type="hidden" name="action" value="selectDisciplina">
            <input type="submit" value="Selecionar">
        </form>
        
        <!-- Tabela de Alunos -->
        <table>
            <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Nome</th>
                    <th>Faltas</th>
                    <th>Adicionar Falta</th>
                </tr>
            </thead>
            <tbody>
                <% for (Aluno aluno : alunos) { %>
                    <tr>
                        <td><%= aluno.getMatricula() %></td>
                        <td><%= aluno.getNome() %></td>
                        <td><%= aluno.getFaltas() %></td>
                        <td>
                            <form action="aulas" method="post">
                                <input type="hidden" name="action" value="addFalta">
                                <input type="hidden" name="matricula" value="<%= aluno.getMatricula() %>">
                                <input type="submit" value="Dar Falta">
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <form action="aulas" method="post">
		    <input type="hidden" name="action" value="addAula">
		     <input type="hidden" name="disciplinaCodigo" value="<%= disciplinaCodigoAttr %>">
		    <input type="submit" value="Adicionar Aula">
		</form>
		<br>
        <h2>Presença</h2>
        <!-- Tabela de Alunos Presentes -->
        <h3>Presentes</h3>
        <table>
            <!-- ... -->
            <tbody>
                <% for (Aluno aluno : presentes) { %>
                    <tr>
                        <td><%= aluno.getMatricula() %></td>
                        <td><%= aluno.getNome() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Tabela de Alunos Faltantes -->
        <h3>Faltantes</h3>
        <table>
            <!-- ... -->
            <tbody>
                <% for (Aluno aluno : faltantes) { %>
                    <tr>
                        <td><%= aluno.getMatricula() %></td>
                        <td><%= aluno.getNome() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        
		<h2>Aulas Anteriores</h2>
		<% for (Map.Entry<Aula, Map<Boolean, List<Aluno>>> entry : aulas.entrySet()) { %>
		    <h3>Aula <%= entry.getKey().getId() %> - <%= new SimpleDateFormat("dd/MM/yyyy").format(entry.getKey().getData()) %></h3>
		    <h4>Presentes</h4>
		    <ul>
		        <% for (Aluno aluno : entry.getValue().get(true)) { %>
		            <li><%= aluno.getNome() %></li>
		        <% } %>
		    </ul>
		    <h4>Faltantes</h4>
		    <ul>
		        <% for (Aluno aluno : entry.getValue().get(false)) { %>
		            <li><%= aluno.getNome() %></li>
		        <% } %>
		    </ul>
		<% } %>
    </div>
</body>
</html>

