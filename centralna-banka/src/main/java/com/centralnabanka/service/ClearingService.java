package com.centralnabanka.service;

import com.centralnabanka.types.Mt102;

public interface ClearingService {

    void processMt102Request(Mt102 request);
}
