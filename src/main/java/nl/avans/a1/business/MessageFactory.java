package nl.avans.a1.business;

public class MessageFactory {

    public Message getMessageImplementation(MessageType messageType) {
        if(messageType.equals(MessageType.EMAIL)) {
            return new EmailMessage();
        }

        if (messageType.equals(MessageType.SLACK)) {
            return new SlackMessage();
        }

        return null;
    }
}
