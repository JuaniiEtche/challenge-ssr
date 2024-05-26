package com.eldar.challengessr.api.services.hibryd.contracts;

import java.util.Map;

public interface HibrydService {
    Map<String, Object> getLocalidadById(Long id) throws InterruptedException;

    Map<String, Object> getProvinciaById(Long id) throws InterruptedException;
}
