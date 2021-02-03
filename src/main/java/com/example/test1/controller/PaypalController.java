package com.example.test1.controller;

import com.example.test1.modele.Entity.Commande;
import com.example.test1.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaypalController {

    @Autowired
    PaypalService paypalService;

    public static final String  SUCCESS_URL = "pay/success";
    public static final String  CANCEL_URL = "pay/cancel";

    @PostMapping("/pay/{nomR}")
    public String payment(@ModelAttribute("commande") Commande commande, @PathVariable(name = "nomR") String nomR){
        System.out.println("bonjour");

        try{
            Payment payment =paypalService.createPayment(commande.getTotal(), commande.getCurrency(), commande.getMode(), commande.getIntent(), "http://localhost:8080/"+ CANCEL_URL, "http://localhost:8080/"+ SUCCESS_URL );

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")){
                    return "redirect:"+link.getHref();
                }
            }

        }catch (PayPalRESTException e){
            e.printStackTrace();
        }
        return "redirect:/plat/paiement/{nomR}";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancerPay(){
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @ModelAttribute("commande") Commande commande){
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")){
                
                return "success";
            }
        }catch (PayPalRESTException e){
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}