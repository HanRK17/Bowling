import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @Test
    void gutterGame() {
        BowlingGame game = new BowlingGame();

        rollZero(game, 20);

        assertEquals(0, game.score());
    }

    @Test
    void onePin() {
        BowlingGame game = new BowlingGame();

        game.roll(1);
        rollZero(game, 19);

        assertEquals(1, game.score());
    }

    @Test
    void accurateBowlingUnder0() {
        BowlingGame game = new BowlingGame();

        assertThrows(IllegalArgumentException.class, () -> {
            game.roll(-1);
        });
    }

    @Test
    void accurateBowlingOver10() {
        BowlingGame game = new BowlingGame();

        assertThrows(IllegalArgumentException.class, () -> {

            game.roll(11);
        });
    }

    @Test
    void spare() {

        BowlingGame game = new BowlingGame();
        game.roll(7);


        game.roll(3);


        game.roll(1);
        rollZero(game, 17);
        assertEquals(12, game.score());
    }

    @Test
    void strike() {
        BowlingGame game = new BowlingGame();

        game.roll(10);
        game.roll(1);
        game.roll(1);
        rollZero(game,16);

        assertEquals(14, game.score());
    }

    private static void rollZero(BowlingGame game, int count) {
        for(int i=0; i<count; ++i) {
            game.roll(0);
        }
    }

    @Test
    void AccurateBowling2(){
        BowlingGame game = new BowlingGame();

        game.roll(4);
        game.roll(7);
        rollZero(game,18);

        assertThrows(IllegalStateException.class, game:: score);
    }

    @Test
    void PerfectGame(){
        BowlingGame game = new BowlingGame();
        for(int i = 0; i < 12; i++){
            game.roll(10);
        }
        assertEquals(300, game.score());
    }

    @Test
    void AccurateBowling3(){
        BowlingGame game = new BowlingGame();
        rollZero(game,18);
        game.roll(10);
        game.roll(3);
        rollZero(game,1);

        assertEquals(13, game.score());
    }
}