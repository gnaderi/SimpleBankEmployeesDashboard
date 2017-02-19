package com.interview.sapro.aib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Copyright (c) 2017  Ghodratollah Naderi Darehkat
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Project:Simple Bank Employees Dashboard Application
 * Author: Ghodratollah Naderi
 * E-Mail: Naderi.ghodrat@gmail.com
 * Date  : 19/02/2017
 */
@Controller
public class PaymentInstrumentController {

    @Autowired
    private PaymentInstrumentService paymentInstrumentService;

    @RequestMapping("/")
    public ModelAndView home() {
        final Map<String, Object> model = new HashMap<String, Object>();
        final List<PaymentInstrument> paymentInstruments = paymentInstrumentService.findPaymentInstruments();
        model.put("paymentInstruments", paymentInstruments);
        return new ModelAndView("home", model);
    }

    @RequestMapping(value = "/lockPaymentInstrument", method = RequestMethod.POST)
    public String lockPaymentInstrument(
            @RequestParam Long paymentInstrumentId,
            RedirectAttributes redirectAttributes
    ) throws LockException {
        paymentInstrumentService.lockPaymentInstrument(paymentInstrumentId);
        redirectAttributes.addFlashAttribute("message", String.format("Payment instrument %s locked.", paymentInstrumentId));
        return "redirect:/";
    }

    @RequestMapping(value = "/unlockPaymentInstrument", method = RequestMethod.POST)
    public String unlockPaymentInstrument(
            @RequestParam Long paymentInstrumentId,
            RedirectAttributes redirectAttributes
    ) throws LockException {
        paymentInstrumentService.unlockPaymentInstrument(paymentInstrumentId);
        redirectAttributes.addFlashAttribute("message", String.format("Payment instrument %s activated.", paymentInstrumentId));
        return "redirect:/";
    }

    @RequestMapping(value = "/createBankAccount", method = RequestMethod.POST)
    public String createBankAccount(
            @RequestParam String accountHolder,
            @RequestParam String bankName,
            @RequestParam String iban,
            @RequestParam String bic,
            RedirectAttributes redirectAttributes
    ) {
        long bankAccountId = paymentInstrumentService.createBankAccount(accountHolder, bankName, iban, bic);
        redirectAttributes.addFlashAttribute("message", String.format("Bank account with ID %s created.", bankAccountId));
        return "redirect:/";
    }

    @RequestMapping(value = "/createCreditCard", method = RequestMethod.POST)
    public String createCreditCard(
            @RequestParam String accountHolder,
            @RequestParam CreditCardScheme scheme,
            @RequestParam String pan,
            @RequestParam Integer expiryMonth,
            @RequestParam Integer expiryYear,
            RedirectAttributes redirectAttributes
    ) {
        long creditCardId = paymentInstrumentService.createCreditCard(accountHolder, scheme, pan, expiryMonth, expiryYear);
        redirectAttributes.addFlashAttribute("message", String.format("Credit card with ID %s created.", creditCardId));
        return "redirect:/";
    }

}
