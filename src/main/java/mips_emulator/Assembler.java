package mips_emulator;

import mips_emulator.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static mips_emulator.model.OpCode.*;

public class Assembler {

    public static List<Instruction> assemble(String assembly) {
        return Arrays.stream(assembly.split("\n")).map(Assembler::assembleLine).toList();
    }

    public static Instruction assembleLine(String s) {
        List<String> parts = Arrays.stream(s.split("[ \t,)(]")).filter(str -> !str.equals("")).map(String::trim).toList();
        OpCode opCode = OpCode.valueOf(parts.get(0).toUpperCase());
        List<String> operands = parts.subList(1, parts.size());
        return switch (opCode) {
            case ADD, ADDU, AND, SLT, SLTU -> extractRegRegInstruction(opCode, operands);
            case ADDI, ADDIU, ANDI, SLTI, SLTIU, LB, LBU, LH, LHU, LW, SB, SH, SW ->
                    extractRegImmInstruction(opCode, operands);
            case BEQ, BNE -> extractBranchInstruction(opCode, operands);
            case J, JR -> extractJumpInstruction(opCode, operands);
            case BGTZ, BLEZ, JAL, JALR, MFHI, MFLO, MTHI, MTLO, TRAP, DIV, DIVU, MULT, MULTU, NOR, OR, ORI, SLL, SLLV, SRA, SRAV, SRL, SRLV, SUB, SUBU, XOR, XORI, LHI, LLO ->
                    throw new UnsupportedOperationException("Instruction %s not implemented yet".formatted(opCode));
        };
    }

    private static Instruction extractRegImmInstruction(OpCode opCode, List<String> operands) {
        return opCode.isLoadStore() ?
                extractLoadStoreInstruction(opCode, operands) :
                extractRegularRegImmInstruction(opCode, operands);
    }

    private static Instruction extractRegularRegImmInstruction(OpCode opCode, List<String> operands) {
        Register rs1 = Register.parseFrom(operands.get(0).toUpperCase());
        Register rs2 = Register.parseFrom(operands.get(1).toUpperCase());
        int immediate = Integer.parseInt(operands.get(2));
        return new RegImmInstruction(opCode, rs1, rs2, immediate);
    }

    // Load/Store instructions have a special syntax: e.g: LW R1, 100(R2)
    // The brackets are removed during parsing, and we are left with the operands in the following order:
    // [R1, 100, R2]
    private static Instruction extractLoadStoreInstruction(OpCode opCode, List<String> operands) {
        Register rd = Register.parseFrom(operands.get(0).toUpperCase());
        int immediate = Integer.parseInt(operands.get(1));
        Register rs1 = Register.parseFrom(operands.get(2).toUpperCase());
        return new RegImmInstruction(opCode, rd, rs1, immediate);
    }

    private static Instruction extractBranchInstruction(OpCode opCode, List<String> operands) {
        Register rs1 = Register.parseFrom(operands.get(0).toUpperCase());
        Register rs2 = Register.parseFrom(operands.get(1).toUpperCase());
        int immediate = Integer.parseInt(operands.get(2));
        return new BranchInstruction(opCode, rs1, rs2, immediate);
    }

    private static Instruction extractJumpInstruction(OpCode opCode, List<String> operands) {
        return new JumpInstruction(opCode, operands.get(0));
    }

    private static Instruction extractRegRegInstruction(OpCode opCode, List<String> operands) {
        Register destination = Register.parseFrom(operands.get(0).toUpperCase());
        Register rs1 = Register.parseFrom(operands.get(1).toUpperCase());
        Register rs2 = Register.parseFrom(operands.get(2).toUpperCase());
        int opx = operands.size() == 4 ? Integer.parseInt(operands.get(3)) : 0;
        return new RegRegInstruction(opCode, rs1, rs2, destination, opx);
    }
}
