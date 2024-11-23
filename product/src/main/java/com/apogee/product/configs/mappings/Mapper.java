package com.apogee.product.configs.mappings;

import java.lang.reflect.InvocationTargetException;

public interface Mapper {

    <S,D> D map(S source, Class<D> destination) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException;
}
