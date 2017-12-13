package com.haulmont.masquerade.sys.matchers;

import com.leacox.motif.extract.Extractor1;

import java.util.Optional;

public class InstanceOfExtractor<T, C> implements Extractor1<T, C> {
    private Class<C> clazz;

    public InstanceOfExtractor(Class<C> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<C> unapply(T t) {
        if (t != null && clazz.isAssignableFrom(t.getClass())) {
            return Optional.of(clazz.cast(t));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Class getExtractorClass() {
        return clazz;
    }
}