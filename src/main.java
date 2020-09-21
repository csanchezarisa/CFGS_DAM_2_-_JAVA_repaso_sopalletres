import java.util.Scanner;

public class main {

    public final int MIDATAULER = 10;
    public char[][] tauler = new char[MIDATAULER][MIDATAULER];
    public Scanner teclat = new Scanner(System.in);

    /* Llanca el programa principal */
    public static void main(String args[]) {
        main launch = new main();
        launch.launcher();
    }

    /* .: 1 :. Metode principal */
    public void launcher() {
        omplenarTauler();

        char opcioUsuari = ' ';
        do {

            imprimirTauler();
            menuPrincipal();
            System.out.print("Escull una opció: ");
            opcioUsuari = Character.toLowerCase(teclat.next().charAt(0));

            switch (opcioUsuari) {

                case 'a':
                    omplenarTauler();
                    netejarPantalla();
                    break;

                case 'b':
                    break;

                case 'c':
                    break;

                case 'd':
                    System.out.println("Sortint...");
                    break;

                default:
                    System.out.println("Escull una opció vàlida!");
                    break;

            }

        }
        while (opcioUsuari != 'd');

    }

    /* Mostra el menú principal */
    private void menuPrincipal() {
        System.out.println();
        System.out.println("a - Refer sopa de lletres");
        System.out.println("b - Buscar paraula 1 vegada");
        System.out.println("c - Buscar paraula totes les possibles");
        System.out.println("d - Sortir");
    }

    /* S'encarrega d'omplenar amb lletres random el tauler */
    private void omplenarTauler() {
        for (int index = 0; index < MIDATAULER; index++) {
            for (int subIndex = 0; subIndex < MIDATAULER; subIndex++) {
                tauler[index][subIndex] = (char) ((Math.random() * 26) + 97);
            }
        }
    }

    /* Mostra el tauler per pantalla */
    private void imprimirTauler() {
        for (int index = 0; index < MIDATAULER; index++) {
            for (int subIndex = 0; subIndex < MIDATAULER; subIndex++)
                System.out.print(tauler[index][subIndex] + " ");
            System.out.println();
        }
    }

    /* Neteja la pantalla */
    public void netejarPantalla() {
        for (int index = 0; index < 50; index++)
            System.out.println();
    }
}
