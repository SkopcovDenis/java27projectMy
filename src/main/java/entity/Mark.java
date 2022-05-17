package entity;

import java.util.Objects;

public class Mark {
    private int mark;
    private Discipline discipline;
    private Term term;
    private Student student;

    public Mark() {
    }

    public Mark(int mark, Discipline discipline, Term term, Student student) {
        this.mark = mark;
        this.discipline = discipline;
        this.term = term;
        this.student = student;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark1 = (Mark) o;
        return mark == mark1.mark && Objects.equals(discipline, mark1.discipline) && Objects.equals(term, mark1.term) && Objects.equals(student, mark1.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, discipline, term, student);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "mark=" + mark +
                ", discipline=" + discipline +
                ", term=" + term +
                ", student=" + student +
                '}';
    }
}
