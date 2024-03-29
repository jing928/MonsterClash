package grp.oozmakappa.monsterclash.model.immutable;

import grp.oozmakappa.monsterclash.model.command.Command;

import java.util.LinkedList;

public interface History {
    void add(Command newCmd);

    Command removeLast();

    LinkedList<Command> getHistory();

    int size();

    int getVersionNum();

    Command peekLast();

    ImmutableHistory getVersion(int versionNum);

    ImmutableHistory getLatestVersion();

    void setHistory(LinkedList<Command> history);

}
