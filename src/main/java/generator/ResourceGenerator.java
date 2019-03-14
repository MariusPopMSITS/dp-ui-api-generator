package generator;

import service.DateUtilsService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ResourceGenerator {

    public static void usingBufferedWritter(String entity, String variable,  String api, String author, String getAllVariable) throws IOException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        String fileContent = "package de.mms.dp.dashboard.web.rest;\n" +
                "\n" +
                "import com.codahale.metrics.annotation.Timed;\n" +
                "import de.mms.dp.dashboard.domain."+entity+";\n" +
                "import de.mms.dp.dashboard.repository."+entity+"Repository;\n" +
                "import de.mms.dp.dashboard.service."+entity+"Service;\n"+
                "import de.mms.dp.dashboard.web.rest.errors.BadRequestAlertException;\n" +
                "import de.mms.dp.dashboard.web.rest.util.HeaderUtil;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "import org.springframework.http.ResponseEntity;\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "\n" +
                "import java.net.URI;\n" +
                "import java.net.URISyntaxException;\n" +
                "import java.util.List;\n" +
                "\n"+
                "import static de.mms.dp.dashboard.domain."+entity+".ENTITY_NAME;\n"+
                "\n" +
                "/**\n" +
                " * REST controller for managing "+entity+".\n" +
                " *\n"+
                " * @author "+author+"\n" +
                " * @since "+ DateUtilsService.formatMonth(dtf.format(localDate))+"\n" +
                " */\n" +
                "@RestController\n" +
                "@RequestMapping(\"/api\")\n" +
                "public class "+entity+"Resource {\n" +
                "\n" +
                "    private final Logger log = LoggerFactory.getLogger("+entity+"Resource.class);\n" +
                "\n" +
                "    private final "+entity+"Repository "+variable+"Repository;\n" +
                "\n" +
                "    private final "+entity+"Service "+variable+"Service;\n" +
                "\n" +
                "    public "+entity+"Resource("+entity+"Repository "+variable+"Repository, "+entity+"Service "+variable+"Service) {\n" +
                "        this."+variable+"Repository = "+variable+"Repository;\n" +
                "        this."+variable+"Service = "+variable+"Service;\n"+
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * POST  /"+api+" : Create a new "+variable+"\n" +
                "     *\n" +
                "     * @param "+variable+" the "+variable+" to create\n" +
                "     * @return the ResponseEntity with status 201 (Created) and with body the new "+variable+", or with status 400 (Bad Request) if the "+variable+" has already an ID\n" +
                "     * @throws URISyntaxException if the Location URI syntax is incorrect\n" +
                "     */\n" +
                "    @PreAuthorize(\"@securityService.hasRole(T(de.mms.dp.dashboard.security.RoleConstants).DP_GOD\")\n"+
                "    @PostMapping(\"/"+api+"\")\n" +
                "    @Timed\n" +
                "    public ResponseEntity<"+entity+"> create"+entity+"(@RequestBody "+entity+" "+variable+") throws URISyntaxException {\n" +
                "        log.debug(\"REST request to save "+variable+" : {}\", "+variable+");\n" +
                "        if ("+variable+".getId() != null) {\n" +
                "            throw new BadRequestAlertException(\"A new "+variable+" cannot already have an ID\", ENTITY_NAME, \"idexists\");\n" +
                "        }\n" +
                "        "+entity+" result = "+variable+"Service.save("+variable+");\n" +
                "        return ResponseEntity.created(new URI(\"/api/"+api+"/\" + result.getId()))\n" +
                "            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))\n" +
                "            .body(result);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * PUT  /"+api+": Updates an existing "+variable+".\n" +
                "     *\n" +
                "     * @param "+variable+" the "+variable+" to update\n" +
                "     * @return the ResponseEntity with status 200 (OK) and with body the updated "+variable+",\n" +
                "     * or with status 400 (Bad Request) if the "+variable+" is not valid,\n" +
                "     * or with status 500 (Internal Server Error) if the "+variable+" couldn't be updated\n" +
                "     * @throws URISyntaxException if the Location URI syntax is incorrect\n" +
                "     */\n" +
                "    @PreAuthorize(\"@securityService.hasRole(T(de.mms.dp.dashboard.security.RoleConstants).DP_GOD\")\n"+
                "    @PutMapping(\"/"+api+"\")\n" +
                "    @Timed\n" +
                "    public ResponseEntity<"+entity+"> update"+entity+"(@RequestBody "+entity+" "+variable+") throws URISyntaxException {\n" +
                "        log.debug(\"REST request to update "+variable+" : {}\", "+variable+");\n" +
                "        if ("+variable+".getId() == null) {\n" +
                "            return create"+entity+"("+variable+");\n" +
                "        }\n" +
                "        "+entity+" result = "+variable+"Service.update("+variable+");\n" +
                "        return ResponseEntity.ok()\n" +
                "            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, "+variable+".getId().toString()))\n" +
                "            .body(result);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * GET  /"+api+" : get all the "+variable+"s.\n" +
                "     *\n" +
                "     * @return the ResponseEntity with status 200 (OK) and the list of "+getAllVariable+" in body\n" +
                "     */\n" +
                "    @PreAuthorize(\"@securityService.hasRole(T(de.mms.dp.dashboard.security.RoleConstants).DP_GOD\")\n"+
                "    @GetMapping(\"/"+api+"\")\n" +
                "    @Timed\n" +
                "    public List<"+entity+"> getAll"+getAllVariable+"() {\n" +
                "        log.debug(\"REST request to get all "+getAllVariable+"\");\n" +
                "        return "+variable+"Repository.findAll();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * DELETE  /"+api+"/:id : delete the \"id\" "+variable+".\n" +
                "     *\n" +
                "     * @param id the id of the "+variable+" to delete\n" +
                "     * @return the ResponseEntity with status 200 (OK)\n" +
                "     */\n" +
                "    @PreAuthorize(\"@securityService.hasRole(T(de.mms.dp.dashboard.security.RoleConstants).DP_GOD\")\n"+
                "    @DeleteMapping(\"/"+api+"/{id}\")\n" +
                "    @Timed\n" +
                "    public ResponseEntity<Void> delete"+entity+"(@PathVariable Long id) {\n" +
                "        log.debug(\"REST request to delete "+variable+": {}\", id);\n" +
                "        "+variable+"Service.delete(id);\n" +
                "        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();\n" +
                "    }\n"+
                "}\n";

        BufferedWriter writer = new BufferedWriter(new FileWriter(entity + "Resource.java"));
        writer.write(fileContent);
        writer.close();
    }

}
