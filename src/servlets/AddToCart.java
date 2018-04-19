package servlets;

import model.HibUtils;
import model.ProductsEntity;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add_to_cart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConcurrentHashMap<ProductsEntity, Integer> cartItems;

        HttpSession session = req.getSession(true);

        if(session.getAttribute("cartItems") != null){
            cartItems = (ConcurrentHashMap<ProductsEntity,Integer>) session.getAttribute("cartItems");
        }
        else{
            cartItems = new ConcurrentHashMap<ProductsEntity, Integer>();
        }


        ProductsEntity product = HibUtils.getProduct(Integer.valueOf(req.getParameter("itemId")));
        int sum = 0;

        for (Map.Entry<ProductsEntity, Integer> entry : cartItems.entrySet()) {
            if(entry.getKey().getId() == product.getId())
                sum = entry.getValue();
        }

        sum += Integer.valueOf(req.getParameter("itemCount"));
        Session ses = HibUtils.factory.openSession();
        cartItems.put(product, sum);
        ses.close();

        session.setAttribute("cartItems", cartItems);

        session.setAttribute("message", "Product added to cart");
        req.getRequestDispatcher("index.jsp").forward(req,resp);
        session.setAttribute("message", null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
