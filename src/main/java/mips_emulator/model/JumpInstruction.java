package mips_emulator.model;


import lombok.ToString;

@ToString
public class JumpInstruction extends Instruction {
    public final int target;
    public JumpInstruction(OpCode opCode, int target) {
        super(opCode);
        this.target = target;
    }
}
