package mips_emulator.model;

public class RegImmInstruction extends Instruction {
    public final Register rs1;
    public final Register destination;
    public final int immediate;
    public RegImmInstruction(OpCode opCode, Register rs1, Register destination, int immediate) {
        super(opCode);
        this.rs1 = rs1;
        this.destination = destination;
        this.immediate = immediate;
    }
}
