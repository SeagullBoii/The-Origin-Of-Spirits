package net.seagullboi.originofspirits.util;

import net.minecraft.util.IStringSerializable;

public enum AbyssalSpawnerEntities implements IStringSerializable {

    NULL("null"),
    JELLYFISH("jellyfish"),
    BOX_JELLYFISH("box_jellyfish"),
    ELECTRIC_SURGEONFISH("electric_surgeonfish"),
    ELECTRIC_EEL("electric_eel");

    private final String entityName;

    private AbyssalSpawnerEntities(String name) {
        this.entityName = name;
    }

    public String toString() {
        return this.getString();
    }

    public String getString() {
        return this.entityName;
    }

}
