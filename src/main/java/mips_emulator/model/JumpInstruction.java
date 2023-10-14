package mips_emulator.model;



public class JumpInstruction extends Instruction {
    public final String target;
    public JumpInstruction(OpCode opCode, String target) {
        super(opCode);
        this.target = target;
    }


    @Override
    public boolean hasImmediateOperand() {
        return false;
    }
}
