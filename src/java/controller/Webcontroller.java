/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import classes.ListPersons;
import entity.Customer;
import entity.OwnCustomer;
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
import session.CustomerFacade;
import session.ProductFacade;
import session.SellerFacade;

/**
 *
 * @author jvm
 */
//Задается имя WebServlet, зпускает при старте приложения и задает список шаблонов обрабатываемых запросов.
@WebServlet(name = "Webcontroller",loadOnStartup = 1, urlPatterns = {"/toIndex","/customer","/seller","/buy","/own_delete"})
public class Webcontroller extends HttpServlet {
    //Аннотация @EJB позволяет объявить EJB компонент в данном сервлете
    @EJB  ProductFacade productFacade;
    @EJB  CustomerFacade customerFacade;
    @EJB  SellerFacade sellerFacade;
   
    //Чтобы запустить сервлет при инициализации приложения, переопределим метод init
    //(правая кнопка мышы -> вставка кода -> переопределение метода -> GenericServlet->init)
    @Override
    public void init() throws ServletException {
       // Метод getServletContext() возращает интерфейс ServletContext. 
       // Этот интерфейс определяет набор методов, которые сервлет использует 
       // для связи с его контейнером сервлетов. Для любого одного 
       // web-приложения существует один и только один ServletContext, 
       // поэтому любую информацию в нем можно считать глобальную и 
       // доступную из любой точки приложения (если приложение НЕ является распределенным).
         
       // метод setAttribute запускает метод EJB компонента, который ищет все продукты в базе 
       // и найденное кладет в переменную product
       List<Seller> sellers = sellerFacade.findAll();
       List<Customer> customers = customerFacade.findAll();
       ListPersons listPersons = new ListPersons(sellers,customers);
       
       getServletContext().setAttribute("listPersons", listPersons);
//       getServletContext().setAttribute("customers", customers);
    }
    
    
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
        // Метод request.getServletPath() возвращает строку запроса, посланного "видом"
        String userPath = request.getServletPath();
        
        
        
        
        
        if(null != userPath) // user нажал ссылку или кнопку, которая послала запрос находящийся в userPath
        switch (userPath) {
            case "/customer":{
                //если userPath содержит "/customer", выполняем следующий код
                if (!request.getParameterNames().hasMoreElements()){
                    getServletContext().setAttribute("customers", customerFacade.findAll());
                    userPath="/WEB-INF/views/customer.jsp";
                }else{
                    Long own_id=Long.parseLong(request.getParameter("own_id"));
                    List<Customer> customers = customerFacade.findAll();
                    for (int i = 0; i < customers.size(); i++) {
                        Customer customer = customers.get(i);
                        for (int j = 0; j < customer.getOwns().size(); j++) {
                            OwnCustomer own = customer.getOwns().get(j);
                            if(own_id.equals(own.getId())){
                                customer.getOwns().remove(own);
                                customerFacade.edit(customer);
                                request.setAttribute("customers", customerFacade.findAll());
                                response.sendRedirect("customer");
                               return;                    
                            }
                        }
                    }
                }
                    break;
                }
            case "/seller":
                List<Seller> sellers = sellerFacade.findAll();
                getServletContext().setAttribute("sellers", sellers);
                userPath="WEB-INF/views/seller.jsp";
                break;
            case "/buy":{
                Long product_id=Long.parseLong(request.getParameter("product_id"));
                Long seller_id= Long.parseLong(request.getParameter("seller_id"));
                Long customer_id= Long.parseLong(request.getParameter("customer_id"));
                
                Product buyProduct = productFacade.find(product_id);
                Seller seller = sellerFacade.find(seller_id);
                Customer customer = customerFacade.find(customer_id);
                for (int i = 0; i < seller.getProducts().size(); i++) {
                    Product sellerProduct = seller.getProducts().get(i);
                    if(sellerProduct.equals(buyProduct) && buyProduct.getPrice() <= customer.getCash()){
                        customer.getOnes().add(new OwnCustomer(buyProduct.getName(),buyProduct.getPrice()));
                        seller.setProfit(seller.getProfit()+buyProduct.getPrice());
                        customer.setCash(customer.getCash()-buyProduct.getPrice());
                        seller.getProducts().remove(buyProduct);
                        sellerFacade.edit(seller);
                        customerFacade.edit(customer);
                        
                    }
                }
                request.setAttribute("customers", customerFacade.findAll());
                response.sendRedirect("seller");
                return;
            }
            case "/own_delete":{
                Long own_id=Long.parseLong(request.getParameter("own_id"));
                List<Customer> customers = customerFacade.findAll();
                for (int i = 0; i < customers.size(); i++) {
                    List<OwnCustomer> ownCustomers = customers.get(i).getOwns();
                    for (int j = 0; j < ownCustomers.size(); j++) {
                        OwnCustomer ownCustomer = ownCustomers.get(j);
                        if(own_id.equals(ownCustomer.getId())){
                            customers.get(i).getOwns().remove(ownCustomer);
                            customerFacade.edit(customers.get(i));
                            request.setAttribute("customers", customerFacade.findAll());
                            request.getRequestDispatcher("WEB-INF/views/customer.jsp").forward(request, response);
                            return;
                        }
                    }
                }       
                break;
            }
            case "/toIndex":{
                sellers = sellerFacade.findAll();
                List<Customer> customers = customerFacade.findAll();
                ListPersons listPersons = new ListPersons(sellers,customers);
                getServletContext().setAttribute("listPersons", listPersons);
                response.sendRedirect("index.jsp");
                return;
            
            }
         
            default:
                break;
        }
            
        
        request.getRequestDispatcher(userPath).forward(request, response);
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
