<%--
  Created by IntelliJ IDEA.
  User: DIEGO GONZALES
  Date: 28/10/2023
  Time: 11:03  a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="trabajador" type="com.example.pagwebuno.beans.Trabajador" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Editar un trabajo</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Editar un trabajador</h1>
    <form method="post" action="<%=request.getContextPath()%>/TrabajadorServlet?action=e">
        <div class="mb-3">
            <label>Nombres</label>
            <%-- Esto es para modificar algo pero sin mostrar en la pag web<input type="hidden" class="form-control" name="trabajadorNombre" value="<%=trabajador.getNombres()%>"> --%>
            <input type="text" class="form-control" name="trabajadorNombre" value="<%=trabajador.getNombres()%>">
        </div>
        <div class="mb-3">
            <label>Apellidos</label>
            <input type="text" class="form-control" name="trabajadorApellido" value="<%=trabajador.getApellidos()%>">
        </div>
        <div class="mb-3">
            <label>Correo</label>
            <input type="text" class="form-control" name="trabajadorCorreo" value="<%=trabajador.getCorreo()%>"> <%--En name se va a emviar al servlet ese mismo nombre --%>
        </div>
        <div class="mb-3">

            <input type="hidden" class="form-control" name="trabajadorDni" value="<%=trabajador.getDni()%>"> <%--No se puede editar la primary keys --%>
        </div>
        <div class="mb-3">

            <input type="hidden" class="form-control" name="trabajadorIdsede" value="<%=trabajador.getIdsede()%>"> <%--No se puede editar las fordwars keys --%>
        </div>
        <a href="<%=request.getContextPath()%>/TrabajadorServlet" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
