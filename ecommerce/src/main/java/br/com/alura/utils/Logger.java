package br.com.alura.utils;


import br.com.alura.TipoLog;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    private final List<Log> logs = new ArrayList<>();

    private final static Logger INSTANCE = new Logger();

    public String create(
            TipoLog tipoLog,
            String descricao
    ) {

        Instant dataCriacao = Instant.now().truncatedTo(ChronoUnit.MICROS); // 6 nano segundos
        Log log = new Log(
                tipoLog, descricao, dataCriacao
        );

        this.logs.add(log);
        return log.toString();
    }

    public void info(String descricao) {
        System.out.println(create(TipoLog.INFO, descricao));
    }

    public void warn(String descricao) {
        System.out.println(create(TipoLog.WARN, descricao));
    }

    public void error(String descricao) {
        System.err.println(create(TipoLog.ERROR, descricao));
    }

    public void fatal(String descricao) {
        System.err.println(create(TipoLog.FATAL, descricao));
    }


    public static Logger getInstance() {
        return INSTANCE;
    }

}