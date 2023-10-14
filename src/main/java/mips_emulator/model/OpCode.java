package mips_emulator.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public enum OpCode {
    // Arithmetic and Logical Instructions
    ADD(0b100000),
    ADDU(0b100001),
    ADDI(0b001000),
    ADDIU(0b001001),
    AND(0b100100),
    ANDI(0b001100),
    DIV(0b011010),
    DIVU(0b011011),
    MULT(0b011000),
    MULTU(0b011001),
    NOR(0b100111),
    OR(0b100101),
    ORI(0b001101),
    SLL(0b000000),
    SLLV(0b000100),
    SRA(0b000011),
    SRAV(0b000111),
    SRL(0b000010),
    SRLV(0b000110),
    SUB(0b100010),
    SUBU(0b100011),
    XOR(0b100110),
    XORI(0b001110),

    // Constant-Manipulating Instructions
    LHI(0b011001),
    LLO(0b011000),

    // Comparison Instructions
    SLT(0b101010),
    SLTU(0b101001),
    SLTI(0b001010),
    SLTIU(0b001001),

    // Branch Instructions
    BEQ(0b000100),
    BGTZ(0b000111),
    BLEZ(0b000110),
    BNE(0b000101),

    // Jump Instructions
    J(0b000010),
    JAL(0b000011),
    JALR(0b001001),
    JR(0b001000),

    // Load/Store Instructions
    LB(0b100000),
    LBU(0b100100),
    LH(0b100001),
    LHU(0b100101),
    LW(0b100011),
    SB(0b101000),
    SH(0b101001),
    SW(0b101011),
    // Data Movement Instructions
    MFHI(0b010000),
    MFLO(0b010010),
    MTHI(0b010001),
    MTLO(0b010011),
    // Exception and Interrupt Instructions
    TRAP(0b011010);
    public final short value;

    private OpCode(int value) {
        this.value = (short) value;
    }

    public static Optional<OpCode> fromValue(short value) {
        return Arrays.stream(values()).findAny().filter(opCode -> opCode.value == value);
    }

    public boolean isLoadStore() {
        return isStore() || isLoad();
    }

    public boolean isStore() {
        return Set.of(SB, SH, SW).contains(this);
    }

    public boolean isLoad() {
        return Set.of(LB, LBU, LH, LHU, LW).contains(this);
    }

    public boolean isBranch() {
        return Set.of(BEQ, BGTZ, BLEZ, BNE).contains(this);
    }

    public InstructionType getInstructionType() {
        if (isLoad()) {
            return InstructionType.LOAD;
        } else if (isStore()) {
            return InstructionType.STORE;
        } else if (isBranch()) {
            return InstructionType.BRANCH;
        } else {
            return InstructionType.OTHER;
        }
    }
}
