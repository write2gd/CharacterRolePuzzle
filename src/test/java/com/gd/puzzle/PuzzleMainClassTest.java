package com.gd.puzzle;

import static java.lang.System.getProperty;
import static java.lang.System.setIn;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PuzzleMainClassTest extends TestCase {
    private MockInputStream mockInput = new MockInputStream();

    /**
     * This test case will test all the scenarios available in the game
     *
     * @throws Exception
     */
    @Test
    public void testPlayGame() {
        mockInput.provideInputs("GD", "1", "1", "2", "P", "K", "A", "H", "S", "2", "P", "K", "A", "H", "S", "3", "4", "Cisco", "hero", "FLY", "97", "78", "88",
                                "95", "Flash", "3", "2", "P", "K", "H", "A", "P", "P", "5", "6");
        try {
            PuzzleMainClass.main(null);
        } catch (Exception e) {
            Assert.fail("There are Exceptions" + e.getMessage());
        }
    }

    private static class MockInputStream {
        public void provideInputs(String... lines) {
            provideInput(joinInputLines(lines));
        }

        void provideInput(String text) {
            setIn(new ByteArrayInputStream(text.getBytes()));
        }

        private String joinInputLines(String[] lines) {
            StringBuilder sb = new StringBuilder();
            for (String line : lines)
                sb.append(line)
                  .append(getProperty("line.separator"));
            return sb.toString();
        }

    }
}
