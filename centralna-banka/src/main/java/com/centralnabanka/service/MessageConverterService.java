package com.centralnabanka.service;

import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.Mt910;

public interface MessageConverterService {

    Mt900 convertToMt900(Object message) throws Exception;
    Mt910 convertToMt910(Object message) throws Exception;
}
