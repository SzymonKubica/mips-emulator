package mips_emulator.emulator_components;

import java.util.Map;
import java.util.stream.Collectors;

public class DataMemory {
    private final Map<Integer, Integer> memory;
    private static final int INSTRUCTION_SIZE = 4; // bytes

    public DataMemory(int sizeInQuadWords) {
        memory = new java.util.HashMap<>();
        for (int i = 0; i < sizeInQuadWords; i++) {
            memory.put(i * INSTRUCTION_SIZE, 0);
        }
    }

    public void storeAt(int address, int rd) {
        memory.put(address, rd);
    }

    public int loadFrom(int address) {
        return memory.get(address);
    }

    @Override
    public String
    toString() {
        return memory.entrySet().stream().filter(entry -> entry.getValue() != 0).map(entry -> "0x%08x".formatted(entry.getKey()) + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
