package fr.sewatech.appserv.web;

import fr.sewatech.appserv.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

//@WebServlet(urlPatterns = "/message", name = "MessageServlet", loadOnStartup = 1)
public class MessageServlet extends HttpServlet {
    private MessageService service = new MessageService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Writer out = response.getWriter();

        out.write("<!DOCTYPE html><html><head><title>Message via Servlet</title></head><body>");

        String message = service.getMessage();
        out.write("<p>" + message + "</p>");

        ClassLoader classLoader = this.getClass().getClassLoader();
        out.write("<p>ClassLoader Hierarchy</p><ul>");
        while (classLoader != null) {
            out.write("<li>" + classLoader.getClass() + "</li>");
            classLoader = classLoader.getParent();
        }
        out.write("</ul>");
        out.write("</body></html>");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("=== Init de MessageServlet");
    }
}
