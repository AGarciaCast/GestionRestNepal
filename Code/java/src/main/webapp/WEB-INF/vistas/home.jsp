<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="UTF-8"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
        <link rel="stylesheet" href="css/cssIndex.css" type="text/css">
    </head>
<body>
    <header>
        <nav> 
            <h1> Restaurante</h1>
    
            <ul class="nav-links">
    
                <li>
                    <a href="#Carta"> Carta</a>
                </li>   
                <li>
                    <a href="./menu">Menu</a>
                </li>
            </ul>
        </nav>

    </header>

    <main>
    	
        <section id="Carta" class="Carta">

            <h1 class="cartaTittle"> Carta</h1>

            <form action="" method="POST" class="seleccionarCartaForm">

                <c:forEach items="${Carta}" var="categoria">
                <h2 class="tituloCategoria">  ${categoria.key} </h2>

                <div class="elementosCarta">
                    <c:forEach items="${categoria.value}" var="plato">
                    <div class="elementoCarta">
                        <div class="buttomsElemento">
                            <input type="checkbox" class="selectButtomCarta" name="seleccion" value="${plato.getId_plato()}" />
                            <button type="button" class="unselectButtom">
                                <i class="fas fa-undo"></i>
                            </button>
                        </div>
                        <div class="textElemento">
                            <h2>${plato.getNombre()}</h2>
                            <h3>${plato.getDescripcion()}</h3>
                        </div>
                        <div class="precioELemento">
                            <h2>${plato.getPrecio()}</h2>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                </c:forEach>
                <div class="buttomEnviar">
                    <button type="submit" class="enviarSeleccionesCarta">
                        <i class="fas fa-plus-square"></i>
                    </button>
                </div>
                
            
            </form>

        </section>

        

    </form>

        <c:forEach var="type" items="${mapa}">
            ${type.key} , ${type.value}
        </c:forEach>

    </main>


    <script language="JavaScript" type="text/javascript" >

        const bttomSeleccionarElementoCarta= document.querySelectorAll(".selectButtomCarta");
        const bttomDesseleccionarElementoCarta= document.querySelectorAll(".unselectButtom")
        const formulario = document.querySelector(".seleccionarCartaForm");
        const bttomSubmit = document.querySelector(".enviarSeleccionesCarta");
        checkLogin();
        checkPedido();
        //ADD event listener
        bttomSeleccionarElementoCarta.forEach(function(buttom)
        {
            buttom.addEventListener("click",seleccionado);
        });
        bttomDesseleccionarElementoCarta.forEach(function(buttom)
        {
            buttom.addEventListener("click",seleccionado);
        });

        bttomSubmit.addEventListener("click",submitSeleccionados)

        //functions

        function checkLogin(){
            let login = localStorage.getItem("login");
            if (login != null)
                formulario.action="hacerMenu"
            else
                formulario.action="hacerPedido"
        }
        function checkPedido(){
            let pedido_id= localStorage.getItem("pedido_id");
            if (pedido_id != null)
                formulario.action="modificarPedido/"+pedido_id
        }

        function seleccionado(event){
            recibido=event.target;
            console.log(recibido.parentElement);
            elementoCarta=recibido.parentElement.parentElement;
            console.log(elementoCarta);
            if( recibido.classList[0] != "selectButtomCarta")
            {
                if (elementoCarta.classList.contains("elementoCartaSelecionado"))
                {
                    checkbox=elementoCarta.querySelector(".selectButtomCarta");
                    checkbox.checked= false;
                    elementoCarta.classList.toggle("elementoCartaSelecionado");
                }
            }
            else
            {
                elementoCarta.classList.toggle("elementoCartaSelecionado");
            }
            
        }

        function submitSeleccionados(event)
        {
            event.preventDefault();

            if (localStorage.getItem("pedido_id"))
            {
                localStorage.removeItem("pedido_id");
            }
            formulario.submit();

        }

    </script>


</body>
</html>