package be.mjodheim.dal.dao;

import be.mjodheim.dal.DbConnection;
import be.mjodheim.dal.entities.StudentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public List<StudentEntity> findAll() {
        List<StudentEntity> students = new ArrayList<>();
        String sql = "select * from student";

        try(
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();)
        {
            while (rs.next()) {
                StudentEntity student  = new StudentEntity();

                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                // Pour le LocalDateTime, on utilise getObject()
                student.setBirthDate(rs.getObject("birth_date", LocalDateTime.class));
                student.setLogin(rs.getString("login"));
                student.setSectionId(rs.getInt("section_id"));
                student.setYearResult(rs.getInt("year_result"));
                student.setCourseId(rs.getString("course_id"));

                students.add(student);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}
