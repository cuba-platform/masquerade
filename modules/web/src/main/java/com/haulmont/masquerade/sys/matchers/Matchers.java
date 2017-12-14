package com.haulmont.masquerade.sys.matchers;

import com.codeborne.selenide.Condition;
import com.leacox.motif.matching.FluentMatching;

import java.util.function.Consumer;

import static com.leacox.motif.Motif.match;

public final class Matchers {
    private Matchers() {
    }

    public static void matchAll(Condition[] conditions, Consumer<FluentMatching<Condition>> matcher) {
        for (Condition c : conditions) {
            FluentMatching<Condition> matching = match(c);
            matcher.accept(matching);
        }
    }
}