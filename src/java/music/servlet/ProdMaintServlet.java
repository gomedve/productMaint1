package music.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

import music.data.*;
import music.business.*;
import music.data.*;

public class ProdMaintServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    // get the products and place into session var
    ServletContext sc = getServletContext();
    // String path = sc.getRealPath("/WEB-INF/products.txt");
    // ProductIO.init(path);
    
    HttpSession session = request.getSession();
    session.setAttribute("products", ProductDB.selectAll());
    
    // decide what to do based on action param
    String action = request.getParameter("action");
    if (action == null)
      action = "displayProducts";
    
    String url = "/prodList.jsp";
    switch (action)
    {
      case "displayProducts":
        url = "/prodList.jsp";
        break;
        
      case "addProduct":
        // set a product into the session if indicated, otherwise clear it
        if (request.getParameter("productCode") != null) {
          session.setAttribute("product", ProductDB.selectByCode(request.getParameter("productCode")));
        } else {
          session.setAttribute("product", null);
        }
        
        url = "/prodAdd.jsp";
        break;
        
      case "addConfirmed":
        Product addProduct = new Product();
        addProduct.setCode(request.getParameter("prodCode"));
        addProduct.setDescription(request.getParameter("prodDescription"));
        addProduct.setPrice(Double.parseDouble(request.getParameter("prodPrice")));

        // decide between update and insert
        if (ProductDB.codeExists(addProduct.getCode()) == false) {
          ProductDB.insert(addProduct);
        } else {
          ProductDB.update(addProduct);
        }

        url = "/prodList.jsp";
        break;
                
      case "deleteProduct":
        // set a product into the session if indicated, otherwise clear it
        if (request.getParameter("productCode") != null) {
          session.setAttribute("product", ProductDB.selectByCode(request.getParameter("productCode")));
        } else {
          session.setAttribute("product", null);
        }
        
        url = "/prodDelete.jsp";
        break;
        
      case "deleteConfirmed":
        Product delProduct = (Product) session.getAttribute("product");
        if (delProduct != null)
        {
          ProductDB.delete(delProduct);
        }
        
        url = "/prodList.jsp";
        break;
    }
    
    // do eet.
    sc.getRequestDispatcher(url).forward(request, response);
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doPost(request, response);
  }   
}