package mips_emulator;

import mips_emulator.model.Instruction;

import java.util.List;

public class MIPSEmulator {
    public static void main(String[] args) {
        List<Instruction> instructions = Assembler.assemble("""
                ADDI R4, R5, 50
                ADDI R3, R5, 123
                ADD R5, R4, R3
                SW R5, 100(R0)
                LW R6, 100(R0)
                """);
        System.out.println(instructions.stream().map(Instruction::toString).reduce("", (a, b) -> a + b + "\n"));
        Emulator emulator = new Emulator(instructions);
        emulator.run();
    }
}