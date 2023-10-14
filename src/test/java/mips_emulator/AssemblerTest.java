package mips_emulator;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Collectors;

class AssemblerTest {

    private static final String testAssembly =
            """ 
                ADD R8, R6, R4
                SW R5, 100(R6)
                ADDI R4, R5, 50
                BEQ R5, R7, 25""";

    @Test
    void assemble() {
        String assembly = Assembler.assemble(testAssembly).stream().map(Objects::toString).collect(Collectors.joining("\n"));
        Assertions.assertThat(assembly).isEqualTo(testAssembly);
    }
}