package com.gd.puzzle;

import static java.lang.System.getProperty;
import static java.lang.System.setIn;

import java.io.ByteArrayInputStream;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PuzzleMainClassTest extends TestCase {
    private MockInputStream mockInput = new MockInputStream();

    @Test
    public void testPlayGame() throws Exception {
        mockInput.provideTextLines("GD", "1", "1", "2", "P", "K", "A", "H", "S", "2", "P", "K", "A", "H", "S", "3", "4", "Cisco", "hero", "FLY", "97", "78",
                                   "88", "95", "Flash", "3", "2", "P", "K", "H", "A", "P", "P", "5", "6");
        PuzzleMainClass.main(null);
    }

    private class MockInputStream {
        private ByteArrayInputStream testIn;

        public void provideTextLines(String... lines) {
            provideText(joinLines(lines));
        }

        void provideText(String text) {
            setIn(new ByteArrayInputStream(text.getBytes()));
        }

        private String joinLines(String[] lines) {
            StringBuilder sb = new StringBuilder();
            for (String line : lines)
                sb.append(line)
                  .append(getProperty("line.separator"));
            return sb.toString();
        }

    }
}
