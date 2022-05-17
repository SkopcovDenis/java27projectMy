package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermModifyController", urlPatterns = "/term-modify")
public class TermModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTerm = req.getParameter("idTermToModify");
        DBManager manager = new DBManager();
        Term term = manager.getTermById(idTerm);
        List<Discipline> disciplines = manager.getAllActiveDisciplines();
        req.setAttribute("term", term);
        req.setAttribute("disciplines", disciplines);
        req.getRequestDispatcher("jsp/term-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTermModify = req.getParameter("idTermModify");
        String duration = req.getParameter("duration");
        String[] idsDisc = req.getParameterValues("idsDisc");

        DBManager manager = new DBManager();
        manager.modifyTerm(idTermModify, duration, idsDisc);

        resp.sendRedirect("/terms");
    }
}
