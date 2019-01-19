package nl.avans.a1.business;

public class MessageServiceFactory {

    public static MessageService getMessageImplementation(String channel) {
        if(channel.equals("email")) {
            return new MessageEmailService();
        }

        if (channel.equals("slack")) {
            return new MessageSlackService();
        }

        return null;
    }
}
