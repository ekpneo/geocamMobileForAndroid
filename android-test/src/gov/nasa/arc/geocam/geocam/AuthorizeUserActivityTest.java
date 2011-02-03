package gov.nasa.arc.geocam.geocam;

import android.test.ActivityUnitTestCase;
import android.test.mock.MockContext;
import android.test.suitebuilder.annotation.MediumTest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import gov.nasa.arc.geocam.geocam.GeoCamMobile;
import gov.nasa.arc.geocam.geocam.AuthorizeUserActivity;

public class AuthorizeUserActivityTest 
    extends ActivityUnitTestCase<AuthorizeUserActivity> 
{
    private Intent mStartIntent;
    private Context mTargetContext;

    public AuthorizeUserActivityTest() {
        super(AuthorizeUserActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mStartIntent = new Intent(Intent.ACTION_MAIN);
        mTargetContext = getInstrumentation().getTargetContext();
    }

    /* Make sure the activity starts and that it doesn't think we're
       already authorized to use the activity. This is the precondition
       for all of the following tests */
    @MediumTest
    public void testPreconditions() {
        assertFalse(GeoCamMobile.SETTINGS_BETA_TEST_CORRECT.equals(""));
        assertNotNull(mTargetContext);

        UnsavedContext context = new UnsavedContext(mTargetContext);
        setActivityContext(context);
        startActivity(mStartIntent, null, null);

        assertNotNull(getActivity());
        assertFalse(getActivity().userIsAuthorized());
    }

    /* Make sure that the commitKey function actually commits the key to
       the sharedPreferences object. */
    @MediumTest
    public void testCommit() {
        final String testKey = "testing key";
     
        UnsavedContext context = new UnsavedContext(mTargetContext);
        setActivityContext(context);
        startActivity(mStartIntent, null, null);

        AuthorizeUserActivity activity = getActivity();
        activity.commitKey(testKey);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String storedKey = prefs.getString(GeoCamMobile.SETTINGS_BETA_TEST_KEY, "");
        assertTrue(testKey.equals(storedKey));
    }

    /* Make sure that when the right key is in the shared preferences, that the
       userIsAuthorized function agrees that it's the right key and that we're
       allowed to proceed */
    @MediumTest
    public void testAuthorized() {
        UnsavedContext context = new UnsavedContext(mTargetContext);
        setActivityContext(context);
        startActivity(mStartIntent, null, null);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor
            .putString(GeoCamMobile.SETTINGS_BETA_TEST_KEY, GeoCamMobile.SETTINGS_BETA_TEST_CORRECT)
            .commit();

        AuthorizeUserActivity activity = getActivity();
        assertTrue(activity.userIsAuthorized());
    }

    /* Test that entering the correct key into the edit box of the view and
       clicking the button, that the activity successfully stores the key,
       thinks the user is authorized and redirects to the next activity. */
    @MediumTest
    public void testCorrectKey() {
        UnsavedContext context = new UnsavedContext(mTargetContext);
        setActivityContext(context);
        startActivity(mStartIntent, null, null);

        AuthorizeUserActivity activity = getActivity();

        Button button = (Button) activity.findViewById(R.id.authorize_user_continue_button);
        EditText key = (EditText) activity.findViewById(R.id.authorize_user_key_entry);

        assertNotNull(button);
        assertNotNull(key);

        key.setText(GeoCamMobile.SETTINGS_BETA_TEST_CORRECT);
        button.performClick();

        assertTrue(activity.userIsAuthorized());
        assertNotNull(getStartedActivityIntent());
        assertTrue(isFinishCalled());        
    }

    /* Make sure that when the activity is started fresh, it does not redirect
       and that the the user is not authorized.  */
    @MediumTest
    public void testNotFinished() {
        UnsavedContext context = new UnsavedContext(mTargetContext);
        setActivityContext(context);
        startActivity(mStartIntent, null, null);

        assertFalse(getActivity().userIsAuthorized());
        assertNull(getStartedActivityIntent());
        assertFalse(isFinishCalled());
    }

    /* Make sure that when the correct key is stored already, that the activity
       redirects to the next activity.  This will be the most common behavior. */
    @MediumTest
    public void testFinishedCalled() {
        UnsavedContext context = new UnsavedContext(mTargetContext);
        setActivityContext(context);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor
            .putString(GeoCamMobile.SETTINGS_BETA_TEST_KEY, GeoCamMobile.SETTINGS_BETA_TEST_CORRECT)
            .commit();

        startActivity(mStartIntent, null, null);

        assertTrue(getActivity().userIsAuthorized());
        assertNotNull(getStartedActivityIntent());
        assertTrue(isFinishCalled());
    }
}
