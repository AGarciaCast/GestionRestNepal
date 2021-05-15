<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <link rel="stylesheet" href="../../css/gestionarPedidos.css">
    <title>Document</title>
</head>
<body>
    

    <h1 id="tittle"> Hola Nombre, estos son tus pedidos realizados</h1>

    <section class="lista-pedidos">
        <c:forEach items="${pedidos}" var="pedido_cliente">
            <div class="pedido">
                <div class="pedido-info">
                    <h2> Pedido 1</h2>
                    <h2 class="fecha_pedido"> ${pedido_cliente.getFecha()} </h2>
                </div>
                <div class="pedido-buttons">
                    <button class="borrar-pedido" type="button" value="${pedido_cliente.getId_pedido()}"> 
                        <i class="fas fa-trash-alt"></i>
                    </button>
                    <button  class="modificar-pedido" type="button" value="${pedido_cliente.getId_pedido()}">
                        <i class="fas fa-edit"></i>
                    </button>
                </div>
            </div>

        </c:forEach>
        
    </section>



    <script language="JavaScript" type="text/javascript" >

    const borrar_pedido= document.querySelectorAll(".borrar-pedido");
    const modificar_pedido= document.querySelectorAll(".modificar-pedido")
    const user= localStorage.getItem("usuario")
    const usuario= JSON.parse(user);

    setName();

    borrar_pedido.forEach(pedido => {
        pedido.addEventListener("click", borrarPedido);
    });

    modificar_pedido.forEach(pedido=>
    {
        pedido.addEventListener("click",modficarPedido)
    })

    function borrarPedido(event)
    {
        console.log(event.target);
        const pedido_id= event.target.value;
        borrarPedidoAPI(pedido_id,"http://localhost:8080/borrarPedido/"+pedido_id)
        
    }


    function borrarPedidoAPI(id,url){

        

        let fetchData = {
            method: 'POST',
            body: null,
            headers: new Headers()
        }
        fetchData.headers.set("Content-Type", "application/json");
        fetchData.headers.set("Content-Encoding", "br");

        fetch(url, fetchData).
        then(data=>console.log(data))
        .catch( ()=> {
        })

        location.href="/gestionarPedidos/"+usuario.id;

        
        
    }

    function modficarPedido(event)
    {
        const pedido= event.target.parentElement.parentElement;
        const time=pedido.querySelector(".fecha_pedido").innerHTML;
        checkTime(time);
        localStorage.setItem("pedido_id",event.target.value);
        location.href='/home'
    }

    function setName()
    {
        
        const name= usuario.name;
        document.getElementById("tittle").innerHTML="Hola "+ name+ ", estos son tus pedidos realizados"
    }

    function checkTime(time)
    {
        const d= new Date();
        console.log(time)
        const fecha=time.slice(0,-7);
        const hora = time.substr(-6);
        console.log(fecha)
        console.log(hora)
        const prueba= Date.parse(fecha+" "+hora);
        console.log(prueba);
    }

    </script>

</body>
</html>