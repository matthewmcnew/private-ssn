package com.mattmcnew.privatessn;

import java.util.function.Function;

public class NoReallyIWantToReadThePrivateSSN {
    private static Function<PrivateSSN, String> ssnReader;

    public String iActuallyWantToReadSSN(PrivateSSN privateSSN) {
        return ssnReader.apply(privateSSN);
    }

    static void ssnReader(Function<PrivateSSN, String> ssnReader) {
        NoReallyIWantToReadThePrivateSSN.ssnReader = ssnReader;
    }
}
