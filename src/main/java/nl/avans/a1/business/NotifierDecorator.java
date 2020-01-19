package nl.avans.a1.business;

public class NotifierDecorator implements Notifier {

    protected Notifier notifier;

    public NotifierDecorator(Notifier c){
        this.notifier=c;
    }

    @Override
    public void notify(String receiver, String message) {

    }
}
