package com.example.test1.controller;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaypalController {

    @Autowired
    PaypalService paypalService;

    public static final String  SUCCESS_URL = "pay/success";
    public static final String  CANCEL_URL = "pay/cancel";

    @PostMapping("/pay/{nomR}")
    public String payment(@ModelAttribute("command") CommandeDto commandeDto, @PathVariable(name = "nomR") String nomR){
        System.out.println("bonjour");
        System.out.println(commandeDto.getTotal());
        System.out.println(commandeDto.getCurrency());
        System.out.println(commandeDto.getMode());
        System.out.println(commandeDto.getIntent());

        try{
            Payment payment =paypalService.createPayment(commandeDto.getTotal(), commandeDto.getCurrency(), commandeDto.getMode(), commandeDto.getIntent(), "http://localhost:8080/"+ CANCEL_URL, "http://localhost:8080/"+ SUCCESS_URL );

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

    @GetMapping(value = SUCCESS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @RequestParam String data){
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")){
                System.out.println(data);
                return "success";
            }
        }catch (PayPalRESTException e){
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}