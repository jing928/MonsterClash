package grp.oozmakappa.monsterclash.model.immutable;

import grp.oozmakappa.monsterclash.model.command.Command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MutableHistory implements History {
    private LinkedList<Command> currentHistory;
    private Deque<ImmutableHistory> versions;

    public MutableHistory() {
        this.currentHistory = new LinkedList<>();
        this.versions = new ArrayDeque<>();
        this.versionNum = -1; // Initialize as negative
    }

    @Override
    public synchronized void add(Command newCmd) {
        // Create immutable history with transformer and save to versions stack
        ImmutableHistory next = new ImmutableHistory(Transformer.add(currentHistory, newCmd));
        versions.push(next);
        // Modify current history so it mirrors the most recent version
        currentHistory.add(newCmd);
    }

    @Override
    public synchronized Command removeLast() {
        ImmutableHistory next = new ImmutableHistory(Transformer.removeLast(currentHistory));
        versions.push(next);
        return currentHistory.removeLast();
    }

    @Override
    public LinkedList<Command> getHistory() {
        return currentHistory;
    }

    @Override
    public synchronized int size() {
        return currentHistory.size();
    }

    @Override
    public int getVersionNum() {
        return versionNum;
    }

}
