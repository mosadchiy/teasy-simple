package com.wiley.assertions;

import com.wiley.holders.TestParamsHolder;
import com.wiley.screenshots.Screenshoter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import java.util.LinkedList;
import java.util.List;

/**
 * User: ntyukavkin
 * Date: 11.04.2018
 * Time: 14:39
 */
public class SoftAssert extends Assertion {

    private final List<String> list = new LinkedList<>();

    @Override
    protected void doAssert(IAssert<?> assertCommand) {
        onBeforeAssert(assertCommand);
        try {
            assertCommand.doAssert();
            onAssertSuccess(assertCommand);
        } catch (AssertionError error) {
            add(error.getMessage());
            onAssertFailure(assertCommand, error);
        }
    }

    public void addWithScreenshot(String error) {
        list.add(error);
        new Screenshoter().takeScreenshot(error, TestParamsHolder.getTestName());
    }

    public void add(String error) {
        list.add(error);
    }

    public void assertAll() {
        if (hasErrors()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            for (String ae : list) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(ae);
            }
            throw new AssertionError(sb.toString());
        }
    }

    private boolean hasErrors() {
        return !list.isEmpty();
    }
}