package nl.avans.a1.business;


public class PrivateNotifier extends NotifierDecorator {

    /**
     * @param c
     */
    public PrivateNotifier(Notifier c) {
        super(c);
    }

    /**
     * @param receiver
     * @param message
     */
    @Override
    public void notify(String receiver, String message){
        super.notify(receiver, "private. " + message);
    }
}