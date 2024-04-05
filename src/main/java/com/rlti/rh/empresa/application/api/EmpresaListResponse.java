package com.rlti.rh.empresa.application.api;

import com.rlti.rh.empresa.domain.Empresa;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class EmpresaListResponse {
    Long idEmpresa;
    String razaoSocial;
    String nomeFantasia;
    String cnpj;
    String nomeAdministrador;
    String inscricaoMunicipal;
    String areaAtuacao;
    LocalDate dataAbertura;
    String email;
    String telefone;
    String enderecoComercial;

    public static List<EmpresaListResponse> converte(List<Empresa> empresas) {
        return empresas.stream()
                .map(EmpresaListResponse::new)
                .toList();
    }

    public EmpresaListResponse(Empresa empresa) {
        this.idEmpresa = empresa.getIdEmpresa();
        this.razaoSocial = empresa.getRazaoSocial();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.cnpj = empresa.getCnpj();
        this.nomeAdministrador = empresa.getNomeAdministrador();
        this.inscricaoMunicipal = empresa.getInscricaoMunicipal();
        this.areaAtuacao = empresa.getAreaAtuacao();
        this.dataAbertura = empresa.getDataAbertura();
        this.email = empresa.getEmail();
        this.telefone = empresa.getTelefone();
        this.enderecoComercial = empresa.getEnderecoComercial();
    }
}
