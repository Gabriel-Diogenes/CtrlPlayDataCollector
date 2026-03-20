package com.ctrlplay.datacollector.repository;

import com.ctrlplay.datacollector.model.Aluno;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter {

    private static final String OUTPUT_PATH = System.getProperty("user.dir") + "/output/";

    public File gerarRelatorioAlunos(List<Aluno> alunos) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Relatorio");

            int rowNum = 0;

            Row header = sheet.createRow(rowNum++);
            header.createCell(0).setCellValue("Data Pagamento");
            header.createCell(1).setCellValue("Nome Responsável");
            header.createCell(2).setCellValue("CPF");
            header.createCell(3).setCellValue("Telefone");
            header.createCell(4).setCellValue("Email");
            header.createCell(5).setCellValue("Rua");
            header.createCell(6).setCellValue("Complemento");
            header.createCell(7).setCellValue("Bairro");
            header.createCell(8).setCellValue("CEP");
            header.createCell(9).setCellValue("Valor Pago");

            for (Aluno a : alunos) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(nvl(a.getDataPagamento()));
                row.createCell(1).setCellValue(nvl(a.getNomeResponsavel()));
                row.createCell(2).setCellValue(nvl(a.getCpf()));
                row.createCell(3).setCellValue(nvl(a.getTelefone()));
                row.createCell(4).setCellValue(nvl(a.getEmail()));
                row.createCell(5).setCellValue(nvl(a.getRua()));
                row.createCell(6).setCellValue(nvl(a.getComplemento()));
                row.createCell(7).setCellValue(nvl(a.getBairro()));
                row.createCell(8).setCellValue(nvl(a.getCep()));
                row.createCell(9).setCellValue(a.getValorPago() != null ? a.getValorPago() : 0.0);
            }

            for (int i = 0; i < 10; i++) {
                sheet.autoSizeColumn(i);
            }

            File dir = new File(OUTPUT_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = "relatorio_" + System.currentTimeMillis() + ".xlsx";
            File file = new File(OUTPUT_PATH + fileName);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }

            System.out.println("Arquivo gerado: " + file.getAbsolutePath());

            return file;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar Excel", e);
        }
    }

    private String nvl(String valor) {
        return valor != null ? valor : "";
    }
}
