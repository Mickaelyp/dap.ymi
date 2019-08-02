package fr.houseofcode.dap.server.ymi.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

@Service
public class CalendarService {

    /** the default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /** the internal APPLICATION_NAME. */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";

    /**
     * @return appel le calendrier Google.
     * @throws IOException if somethings down.
     * @throws GeneralSecurityException if security failure.
     */

    public Calendar getService(String userKeyName1) throws IOException, GeneralSecurityException {
        final NetHttpTransport hTTPTRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(hTTPTRANSPORT, JSON_FACTORY,
                Utils.getCredentials(hTTPTRANSPORT, userKeyName1)).setApplicationName(APPLICATION_NAME).build();
        return service;
    }

    /**
    * Create only one new instance of Service Calendar Singleton.
    * @return cf: Singleton
    * @throws IOException if somethings down.
    * @throws GeneralSecurityException if security failure.
    * If modifying these scopes, delete your previously saved tokens/ folder.
    */

    // Load client secrets.

    @RequestMapping("/prochainevent")
    public String getNextEvent(String userKeyName) throws IOException, GeneralSecurityException {
        getService(userKeyName);
        String returnEvent;

        // Build a new authorized API client service.

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKeyName).events().list("primary").setMaxResults(1).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            returnEvent = "No upcoming events found.";
        } else {
            returnEvent = "Next Events:";

            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();

                if (start == null) {
                    start = event.getStart().getDate();
                }
                returnEvent = returnEvent + event.getSummary() + start;
            }
        }

        return returnEvent;

    }

}
