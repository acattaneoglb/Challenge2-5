package co.mobilemakers.cyoa_withpreferences;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    private final static String KEY_FRAGMENT = "KEY_FRAGMENT";

    public enum DifficultyEnum {
        EASY,
        NORMAL,
        HARD
    }

    MainFragment mMainFragment;

    Fragment shownFragment;

    public void goToAlley(DifficultyEnum difficulty) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, shownFragment = new AlleyFragment())
                .addToBackStack(null)
                .commit();

        ((Levellable)shownFragment).setDifficulty(difficulty);
    }

    public void goToRoom(DifficultyEnum difficulty) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, shownFragment = new RoomFragment())
                .addToBackStack(null)
                .commit();

        ((Levellable)shownFragment).setDifficulty(difficulty);
    }

    public void winGame() {
        FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.container, shownFragment = new WinningFragment())
                .addToBackStack(null)
                .commit();
    }

    public void loseGame() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.container, shownFragment = new LosingFragment())
                .commit();
    }

    public void restartGame() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, shownFragment = mMainFragment)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFragment = new MainFragment();

        FragmentManager fragmentManager = getFragmentManager();
        if (savedInstanceState != null) {
            shownFragment = getFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
        }
        else {
            fragmentManager.beginTransaction()
                    .add(R.id.container, shownFragment = mMainFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getFragmentManager().putFragment(outState, KEY_FRAGMENT, shownFragment);
    }
}
