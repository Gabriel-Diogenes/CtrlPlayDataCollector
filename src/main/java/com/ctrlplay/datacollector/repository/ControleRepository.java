package com.ctrlplay.datacollector.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ControleRepository {

    private static final String PATH = "controle.json";

    private ObjectMapper mapper = new ObjectMapper();

    public Set<String> carregar() {
        try {
            File file = new File(PATH);

            if (!file.exists()) {
                return new HashSet<>();
            }

            return mapper.readValue(file, new TypeReference<Set<String>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar controle", e);
        }
    }

    public void salvar(Set<String> controle) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(PATH), controle);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar controle", e);
        }
    }
}