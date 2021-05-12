<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
        <link rel="stylesheet" href="css/gestor.css" type="text/css">
        <link rel="stylesheet"
        	href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
    </head>
<body>

    <main>

        <section class="Carta">

            <h1 class="cartaTittle"> Facturación</h1>

            <form method="POST" action="facturacion" >

            		<section id="principal">
            			<section id="publicaciones">
            				<article class="tituloCategoria">
            				    <h2>Seleccione una fecha a consultar:</h2>
                            </article>
                        </section>

            			<section id="publicaciones">
            			    <article class="boton">
            			        <input type="text" class="fecha" name="fecha_inicio" id="inicio" readonly="readonly"  placeholder="yyyy-mm-dd" required>
            				    <input type="submit" class="button" value="Enviar" onClick="return mensaje('factura')">
                            </article>
            			</section>

            		</section>
            	</form>

        </section>
        <h2 id="factura" style="display: none;">La facturacion de este día fue: ${costeFactura}</h2>

    </form>
    </main>


    <script type="text/javascript">
    		$(function() {
    			$("#inicio").datepicker({
    				dateFormat : "yy-mm-dd"
    			}).val()
    		});
    		function mensaje(id) {
    		    var element = document.getElementById(id);
    		    var todo_correcto = true;
                if (document.getElementById('inicio').value == '') {
                    todo_correcto = false;
                }

                if (!todo_correcto) {
                    alert('Debes rellenar los campos');
                }
                else{
                    console.log(element.style.display)
                    if( 'none' == element.style.display ){
                        //element.style.display = 'block';
                    }
                }
                //return false;

    		}
    	</script>


</body>

</html>