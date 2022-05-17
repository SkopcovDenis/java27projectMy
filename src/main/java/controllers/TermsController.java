package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Term;
import services.TermService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermsController", urlPatterns = "/terms")
public class TermsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idSelectedTerm = req.getParameter("idSelectedTerm");

        DBManager manager = new DBManager();
        List<Term> terms = manager.getAllActiveTerms();

        Term selectedTerm = null;
        if (idSelectedTerm == null) {
            selectedTerm = terms.get(0);
            req.setAttribute("selectedTerm", selectedTerm);
        } else {
            selectedTerm = TermService.getTermById(terms, idSelectedTerm);
            req.setAttribute("selectedTerm", selectedTerm);
        }


        if (terms.size() != 0) {
            List<Discipline> disciplines = manager.getDisciplinesByTerm(selectedTerm.getId());
            req.setAttribute("disciplines", disciplines);
        }
        req.setAttribute("terms", terms);
        req.getRequestDispatcher("jsp/terms.jsp").forward(req, resp);
    }
}
