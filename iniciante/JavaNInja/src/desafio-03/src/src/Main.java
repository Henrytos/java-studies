//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Uchiha sasuke = new Uchiha(18, "Sasuke uchiha", "Vigar o meu clã", NivelDeMissao.ALTA, StatusDaMisao.PENDENTE);
        sasuke.mostrarInformacoes();

        System.out.println("---------------");
        sasuke.habilidadeEspecial("Sharigan");
        sasuke.mostrarInformacoes();

        System.out.println(sasuke);

    }
}