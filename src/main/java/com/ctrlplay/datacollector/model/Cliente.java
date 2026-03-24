package com.ctrlplay.datacollector.model;

public class Cliente {
    private String nomeResponsavel;
    private String cpf;
    private String telefone;
    private String email;

    private String endereco;
    private String complemento;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    private String dataPagamento;
    private String valorPago;
    private String dataVencimento;
    private String parcela;
    private String nomeFilhoCliente;


    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getNomeFilhoCliente() {
        return nomeFilhoCliente;
    }

    public void setNomeFilhoCliente(String nomeFilhoCliente) {
        this.nomeFilhoCliente = nomeFilhoCliente;
    }

    public Cliente clone() {
        Cliente c = new Cliente();

        c.setNomeResponsavel(this.nomeResponsavel);
        c.setCpf(this.cpf);
        c.setTelefone(this.telefone);
        c.setEmail(this.email);

        c.setEndereco(this.endereco);
        c.setComplemento(this.complemento);
        c.setNumero(this.numero);
        c.setBairro(this.bairro);
        c.setCidade(this.cidade);
        c.setEstado(this.estado);
        c.setCep(this.cep);

        return c;
    }
}
