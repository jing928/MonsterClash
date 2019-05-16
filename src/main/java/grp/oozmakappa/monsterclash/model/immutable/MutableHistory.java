package grp.oozmakappa.monsterclash.model.immutable;

import grp.oozmakappa.monsterclash.model.command.Command;

import java.util.*;

public class MutableHistory implements History {
    private LinkedList<Command> currentHistory;
    private List<ImmutableHistory> versions;
    private int versionNum; // Can be used as index

    public MutableHistory() {
        this.currentHistory = new LinkedList<>();
        this.versions = new ArrayList<>();
        this.versionNum = -1; // Initialize as negative
    }

    @Override
    public ImmutableHistory getVersion(int versionNum) {
        return versions.get(versionNum);
    }

    public ImmutableHistory getLatestVersion() {
        return getVersion(versionNum);
    }

    @Override
    public void setHistory(LinkedList<Command> history) {
        ImmutableHistory next = new ImmutableHistory(++versionNum, Transformer.duplicate(history));
        versions.add(next);
        this.currentHistory = history;
    }

    @Override
    public Command peekLast() {
        return currentHistory.peekLast();
    }

    @Override
    public synchronized void add(Command newCmd) {
        // Create immutable history with transformer and save to versions stack
        ImmutableHistory next = new ImmutableHistory(++versionNum, Transformer.add(currentHistory, newCmd));
        versions.add(next);
        // Modify current history so it mirrors the most recent version
        currentHistory.add(newCmd);
    }

    @Override
    public synchronized Command removeLast() {
        ImmutableHistory next = new ImmutableHistory(++versionNum, Transformer.removeLast(currentHistory));
        versions.add(next);
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
