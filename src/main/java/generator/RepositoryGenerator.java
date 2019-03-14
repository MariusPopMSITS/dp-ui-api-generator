package generator;

import service.DateUtilsService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RepositoryGenerator {

    public static void usingBufferedWritter(String entity, String author) throws IOException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String fileContent = "package de.mms.dp.dashboard.repository;\n" +
                "\n" +
                "import de.mms.dp.dashboard.domain."+entity+";\n" +
                "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "\n" +
                "/**\n" +
                " * Spring Data JPA repository for the "+entity+".\n" +
                " *\n"+
                " * @author "+author+"\n" +
                " * @since "+ DateUtilsService.formatMonth(dtf.format(localDate))+"\n" +
                " */\n" +
                "@SuppressWarnings(\"unused\")\n" +
                "@Repository\n" +
                "public interface "+entity+"Repository extends JpaRepository<"+entity+", Long> {\n" +
                "\n" +
                "}\n";

        BufferedWriter writer = new BufferedWriter(new FileWriter(entity + "Repository.java"));
        writer.write(fileContent);
        writer.close();
    }
}
