import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntityGenerator {

    public static void usingBufferedWritter(String entity, String database, String author) throws IOException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        String fileContent = "package de.mms.dp.dashboard.domain;\n" +
                "\n" +
                "import org.codehaus.jackson.annotate.JsonIgnore;\n" +
                "\n" +
                "import javax.persistence.*;\n" +
                "import javax.validation.constraints.NotNull;\n" +
                "\n" +
                "import static de.mms.dp.dashboard.domain."+entity+".ENTITY_NAME;\n" +
                "\n" +
                "/**\n" +
                " * Entity class "+entity+".\n" +
                " * @author "+author+"\n" +
                " * @since "+dtf.format(localDate)+"\n" +
                " */\n" +
                "@Entity\n" +
                "@Table(name = ENTITY_NAME)\n" +
                "public class "+entity+" implements Serializable {\n" +
                "\n" +
                "    public static final String ENTITY_NAME = \""+database+"\";\n" +
                "\n" +
                "    @Id\n" +
                "    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = \"gen_"+database+"\")\n" +
                "    @SequenceGenerator(name = \"gen_"+database+"\", sequenceName = \"seq_"+database+"\")\n" +
                "    private Long id;\n"+
                "\n" +
                "    @Override\n" +
                "    public boolean equals(Object o) {\n" +
                "        if (this == o) return true;\n" +
                "        if (o == null || getClass() != o.getClass()) return false;\n" +
                "        "+entity+" that = ("+entity+") o;\n" +
                "        return Objects.equals(id, that.id);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int hashCode() {\n" +
                "        return Objects.hash(id);\n" +
                "    }"+
                "\n" +
                "}\n";

        BufferedWriter writer = new BufferedWriter(new FileWriter(entity + ".java"));
        writer.write(fileContent);
        writer.close();
    }

}
