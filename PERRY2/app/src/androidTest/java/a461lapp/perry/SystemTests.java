package a461lapp.perry;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SystemTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // Create an Alarm and delete it
    @Test
    public void systemTest_1() {

        System.out.println("Create an Alarm and delete it");

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.alarmName), isDisplayed()));
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.createAlarm), withText("+"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.setTime), withText("Set Time"), isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.time_activity_confirm_button), withText("Change Time"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.setDate), withText("Set Date"), isDisplayed()));
        button2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.confirm_date), withText("Set Date"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.checkBox), withText("Set Alarm"), isDisplayed()));
        checkBox.perform(click());

        ViewInteraction tableLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.table),
                        0),
                        isDisplayed()));
        tableLayout.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.deleteButton), isDisplayed()));
        imageButton.perform(click());

    }

    // Creates and Alarm and then edits time and date
    @Test
    public void systemTest_2() {

        System.out.println("Creates and Alarm and then edits time and date");

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.alarmName), isDisplayed()));
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.createAlarm), withText("+"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.setTime), withText("Set Time"), isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.time_activity_confirm_button), withText("Change Time"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.setDate), withText("Set Date"), isDisplayed()));
        button2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.confirm_date), withText("Set Date"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.checkBox), withText("Set Alarm"), isDisplayed()));
        checkBox.perform(click());

        ViewInteraction tableLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.table),
                        0),
                        isDisplayed()));
        tableLayout.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.setTime), withText("Set Time"), isDisplayed()));
        button3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.time_activity_confirm_button), withText("Change Time"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.checkBox), withText("Set Alarm"), isDisplayed()));
        checkBox2.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
