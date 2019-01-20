package nl.avans.a1.business.library;

public class EmailAdapter {

    public static boolean sendEmailMessage(String emailAddress, String subject, String message) {
        /*
            aanspreken microservice API om een slack message te sturen
         */
        System.out.println("Sending email to: "+emailAddress +", subject: "+subject+", message: "+message);

        return true;
    }
}
