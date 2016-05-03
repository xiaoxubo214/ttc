package wind.mj.com.board.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by wind on 16/5/3.
 */
public class Utils {

    /**
     * 替换fragment
     *
     * @param activity
     * @param fragment
     * @param contentId
     */
    public static void replaceFragment(Activity activity, Fragment fragment, int contentId, boolean shouldRmove) {
        if (fragment == null) return;
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (shouldRmove)
            transaction.remove(fragment);
        transaction.replace(contentId, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}
