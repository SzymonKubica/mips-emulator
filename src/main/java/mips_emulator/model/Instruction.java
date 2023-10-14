package mips_emulator.model;

import lombok.ToString;

@ToString
public abstract class Instruction {
    public final OpCode opCode;

    protected Instruction(OpCode opCode) {
        this.opCode = opCode;
    }
}
