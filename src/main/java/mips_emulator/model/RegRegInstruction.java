package mips_emulator.model;

import lombok.ToString;

public class RegRegInstruction extends Instruction {

    public final Register rs1;
    public final Register rs2;
    public final Register rd;
    public final int opx;

    public RegRegInstruction(OpCode opCode, Register rs1, Register rs2, Register rd, int opx) {
        super(opCode);
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.rd = rd;
        this.opx = opx;
    }

    @Override
    public String toString() {
       return "%s %s, %s, %s".formatted(opCode, rd, rs1, rs2);
    }

    @Override
    public boolean hasImmediateOperand() {
        return false;
    }
}
