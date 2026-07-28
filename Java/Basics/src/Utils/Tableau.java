package Utils;

public class Tableau {
    private Tableau (){

    }

    public static void afficherTableau(String[] tab){
        StringBuilder result  = new StringBuilder();
        for (String s : tab) {
            result.append(s);
        }
        System.out.print(result);
    }

    public static int  getIndex (String[] tab, String valeur){
        for (int i = 0; i < tab.length; i++){
            if (tab[i].equals(valeur)) return i;
        }
        return -1;
    }

}
