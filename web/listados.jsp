<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Productos</title>
        <style>
            
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: pink;
            }
        </style>
    </head>
    <body>
        <h1>Lista de Articulos</h1>
        <c:if test="${not empty listaArticulos}">
            <table>
                <thead>
                    <tr>
                        <th>ID Producto</th>
                        <th>Categoría</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="producto" items="${listaArticulos}">
                        <tr>
                            <td>${producto.idprod}</td>
                            <td>${producto.categoria}</td>
                            <td>${producto.descripcion}</td>
                            <td>${producto.precio}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <br>
        <a href="javascript:history.back()" class="button">Volver atrás</a>
    </body>
</html>
