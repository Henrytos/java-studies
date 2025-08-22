package programacao_funcional;

public class Main {
    static SaudacaoFuncaoInterface saudacaoFuncaoInterface = new SaudacaoFuncaoInterface() {
        @Override
        public void saudar() {
            System.out.println("olá função saudar");
        }
    };

    public static void main(String[] args) {
        saudacaoFuncaoInterface.saudar();
    }
}
