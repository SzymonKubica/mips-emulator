package mips_emulator.model;

public abstract class Instruction {
    public final OpCode opCode;

    protected Instruction(OpCode opCode) {
        this.opCode = opCode;
    }
}
