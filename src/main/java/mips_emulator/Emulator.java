package mips_emulator;

import lombok.extern.java.Log;
import mips_emulator.emulator_components.*;
import mips_emulator.model.*;

import java.util.List;

import static mips_emulator.emulator_components.InstructionMemory.INSTRUCTION_SIZE;

@Log
public class Emulator {
    private final List<Instruction> instructions;

    private final Adder adder;
    private final InstructionMemory instructionMemory;
    private final RegisterFile registerFile;
    private final SignExtender signExtender;
    private final ArithmeticLogicUnit alu;
    private final DataMemory dataMemory;
    private final Multiplexer aluLeftMUX;
    private final Multiplexer aluRightMUX;
    private final Multiplexer memoryAccessMultiplexer;
    private final Multiplexer writeBackMultiplexer;
    private int clockCycle;

    public Emulator(List<Instruction> instructions) {
        this.instructions = instructions;
        adder = new Adder(4); // Increment PC by 4.
        instructionMemory = new InstructionMemory(instructions);
        registerFile = new RegisterFile();
        signExtender = new SignExtender();
        alu = new ArithmeticLogicUnit();
        dataMemory = new DataMemory(1024);
        aluLeftMUX = new Multiplexer();
        aluRightMUX = new Multiplexer();
        memoryAccessMultiplexer = new Multiplexer();
        writeBackMultiplexer = new Multiplexer();
    }


    public void run() {
        clockCycle = 0;
        int pc = 0;
        log.info("Starting execution");
        log.info("Initial program state: %s".formatted(toString()));
        // Figure out the proper condition to detect termination
        while (clockCycle < instructions.size() + 10) {
            clockCycle++;
            aluLeftMUX.resetSignal();
            aluRightMUX.resetSignal();
            memoryAccessMultiplexer.resetSignal();
            writeBackMultiplexer.resetSignal();

            // Stage 1: Instruction Fetch
            Instruction instruction = instructionMemory.getInstructionAt(pc);
            log.info("Executing instruction: %s".formatted(instruction));
            pc = adder.performAdditionTo(pc);

            // Stage 2: Instruction Decode Register Fetch

            int rs1 = 0;
            int rs2 = 0;
            int rd = 0;
            int signExtendedImmediate = 0;
            Register destinationRegister = null;

            if (instruction instanceof JumpInstruction jumpInstruction) {
                switch (instruction.opCode) {
                    case J:
                        pc += (Integer.parseInt(jumpInstruction.target) << 2);
                        log.info(toString());
                        continue;
                    case JR:
                        pc = registerFile.lookupRegisterValue(Register.parseFrom(jumpInstruction.target));
                        log.info(toString());
                        continue;
                }
            }

            if (instruction instanceof RegImmInstruction regImmInstr) {
                rs1 = registerFile.lookupRegisterValue(regImmInstr.rs1);
                rs2 = registerFile.lookupRegisterValue(regImmInstr.rd);
                destinationRegister = regImmInstr.rd;
                signExtendedImmediate = signExtender.extend(regImmInstr.immediate);
            }

            if (instruction instanceof RegRegInstruction regRegInstr) {
                rs1 = registerFile.lookupRegisterValue(regRegInstr.rs1);
                rs2 = registerFile.lookupRegisterValue(regRegInstr.rs2);
                rd = registerFile.lookupRegisterValue(regRegInstr.rd);
                destinationRegister = regRegInstr.rd;
            }

            if (instruction instanceof BranchInstruction branchInstr) {
                rs1 = registerFile.lookupRegisterValue(branchInstr.rs1);
                rs2 = registerFile.lookupRegisterValue(branchInstr.rs2);
                signExtendedImmediate = signExtender.extend(branchInstr.immediate);
            }

            // Stage 3: Execute
            aluLeftMUX.setControlSignal(instruction.opCode.isBranch());
            aluRightMUX.setControlSignal(instruction.hasImmediateOperand());

            int aluLeftInput = aluLeftMUX.selectValue(pc, rs1);
            int aluRightInput = aluRightMUX.selectValue(signExtendedImmediate, rs2);

            int aluResult = alu.performOperation(instruction.opCode, aluLeftInput, aluRightInput);

            //
            int lmd = 0;

            // Stage 4: Memory Access
            switch (instruction.opCode.getInstructionType()) {
                case BRANCH -> {
                    memoryAccessMultiplexer.setControlSignal(rs1 - rs2 == 0);
                    pc = memoryAccessMultiplexer.selectValue(aluResult, pc);
                }
                case STORE -> {
                    log.info("%s".formatted(rs1));
                    log.info("%s".formatted(aluResult));
                    dataMemory.storeAt(aluResult, rs2);
                    log.info(toString());
                    continue;
                }
                case LOAD -> {
                    log.info("%s".formatted(aluResult));
                    lmd = dataMemory.loadFrom(aluResult);
                }
                case OTHER -> {
                }
            }

            // Stage 5: Write Back
            if (instruction.opCode.isLoad()) {
                writeBackMultiplexer.setControlSignal(true);
            }

            int writeBackValue = writeBackMultiplexer.selectValue(lmd, aluResult);
            log.info("%s".formatted(destinationRegister));
            log.info("%s".formatted(writeBackValue));
            registerFile.writeToRegister(destinationRegister, writeBackValue);

            log.info(toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("Program State:\n");
        sb.append("Clock cycle: %d\n".formatted(clockCycle));
        sb.append("Registers:\n");
        sb.append(registerFile);
        sb.append("\n");
        sb.append("Data Memory:\n");
        sb.append(dataMemory);
        return sb.toString();
    }
}
