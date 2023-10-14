package mips_emulator.emulator_components;

import mips_emulator.model.Register;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class RegisterFile {
    private final Map<Register, Integer> registerFile;
    public RegisterFile() {
       registerFile = Arrays.stream(Register.values()).collect(Collectors.toMap(register -> register, register -> 0));
    }

    public int lookupRegisterValue(Register register) {
        return registerFile.get(register);
    }

    public void writeToRegister(Register register, int writeBackValue) {
        registerFile.put(register, writeBackValue);
    }

    @Override
    public String
    toString() {
        return registerFile.entrySet().stream().filter(entry -> entry.getValue() != 0).map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
