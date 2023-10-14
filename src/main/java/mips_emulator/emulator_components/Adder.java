package mips_emulator.emulator_components;

public class Adder {
    private final int value;

    public Adder(int value) {
        this.value = value;
    }

    public int performAdditionTo(int value) {
        return this.value + value;
    }
}
