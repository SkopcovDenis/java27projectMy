package controllers;

import database.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DisciplineController", urlPatterns = "/discipline")
public class DisciplineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Discipline> disciplines = manager.getAllDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.getRequestDispatcher("jsp/discipline.jsp").forward(req, resp);
    }
}
