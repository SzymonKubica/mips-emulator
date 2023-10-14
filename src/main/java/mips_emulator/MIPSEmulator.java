package mips_emulator;

import mips_emulator.model.Instruction;

import java.util.List;

public class MIPSEmulator {
    public static void main(String[] args) {
        List<Instruction> instructions = Assembler.assemble("""
                ADD R8, R6, R4
                SW R5, 100(R6)
                ADDI R4, R5, 50
                """);
        System.out.println(instructions.stream().map(Instruction::toString).reduce("", (a, b) -> a + b + "\n"));
        Emulator emulator = new Emulator(instructions);
        emulator.run();
    }
}