package programacao_funcional;

public class Main {

    // INNER CLASSES
    static SaudacaoFuncaoInterface saudacaoFuncaoInterfaceInnerClasse = new SaudacaoFuncaoInterface() {
        @Override
        public void saudar() {
            System.out.println("olá função saudar inner classe");
        }
    };

    static SaudacaoFuncaoInterface saudacaoFuncaoInterfaceLambda = () -> System.out.println("olá função lambda saudar");

    public static void main(String[] args) {
        saudacaoFuncaoInterfaceInnerClasse.saudar();
        saudacaoFuncaoInterfaceLambda.saudar();
        Runnable runnable = () -> System.out.println("olá função saudar em runner");
        runnable.run();
    }
}
