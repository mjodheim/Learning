package Utils;

public class Tableau {
    private Tableau (){

    }

    public static void afficherTableau(String[] tab){
        StringBuilder result  = new StringBuilder();
        for (int i = 0; i < tab.length; i++){
            result.append(tab[i]);
        }
        System.out.println(result.toString());
    }

    public static void initialiserTableau (String[] tab, String valeur){
        for (int i = 0; i < tab.length; i++){
            tab[i] = valeur;
        }
    }
}
