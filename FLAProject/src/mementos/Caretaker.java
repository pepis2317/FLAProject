package mementos;

import java.util.ArrayList;
import java.util.List;


public class Caretaker {
	private final List<Memento> history = new ArrayList<>();
	private final List<Memento> removedHistory = new ArrayList<>();
	public void save(Memento memento) {
        history.add(memento);
        removedHistory.clear();
    }

    public Memento undo() {
        if (history.size() > 1) {
            Memento lastState = history.remove(history.size() - 1);
            removedHistory.add(lastState);
            return history.get(history.size() - 1);
        }
        return null;
    }

    public Memento redo() {
        if (!removedHistory.isEmpty()) {
            Memento lastUndone = removedHistory.remove(removedHistory.size() - 1);
            history.add(lastUndone);
            return lastUndone;
        }
        return null;
    }
    public void reset() {
    	history.clear();
    	removedHistory.clear();
    }

}
