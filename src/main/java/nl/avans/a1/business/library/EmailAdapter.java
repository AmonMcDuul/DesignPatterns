package nl.avans.a1.business.library;

import nl.avans.a1.business.Notifier;

public class EmailAdapter implements Notifier {

    public static boolean sendEmailMessage(String emailAddress, String subject, String message) {
        /*
            aanspreken microservice API om een slack message te sturen
         */
        System.out.println("Sending email to: "+emailAddress +", subject: "+subject+", message: "+message);

        return true;
    }

    @Override
    public void notify(String receiver, String message) {
        EmailAdapter.sendEmailMessage(receiver, "There is a new note!", message);
    }
}
