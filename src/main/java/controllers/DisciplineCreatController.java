package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplineCreatController", urlPatterns = "/discipline-creat")
public class DisciplineCreatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/discipline-creat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String discipline = req.getParameter("discipline");


        if (discipline.isEmpty()) {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("jsp/discipline-creat.jsp").forward(req, resp);
            return;
        }
        DBManager manager = new DBManager();
        manager.createDiscipline(discipline);

        resp.sendRedirect("/discipline");
    }
}
