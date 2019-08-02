package fr.houseofcode.dap.server.ymi;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.ymi.google.GMailService;

/**
 * @author ymi
 */
@RestController
public class EmailController {

    @Autowired
    private GMailService service;

    @RequestMapping
    public Integer displayNbUnreadEmail(@RequestParam String userKey) throws IOException, GeneralSecurityException {

        return service.getNbUnreadEmails(userKey);

    }
}
