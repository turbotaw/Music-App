package com.music.musicapp.util.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class CodeGeneratorTests {
    @Test
    public void testGenerateCodeVerifier() {
        String verifier = CodeGenerator.generateCodeVerifier();

        assertNotNull(verifier);
        assertEquals(43, verifier.length()); // Base64 URL encoded 32 bytes should be 43 characters long without padding
        assertTrue(Pattern.matches("[A-Za-z0-9_-]+", verifier)); // Base64 URL character set
    }

    @Test
    public void testGenerateCodeChallenge() {
        String verifier = "testVerifier";
        String challenge = CodeGenerator.generateCodeChallenge(verifier);

        assertNotNull(challenge);
        // Optionally, assert the length or pattern of the challenge, similar to
        // verifier
    }

}
