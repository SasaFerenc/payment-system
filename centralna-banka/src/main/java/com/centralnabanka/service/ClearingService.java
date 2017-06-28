package com.centralnabanka.service;

import com.centralnabanka.model.GroupPayment;
import com.centralnabanka.types.Mt102;

public interface ClearingService {

    void saveMt102(Mt102 request);

    void processPayment(GroupPayment payment) throws Exception;
}
