<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8"> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/pedidos.css" type="text/css">
</head>
<body>


    <section class="seleccionesBarra">

        <button class="backButton">
            <i class="fas fa-step-backward"></i>
        </button>
        <div class="platosSeleccionados">
            <div class="platoSeleccionado">
                <h2 class="plateName"> Plato 1</h2>
                <h2 class="pricePlate"> Precio Plato</h2>
            </div>
            <div class="platoSeleccionado">
                <h2 class="plateName"> Plato 2</h2>
                <h2 class="pricePlate"> Precio Plato</h2>
            </div>
            <div class="platoSeleccionado">
                <h2 class="plateName"> Plato 3</h2>
                <h2 class="pricePlate"> Precio Plato</h2>
            </div>
            <div class="platoSeleccionado hidden">
                <h2 class="plateName"> Plato 4</h2>
                <h2 class="pricePlate"> Precio Plato</h2>
            </div>
            <div class="platoSeleccionado hidden">
                <h2 class="plateName"> Plato 5</h2>
                <h2 class="pricePlate"> Precio Plato</h2>
            </div>
            <div class="platoSeleccionado hidden">
                <h2 class="plateName"> Plato 6</h2>
                <h2 class="pricePlate"> Precio Plato</h2>
            </div>
        </div>
        <button class="forwardButton">
            <i class="fas fa-step-forward"></i>
        </button>
    </section>


    <div style="text-align:center"><h1>Direccion y tarjeta </h1> </div>
    <br>
    <section class="formulario">
    <form action="" name="form"  method="post">

        <table align="center">
         <tr>
         <td>Direccion</td>
         <td><input type="text" name="direccion" /></td>
         </tr>
         <tr>
         <td>Tarjeta</td>
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
    </section>




    <script language="JavaScript" type="text/javascript" >

        let counter = 0;
        const numberElementstoRight = 2;
        const forwardButtom = document.querySelector(".forwardButton");
        const backButtom = document.querySelector(".backButton");
        const plates = document.getElementsByClassName("platoSeleccionado");
        const numberPlates= plates.length;
        

        //event listeners

        forwardButtom.addEventListener("click",moveRight);
        backButtom.addEventListener("click",moveLeft); 


        //functions

        function moveRight(){
            newtPlate=plates[numberPlates-1];
            newtPlate.classList.toggle("hidden");
            oldPlate=document.querySelectorAll(".platoSeleccionado")[numberPlates-1];
            oldPlate.remove();

            rightPlate=plates[numberElementstoRight];
            rightPlate.classList.toggle("hidden");
            document.querySelector(".platosSeleccionados").insertAdjacentElement("afterbegin",newtPlate); 
            
        }

        function moveLeft(event){

            let oldPlate=plates[0];
            let newPlate=document.querySelectorAll(".platoSeleccionado")[0];
            newPlate.classList.toggle("hidden");

            oldPlate.remove();
            document.querySelector(".platosSeleccionados").insertAdjacentElement("beforeend",newPlate);

            rightPlate=plates[numberElementstoRight];
            rightPlate.classList.toggle("hidden");


        }

    
    </script>
</body>
</html>
