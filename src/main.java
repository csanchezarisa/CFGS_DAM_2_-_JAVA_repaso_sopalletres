import java.util.Scanner;

public class main {

    public final int MIDATAULER = 12;
    public char[][] tauler = new char[MIDATAULER][MIDATAULER];
    public Scanner teclat = new Scanner(System.in);

    /* Llanca el programa principal */
    public static void main(String args[]) throws InterruptedException {
        main launch = new main();
        launch.launcher();
    }

    /* .: 1 :. Metode principal */
    public void launcher() throws InterruptedException {
        omplenarTauler();

        char opcioUsuari = ' ';
        do {

            netejarPantalla();
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
                    buscarParaula(false);
                    break;

                case 'c':
                    buscarParaula(true);
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
        for (int index = 1; index < MIDATAULER - 1; index++) {
            for (int subIndex = 1; subIndex < MIDATAULER - 1; subIndex++) {
                tauler[index][subIndex] = (char) ((Math.random() * 26) + 97);
            }
        }
    }

    /* Mostra el tauler per pantalla */
    private void imprimirTauler() {
        for (int index = 1; index < MIDATAULER - 1; index++) {
            for (int subIndex = 1; subIndex < MIDATAULER - 1; subIndex++)
                System.out.print(tauler[index][subIndex] + " ");
            System.out.println();
        }
    }

    /* .: 2 :. Buscar paraula en el tauler */
    /* Aquest metode ha de rebre un boolean, aquest decideix si, quan troba la paraula, deixa de buscar-ne mes repeticions o hi continua */
    private void buscarParaula(boolean continuat) throws InterruptedException {

        netejarPantalla();
        imprimirTauler();
        System.out.print("Paraula a cercar: ");
        String paraula = teclat.next();
        if (paraula.length() >= 1) {
            cercaParaula(continuat, paraula);
        }
        else {
            System.out.println("Introdueix una paraula valida...");
            stop();
        }

    }

    /* Busca en tot el tauler si hi figura la primera lletra de la paraula a cercar */
    private void cercaParaula(boolean continuat, String paraulaPerCercar) {
        boolean existeix = false;
        for (int fila = 1; fila < MIDATAULER - 1; fila++) {
            for (int columna = 1; columna < MIDATAULER - 1; columna++) {
                if (tauler[fila][columna] == paraulaPerCercar.charAt(0)) {
                    existeix = false;

                    if (paraulaPerCercar.length() == 1) {
                        existeix = true;
                    }
                    else {
                        existeix = revisarVoltant(fila, columna, paraulaPerCercar);
                    }

                    if (existeix) {
                        tauler[fila][columna] = Character.toUpperCase(tauler[fila][columna]);

                        if (!continuat) {
                            fila = MIDATAULER;
                            columna = MIDATAULER;
                        }
                    }
                }
            }
        }
    }

    public boolean revisarVoltant(int fila, int columna, String paraulaPerCercar) {
        boolean existeix = false;

        for (int filaPerRevisar = fila - 1; filaPerRevisar <= fila + 1; filaPerRevisar++) {
            for (int columnaPerRevisar = columna - 1; columnaPerRevisar <= columna + 1; columnaPerRevisar++) {
                if (tauler[filaPerRevisar][columnaPerRevisar] == paraulaPerCercar.charAt(1) && !(filaPerRevisar == fila && columnaPerRevisar == columna)) {
                    if (paraulaPerCercar.length() == 2) {
                        existeix = true;
                        tauler[filaPerRevisar][columnaPerRevisar] = Character.toUpperCase(tauler[filaPerRevisar][columnaPerRevisar]);
                        columnaPerRevisar = columna + 1;
                        filaPerRevisar = fila + 1;
                    }
                    else {

                    }
                }
            }
        }

        return existeix;
    }

    /* Neteja la pantalla */
    public void netejarPantalla() {
        for (int index = 0; index < 50; index++)
            System.out.println();
    }

    /* Mètode que para el programa i el deixa atorat */
    public void stop() throws InterruptedException {
        Thread.sleep(10000);
    }
}
