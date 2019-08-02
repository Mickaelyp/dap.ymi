package fr.houseofcode.dap.server.ymi;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.ymi.google.CalendarService;

/**
 * @author ymi
 *
 */
@RestController
public class EventController {

    @Autowired
    private CalendarService service;

    @RequestMapping("/calendar/event/")
    public String displayNbUnreadEmail(String userKeyName) throws IOException, GeneralSecurityException {

        return service.getNextEvent(userKeyName);

    }
}
