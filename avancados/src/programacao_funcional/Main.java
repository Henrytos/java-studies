package programacao_funcional;

public class Main {

    // INNER CLASSES
    static final SaudacaoFuncaoInterface saudacaoFuncaoInterfaceInnerClasse = new SaudacaoFuncaoInterface() {
        @Override
        public void saudar() {
            System.out.println("olá função saudar inner classe");
        }
    };

    static final SaudacaoFuncaoInterface saudacaoFuncaoInterfaceLambda = () -> System.out
            .println("olá função lambda saudar");

    static final ProcessString processString = System.out::println;

    public static void main(String[] args) {
        saudacaoFuncaoInterfaceInnerClasse.saudar();
        saudacaoFuncaoInterfaceLambda.saudar();
        Runnable runnable = () -> System.out.println("olá função saudar em runner");
        runnable.run();

        processString.process("olá função method reference");
    }
}
