package nl.avans.a1.business;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteObserver {
    private List<Notifier> listeners = new ArrayList<>();

    public void notifyListeners(String receiver, String message) {
        for(Notifier listener : listeners) {
            listener.notify(receiver, message);
        }
    }

    public void addListener(Notifier notifier) {
        notifier = new PrivateNotifier(new Notifier() {
            @Override
            public void notify(String receiver, String message) {
            }
        });
        listeners.add(notifier);
    }
}
