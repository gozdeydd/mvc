

package es.mvc.control;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gozde Yazganoglu
 * 
 ControlServlet (Servlet):

Este es un servlet Java que actúa como controlador en la arquitectura MVC.
Recibe y procesa solicitudes HTTP de clientes, como navegadores web, y las
despacha a acciones apropiadas.
    
El servlet típicamente contiene lógica para analizar datos de formulario, 
validar entradas y delegar la lógica de negocio a otros componentes como clases
de servicio o clases controladoras.
En el proyecto "AplicaMVC", es probable que el ControlServlet reciba envíos de 
formularios desde la interfaz web, extraiga datos e invoque métodos en el
ArticulosJpaController para realizar operaciones CRUD en artículos.
 */
public class ControlServlet extends HttpServlet {

    @Inject
    ArticulosBean articulosBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            switch (request.getParameter("opcion")) {
                case "Alta": {
                    articulosBean.añadirArticulos(obtenerArticulo(request));
                    mostrarResultado(request, response, "ok");
                    break;
                }
                case "Baja": {
                    articulosBean.borrarArticulos(Integer.parseInt(request.getParameter("identi")));
                    mostrarResultado(request, response, "ok");
                    break;
                }
                case "Edicion": {
                    articulosBean.editarArticulos(obtenerArticulo(request));
                    mostrarResultado(request, response, "ok");
                    break;
                }
                case "Lista": {
                    request.setAttribute("listaArticulos", articulosBean.listarArticulos());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("listados.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
            }
        } catch (Exception e) {
            mostrarResultado(request, response, "error");
        }

    }

    private void mostrarResultado(HttpServletRequest request, HttpServletResponse response, String resultado)
            throws ServletException, IOException {
        request.setAttribute("resultado", resultado);
        RequestDispatcher dispatcher = request.getRequestDispatcher("resultado.jsp");
        dispatcher.forward(request, response);
    }

    private Articulos obtenerArticulo(HttpServletRequest request) {
        return new Articulos(
                Integer.parseInt(request.getParameter("identi")),
                request.getParameter("catego"),
                request.getParameter("descri"),
                Double.parseDouble(request.getParameter("precio"))
        );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
