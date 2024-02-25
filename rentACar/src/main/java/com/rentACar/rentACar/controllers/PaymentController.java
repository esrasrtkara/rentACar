package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.dtos.requests.Payment.PaymentRequest;
import com.rentACar.rentACar.services.dtos.requests.Payment.RefundRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {

    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    @PostMapping("/makePayment")
    public String makePayment(@RequestBody PaymentRequest request
                              ) {
        Stripe.apiKey = stripeSecretKey;

        try {
            // Token'ı kullanarak bir ödeme yapın
            Charge charge = Charge.create(getChargeParams(request.getTokenId(), request.getAmount(), request.getCurrency()));

            // Ödeme başarılıysa
            return  charge.getId();
        } catch (StripeException e) {
            // Hata durumunda
            e.printStackTrace();
            return "Ödeme başarısız oldu. Hata: " + e.getMessage();
        }
    }

    @PostMapping("/refund")
    public String refundPayment(@RequestBody RefundRequest refundRequest) {
        Stripe.apiKey = stripeSecretKey;

        try {
            Refund refund = Refund.create(
                    // Iade miktarı ve ödeme ID'si
                    new HashMap<String, Object>() {{
                        put("charge", refundRequest.getChargeId());
                        // Eğer iade miktarı belirtilmişse
                        if (refundRequest.getAmount() > 0) {
                            put("amount", refundRequest.getAmount());
                        }
                    }}
            );
            return "Ödeme başarıyla iade edildi: " + refund.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "Ödeme iadesi başarısız oldu. Hata: " + e.getMessage();
        }
    }


    private Map<String, Object> getChargeParams(String tokenId, int amount, String currency) {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount); // Ödeme miktarını cent cinsinden belirtin
        chargeParams.put("currency", currency);
        chargeParams.put("description", "React Stripe Örneği");
        chargeParams.put("source", tokenId); // Kullanılacak token'ı belirtin
        return chargeParams;
    }
}





