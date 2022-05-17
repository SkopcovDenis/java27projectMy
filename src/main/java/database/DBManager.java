package database;

import constants.Constants;
import entity.Discipline;
import entity.Mark;
import entity.Student;
import entity.Term;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager implements IDBManager {


    @Override
    public List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student where status = 1;");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void createStudent(String surname, String name, String group, String date) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `student` (`surname`, `name`, `group`, `date`) VALUES ('" + surname + "', '" + name + "', '" + group + "', '" + date + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student where status = 1 and id = " + id);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void modifyStudent(String id, String surname, String name, String group, String dateToDatabase) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `surname` = '" + surname + "', `name` = '" + name + "', `group` = '" + group + "', `date` = '" + dateToDatabase + "' WHERE (`id` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from term where status = 1;");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setTerm(rs.getString("term"));
                term.setDuration(rs.getString("duration"));
                terms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    @Override
    public List<Discipline> getDisciplinesByTerm(int id) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.discipline FROM term_descipline as td\n" +
                    "left join discipline as d on td.id_descipline = d.id\n" +
                    "where td.id_term = " + id + " and d.status = 1 and td.status = '1'");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public List<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline where status = 1");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public List<Mark> getMarksByTerm(int idTerm, String idStudent) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.discipline, m.mark FROM mark as m\n" +
                    "left join term_descipline as td on m.id_term_descipline = td.id\n" +
                    "left join discipline as d on td.id_descipline = d.id\n" +
                    "where d.status = 1 and m.id_student = " + idStudent + " and td.id_term = " + idTerm);
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));

                Mark mark = new Mark();
                mark.setMark(rs.getInt("mark"));
                mark.setDiscipline(discipline);
                marks.add(mark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marks;
    }

    @Override
    public boolean canLogin(String login, String password, String role) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_role as ur\n" +
                    "left join user as u on ur.id_user = u.id\n" +
                    "where u.login = '" + login + "' and u.password = '" + password + "' and ur.id_role = " + role);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getLastTermName() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from term order by id desc limit 1;");
            while (rs.next()) {
                return rs.getString("term");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createNewTerm(String newName, String duration, String[] ids) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `term` (`term`, `duration`) VALUES ('" + newName + "', '" + duration + "');\n");

            ResultSet rs = stmt.executeQuery("SELECT last_insert_id() as id");

            int idTerm = 0;
            while (rs.next()) {
                idTerm = rs.getInt("id");

            }

            for (String idDisc : ids) {
                stmt.execute("INSERT INTO `term_descipline` (`id_term`, `id_descipline`) VALUES ('" + idTerm + "', '" + idDisc + "');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTerm(String idTerm) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `term` SET `status` = '0' WHERE (`id` = '" + idTerm + "');");
            stmt.execute("UPDATE `term_descipline` SET `status` = '0' WHERE (`id` = '" + idTerm + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Term getTermById(String idTerm) {
        Term term = new Term();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from term where status =1 and id=" + idTerm);
            while (rs.next()) {
                term.setId(rs.getInt("id"));
                term.setTerm(rs.getString("term"));
                term.setDuration(rs.getString("duration"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return term;
    }

    @Override
    public void modifyTerm(String idTermModify, String duration, String[] idsDisc) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `term` SET `duration` = '" + duration + "' WHERE (`id` = '" + idTermModify + "');");
            stmt.execute("UPDATE `term_descipline` SET `status` = '0' WHERE (`id_term` = '" + idTermModify + "');");


            for (String idDisc : idsDisc) {
                stmt.execute("INSERT INTO `term_descipline` (`id_term`, `id_descipline`) VALUES ('" + idTermModify + "', '" + idDisc + "');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline where status = 1");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public void createDiscipline(String discipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `discipline` (`discipline`) VALUES ('" + discipline + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDiscipline(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `status` = '0' WHERE (`id` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Discipline getDisciplineById(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from discipline where status = 1 and id = " + id);
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                return discipline;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void modifyDiscipline(String id, String discipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `discipline` = '"+discipline+"', `status` = '1' WHERE (`id` = '"+id+"');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

