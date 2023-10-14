package mips_emulator;

import mips_emulator.model.Instruction;

import java.util.List;

public class Emulator {
    private final List<Instruction> instructions;

    private final Adder adder;
    private final Memory instructionMemory;
    private final RegisterFile registerFile;
    private final SignExtender signExtender;
    private final ArithmeticLogicUnit alu;
    private final Memory dataMemory;
    private final Multiplexer aluLeftMUX;
    private final Multiplexer aluRightMUX;
    private final Multiplexer memoryAccessMultiplexer;
    public Emulator(List<Instruction> instructions) {

        this.instructions = instructions;
    }



    public void run() {

    }
}
