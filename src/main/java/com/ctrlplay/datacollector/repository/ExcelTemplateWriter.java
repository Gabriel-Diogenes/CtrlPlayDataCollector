package com.ctrlplay.datacollector.repository;

import com.ctrlplay.datacollector.model.Cliente;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelTemplateWriter {

    private static final String CAMINHO_TEMPLATE = "C:\\ROBOS\\CtrlPlayDataCollector\\TEMPLATE CTRLPLAY.xls";
    private static final String NOME_ABA = "ContasReceber-CNA";

    public void preencherTemplate(List<Cliente> clientes) {

        try (FileInputStream fis = new FileInputStream(new File(CAMINHO_TEMPLATE));
             Workbook workbook = new HSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(NOME_ABA);

            int linhaInicial = 8;

            for (Cliente c : clientes) {

                Row row = sheet.getRow(linhaInicial);

                if (row == null) {
                    row = sheet.createRow(linhaInicial);
                }

                row.createCell(0).setCellValue(nvl(c.getTelefone()));

                row.createCell(2).setCellValue(nvl(c.getDataVencimento()));

                row.createCell(3).setCellValue(nvl(c.getDataPagamento()));

                row.createCell(4).setCellValue(nvl(c.getNomeResponsavel()));

                row.createCell(5).setCellValue(nvl(c.getCpf()));

                row.createCell(6).setCellValue(nvl(c.getEmail()));

                String enderecoCompleto = nvl(c.getEndereco()) + " - " + nvl(c.getBairro());
                row.createCell(7).setCellValue(enderecoCompleto);

                row.createCell(8).setCellValue(nvl(c.getNumero()));

                row.createCell(9).setCellValue(nvl(c.getComplemento()));

                row.createCell(10).setCellValue(nvl(c.getBairro()));

                row.createCell(11).setCellValue(nvl(c.getCidade()));

                row.createCell(12).setCellValue(nvl(c.getEstado()));

                row.createCell(13).setCellValue(nvl(c.getCep()));

                row.createCell(14).setCellValue(nvl(c.getParcela()));

                row.createCell(15).setCellValue(nvl(c.getValorPago()));

                linhaInicial++;
            }

            try (FileOutputStream fos = new FileOutputStream("resultado.xls")) {
                workbook.write(fos);
            }

            System.out.println("Planilha preenchida com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao preencher template", e);
        }
    }

    private String nvl(String valor) {
        return valor != null ? valor : "";
    }
}