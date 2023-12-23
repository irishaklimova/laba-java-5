package com.java-5-Klimova.somepackage;

import com.java-5-Klimova.annotations.AutoInjectable;

public class SomeBean {

    public SomeBean() {}

    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}