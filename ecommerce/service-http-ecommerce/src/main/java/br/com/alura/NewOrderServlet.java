package br.com.alura;

import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

public class NewOrderServlet extends HttpServlet {
    private final KafkaDispatch<Order> orderDispatch = new KafkaDispatch<>();
    private final KafkaDispatch<Email> emailDispatch = new KafkaDispatch<>();


    @Override
    public void destroy() {
        super.destroy();
        orderDispatch.close();
        emailDispatch.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orderId = UUID.randomUUID().toString();
        var subject = req.getParameter("email");
        var amount = new BigDecimal(req.getParameter("amount"));

        try {
            var order = new Order(orderId, amount, subject);
            orderDispatch.send("ECOMMERCE_NEW_ORDER_DEV", subject, order);

            var email = new Email(subject, "thank you for you order!");
            emailDispatch.send("ECOMMERCE_SEND_EMAIL_DEV", subject, email);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().println("New Order sent");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
