package gov.nasa.arc.geocam.geocam;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.Map.Entry;

public class UnsavedSharedPreferences implements SharedPreferences {
    private Map mPrefs = null;

    public UnsavedSharedPreferences(Map initialContents) {
        mPrefs = (initialContents == null) ? new HashMap() : initialContents;
    }

    public boolean contains(String key) {
        return mPrefs.containsKey(key);
    }
    public Map<String, ?> getAll() {
        return new HashMap(mPrefs);
    }

    public boolean getBoolean(String key, boolean defValue) {
        Boolean v = (Boolean) mPrefs.get(key);
        return (v != null) ? v : defValue;
    }
    public float getFloat(String key, float defValue) {
        Float v = (Float) mPrefs.get(key);
        return (v != null) ? v : defValue;
    }
    public int getInt(String key, int defValue) {
        Integer v = (Integer) mPrefs.get(key);
        return (v != null) ? v : defValue;
    }
    public long getLong(String key, long defValue) {
        Long v = (Long) mPrefs.get(key);
        return (v != null) ? v : defValue;
    }
    public String getString(String key, String defValue) {
        String v = (String) mPrefs.get(key);
        return (v != null) ? v : defValue;
    }

    private static final Object sContent = new Object();
    private final WeakHashMap<OnSharedPreferenceChangeListener,Object> mListeners = 
        new WeakHashMap<OnSharedPreferenceChangeListener,Object>();

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        mListeners.put(listener, sContent);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        mListeners.remove(listener);
    }
    
    public final class UnsavedEditor implements Editor {
        private final HashMap<String,Object> mModified = new HashMap<String,Object>();
        private boolean mClear = false;

        public Editor putBoolean(String key, boolean value) {
            mModified.put(key, value);
            return this;
        }
        public Editor putFloat(String key, float value) {
            mModified.put(key, value);
            return this;
        }
        public Editor putInt(String key, int value) {
            mModified.put(key, value);
            return this;
        }
        public Editor putLong(String key, long value) {
            mModified.put(key, value);
            return this;
        }
        public Editor putString(String key, String value) {
            mModified.put(key, value);
            return this;
        }
        public Editor remove(String key) {
            mModified.put(key, this);
            return this;
        }
       
        public Editor clear() {
            mClear = true;
            return this;
        }

        public boolean apply() {
            return commit();
        }

        public boolean commit() {
            boolean hasListeners = (mListeners.size() > 0);
            List<String> changedKeys = null;
            Set<OnSharedPreferenceChangeListener> listeners = null;

            if (hasListeners) {
                changedKeys = new ArrayList<String>();
                listeners = new HashSet<OnSharedPreferenceChangeListener>(mListeners.keySet());
            }

            if (mClear) {
                mPrefs.clear();
                mClear = false;
            }

            String k = null;
            Object v = null;

            for (Entry<String, Object> e : mModified.entrySet()) {
                k = e.getKey();
                v = e.getValue();

                if (v == this)
                    mPrefs.remove(k);
                else
                    mPrefs.put(k, v);

                if (hasListeners)
                    changedKeys.add(k);
            }

            mModified.clear();

            if (!hasListeners)
                return true;

            int numModified = changedKeys.size();
            int i;
            String key;

            for (i = 0; i < numModified; i++) {
                key = changedKeys.get(i);
                for (OnSharedPreferenceChangeListener listener : listeners) {
                    if (listener == null)
                        continue;
                    listener.onSharedPreferenceChanged(UnsavedSharedPreferences.this, key);
                }
            }

            return true;
        }
        
    }

    public Editor edit() {
        return new UnsavedEditor();
    }
}