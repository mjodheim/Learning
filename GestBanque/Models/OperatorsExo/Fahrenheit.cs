namespace Models.OperatorsExo
{
    public class Fahrenheit
    {
        public static explicit operator Celsius(Fahrenheit fahrenheit)
        {
            return new Celsius() { Temperature = (fahrenheit.Temperature - 32) / 1.8 };
        }

        public double Temperature { get; set; }
    }
}
