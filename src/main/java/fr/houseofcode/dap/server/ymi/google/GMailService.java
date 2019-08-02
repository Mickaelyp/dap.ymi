package fr.houseofcode.dap.server.ymi.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

@Service
public class GMailService {

    private static final Logger LOG = LogManager.getLogger();
    /** the default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /** the internal APPLICATION_NAME. */
    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";

    //    /** singleton.*/
    //    private static GMailService instance;
    //
    //    private GMailService() {
    //
    //        LOG.debug("Utilisation Pattern Singleton");
    //    }
    //
    //    /**
    //    * Singleton.
    //    * @return Private constructor to respect a Singleton Pattern.
    //    */
    //
    //    public static GMailService getInstance() {
    //        if (instance == null) {
    //            instance = new GMailService();
    //
    //        }
    //        return instance;
    //    }

    /**
     * Singleton.
     * @return Private constructor to respect a Singleton Pattern.
     * @throws IOException if somethings down.
     * @throws GeneralSecurityException if security failure.
     */

    private Gmail getService(String userKeyName) throws IOException, GeneralSecurityException {

        LOG.debug("Début Get.Service google: récupération lables + nbr de mail avec in:box et filtre unread");

        final NetHttpTransport hTTPTRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(hTTPTRANSPORT, JSON_FACTORY, Utils.getCredentials(hTTPTRANSPORT, userKeyName))
                .setApplicationName(APPLICATION_NAME).build();
        return service;
    }

    /**
     * Create only one new instance of Service Gmail Singleton.
     * @return cf: Singleton
     * @throws IOException if somethings down.
     * @throws GeneralSecurityException if security failure
     */

    public List<Label> getLabels(String userKeyName1) throws IOException, GeneralSecurityException {
        getService(userKeyName1);
        String user = "me";
        //String newLabels = "";
        ListLabelsResponse listResponse = getService(userKeyName1).users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        //        if (labels.isEmpty()) {
        //
        //            newLabels = "No labels found.";
        //        } else {
        //            System.out.println("Labels:");
        //            for (Label label : labels) {
        //
        //                newLabels = newLabels + label.getName() + "\n";
        //            }
        //        }
        LOG.debug("nombre de labels Gmail :" + labels.size());
        return labels;
    }

    /**
     * @param userKey 
     * @return nombre message google.
     * @throws IOException if somethings down.
     * @throws GeneralSecurityException if security failure
     */
    public Integer getNbUnreadEmails(String userKeyName) throws IOException, GeneralSecurityException {
        getService(userKeyName);
        Integer result = 0;

        String user = "me";

        ListMessagesResponse allMessages = getService(userKeyName).users().messages().list(user)
                .setQ("in:inbox is:unread").execute();
        List<Message> messages = allMessages.getMessages();
        if (messages != null) {
            if (!messages.isEmpty()) {
                result = messages.size();
            }
        }

        return result;

    }
}
