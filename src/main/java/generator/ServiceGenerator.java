package generator;

import service.DateUtilsService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ServiceGenerator {

    public static void usingBufferedWritter(String entity, String variable, String author) throws IOException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        String fileContent = "package de.mms.dp.dashboard.service;\n" +
                "\n" +
                "import de.mms.dp.dashboard.domain."+entity+";\n" +
                "import de.mms.dp.dashboard.repository."+entity+"Repository;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import org.springframework.transaction.annotation.Transactional;\n" +
                "\n" +
                "/**\n" +
                " * Service class for "+entity+".\n" +
                " *\n"+
                " * @author "+author+"\n" +
                " * @since "+ DateUtilsService.formatMonth(dtf.format(localDate))+"\n" +
                " */\n" +
                "@Service\n" +
                "public class "+entity+"Service {\n" +
                "\n" +
                "    private final "+entity+"Repository "+variable+"Repository;\n" +
                "\n" +
                "    public "+entity+"Service("+entity+"Repository "+variable+"Repository) {\n" +
                "        this."+variable+"Repository = "+variable+"Repository;\n" +
                "    }\n" +
                "\n" +
                "    @Transactional\n" +
                "    public "+entity+" save("+entity+" "+variable+") {\n" +
                "        return "+variable+"Repository.save("+variable+");\n" +
                "    }\n" +
                "\n" +
                "    @Transactional\n" +
                "    public void delete(Long id) {\n" +
                "        "+variable+"Repository.delete(id);\n" +
                "    }\n" +
                "\n" +
                "    @Transactional\n" +
                "    public "+entity+" update("+entity+" "+variable+") {\n" +
                "        return  "+variable+"Repository.save("+variable+");\n" +
                "    }\n" +
                "}\n" +
                "\n";

        BufferedWriter writer = new BufferedWriter(new FileWriter(entity + "Service.java"));
        writer.write(fileContent);
        writer.close();
    }

}
