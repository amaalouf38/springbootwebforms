package springbootforms.springbootforms.model;

import jakarta.persistence.*;

@Entity
public class Course {

    public Course() {
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course ID: " + this.id + " Name: " + this.name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * @ManyToMany(mappedBy = "courses")
     * private Set<Student> students = new HashSet<>();
     * 
     * public Set<Student> getStudents() {
     * return students;
     * }
     * 
     * public void setStudents(Set<Student> students) {
     * this.students = students;
     * }
     */

}
