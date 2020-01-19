package nl.avans.a1.business;

public class PrivateNotifier extends NotifierDecorator {

    public PrivateNotifier(Notifier c) {
        super(c);
    }

    @Override
    public void notify(String receiver, String message){
        super.notify("This message is private: "+ receiver, message);
    }
}