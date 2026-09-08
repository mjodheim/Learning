package be.mjodheim.dal.dao;

import be.mjodheim.dal.DbConnection;
import be.mjodheim.dal.entities.CourseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    public List<CourseEntity> findAll() {
        List<CourseEntity> courses = new ArrayList<>();
        String sql = "select * from courses";

        try(
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
        )
        {
            while (rs.next()) {
                CourseEntity course = new CourseEntity();

                course.setCourseId(rs.getString("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setCourseECTS(rs.getInt("course_ects"));
                course.setProfessorId(rs.getInt("professor_id"));

                courses.add(course);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return courses;
    }
}
