<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.pagwebuno.beans.Trabajador" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="ArrayList<Trabajador>" />
<html>
<head>
    <title>Trabajos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/TrabajadorServlet">
            <h1 class="float-start link-dark">Lista de trabajadores en Bicicentro</h1>
        </a>
        <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/TrabajadorServlet?action=new">Crear trabajo</a>
    </div>
    <hr/>
    <!--<form method="post" action="                /JobServlet?action=s">
        <div class="form-floating mt-3">
            <input type="text" class="form-control" id="floatingInput"
                   placeholder="Por ID o por nombre" name="textoBuscar" value="                                 ">
            <label for="floatingInput">Buscar trabajo</label>
        </div>
    </form>-->
    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Correo</th>
            <th>DNI</th>
            <th>Editar</th>
            <th>Borrar</th>
            <th></th>
            <th></th>
        </tr>
        <% for (Trabajador trabajador : lista) { %>
        <tr>
            <td><%=trabajador.getNombres()%>
            </td>
            <td><%=trabajador.getApellidos()%>
            </td>
            <td><%=trabajador.getCorreo()%>
            </td>
            <td><%=trabajador.getDni()%>
            </td>
            <td><a class="btn btn-success" href="<%=request.getContextPath()%>/TrabajadorServlet?action=edit&id=<%= trabajador.getDni() %>">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/TrabajadorServlet?action=del&id=<%= trabajador.getDni() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
