package com.wiley;

import com.wiley.holders.AssertionsHolder;
import org.testng.ITestResult;

/**
 * User: ntyukavkin
 * Date: 10.04.2018
 * Time: 14:57
 */
public class WebServiceBaseTest extends BaseTest {

    protected void setThrowable(ITestResult testResult, String methodType) {
        final Throwable testResultThrowable = testResult.getThrowable();
        String message = testResultThrowable.getMessage() != null
                ? testResultThrowable.getMessage()
                : testResultThrowable.getCause().getMessage();

        if (message == null) {
            message = methodType + " failed";
        }

        AssertionsHolder.softAssert().add(message);
    }
}
