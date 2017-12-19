package com.haulmont.masquerade.sys.matchers;

import com.leacox.motif.extract.FieldExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstanceOfFieldExtractor<T, C> implements FieldExtractor<T> {
    private final InstanceOfExtractor<T, C> extractor;

    public InstanceOfFieldExtractor(Class<C> clazz) {
        this.extractor = new InstanceOfExtractor<>(clazz);
    }

    @Override
    public Optional<List<Object>> unapply(T value) {
        Optional<C> opt = extractor.unapply(value);
        if (!opt.isPresent()) {
            return Optional.empty();
        }

        List<Object> fields = new ArrayList<>();
        fields.add(opt.get());

        return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
        return extractor.getExtractorClass();
    }
}