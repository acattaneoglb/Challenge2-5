package co.mobilemakers.cyoa_withpreferences;

/**
 * Can set a difficulty level
 *
 * Created by ariel.cattaneo on 30/01/2015.
 */
public interface Levellable {
    final static float LOW_CHANCE = 0.1f;
    final static float NORMAL_CHANCE = 0.25f;
    final static float HIGH_CHANCE = 0.4f;

    public void setDifficulty(MainActivity.DifficultyEnum level);
}
