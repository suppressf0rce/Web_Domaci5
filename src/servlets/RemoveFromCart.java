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

@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/remove_from_cart"})
public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConcurrentHashMap<ProductsEntity, Integer> cartItems;

        HttpSession session = req.getSession(true);

        if(session.getAttribute("cartItems") != null){
            cartItems = (ConcurrentHashMap<ProductsEntity,Integer>) session.getAttribute("cartItems");
        }
        else{
           resp.sendRedirect("index.jsp");
           return;
        }


        ProductsEntity product = HibUtils.getProduct(Integer.valueOf(req.getParameter("itemId")));
        int sum = 0;

        for (Map.Entry<ProductsEntity, Integer> entry : cartItems.entrySet()) {
            if(entry.getKey().getId() == product.getId())
                sum = entry.getValue();
        }

        sum -= Integer.valueOf(req.getParameter("itemCount"));
        Session ses = HibUtils.factory.openSession();
        if(sum>0)
            cartItems.put(product, sum);
        else
            cartItems.remove(product);
        ses.close();

        if(cartItems.size()>0)
        session.setAttribute("cartItems", cartItems);
        else
            session.setAttribute("cartItems", null);

        session.setAttribute("message", "Product removed from the cart");
        req.getRequestDispatcher("cart.jsp").forward(req,resp);
        session.setAttribute("message", null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
