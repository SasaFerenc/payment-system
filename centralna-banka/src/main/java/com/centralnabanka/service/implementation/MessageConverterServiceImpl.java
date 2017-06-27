package com.centralnabanka.service.implementation;

import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.types.Mt900;
import com.centralnabanka.types.Mt910;
import com.centralnabanka.types.PodaciOPlacanju;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.stereotype.Service;

@Service
public class MessageConverterServiceImpl implements MessageConverterService {

    @Override
    public Mt900 convertToMt900(Object message) throws Exception {
        Mt900 response = (Mt900) initializeMt9xxMessage(Mt900.class, message);

        response.setSwiftKodDuznika((String) invokeMethod(message, "getSwiftKodDuznika"));
        response.setObracunskiRacunDuznika((String) invokeMethod(message, "getObracunskiRacunDuznika"));

        return  response;
    }

    @Override
    public Mt910 convertToMt910(Object message) throws Exception {
        Mt910 response = (Mt910) initializeMt9xxMessage(Mt910.class, message);

        response.setSwiftKodPoverioca((String) invokeMethod(message, "setSwiftKodPoverioca"));
        response.setObracunskiRacunPoverioca((String) invokeMethod(message, "setObracunskiRacunPoverioca"));

        return  response;
    }

    private Object initializeMt9xxMessage(Class<?> messageClass, Object base) throws Exception {
        Object mt9xx = messageClass.newInstance();
        String idPoruke = (String) invokeMethod(base, "getIdPoruke");
        PodaciOPlacanju podaciOPlacanju = (PodaciOPlacanju) invokeMethod(base, "getPodaciOPlacanju");

        invokeMethod(mt9xx, "setIdPoruke", idPoruke);
        invokeMethod(mt9xx, "setIznos", podaciOPlacanju.getIznos());
        invokeMethod(mt9xx, "setDatumValute", podaciOPlacanju.getDatumValute());
        invokeMethod(mt9xx, "setSifraValute", (String) invokeMethod(base, "getSifraValute"));

        String idPorukeNaloga = base.getClass().getSimpleName().toUpperCase() + "-" + idPoruke;
        invokeMethod(mt9xx, "setIdPorukeNaloga", idPorukeNaloga);

        return mt9xx;
    }

    private Object invokeMethod(Object object, String methodName) throws Exception {
        return object.getClass().getMethod(methodName, new Class<?>[] {}).invoke(object);
    }

    private Object invokeMethod(Object object, String methodName, Object arg) throws Exception {
        Class<?> argClass = arg instanceof XMLGregorianCalendarImpl ? arg.getClass().getSuperclass() : arg.getClass();

        return object.getClass().getMethod(methodName, argClass).invoke(object, arg);
    }

}
