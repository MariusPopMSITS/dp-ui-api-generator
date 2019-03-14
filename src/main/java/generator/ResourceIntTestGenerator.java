package generator;

import service.DateUtilsService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ResourceIntTestGenerator {

    public static void usingBufferedWritter(String entity, String database, String author, String type, String attribute, String variable, String api) throws IOException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String defaulttypes = "", updatedtypes="";
        if (type.equals("String")) {
            defaulttypes = "\"AAAAAAAA\"";
            updatedtypes = "\"BBBBBBBB\"";
        }
        if (type.equals("Long")) {
            defaulttypes = String.valueOf(Math.random());
            updatedtypes = String.valueOf(Math.random());
        }
        if (type.equals("Boolean")) {
            defaulttypes = "true";
            updatedtypes = "false";
        }
        String fileContent = "package de.mms.dp.dashboard.web.rest;\n" +
                "\n" +
                "import de.mms.dp.dashboard.DashboardApp;\n" +
                "import de.mms.dp.dashboard.domain."+entity+";\n" +
                "import de.mms.dp.dashboard.repository."+entity+"Repository;\n" +
                "import de.mms.dp.dashboard.service."+entity+"Service;\n" +
                "import org.junit.Before;\n" +
                "import org.junit.Test;\n" +
                "import org.junit.runner.RunWith;\n" +
                "import org.mockito.MockitoAnnotations;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.boot.test.context.SpringBootTest;\n" +
                "import org.springframework.test.context.junit4.SpringRunner;\n" +
                "import org.springframework.test.web.servlet.MockMvc;\n" +
                "import org.springframework.transaction.annotation.Transactional;\n" +
                "\n" +
                "import javax.persistence.EntityManager;\n" +
                "import java.util.List;\n" +
                "\n" +
                "import static org.assertj.core.api.Assertions.assertThat;\n" +
                "import static org.hamcrest.Matchers.hasItem;\n" +
                "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;\n" +
                "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;\n" +
                "\n" +
                "/**\n"+
                " * Integration test class for "+entity+".\n" +
                " *\n"+
                " * @author "+author+"\n" +
                " * @since "+ DateUtilsService.formatMonth(dtf.format(localDate))+"\n" +
                " */\n" +
                "@RunWith(SpringRunner.class)\n" +
                "@SpringBootTest(classes = DashboardApp.class)\n" +
                "public class "+entity+"ResourceIntTest extends AbstractResourceIntTest {\n" +
                "\n" +
                "    private static final "+type+" DEFAULT_"+attribute.toUpperCase()+" = "+defaulttypes+";\n" +
                "\n" +
                "    private static final "+type+" UPDATED_"+attribute.toUpperCase()+" = "+updatedtypes+";\n" +
                "\n" +
                "    @Autowired\n" +
                "    private "+entity+"Repository "+variable+"Repository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    private "+entity+"Service "+variable+"Service;\n" +
                "\n" +
                "    @Autowired\n" +
                "    private EntityManager em;\n" +
                "\n" +
                "    private MockMvc rest"+entity+"MockMvc;\n" +
                "\n" +
                "    private "+entity+" "+variable+";\n" +
                "\n" +
                "    @Before\n" +
                "    public void setUp() {\n" +
                "        MockitoAnnotations.initMocks(this);\n" +
                "        final "+entity+"Resource "+variable+"Resource = new "+entity+"Resource("+variable+"Repository, "+variable+"Service);\n" +
                "        this.rest"+entity+"MockMvc = buildMockMvc("+variable+"Resource);\n" +
                "        "+variable+"Repository.deleteAll();\n" +
                "        "+variable+" = createEntity();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Create an entity for this test.\n" +
                "     */\n" +
                "    public "+entity+" createEntity() {\n" +
                "        "+entity+" "+variable+" = new "+entity+"();\n" +
                "\n" +
                "        "+variable+"\n" +
                "            ."+attribute+"(DEFAULT_"+attribute.toUpperCase()+");\n" +
                "        return "+variable+";\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    @Transactional\n" +
                "    public void create"+entity+"() throws Exception {\n" +
                "        int databaseSizeBeforeCreate = "+variable+"Repository.findAll().size();\n" +
                "\n" +
                "        // Create the "+variable+"\n" +
                "        rest"+entity+"MockMvc.perform(post(\"/api/"+api+"\")\n" +
                "            .contentType(TestUtil.APPLICATION_JSON_UTF8)\n" +
                "            .content(TestUtil.convertObjectToJsonBytes(defaultExternalTool)))\n" +
                "            .andExpect(status().isCreated())\n" +
                "            .andExpect(jsonPath(\"$."+attribute+"\").value(DEFAULT_"+attribute.toUpperCase()+"));\n" +
                "\n" +
                "        assertThat("+variable+"Repository.findAll().size()).isEqualTo(databaseSizeBeforeCreate + 1);\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    @Transactional\n" +
                "    public void create"+entity+"When"+entity+"AlreadyExists() throws Exception {\n" +
                "        "+variable+"Repository.save("+variable+");\n" +
                "        int databaseSizeBeforeCreate = "+variable+"Repository.findAll().size();\n" +
                "\n" +
                "        // Create the "+variable+"\n" +
                "        rest"+entity+"MockMvc.perform(post(\"/api/"+api+"\")\n" +
                "            .contentType(TestUtil.APPLICATION_JSON_UTF8)\n" +
                "            .content(TestUtil.convertObjectToJsonBytes("+variable+")))\n" +
                "            .andExpect(status().isBadRequest());\n" +
                "\n" +
                "        assertThat("+variable+"Repository.findAll().size()).isEqualTo(databaseSizeBeforeCreate);\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    @Transactional\n" +
                "    public void getAll"+entity+"s() throws Exception {\n" +
                "        "+variable+"Repository.saveAndFlush("+variable+");\n" +
                "        List<"+entity+"> all = "+variable+"Repository.findAll();\n" +
                "\n" +
                "        rest"+entity+"MockMvc.perform(get(\"/api/"+api+"\")\n" +
                "            .accept(TestUtil.APPLICATION_JSON_UTF8))\n" +
                "            .andExpect(status().isOk())\n" +
                "            .andExpect(jsonPath(\"$.[*]."+attribute+"\").value(hasItem(all.get(0).get"+attribute.substring(0,1).toUpperCase() + attribute.substring(1)+"())));\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    @Transactional\n" +
                "    public void update"+entity+"() throws Exception {\n" +
                "        // Initialize the database\n" +
                "        "+variable+"Repository.saveAndFlush("+variable+");\n" +
                "        int databaseSizeBeforeUpdate = "+variable+"Repository.findAll().size();\n" +
                "\n" +
                "        // Update the "+variable+" \n" +
                "        "+entity+" "+variable+" = "+variable+"Repository.findOne("+variable+".getId());\n" +
                "        // Disconnect from session so that the updates on updated"+entity+" are not directly saved in db\n" +
                "        em.detach(updated"+entity+");\n" +
                "        updated"+entity+"\n" +
                "            .name(UPDATED_"+attribute.toUpperCase()+");\n" +
                "\n" +
                "        rest"+entity+"MockMvc.perform(put(\"/api/"+api+"\")\n" +
                "            .contentType(TestUtil.APPLICATION_JSON_UTF8)\n" +
                "            .content(TestUtil.convertObjectToJsonBytes(updated"+entity+")))\n" +
                "            .andExpect(status().isOk())\n" +
                "            .andExpect(jsonPath(\"$.name\").value(UPDATED_"+attribute.toUpperCase()+"));\n" +
                "\n" +
                "        // Validate the "+variable+" in the database\n" +
                "        List<"+entity+"> "+variable+"List = "+variable+"Repository.findAll();\n" +
                "        assertThat("+variable+"List).hasSize(databaseSizeBeforeUpdate);\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    @Transactional\n" +
                "    public void delete"+entity+"() throws Exception {\n" +
                "        // Initialize the database\n" +
                "        "+variable+"Repository.saveAndFlush("+variable+");\n" +
                "        int databaseSizeBeforeDelete = "+variable+"Repository.findAll().size();\n" +
                "\n" +
                "        // Get the "+variable+"\n" +
                "        rest"+entity+"MockMvc.perform(delete(\"/api/"+api+"/{id}\", "+variable+".getId())\n" +
                "            .accept(TestUtil.APPLICATION_JSON_UTF8))\n" +
                "            .andExpect(status().isOk());\n" +
                "\n" +
                "        // Validate the database is empty\n" +
                "        List<"+entity+"> "+variable+"List = "+variable+"Repository.findAll();\n" +
                "        assertThat("+variable+"List).hasSize(databaseSizeBeforeDelete - 1);\n" +
                "    }\n" +
                "}\n";

        BufferedWriter writer = new BufferedWriter(new FileWriter(entity + "ResourceIntTest.java"));
        writer.write(fileContent);
        writer.close();
    }

}
