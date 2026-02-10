package br.com.alura;

import java.time.Instant;

public class Log {
    private Integer idLog;
    private TipoLog tipo;
    private String descricao;
    private Instant dataCriacao;

    public Log() {
    }

    public Log(TipoLog tipo, String descricao, Instant dataCriacao) {
        this.idLog = null;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
    }

    public Log(Integer idLog, TipoLog tipo, String descricao, Instant dataCriacao) {
        this.idLog = idLog;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public TipoLog getTipo() {
        return tipo;
    }

    public void setTipo(TipoLog tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {

        String templateLog = "%s %s --- [moveon] : %s";
        return templateLog.formatted(this.dataCriacao, this.tipo, this.descricao);

    }
}
