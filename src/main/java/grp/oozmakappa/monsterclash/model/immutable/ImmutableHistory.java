package grp.oozmakappa.monsterclash.model.immutable;

import grp.oozmakappa.monsterclash.model.command.Command;

import java.util.Collections;
import java.util.LinkedList;

public final class ImmutableHistory implements History {
    private final LinkedList<Command> history;

    public ImmutableHistory(LinkedList<Command> history) {
        this.history = history;
    }

    @Override
    public void add(Command newCmd) {
        throw new UnsupportedOperationException("This is immutable!");
    }

    @Override
    public Command removeLast() {
        throw new UnsupportedOperationException("This is immutable!");
    }

    @Override
    public LinkedList<Command> getHistory() {
        return (LinkedList<Command>) Collections.unmodifiableList(history);
    }

    @Override
    public int size() {
        return history.size();
    }

}
