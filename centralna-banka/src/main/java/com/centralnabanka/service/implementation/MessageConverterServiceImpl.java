package com.centralnabanka.service.implementation;

import com.centralnabanka.model.GroupPayment;
import com.centralnabanka.model.PaymentRequest;
import com.centralnabanka.service.MessageConverterService;
import com.centralnabanka.types.*;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class MessageConverterServiceImpl implements MessageConverterService {

    @Override
    public Mt910 convertToMt910(Object message) throws Exception {
        if (message instanceof Mt102) return convertToMt910((Mt102) message);
        if (message instanceof Mt103) return convertToMt910((Mt103) message);

        throw new IllegalArgumentException();
    }

    @Override
    public Mt900 convertToMt900(Object message) throws Exception {
        if (message instanceof Mt102) return convertToMt900((Mt102) message);
        if (message instanceof Mt103) return convertToMt900((Mt103) message);

        throw new IllegalArgumentException();
    }

    @Override
    public GroupPayment convertToGroupPayment(Mt102 message) {
        GroupPayment groupPayment = new GroupPayment();
        List<PaymentRequest> paymentRequests = new ArrayList<>();

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
            paymentRequest.setValuteDate(payment.getPodaciOPlacanju().getDatumValute().toGregorianCalendar().getTime());
            paymentRequest.setCreditorAccountNumber(payment.getPodaciOPlacanju().getRacunDuznika());
            paymentRequest.setChargeModel(payment.getPodaciOPlacanju().getModelZaduzenja());
            paymentRequest.setDebitReferenceNumber(payment.getPodaciOPlacanju().getPozivNaBrojZaduzenja());
            paymentRequest.setDebtorAccountNumber(payment.getPodaciOPlacanju().getRacunPoverioca());
            paymentRequest.setAllowanceModel(payment.getPodaciOPlacanju().getModelOdobrenja());
            paymentRequest.setCreditReferenceNumber(payment.getPodaciOPlacanju().getPozivNaBrojOdobrenja());
            paymentRequest.setAmount(payment.getPodaciOPlacanju().getIznos());
            paymentRequest.setValuteCode(payment.getSifraValute());

            paymentRequest.setGroupPayment(groupPayment);
            paymentRequests.add(paymentRequest);
        }

        groupPayment.setPaymentRequests(paymentRequests);

        return groupPayment;
    }

    @Override
    public Mt102 convertToMt102(GroupPayment payment) throws Exception {
        Mt102 mt102 =  new Mt102();

        mt102.setIdPoruke(payment.getMessageId());
        mt102.setSwiftKodDuznika(payment.getCreditorSwiftCode());
        mt102.setObracunskiRacunDuznika(payment.getCreditorAccountNumber());
        mt102.setSwiftKodPoverioca(payment.getDebtorSwiftCode());
        mt102.setObracunskiRacunPoverioca(payment.getDebtorAccountNumber());
        mt102.setUkupanIznos(payment.getTotal());
        mt102.setSifraValute(payment.getValuteCode());
        mt102.setDatumValute(toGregorianCalendarDate(payment.getValuteDate()));
        mt102.setDatum(toGregorianCalendarDate(payment.getPaymentDate()));

        for (PaymentRequest paymentRequest : payment.getPaymentRequests()) {
            Mt102.PojedinacnaPlacanja individualPayment = new Mt102.PojedinacnaPlacanja();

            individualPayment.setIdNaloga(paymentRequest.getPaymentId());

            PodaciOPlacanju paymentInfo = new PodaciOPlacanju();
            paymentInfo.setDuznikNalogodavac(paymentRequest.getCreditorName());
            paymentInfo.setSvrhaPlacanja(paymentRequest.getPurpose());
            paymentInfo.setPrimalacPoverilac(paymentRequest.getDebtorName());
            paymentInfo.setDatumNaloga(toGregorianCalendarDate(paymentRequest.getPaymentDate()));
            paymentInfo.setRacunDuznika(paymentRequest.getCreditorAccountNumber());
            paymentInfo.setModelZaduzenja(paymentRequest.getChargeModel());
            paymentInfo.setPozivNaBrojZaduzenja(paymentRequest.getDebitReferenceNumber());
            paymentInfo.setRacunPoverioca(paymentRequest.getDebtorAccountNumber());
            paymentInfo.setModelOdobrenja(paymentRequest.getAllowanceModel());
            paymentInfo.setPozivNaBrojOdobrenja(paymentRequest.getCreditReferenceNumber());
            paymentInfo.setIznos(paymentRequest.getAmount());
            paymentInfo.setDatumValute(toGregorianCalendarDate(paymentRequest.getValuteDate()));

            individualPayment.setPodaciOPlacanju(paymentInfo);
            individualPayment.setSifraValute(paymentRequest.getValuteCode());

            mt102.getPojedinacnaPlacanja().add(individualPayment);
        }

        return mt102;
    }

    private Mt900 convertToMt900(Mt102 message) throws Exception {
        Mt900 response = new Mt900();

        response.setIdPoruke(message.getIdPoruke());
        response.setDatumValute(message.getDatumValute());
        response.setSifraValute(message.getSifraValute());
        response.setIznos(message.getUkupanIznos());
        response.setIdPorukeNaloga(message.getIdPoruke());
        response.setSwiftKodDuznika(message.getSwiftKodDuznika());
        response.setObracunskiRacunDuznika(message.getObracunskiRacunDuznika());

        return response ;
    }

    private Mt900 convertToMt900(Mt103 message) throws Exception {
        Mt900 response = new Mt900();

        response.setIdPoruke(message.getIdPoruke());
        response.setDatumValute(message.getPodaciOPlacanju().getDatumValute());
        response.setSifraValute(message.getSifraValute());
        response.setIznos(message.getPodaciOPlacanju().getIznos());
        response.setIdPorukeNaloga(message.getIdPoruke());
        response.setSwiftKodDuznika(message.getSwiftKodDuznika());
        response.setObracunskiRacunDuznika(message.getObracunskiRacunDuznika());

        return response;
    }

    private Mt910 convertToMt910(Mt102 message) throws Exception {
        Mt910 response = new Mt910();

        response.setIdPoruke(message.getIdPoruke());
        response.setDatumValute(message.getDatumValute());
        response.setSifraValute(message.getSifraValute());
        response.setIznos(message.getUkupanIznos());
        response.setIdPorukeNaloga("MT102-" + message.getIdPoruke());
        response.setSwiftKodPoverioca(message.getSwiftKodPoverioca());
        response.setObracunskiRacunPoverioca(message.getObracunskiRacunPoverioca());

        return response;
    }

    private Mt910 convertToMt910(Mt103 message) throws Exception {
        Mt910 response = new Mt910();

        response.setIdPoruke(message.getIdPoruke());
        response.setDatumValute(message.getPodaciOPlacanju().getDatumValute());
        response.setSifraValute(message.getSifraValute());
        response.setIznos(message.getPodaciOPlacanju().getIznos());
        response.setIdPorukeNaloga("MT103-" + message.getIdPoruke());
        response.setSwiftKodPoverioca(message.getSwiftKodPoverioca());
        response.setObracunskiRacunPoverioca(message.getObracunskiRacunPoverioca());

        return response;
    }

    private XMLGregorianCalendar toGregorianCalendarDate(Date date) throws Exception {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }
}
