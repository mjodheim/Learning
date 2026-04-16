namespace ADO.Domain.Entities;

public class Student
{
    public int Student_ID { get; set; }
    public string First_Name { get; set; }
    public string Last_Name { get; set; }
    public DateTime BirthDate { get; set; }
    public string Login { get; set; }
    public int Section_ID { get; set; }
    public int Year_Result { get; set; }
    public string Course_ID { get; set; }

    public Student()
    {

    }

    public Student(int student_ID, string first_Name, string last_Name, DateTime birthDate, string login, int section_ID, int year_Result, string course_ID)
    {
        Student_ID = student_ID;
        First_Name = first_Name;
        Last_Name = last_Name;
        BirthDate = birthDate;
        Login = login;
        Section_ID = section_ID;
        Year_Result = year_Result;
        Course_ID = course_ID;
    }
}
