using System.Data;
using ADO.Domain.Entities;

namespace ADO.Domain;

public static class Mapper
{
    //public static Student ToStudent(this IDataRecord record)
    //{
    //    return new Student(
    //        (int)record["Student_ID"],
    //        (string)record["First_Name"],
    //        (string)record["Last_Name"],
    //        (DateTime)record["BirthDate"],
    //        (string)record["Login"],
    //        (int)record["Section_ID"],
    //        (int)record["Year_Result"],
    //        (string)record["Course_ID"]);
    //}

    extension(IDataRecord record)
    {
        public Student ToStudent()
        {
            return new Student(
            (int)record["Student_ID"],
            (string)record["First_Name"],
            (string)record["Last_Name"],
            (DateTime)record["BirthDate"],
            (string)record["Login"],
            (int)record["Section_ID"],
            (int)record["Year_Result"],
            (string)record["Course_ID"]);
        }
    }
        
    public static Professor ToProfessor(this IDataRecord record)
    {
        return new Professor(
            (int)record["Professor_ID"],
            (string)record["Professor_Name"],
            (string)record["Professor_Surname"],
            (int)record["Section_ID"],
            (int)record["Professor_Office"],
            (string)record["Professor_Email"],
            (DateTime)record["Professor_Hire_Date"],
            (int)record["Professor_Wage"]);
    }
}
