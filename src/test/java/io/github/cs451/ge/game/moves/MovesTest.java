package io.github.cs451.ge.game.moves;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MovesTest {

    @Test
    public void testAttackMove() {
        AttackMove move = new AttackMove(null, null, null);
        assertTrue(move.mustBeTaken());
    }

    @Test
    public void testNormalMove() {
        NormalMove move = new NormalMove(null, null);
        assertFalse(move.mustBeTaken());
    }
}
