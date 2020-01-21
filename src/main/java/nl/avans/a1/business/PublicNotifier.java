package nl.avans.a1.business;

public class PublicNotifier extends NotifierDecorator {

    public PublicNotifier(Notifier c) {
        super(c);
    }

    @Override
    public void notify(String receiver, String message){
        super.notify(receiver, "public. " + message);
    }
}