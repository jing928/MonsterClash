package grp.oozmakappa.monsterclash.model.immutable;

import grp.oozmakappa.monsterclash.model.command.Command;

import java.util.LinkedList;

public final class ImmutableHistory implements History {
    private final int versionNum;
    private final LinkedList<Command> history;

    public ImmutableHistory(int versionNum, LinkedList<Command> history) {
        this.versionNum = versionNum;
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
        // Create a new list reference to keep the original list intact
        return new LinkedList<>(history);
    }

    @Override
    public int size() {
        return history.size();
    }

    @Override
    public int getVersionNum() {
        return versionNum;
    }

    @Override
    public Command peekLast() {
        throw new UnsupportedOperationException("Unsupported.");
    }

    @Override
    public ImmutableHistory getLatestVersion() {
        return this;
    }

}
