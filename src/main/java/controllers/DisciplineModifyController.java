package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "DisciplineModifyController", urlPatterns = "/discipline-modify")
public class DisciplineModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("modifyDiscHidden");
        DBManager dbManager = new DBManager();
        Discipline discipline = dbManager.getDisciplineById(id);
        req.setAttribute("discipline", discipline);
        req.getRequestDispatcher("jsp/discipline-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String discipline = req.getParameter("discipline");

        if (discipline.isEmpty()) {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("jsp/discipline-modify.jsp").forward(req, resp);
            return;
        }

        DBManager manager = new DBManager();
        manager.modifyDiscipline(id, discipline);

        resp.sendRedirect("/discipline");
    }
}
