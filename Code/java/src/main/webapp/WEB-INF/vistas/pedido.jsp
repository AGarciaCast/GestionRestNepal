<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
    <div style="text-align:center"><h1>Direccion y contraseña </h1> </div>
    <br>
    <form action="" name="form"  method="post">

        <table align="center">
         <tr>
         <td>Direccion</td>
         <td><input type="text" name="direccion" /></td>
         </tr>
         <tr>
         <td>Contraseña</td>
         <td><input type="password" name="contraseña" /></td>
         </tr>
         <tr>
         <td><span style="color:red"></span></td>
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="Validar"></input></td>
         </tr>
        </table>
    </form>
</body>
</html>
