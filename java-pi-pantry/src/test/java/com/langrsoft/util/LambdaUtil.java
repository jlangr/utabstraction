package com.langrsoft.util;

import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

public class LambdaUtil {
    static public <T, ResultType> List<ResultType> map(List<T> list, Function<? super T, ? extends ResultType> func) {
        return list.stream().map(func).collect(toList());
    }
}
