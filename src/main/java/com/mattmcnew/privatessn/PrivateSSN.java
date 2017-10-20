package com.mattmcnew.privatessn;

final public class PrivateSSN {
    private final String ssn;

    public PrivateSSN(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "SSN-***-**-****";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateSSN that = (PrivateSSN) o;

        return ssn != null ? ssn.equals(that.ssn) : that.ssn == null;
    }

    @Override
    public int hashCode() {
        return ssn != null ? ssn.hashCode() : 0;
    }

    static {
        NoReallyIWantToReadThePrivateSSN.ssnReader((privateSSN -> privateSSN.ssn));
    }
}
