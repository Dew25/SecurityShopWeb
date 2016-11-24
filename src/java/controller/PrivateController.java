/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.ListPersons;
import entity.Customer;
import entity.Product;
import entity.Seller;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CustomerFacade;
import session.ProductFacade;
import session.SellerFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "SecureController", urlPatterns = {"/manager","/addProduct","/deleteProduct","/logout"})
public class PrivateController extends HttpServlet {
    @EJB  ProductFacade productFacade;
    @EJB  CustomerFacade customerFacade;
    @EJB  SellerFacade sellerFacade;
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
        response.setContentType("text/html;charset=UTF-8");
        if(null != request.getServletPath())switch (request.getServletPath()) {
            case "/addProduct":
                
                String seller_id = request.getParameter("seller_id");
                String product_id = request.getParameter("product_id");
                String productName = request.getParameter("name");
                String productPrice = request.getParameter("price");
                            
                Product product = new Product(productName,Integer.parseInt(productPrice));
                try {
                    sellerFacade.addProductToSeller(seller_id, product);
                } catch (Exception e) {
                    
                }
                
                
                List<Seller> newListSellers = sellerFacade.findAll();
                getServletContext().setAttribute("sellers", newListSellers);
                request.getRequestDispatcher("WEB-INF/private/manager.jsp").forward(request, response);
                break;
            case "/logout":
                HttpSession session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                }
                //делаем запрос в базу для index страницы
                List<Seller> sellersForIndex = sellerFacade.findAll();
                List<Customer> customers = customerFacade.findAll();
                ListPersons listPersons = new ListPersons(sellersForIndex,customers);
                getServletContext().setAttribute("listPersons", listPersons);
                //редирект на index.jap
                response.sendRedirect("index.jsp");
                return;
            
            case "/manager":
                List<Seller> sellersForManager = sellerFacade.findAll();
                getServletContext().setAttribute("sellers", sellersForManager);
                request.getRequestDispatcher("WEB-INF/private/manager.jsp").forward(request, response);
                break;
            default:
                break;
        }
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
