package mips_emulator.emulator_components;

import lombok.Setter;

public class Multiplexer {
    @Setter
    private boolean controlSignal;

    public int selectValue(int value1, int value2) {
        return controlSignal ? value1 : value2;
    }

}
