package pers.tuershen.nbtedit.command;

import java.util.UUID;

public interface ListenerValve {

    boolean value(UUID uuid);

    boolean valueTile(UUID uuid);

}
