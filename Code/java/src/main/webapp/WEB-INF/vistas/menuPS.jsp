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
                    <a href="#Menu">Menu</a>
                </li>
            </ul>
        </nav>

    </header>

    <main>

        <section class="Carta">

            <h1 class="cartaTittle"> Menu</h1>

            <form action="menuPostre" class="seleccionarCartaForm">
                <p> Seleccione un segundo plato</p>
                <div class="elementosCarta">
                     <c:forEach items="${segundos}" var="segundos">
                        <div class="elementoCarta">
                            <div class="buttomsElemento">
                            <input type="radio" class="selectButtomCarta" name="seleccion" value="1"/>
                            <button type="button" class="unselectButtom">
                                <i class="fas fa-undo"></i>
                            </button>
                        </div>
                        <div class="textElemento">
                            <h2> ${segundos.nombre_plato}</h2>
                            <h3> ${segundos.descripcion_plato}</h3>
                        </div>
                     </div>
                     </c:forEach>
                </div>

                <button type="submit" class="enviarSeleccionesCarta">
                    <i class="fas fa-plus-square"></i>
                </button>

            </form>

        </section>

        <section class="seleccionadosCarta">
            <!--Esta seccion se mantiene al final de la pñagina y muestra los platos que se han ido seleccionando de forma horizontal y con un boton
                para desseleccionar, si se hace bien, si ya no entran en la pagina , podras ir moviendote horizontamente por ella-->
            <div class="elementosSeleccionadosCarta">
                <div class="elementoSeleccionadoCarta">
                    <button type="button" class="unselectButtom">
                        <i class="fas fa-undo"></i>
                    </button>
                    <h2>Segundo Plato</h2>
                </div>
            </div>
        </section>

    </form>
    </main>


    <script language="JavaScript" type="text/javascript" >

        const bttomSeleccionarElementoCarta= document.querySelectorAll(".selectButtomCarta");
        const bttomDesseleccionarElementoCarta= document.querySelectorAll(".unselectButtom")

        //ADD event listener
        bttomSeleccionarElementoCarta.forEach(function(buttom)
        {
            buttom.addEventListener("click",seleccionMenu);
        });
        bttomDesseleccionarElementoCarta.forEach(function(buttom)
        {
            buttom.addEventListener("click",seleccionado);
        });


        //functions

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
        function seleccionMenu(event){
            console.log(bttomSeleccionarElementoCarta);
            bttomSeleccionarElementoCarta.forEach(function(buttom)
            {
                elementoCarta = buttom.parentElement.parentElement;

                console.log(elementoCarta.classList);
                if(elementoCarta.classList.contains("elementoCartaSelecionado"))
                {
                    elementoCarta.classList.toggle("elementoCartaSelecionado");
                    console.log("estoy aqui");
                }
            });
            recibido=event.target;
            elementoCartaSeleccionado= recibido.parentElement.parentElement;
            elementoCartaSeleccionado.classList.add("elementoCartaSelecionado");
        }
    </script>


</body>
</html>