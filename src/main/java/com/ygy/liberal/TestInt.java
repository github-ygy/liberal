package com.ygy.liberal;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.JdRequest;
import com.jd.open.api.sdk.response.AbstractResponse;

/**
 * @author guoyao
 * @date 2020-07-08
 */
public interface TestInt {

    <T extends AbstractResponse> T execute(JdRequest<T> var1) throws JdException;
}


