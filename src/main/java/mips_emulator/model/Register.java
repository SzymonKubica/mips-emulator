package mips_emulator.model;

public enum Register {
    R0,
    R1,
    R2,
    R3,
    R4,
    R5,
    R6,
    R7,
    R8,
    R9,
    R10,
    R11,
    R12,
    R13,
    R14,
    R15,
    R16,
    R17,
    R18,
    R19,
    R20,
    R21,
    R22,
    R23,
    R24,
    R25,
    R26,
    R27,
    R28,
    R29,
    R30,
    R31;

    public static Register parseFrom(String str) {
        switch (str.toLowerCase()) {
            case "$zero": return R0;
            case "$gp": return R28;
            case "$sp": return R29;
            case "$fp": return R30;
            case "$ra": return R31;
        }
        try {
            return Register.valueOf(str);
        } catch (IllegalArgumentException e) {
            // The register might be in the form $1, $2, $zero
            if (str.startsWith("$")) {
                return Register.valueOf("R" + str.substring(1));
            } else {
                throw new IllegalArgumentException("Invalid register name: %s".formatted(str));
            }
        }
    }
}
