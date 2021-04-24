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
            <c:forEach items="${platos}" var="plato">
                <div class="platoSeleccionado">
                    <h2 class="plateName"> ${plato.getNombre()}</h2>
                    <h2 class="pricePlate"> ${plato.getPrecio()} </h2>
                </div>
            </c:forEach>
        </div>
        <button class="forwardButton">
            <i class="fas fa-step-forward"></i>
        </button>
    </section>


    <section class="secformulario">
        <h1 class="tittleForm">Direccion y tarjeta </h1>
        <form action="pedido" name="form"  method="POST" class="formulario">
            <div class="campos">
                <h1>Direccion</h1>
                <input type="text" name="direccion" />
            </div>
            <div class="campos">
                <h1>Tarjeta</h1>
                <input type="text" name="numeroTarjeta" class="tarjeta" />
            </div>
            <div class="buttomsPedido">
                <span class="dot dot-1"></span>
                <span class="dot dot-2"></span>
                <span class="dot dot-3"></span>
                <button type="submit" class="bttomPedido">
                    Hacer Pedido
                </button>
            </div>
            
            
        </form>
    </section>




    <script language="JavaScript" type="text/javascript" >

        //declare variables for top part
        let counter = 0;
        const numberElementstoRight = 2;
        const forwardButtom = document.querySelector(".forwardButton");
        const backButtom = document.querySelector(".backButton");
        const plates = document.getElementsByClassName("platoSeleccionado");
        const numberPlates= plates.length;
        const dots= document.querySelectorAll(".dot");
        const bttomPedido = document.querySelector(".bttomPedido");


        

        function calcularPrecio()
        {
            let precio =0;
            plates.forEach(plate =>
             {
                precio+=plate.getElementsByClassName("pricePlate")[0].innerHTML; 
            })
            return precio;
        }

        function setHiddenCircles()
        {
            dots.forEach(dot => {dot.classList.add("hidden")});
        }

        function setHiddenPlates(){
            [primerplato, segundoplato,tercerplato, ...rest]= plates;
            rest.forEach( plato => {
                plato.classList.add("hidden");
            })
        }

        
        function setupHidden()
        {
            if ( calcularPrecio < 13 )
            {
                setHiddenCircles();
                bttomPedido.classList.toggle("hidden");

            }
        }

        //event listeners for top part
        if(numberPlates >3){
            setHiddenPlates();
            forwardButtom.addEventListener("click",moveRight);
            backButtom.addEventListener("click",moveLeft); 
        }else {
            forwardButtom.remove();
            backButtom.remove();
        }
        
        setupHidden();
        

        //functions for top screen 

       
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
        // declare variable for botton part

        let timer, maxTime= 1000; 
        const tarjeta= document.querySelector(".tarjeta");
        

        // add event listeners for botton part

        tarjeta.addEventListener("keypress",keyPressed);
        tarjeta.addEventListener("keyup",keyDown);

        //function bottom screen

        function keyPressed(event) {

            //reinicio timer ya que si habia iniciado timer y vuelvo a escribir significa que no 
            // ha terminado de escribir

            window.clearTimeout(timer);

        }

        function keyDown(event){
            window.clearTimeout(timer);
            timer= window.setTimeout( () => {
                dots.forEach(dot => {
                    dot.classList.add("dot-transition")
                })
            }, maxTime);
        }
    
    </script>
</body>
</html>
