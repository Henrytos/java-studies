public class Uchiha extends Ninja implements HabilidadeEspecial {

    private String habilidadeEspecial;

    public Uchiha(int idade, String nome, String missao, NivelDeMissao nivelDificuldade, StatusDaMisao statusMissao, String habilidadeEspecial) {
        super(idade, nome, missao, nivelDificuldade, statusMissao);
        this.habilidadeEspecial = habilidadeEspecial;
    }

    public Uchiha(int idade, String nome, String missao, NivelDeMissao nivelDificuldade, StatusDaMisao statusMissao) {
        super(idade, nome, missao, nivelDificuldade, statusMissao);
    }

    public Uchiha(String habilidadeEspecial) {
        this.habilidadeEspecial = habilidadeEspecial;
    }

    @Override
    public void habilidadeEspecial(String habilidadeEspecial) {
        this.habilidadeEspecial = habilidadeEspecial;
    }

    @Override
    public void mostrarHabilidadeEspecial() {
        System.out.format("Sou um uchiha e minha hablidade especial é %s", this.habilidadeEspecial.concat(System.lineSeparator()));
    }

    @Override
    public void  mostrarInformacoes(){
        super.mostrarInformacoes();
        if(habilidadeEspecial != null){
            System.out.println("habilidade especial "+habilidadeEspecial);
        }
    }

}
