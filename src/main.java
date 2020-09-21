public class main {

    public final int MIDATAULER = 10;
    public int[][] tauler = new int[MIDATAULER][MIDATAULER];

    /* Llanca el programa principal */
    public static void main(String args[]) {
        main launch = new main();
        launch.launcher();
    }

    /* .: 1 :. Metode principal */
    public void launcher() {
        omplenarTauler();
        imprimirTauler();
    }

    /* S'encarrega d'omplenar amb lletres random el tauler */
    private void omplenarTauler() {
        for (int index = 0; index < MIDATAULER; index++) {
            for (int subIndex = 0; subIndex < MIDATAULER; subIndex++) {
                tauler[index][subIndex] = (char) ((Math.random() * 25) + 97);
            }
        }
    }

    private void imprimirTauler() {
        for (int index = 0; index < MIDATAULER; index++) {
            for (int subIndex = 0; subIndex < MIDATAULER; subIndex++)
                System.out.print(tauler[index][subIndex]);
            System.out.println();
        }
    }
}
