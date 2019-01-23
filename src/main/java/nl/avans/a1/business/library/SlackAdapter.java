package nl.avans.a1.business.library;

import nl.avans.a1.business.Notifier;

public final class SlackAdapter implements Notifier {

    public static boolean sendSlackMessage(String slackEmail, String message) {
        /*
            aanspreken microservice API om een slack message te sturen
         */
        System.out.println("Sending message to slack ID: "+slackEmail+", message: "+message);

        return true;
    }

    @Override
    public void notify(String receiver, String message) {
        SlackAdapter.sendSlackMessage(receiver, message);
    }
}
