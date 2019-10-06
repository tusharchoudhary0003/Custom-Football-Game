package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import java.io.IOException;

public class ObjectIdReferenceProperty extends SettableBeanProperty {
    private final SettableBeanProperty _forward;

    public static final class PropertyReferring extends Referring {
        private final ObjectIdReferenceProperty _parent;
        public final Object _pojo;

        public PropertyReferring(ObjectIdReferenceProperty parent, UnresolvedForwardReference ref, Class<?> type, Object ob) {
            super(ref, type);
            this._parent = parent;
            this._pojo = ob;
        }

        public void handleResolvedForwardReference(Object id, Object value) throws IOException {
            if (hasId(id)) {
                this._parent.set(this._pojo, value);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Trying to resolve a forward reference with id [");
            sb.append(id);
            sb.append("] that wasn't previously seen as unresolved.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public ObjectIdReferenceProperty(SettableBeanProperty forward, ObjectIdInfo objectIdInfo) {
        super(forward);
        this._forward = forward;
        this._objectIdInfo = objectIdInfo;
    }

    public ObjectIdReferenceProperty(ObjectIdReferenceProperty src, JsonDeserializer<?> deser, NullValueProvider nva) {
        super(src, deser, nva);
        this._forward = src._forward;
        this._objectIdInfo = src._objectIdInfo;
    }

    public ObjectIdReferenceProperty(ObjectIdReferenceProperty src, PropertyName newName) {
        super(src, newName);
        this._forward = src._forward;
        this._objectIdInfo = src._objectIdInfo;
    }

    public SettableBeanProperty withName(PropertyName newName) {
        return new ObjectIdReferenceProperty(this, newName);
    }

    public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> deser) {
        if (this._valueDeserializer == deser) {
            return this;
        }
        return new ObjectIdReferenceProperty(this, deser, this._nullProvider);
    }

    public SettableBeanProperty withNullProvider(NullValueProvider nva) {
        return new ObjectIdReferenceProperty(this, this._valueDeserializer, nva);
    }

    public void fixAccess(DeserializationConfig config) {
        SettableBeanProperty settableBeanProperty = this._forward;
        if (settableBeanProperty != null) {
            settableBeanProperty.fixAccess(config);
        }
    }

    public AnnotatedMember getMember() {
        return this._forward.getMember();
    }

    public int getCreatorIndex() {
        return this._forward.getCreatorIndex();
    }

    public void deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
        deserializeSetAndReturn(p, ctxt, instance);
    }

    public Object deserializeSetAndReturn(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
        try {
            return setAndReturn(instance, deserialize(p, ctxt));
        } catch (UnresolvedForwardReference reference) {
            if ((this._objectIdInfo == null && this._valueDeserializer.getObjectIdReader() == null) ? false : true) {
                reference.getRoid().appendReferring(new PropertyReferring(this, reference, this._type.getRawClass(), instance));
                return null;
            }
            throw JsonMappingException.from(p, "Unresolved forward reference but no identity info", (Throwable) reference);
        }
    }

    public void set(Object instance, Object value) throws IOException {
        this._forward.set(instance, value);
    }

    public Object setAndReturn(Object instance, Object value) throws IOException {
        return this._forward.setAndReturn(instance, value);
    }
}
