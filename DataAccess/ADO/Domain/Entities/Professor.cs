namespace ADO.Domain.Entities;

public class Professor
{
    public int Professor_ID { get; set; }
    public string Professor_Name { get; set; }
    public string Professor_Surname { get; set; }
    public int Section_ID { get; set; }
    public int Professor_Office { get; set; }
    public string Professor_Email { get; set; }
    public DateTime Professor_Hire_Date { get; set; }
    public int Professor_Wage { get; set; }

    public Professor()
    {

    }

    public Professor(int professor_ID, string professor_Name, string professor_Surname, int section_ID, int professor_Office, string professor_Email, DateTime professor_HireDate, int professor_Wage)
    {
        Professor_ID = professor_ID;
        Professor_Name = professor_Name;
        Professor_Surname = professor_Surname;
        Section_ID = section_ID;
        Professor_Office = professor_Office;
        Professor_Email = professor_Email;
        Professor_Hire_Date = professor_HireDate;
        Professor_Wage = professor_Wage;
    }
}
