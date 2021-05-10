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
        <div class="pedido">
            <div class="pedido-info">
                <h2> Pedido 1</h2>
                <h2> Fecha </h2>
            </div>
            <div class="pedido-buttons">
                <button class="borrar-pedido" type="button" value="1"> 
                    <i class="fas fa-trash-alt"></i>
                </button>
                <button  class="modificar-pedido" type="button" value="2">
                    <i class="fas fa-edit"></i>
                </button>
            </div>
        </div>
    </section>



    <script language="JavaScript" type="text/javascript" >

    const borrar_pedido= document.querySelectorAll(".borrar-pedido");
    const modificar_pedido= document.querySelectorAll(".modificar-pedido")


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
        event.target.parentElement.parentElement.remove();
        borrarPedidoAPI(pedido_id,"http://localhost:8080/borrarPedido/"+pedido_id)
        
    }


    function borrarPedidoAPI(id,url){

        let data= 
        {
            pedido_id: id
        }

        let fetchData = {
            method: 'POST',
            body: data,
            headers: new Headers()
        }

        fetch(url,fetchData).
        catch( ()=> {
            alert("login fallido");
        })
        
        
    }

    function modficarPedido(event)
    {
        localStorage.setItem("pedido_id",event.target.value);
        location.href='/home'
    }

    </script>

</body>
</html>