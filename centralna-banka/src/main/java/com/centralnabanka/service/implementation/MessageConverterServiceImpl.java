package com.centralnabanka.service.implementation;

import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.Mt910;
import com.centralnabanka.types.PodaciOPlacanju;
import org.springframework.stereotype.Service;

@Service
public class MessageConverterServiceImpl implements MessageConverterService {

    @Override
    public Mt900 convertToMt900(Object message) throws Exception {
        Mt900 response = new Mt900();

        String idPoruke = (String) invokeMethod(message, "getIdPoruke");
        PodaciOPlacanju podaciOPlacanju = (PodaciOPlacanju) invokeMethod(message, "getPodaciOPlacanju");
        String idPorukeNaloga = message.getClass().getSimpleName().toUpperCase() + "-" + idPoruke;

        response.setIdPoruke(idPoruke);
        response.setDatumValute(podaciOPlacanju.getDatumValute());
        response.setSifraValute((String) invokeMethod(message, "getSifraValute"));
        response.setIznos(podaciOPlacanju.getIznos());
        response.setIdPorukeNaloga(idPorukeNaloga);
        response.setSwiftKodDuznika((String) invokeMethod(message, "getSwiftKodDuznika"));
        response.setObracunskiRacunDuznika((String) invokeMethod(message, "getObracunskiRacunDuznika"));

        return response;
    }

    @Override
    public Mt910 convertToMt910(Object message) throws Exception {
        Mt910 response = new Mt910();

        String idPoruke = (String) invokeMethod(message, "getIdPoruke");
        PodaciOPlacanju podaciOPlacanju = (PodaciOPlacanju) invokeMethod(message, "getPodaciOPlacanju");
        String idPorukeNaloga = message.getClass().getSimpleName().toUpperCase() + "-" + idPoruke;

        response.setIdPoruke(idPoruke);
        response.setDatumValute(podaciOPlacanju.getDatumValute());
        response.setSifraValute((String) invokeMethod(message, "getSifraValute"));
        response.setIznos(podaciOPlacanju.getIznos());
        response.setIdPorukeNaloga(idPorukeNaloga);
        response.setSwiftKodPoverioca((String) invokeMethod(message, "getSwiftKodPoverioca"));
        response.setObracunskiRacunPoverioca((String) invokeMethod(message, "getObracunskiRacunPoverioca"));

        return response;
    }

    private Object invokeMethod(Object object, String methodName) throws Exception {
        return object.getClass().getMethod(methodName, new Class<?>[] {}).invoke(object);
    }
}
