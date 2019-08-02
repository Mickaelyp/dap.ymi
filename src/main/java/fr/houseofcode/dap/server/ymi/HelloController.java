package fr.houseofcode.dap.server.ymi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ymi
 *
 */

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Bienvenue sur Spring Boot!";
    }

}
