package com.centralnabanka.service;

import com.centralnabanka.model.GroupPayment;
import com.centralnabanka.types.Mt102;
import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.Mt910;

public interface MessageConverterService {

    Mt900 convertToMt900(Object message) throws Exception;
    Mt910 convertToMt910(Object message) throws Exception;

    Mt102 convertToMt102(GroupPayment payment) throws Exception;
    GroupPayment convertToGroupPayment(Mt102 message);
}
