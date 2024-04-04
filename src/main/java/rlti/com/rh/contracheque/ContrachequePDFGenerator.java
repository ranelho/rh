package rlti.com.rh.contracheque;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import rlti.com.rh.funcionario.domain.Funcionario;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ContrachequePDFGenerator {
    public String generateContrachequePDFBase64(List<Funcionario> employees) throws IOException {
        try (PDDocument document = new PDDocument()) {
            for (Funcionario employee : employees) {
                PDPage page = new PDPage();
                document.addPage(page);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.showText("Contracheque de " + employee.getNomeCompleto());
                    contentStream.newLine();
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    contentStream.showText("ID: " + employee.getIdFuncionario());
                    contentStream.newLine();
                    contentStream.showText("Salário: R$ " + " 2000,00");
                    contentStream.endText();
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }
    }
}
