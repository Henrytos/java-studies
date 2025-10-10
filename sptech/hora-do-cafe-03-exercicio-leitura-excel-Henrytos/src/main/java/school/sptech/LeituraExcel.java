package school.sptech;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.*;

public class LeituraExcel {

    public LeituraExcel() {
    }

    /* Implemente os métodos abaixo */

    List<Musica> lerMusicas(String caminhoArquivo) throws IOException {
        Workbook workbook = new XSSFWorkbook(caminhoArquivo);
        Sheet sheet = workbook.getSheetAt(0);

        List<Musica> musicas = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Musica musica = new Musica(
                    (int) row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(5).getStringCellValue(),
                    row.getCell(10).getLocalDateTimeCellValue().toLocalDate()
            );
            musicas.add(musica);
        }

        return musicas;
    }


    Produto produtoMaisCaro(String caminhoArquivo) throws IOException {
        Workbook workbook = new XSSFWorkbook(caminhoArquivo);
        Sheet sheet = workbook.getSheetAt(0);

        List<Produto> produtos = new ArrayList<>();

        Iterator<Row> rowIterator = sheet.rowIterator();

        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Produto produto = new Produto(
                    (int) row.getCell(0).getNumericCellValue(),
                    row.getCell(1).getStringCellValue(),
                    row.getCell(4).getNumericCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(7).getLocalDateTimeCellValue().toLocalDate()
            );

            produtos.add(produto);
        }

        Produto produtoCaro = produtos.getFirst();

        for (Produto produto : produtos) {
            if (produto.getValor() >= produtoCaro.getValor()) {
                produtoCaro = produto;
            }
        }

        return produtoCaro;
    }

    Double faturamentoTotal(String caminhoArquivo) throws IOException {
        Workbook workbook = new XSSFWorkbook(caminhoArquivo);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();

        Double faturamento = 0.0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            int quantidadeVendida = (int) row.getCell(10).getNumericCellValue();
            Double precoUnitario = row.getCell(11).getNumericCellValue();
            Double descontoAplicado = row.getCell(12).getNumericCellValue();

            if (quantidadeVendida != 0 && precoUnitario != 0.0 ) {
                faturamento += quantidadeVendida * precoUnitario * (1.0 - descontoAplicado / 100.00);
            }
        }

        return faturamento;
    }

    List<VendasCidade> faturamentoPorCidade(String caminhoArquivo) throws IOException {
        Workbook workbook = new XSSFWorkbook(caminhoArquivo);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();

        List<VendasCidade> vendas = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            int quantidadeVendida = (int) row.getCell(10).getNumericCellValue();
            Double precoUnitario = row.getCell(11).getNumericCellValue();
            Double descontoAplicado = row.getCell(12).getNumericCellValue();

            if (quantidadeVendida != 0 && precoUnitario != 0.0 ) {
                Double faturamento = quantidadeVendida * precoUnitario * (1.0 - descontoAplicado / 100.00);
                VendasCidade venda = new VendasCidade(row.getCell(20).getStringCellValue().toLowerCase(), faturamento);
                vendas.add(venda);
            }
        }

        List<VendasCidade> vendasPorCidade = new ArrayList<>();

        for (VendasCidade vendaAtual : vendas) {
            Boolean presente = false;

            for (VendasCidade vendasCidade : vendasPorCidade) {
                if (vendasCidade.getCidade().equalsIgnoreCase(vendaAtual.getCidade())) {
                    presente = true;
                    break;
                }
            }

            if (!presente) {
                vendasPorCidade.add(new VendasCidade(vendaAtual.getCidade(), 0.0));
            }
        }

        for (VendasCidade vendaAtual : vendasPorCidade) {
            Double faturaTotal = 0.0;

            for (VendasCidade venda : vendas) {
                if (venda.getCidade().equalsIgnoreCase(vendaAtual.getCidade()))
                    faturaTotal += venda.getFaturamentoTotal();
            }

            vendaAtual.setFaturamentoTotal(faturaTotal);
        }

        return vendasPorCidade;
    }
}
