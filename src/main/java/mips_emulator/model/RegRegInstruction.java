package mips_emulator.model;

public class RegRegInstruction extends Instruction {

    public final Register rs1;
    public final Register rs2;
    public final Register destination;
    public final int opx;

    public RegRegInstruction(OpCode opCode, Register rs1, Register rs2, Register destination, int opx) {
        super(opCode);
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.destination = destination;
        this.opx = opx;
    }
}
