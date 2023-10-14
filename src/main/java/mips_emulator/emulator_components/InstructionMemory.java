package mips_emulator.emulator_components;

import mips_emulator.model.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructionMemory {
    private final Map<Integer, Instruction> instructionsMap;
    public static final int INSTRUCTION_SIZE = 4; // bytes


    public InstructionMemory(List<Instruction> instructionList) {
        instructionsMap = new HashMap<>();
        for (int i = 0; i < instructionList.size(); i++) {
            instructionsMap.put(i * INSTRUCTION_SIZE, instructionList.get(i));
        }
    }

    public boolean hasInstructionAt(int address) {
        return instructionsMap.containsKey(address);
    }

    public Instruction getInstructionAt(int address) {
        return instructionsMap.get(address);
    }
}
