package mips_emulator.model;

public class RegImmInstruction extends Instruction {
    public final Register rs1;
    public final Register rd;
    public final int immediate;
    public RegImmInstruction(OpCode opCode,  Register rd, Register rs1,int immediate) {
        super(opCode);
        this.rd = rd;
        this.rs1 = rs1;
        this.immediate = immediate;
    }

    @Override
    public String toString() {
        return opCode.isLoadStore() ?
                "%s %s, %d(%s)".formatted(opCode, rd, immediate, rs1) :
                "%s %s, %s, %d".formatted(opCode, rd, rs1, immediate);
    }
}
