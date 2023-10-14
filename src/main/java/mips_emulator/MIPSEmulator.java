package mips_emulator;

import mips_emulator.model.Instruction;

import java.util.List;

public class MIPSEmulator {
    public static void main(String[] args) {
        List<Instruction> instructions = Assembler.assemble("""
                ADDI R4, R5, 50
                ADDI R3, R5, 123
                ADD R5, R4, R3
                SW R5, 100(R0)
                LW R6, 100(R0)
                """);

        List<Instruction> instructions2 = Assembler.assemble("""
                	addiu	$sp, $sp, -24
                	sw	$ra, 20($sp)                    
                	sw	$fp, 16($sp)                    
                	add	$fp, $sp, $zero
                	sw	$zero, 12($fp)
                	addiu	$1, $zero, 123
                	sw	$1, 8($fp)
                	addiu	$1, $zero, 456
                	sw	$1, 4($fp)
                	lw	$1, 8($fp)
                	lw	$2, 4($fp)
                	addu	$1, $1, $2
                	sw	$1, 0($fp)
                	lw	$1, 8($fp)
                	addiu	$1, $1, 1
                	sw	$1, 8($fp)
                	addiu	$2, $zero, 0
                	add	$sp, $fp, $zero
                	lw	$fp, 16($sp)                    
                	lw	$ra, 20($sp)                    
                	addiu	$sp, $sp, 24
                	jr	$ra
                                
                """);
        Emulator emulator = new Emulator(instructions2);
        emulator.run();
    }
}