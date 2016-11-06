/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CustomerFacade;
import session.ProductFacade;

/**
 *
 * @author jvm
 */
//Задается имя WebServlet, зпускает при старте приложения и задает список шаблонов обрабатываемых запросов.
@WebServlet(name = "Webcontroller",loadOnStartup = 1, urlPatterns = {"/customer","/index"})
public class Webcontroller extends HttpServlet {
    //Аннотация @EJB позволяет объявить EJB компонент в данном сервлете
    @EJB  ProductFacade productFacade;
    @EJB  CustomerFacade customerFacade;
    //Чтобы запустить сервлет при создании сервлета, переопределим метод init
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
       
       getServletContext().setAttribute("products", productFacade.findAll());
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
        // user нажал ссылку или кнопку, которая послала запрос находящийся в userPath
        if("/customer".equals(userPath)){ //если userPath содержит "/customer", выполняем следующий код
            // Создаем переменную title и помещаем ее в response, т.е. отправляем на страницу, 
            // указанную в userPath
//            String title = "Покупка";                      
//            getServletContext().setInitParameter("title", title);
            
        }
        request.getRequestDispatcher(userPath + ".jsp").forward(request, response);
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
