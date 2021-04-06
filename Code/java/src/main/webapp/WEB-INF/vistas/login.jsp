<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script>
function validate()
{
     var username = document.form.username.value;
     var password = document.form.password.value;

     if (username==null || username=="")
     {
     alert("Username cannot be blank");
     return false;
     }
     else if(password==null || password=="")
     {
     alert("Password cannot be blank");
     return false;
     }
}
</script>
</head>
<body>
    <div style="text-align:center"><h1>Login  </h1> </div>
    <br>
    <form action="" name="form"  method="post">

        <table align="center">
         <tr>
         <td>Username</td>
         <td><input type="text" name="username" /></td>
         </tr>
         <tr>
         <td>Password</td>
         <td><input type="password" name="password" /></td>
         </tr>
         <tr>
         <td><span style="color:red"></span></td>
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="Login"></input><input
         type="reset" value="Reset"></input></td>
         </tr>
        </table>
    </form>
</body>
</html>
