package generator;

import service.DateUtilsService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntityGenerator {

    public static void usingBufferedWritter(String entity, String database, String author, String type, String attribute) throws IOException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        String fileContent = "package de.mms.dp.dashboard.domain;\n" +
                "\n" +
                "import javax.persistence.*;\n" +
                "\n"+
                "import java.io.Serializable;"+
                "import java.util.Objects;"+
                "\n" +
                "import static de.mms.dp.dashboard.domain."+entity+".ENTITY_NAME;\n" +
                "\n" +
                "/**\n" +
                " * Entity class "+entity+".\n" +
                " *\n"+
                " * @author "+author+"\n" +
                " * @since "+ DateUtilsService.formatMonth(dtf.format(localDate)) +"\n" +
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
                "    @NotNull\n" +
                "    @Size(min = 3, max = 255)\n" +
                "    @Column(name = "+attribute+")\n" +
                "    private "+type+" "+attribute+";\n"+
                "\n"+
                "    public Long getId() {\n" +
                "        return id;\n" +
                "    }\n" +
                "\n" +
                "    public void setId(Long id) {\n" +
                "        this.id = id;\n" +
                "    }\n"+
                "\n" +
                "   public "+type+" get"+attribute.substring(0, 1).toUpperCase() + attribute.substring(1)+"() {\n" +
                "        return "+attribute+";\n" +
                "    }\n" +
                "\n" +
                "    public void set"+attribute.substring(0, 1).toUpperCase() + attribute.substring(1)+"("+type+" "+attribute+") {\n" +
                "        this."+attribute+" = "+attribute+";\n" +
                "    }\n"+
                "\n"+
                "    public "+entity +" "+attribute+"("+type+" "+attribute+") {\n" +
                "        this."+attribute+" = "+attribute+";\n" +
                "        return this;\n" +
                "    }\n"+
                "\n"+
                "    @Override\n" +
                "    public boolean equals(Object o) {\n" +
                "        if (this == o) {\n" +
                "            return true;\n" +
                "        };\n" +
                "        if (o == null || getClass() != o.getClass()) {\n" +
                "            return false;\n" +
                "        };\n" +
                "        "+entity+" that = ("+entity+") o;\n" +
                "        return Objects.equals(id, that.id);\n" +
                "    }" +
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
