package nl.avans.a1.business.library;

public final class SlackAdapter {

    public static boolean sendSlackMessage(String slackId, String message) {
        /*
            aanspreken microservice API om een slack message te sturen
         */
        System.out.println("Sending message to slack ID: "+slackId+", message: "+message);

        return true;
    }
}
