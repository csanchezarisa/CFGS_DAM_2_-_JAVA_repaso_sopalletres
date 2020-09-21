import java.util.Scanner;

public class sopalletres {

    public final int MIDATAULER = 12; // La constant que deifineix la mida del tauler ha de tenir 2 files i columnes més, ja que s'ha de deixar un marge sense utilitzar per que el programa funcioni
    public char[][] tauler = new char[MIDATAULER][MIDATAULER];
    public Scanner teclat = new Scanner(System.in);

    /* Llanca el programa principal */
    public static void main(String[] arg) throws InterruptedException {
        sopalletres launch = new sopalletres();
        launch.launcher();
    }

    /* .: 1 :. Metode principal */
    public void launcher() throws InterruptedException {
        omplenarTauler();

        char opcioUsuari;
        do {

            netejarPantalla();
            imprimirTauler();
            menuPrincipal();
            System.out.print("Escull una opció: ");
            opcioUsuari = Character.toLowerCase(teclat.next().charAt(0));

            switch (opcioUsuari) {

                case 'a': // Decideix tornar a omplenar el tauler amb lletres noves
                    omplenarTauler();
                    netejarPantalla();
                    break;

                case 'b': // Busca la paraula només 1 cop
                    buscarParaula(false);
                    break;

                case 'c': // Busca la paraula repetida tants cops com hi sigui
                    buscarParaula(true);
                    break;

                case 'd': // Surt del programa
                    System.out.println("Sortint...");
                    stop();
                    break;

                default: // Introdueix una opcio no valida
                    System.out.println("Escull una opció vàlida!");
                    stop();
                    break;

            }

        }
        while (opcioUsuari != 'd');

    }

    /* Mostra el menú principal */
    private void menuPrincipal() {
        System.out.println("a - Refer sopa de lletres");
        System.out.println("b - Buscar paraula 1 vegada");
        System.out.println("c - Buscar paraula totes les possibles");
        System.out.println("d - Sortir");
        System.out.println();
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
        System.out.println();
    }

    /* .: 2 :. Buscar paraula en el tauler */
    /* Aquest metode ha de rebre un boolean, aquest decideix si, quan troba la paraula, deixa de buscar-ne mes repeticions o hi continua */
    private void buscarParaula(boolean continuat) throws InterruptedException {

        netejarPantalla();
        imprimirTauler();
        /* Es demana la paraula a cercar a l'usuari */
        System.out.print("Paraula a cercar: ");
        String paraula = teclat.next();

        /* Comprova que la paraula contingui alguna lletra */
        if (paraula.length() >= 1) {
            cercaParaula(continuat, paraula);
        }
        else {
            System.out.println("Introdueix una paraula valida...");
            stop();
        }

    }

    /* Busca en tot el tauler si hi figura la primera lletra de la par
    aula a cercar */
    private void cercaParaula(boolean continuat, String paraulaPerCercar) throws InterruptedException {

        boolean existeix = false;

        for (int fila = 1; fila < MIDATAULER - 1; fila++) {
            for (int columna = 1; columna < MIDATAULER - 1; columna++) {

                if (tauler[fila][columna] == paraulaPerCercar.charAt(0)) { // Revisa que la lletra analitzada sigui igual que la primera de la paraula per analitzar

                    if (paraulaPerCercar.length() == 1) { // Si la paraula te nomes una lletra deixa de buscar més
                        existeix = true;
                    }
                    else { // En cas que sigui mes llarga, executa el metode que revisa si la segona lletra esta al voltant
                        existeix = revisarVoltant(fila, columna, paraulaPerCercar);
                    }

                    if (existeix) { // Posa la lletra en majuscula
                        tauler[fila][columna] = Character.toUpperCase(tauler[fila][columna]);

                        if (!continuat) { // Si s'ha escollit nomes trobar una repeticio es sortira de la iteracio
                            fila = MIDATAULER;
                            columna = MIDATAULER;
                        }
                    }
                }
            }
        }

        if (!existeix) {
            System.out.println("No s'ha trobat aquesta paraula, prova amb una altre");
            stop();
        }
    }

    /* Revisa el voltant de la primera lletra trobada, per buscar una coincidencia amb la segona lletra de la paraula */
    public boolean revisarVoltant(int fila, int columna, String paraulaPerCercar) {
        boolean existeix = false;

        /* Des de la coordenada on es troba la primera lletra, es revisa tot el seu voltant en busca de la segona lletra */
        for (int filaPerRevisar = fila - 1; filaPerRevisar <= fila + 1; filaPerRevisar++) {
            for (int columnaPerRevisar = columna - 1; columnaPerRevisar <= columna + 1; columnaPerRevisar++) {

                if (tauler[filaPerRevisar][columnaPerRevisar] == paraulaPerCercar.charAt(1) && !(filaPerRevisar == fila && columnaPerRevisar == columna)) {
                    if (paraulaPerCercar.length() == 2) { // En cas que la paraula a cercar tingui només 2 lletres
                        existeix = true;
                    }
                    else { // Si la paraula a cercar en te mes de dos
                        existeix = revisarDireccio(filaPerRevisar, columnaPerRevisar, (filaPerRevisar - fila), (columnaPerRevisar - columna), paraulaPerCercar);
                    }

                    if (existeix) { // En cas de la paraula trobar-se dins del tauler es posa en majuscula la lletra i es surt
                        tauler[filaPerRevisar][columnaPerRevisar] = Character.toUpperCase(tauler[filaPerRevisar][columnaPerRevisar]);
                        columnaPerRevisar = columna + 1;
                        filaPerRevisar = fila + 1;
                    }
                }
            }
        }

        return existeix;
    }

    /* Revisa les lletres en la direcció en la que s'ha trobat coincidencia amb la segona lletra de la paraula a cercar. Per saber la direccio s'utilitza l'increment que ha hagut de coordenades de la segona lletra respecte la primera */
    private boolean revisarDireccio(int filaSegonaLletra, int columnaSegonaLletra, int incrementY, int incrementX, String paraulaPerCercar) {
        boolean existeix = false;
        int lletraParaula = 2; // Emmagatzemara la posicio de la paraula que s'esta analitzant

        for (int filaPerRevisar = (filaSegonaLletra + incrementY); filaPerRevisar >= 0 && filaPerRevisar < MIDATAULER; filaPerRevisar += incrementY) {
            for (int columnaPerRevisar = (columnaSegonaLletra + incrementX); columnaPerRevisar >= 0 && columnaPerRevisar < MIDATAULER; columnaPerRevisar += incrementX) {

                if (tauler[filaPerRevisar][columnaPerRevisar] == paraulaPerCercar.charAt(lletraParaula)) {
                    existeix = true;
                    lletraParaula ++;
                }

                if (lletraParaula >= paraulaPerCercar.length()) { // Si la posicio de la paraula es mes gran que la longitud de la mateixa, surt
                    filaPerRevisar = MIDATAULER + 1;
                    columnaPerRevisar = MIDATAULER + 1;
                }

            }
        }

        if (existeix) { // En cas que la resta de llestres existeixin en la direccio correcta, aquestes es posaran en majuscula
            int repeticioPosarMajuscules = lletraParaula - 2; // Es calcula quantes iteracions s'han de fer fins arribar al final de la paraula repetida

            for (int index = 0; index < repeticioPosarMajuscules; index++) { // Es repeteix el bucle i incrementant les coordenades, possant en majuscula cada lletra
                filaSegonaLletra += incrementY;
                columnaSegonaLletra += incrementX;
                tauler[filaSegonaLletra][columnaSegonaLletra] = Character.toUpperCase(tauler[filaSegonaLletra][columnaSegonaLletra]);
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
        Thread.sleep(3000);
    }
}
