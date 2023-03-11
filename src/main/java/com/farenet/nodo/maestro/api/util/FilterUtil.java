package com.farenet.nodo.maestro.api.util;

import java.util.Collection;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;

public class FilterUtil {

	public static Collection collect(Collection collection, String propertyName) {
	    return CollectionUtils.collect(collection, new BeanToPropertyValueTransformer(propertyName));
	}
	
}
