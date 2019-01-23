package nl.avans.a1.business;

public class MessageServiceFactory {

    public static MessageService getMessageImplementation(MessageType channel) {
        if(channel.equals(MessageType.EMAIL)) {
            return new MessageEmailService();
        }

        if (channel.equals(MessageType.SLACK)) {
            return new MessageSlackService();
        }

        return null;
    }
}
