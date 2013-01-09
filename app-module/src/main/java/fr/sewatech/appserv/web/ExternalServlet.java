package fr.sewatech.appserv.web;

import fr.sewatech.appserv.util.ClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

//@WebServlet(urlPatterns = "/message", name = "ExternalServlet", loadOnStartup = 1)
public class ExternalServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Writer out = response.getWriter();

        out.write("<!DOCTYPE html><html><head><title>External Servlet</title></head><body>");

        out.write("<p>Servlet loaded from " + ClassUtil.getLibrary(this) + " with the classloader" + ClassUtil.getClassLoader(this) + "</p>");

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
        System.out.println("=== ExternalServlet.init");
    }
}
