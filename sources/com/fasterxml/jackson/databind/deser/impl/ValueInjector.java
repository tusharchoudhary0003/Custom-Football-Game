package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.IOException;

public class ValueInjector extends Std {
    protected final Object _valueId;

    public ValueInjector(PropertyName propName, JavaType type, AnnotatedMember mutator, Object valueId) {
        super(propName, type, null, mutator, PropertyMetadata.STD_OPTIONAL);
        this._valueId = valueId;
    }

    public Object findValue(DeserializationContext context, Object beanInstance) throws JsonMappingException {
        return context.findInjectableValue(this._valueId, this, beanInstance);
    }

    public void inject(DeserializationContext context, Object beanInstance) throws IOException {
        this._member.setValue(beanInstance, findValue(context, beanInstance));
    }
}
