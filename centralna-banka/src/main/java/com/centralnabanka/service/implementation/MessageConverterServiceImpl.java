package com.centralnabanka.service.implementation;

import com.centralnabanka.model.GroupPayment;
import com.centralnabanka.model.PaymentRequest;
import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.types.Mt102;
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

    @Override
    public GroupPayment convertToGroupPayment(Mt102 message) {
        GroupPayment groupPayment = new GroupPayment();

        groupPayment.setMessageId(message.getIdPoruke());
        groupPayment.setCreditorSwiftCode(message.getSwiftKodDuznika());
        groupPayment.setCreditorAccountNumber(message.getObracunskiRacunDuznika());
        groupPayment.setDebtorSwiftCode(message.getSwiftKodPoverioca());
        groupPayment.setDebtorAccountNumber(message.getObracunskiRacunPoverioca());
        groupPayment.setTotal(message.getUkupanIznos());
        groupPayment.setValuteCode(message.getSifraValute());
        groupPayment.setValuteDate(message.getDatumValute().toGregorianCalendar().getTime());
        groupPayment.setPaymentDate(message.getDatum().toGregorianCalendar().getTime());

        for (Mt102.PojedinacnaPlacanja payment : message.getPojedinacnaPlacanja()) {
            PaymentRequest paymentRequest = new PaymentRequest();

            paymentRequest.setPaymentId(payment.getIdNaloga());
            paymentRequest.setCreditorName(payment.getPodaciOPlacanju().getDuznikNalogodavac());
            paymentRequest.setPurpose(payment.getPodaciOPlacanju().getSvrhaPlacanja());
            paymentRequest.setDebtorName(payment.getPodaciOPlacanju().getPrimalacPoverilac());
            paymentRequest.setPaymentDate(payment.getPodaciOPlacanju().getDatumNaloga().toGregorianCalendar().getTime());
            paymentRequest.setCreditorAccountNumber(payment.getPodaciOPlacanju().getRacunDuznika());
            paymentRequest.setChargeModel(payment.getPodaciOPlacanju().getModelZaduzenja());
            paymentRequest.setDebitReferenceNumber(payment.getPodaciOPlacanju().getPozivNaBrojZaduzenja());
            paymentRequest.setDebtorAccountNumber(payment.getPodaciOPlacanju().getRacunPoverioca());
            paymentRequest.setAllowanceModel(payment.getPodaciOPlacanju().getModelOdobrenja());
            paymentRequest.setCreditReferenceNumber(payment.getPodaciOPlacanju().getPozivNaBrojOdobrenja());
            paymentRequest.setAmount(payment.getPodaciOPlacanju().getIznos());
            paymentRequest.setValuteCode(payment.getSifraValute());

            paymentRequest.setGroupPayment(groupPayment);
            groupPayment.getPaymentRequests().add(paymentRequest);
        }

        return groupPayment;
    }

    private Object invokeMethod(Object object, String methodName) throws Exception {
        return object.getClass().getMethod(methodName, new Class<?>[] {}).invoke(object);
    }
}
