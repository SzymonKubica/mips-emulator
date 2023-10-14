package mips_emulator.emulator_components;

import mips_emulator.model.OpCode;

public class ArithmeticLogicUnit {
    public int performOperation(OpCode opCode, int aluLeftInput, int aluRightInput) {
        return switch (opCode) {
            case ADD, ADDU, ADDI, ADDIU -> aluLeftInput + aluRightInput;
            case AND, ANDI -> aluLeftInput & aluRightInput;
            case DIV, DIVU -> aluLeftInput / aluRightInput;
            case MULT, MULTU -> aluLeftInput * aluRightInput;
            case NOR -> ~(aluLeftInput | aluRightInput);
            case OR, ORI -> aluLeftInput | aluRightInput;
            case SLL, SLLV -> aluLeftInput << aluRightInput;
            case SRA, SRAV -> aluLeftInput >> aluRightInput;
            case SRL, SRLV -> aluLeftInput >>> aluRightInput;
            case SUB, SUBU -> aluLeftInput - aluRightInput;
            case XOR, XORI -> aluLeftInput ^ aluRightInput;
            // In case of load and store we use ALU to compute the right address.
            case SW, LW -> aluLeftInput + aluRightInput;
            default -> throw new IllegalStateException("Not an arithmetic / logical operation: " + opCode);
        };
    }
}
