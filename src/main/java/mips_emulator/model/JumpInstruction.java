package mips_emulator.model;



public class JumpInstruction extends Instruction {
    public final int target;
    public JumpInstruction(OpCode opCode, int target) {
        super(opCode);
        this.target = target;
    }


    @Override
    public boolean hasImmediateOperand() {
        return false;
    }
}
