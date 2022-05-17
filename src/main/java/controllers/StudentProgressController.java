package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Mark;
import entity.Student;
import entity.Term;
import services.MarksService;
import services.TermService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student-progress")
public class StudentProgressController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("progressHidden");
        String idSelectedTerm = req.getParameter("idSelectedTerm");
        DBManager manager = new DBManager();

        // 1 - студент по id
        // 2 - все активные семестры
        // 3 - определить первый актвивный  семестр (выбранный по умолчанию)

        // 5 - оценки, если есть по этому студенту по выбранному семестру
        // 6 - оперделить среднюю оценку

        Student student = manager.getStudentById(id);
        List<Term> terms = manager.getAllActiveTerms();
        req.setAttribute("terms", terms);

        Term selectedTerm = null;
        if (idSelectedTerm == null) {
            selectedTerm = terms.get(0);
            req.setAttribute("selectedTerm", selectedTerm);
        } else {
            selectedTerm = TermService.getTermById(terms, idSelectedTerm);
            req.setAttribute("selectedTerm", selectedTerm);
        }

        List<Mark> marks = manager.getMarksByTerm(selectedTerm.getId(), id);
        if (marks.isEmpty()) {

            List<Discipline> disciplines = manager.getDisciplinesByTerm(selectedTerm.getId());
            for (Discipline d:disciplines){
                Mark mark = new Mark();
                mark.setDiscipline(d);
                marks.add(mark);
            }
        } else {
            req.setAttribute("haveMarks", 1);
            double avarage = MarksService.getAvarageMarks(marks);
            req.setAttribute("avarage", avarage);
        }
        req.setAttribute("marks", marks);
        req.setAttribute("student", student);
        req.getRequestDispatcher("jsp/student-progress.jsp").forward(req, resp);
    }
}
