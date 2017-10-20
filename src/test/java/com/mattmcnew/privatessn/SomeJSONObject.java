package com.mattmcnew.privatessn;

public class SomeJSONObject {
    private PrivateSSN socialSecurityNum;

    public SomeJSONObject() {
    }

    public SomeJSONObject(PrivateSSN socialSecurityNum) {
        this.socialSecurityNum = socialSecurityNum;
    }

    public PrivateSSN getSocialSecurityNum() {
        return socialSecurityNum;
    }
}
