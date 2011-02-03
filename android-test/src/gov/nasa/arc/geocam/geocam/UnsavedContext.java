package gov.nasa.arc.geocam.geocam;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.mock.MockContentResolver;
import android.test.IsolatedContext;
import android.util.Log;

import java.util.HashMap;
import junit.framework.Assert;

public class UnsavedContext extends IsolatedContext {
    private final HashMap<String,UnsavedSharedPreferences> mSharedPrefs =
        new HashMap<String,UnsavedSharedPreferences>();
    private final Context mWrapper;

    public UnsavedContext(Context context) {
        super(context.getContentResolver(), context);
        mWrapper = context;
    }

    @Override
    public Object getSystemService(String name) {
        // Need LayoutInflater to construct our activity's view
        if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
            return mWrapper.getSystemService(name);            
        }

        return super.getSystemService(name);
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (!mSharedPrefs.containsKey(name)) {
            UnsavedSharedPreferences prefs = new UnsavedSharedPreferences(null);
            mSharedPrefs.put(name, prefs);
        }

        return mSharedPrefs.get(name);
    }
}
