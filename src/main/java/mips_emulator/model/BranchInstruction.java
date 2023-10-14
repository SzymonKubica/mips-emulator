package mips_emulator.model;


public class BranchInstruction extends Instruction {
    public final Register rs1;
    public final Register rs2;
    public final int immediate;

    @Override
    public String toString() {
        return "%s %s, %s, %d".formatted(opCode, rs1, rs2, immediate);
    }

    public BranchInstruction(OpCode opCode, Register rs1, Register rs2, int immediate) {
        super(opCode);
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.immediate = immediate;
    }
}
