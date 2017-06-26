package com.centralnabanka.service;

import com.centralnabanka.types.Mt103;
import com.centralnabanka.types.Mt900;

public interface RtgsService {

    Mt900 processMt103Request(Mt103 request);
}
