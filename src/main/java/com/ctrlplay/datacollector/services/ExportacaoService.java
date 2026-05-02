package com.ctrlplay.datacollector.services;

import com.ctrlplay.datacollector.model.Cliente;
import com.ctrlplay.datacollector.repository.ExcelTemplateWriter;

import java.util.List;

public class ExportacaoService {

    public void exportar(List<Cliente> clientes) {

        ExcelTemplateWriter writer = new ExcelTemplateWriter();
        writer.preencherTemplate(clientes);
    }
}