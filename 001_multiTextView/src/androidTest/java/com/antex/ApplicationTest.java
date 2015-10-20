package com.antex;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    //方法必须以test开头，不然android studio无法识别它为测试代码
    public void test_addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 1);
    }

}