package org.example;

public class Main {
    public static void main(String[] args) {
        List<Instruction> instructions = Assembler.assmeble("""
                ADD R8, R6, R4
                """);
        Emulator.execute(instructions);

    }
}