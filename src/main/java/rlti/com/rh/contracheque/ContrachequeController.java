package rlti.com.rh.contracheque;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.service.FuncionarioService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ContrachequeController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/gerarContrachequeBase64")
    public String gerarContracheque() {
        List<Funcionario> employees = funcionarioService.findAllFuncionarios();
        ContrachequePDFGenerator pdfGenerator = new ContrachequePDFGenerator();
        try {
            return pdfGenerator.generateContrachequePDFBase64(employees);
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao gerar contracheque: " + e.getMessage();
        }
    }

    @GetMapping("/gerarContracheque")
    public String gerarContracheque2() {
        List<Funcionario> funcionarios = funcionarioService.findAllFuncionarios();

        try {
            // Carregar o template JRXML
            InputStream templateStream = getClass().getResourceAsStream("/templates/contracheque.jrxml");

            // Compilar o template JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

            // Adicionar os dados para o relatório
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(funcionarios);
            Map<String, Object> parameters = new HashMap<>();

            // Gerar o relatório em PDF
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Converter o PDF para um array de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            byte[] pdfBytes = baos.toByteArray();

            // Converter o array de bytes para Base64
            return Base64.getEncoder().encodeToString(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            // Lidar com exceções, se necessário
            return "Erro ao gerar contracheque: " + e.getMessage();
        }
    }
}