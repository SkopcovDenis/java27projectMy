package database;

import entity.Discipline;
import entity.Mark;
import entity.Student;
import entity.Term;

import java.util.List;
import java.util.Map;

public interface IDBManager {

    List<Student> getAllActiveStudents();

    void createStudent(String surname, String name, String group, String date);

    void deleteStudent(String id);

    Student getStudentById(String id);

    void modifyStudent(String id, String surname, String name, String group, String dateToDatabase);

    List<Term> getAllActiveTerms();

    List<Discipline> getDisciplinesByTerm(int id);

    List<Discipline> getAllActiveDisciplines();

    List<Mark> getMarksByTerm(int idTerm, String idStudent);

    boolean canLogin(String login, String password, String role);

    String getLastTermName();

    void createNewTerm(String newName, String duration, String[] ids);

    void deleteTerm(String idTerm);

    Term getTermById(String idTerm);

    void modifyTerm(String idTermModify, String duration, String[] idsDisc);

    List<Discipline> getAllDisciplines();

    void createDiscipline(String discipline);

    void deleteDiscipline(String id);

    Discipline getDisciplineById(String id);

    void modifyDiscipline(String id, String discipline);
}
