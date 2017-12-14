package com.haulmont.masquerade.sys.matchers;

import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.matchers.ArgumentMatchers;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;

import java.util.List;

public final class InstanceOfCases {
    private InstanceOfCases() {
    }

    public static <T, C> DecomposableMatchBuilder1<T, C> hasType(Class<C> clazz) {
        List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
        return new DecomposableMatchBuilder1<>(matchers, 0, new InstanceOfFieldExtractor<>(clazz));
    }
}