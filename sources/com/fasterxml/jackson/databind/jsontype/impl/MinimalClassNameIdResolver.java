package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;

public class MinimalClassNameIdResolver extends ClassNameIdResolver {
    protected final String _basePackageName;
    protected final String _basePackagePrefix;

    protected MinimalClassNameIdResolver(JavaType baseType, TypeFactory typeFactory) {
        super(baseType, typeFactory);
        String base = baseType.getRawClass().getName();
        int ix = base.lastIndexOf(46);
        if (ix < 0) {
            this._basePackageName = "";
            this._basePackagePrefix = ".";
            return;
        }
        this._basePackagePrefix = base.substring(0, ix + 1);
        this._basePackageName = base.substring(0, ix);
    }

    public String idFromValue(Object value) {
        String n = value.getClass().getName();
        if (n.startsWith(this._basePackagePrefix)) {
            return n.substring(this._basePackagePrefix.length() - 1);
        }
        return n;
    }

    /* access modifiers changed from: protected */
    public JavaType _typeFromId(String id, DatabindContext ctxt) throws IOException {
        if (id.startsWith(".")) {
            StringBuilder sb = new StringBuilder(id.length() + this._basePackageName.length());
            if (this._basePackageName.length() == 0) {
                sb.append(id.substring(1));
            } else {
                sb.append(this._basePackageName);
                sb.append(id);
            }
            id = sb.toString();
        }
        return super._typeFromId(id, ctxt);
    }
}
