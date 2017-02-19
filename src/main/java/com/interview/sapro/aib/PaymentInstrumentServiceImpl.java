package com.interview.sapro.aib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class PaymentInstrumentServiceImpl implements PaymentInstrumentService {

    @Autowired
    private PaymentInstrumentRepository paymentInstrumentRepository;

    @Autowired
    private AuditMessageRepository auditMessageRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void createAuditMessage(String message) {
        AuditMessage auditMessage = new AuditMessage();
        auditMessage.setDate(new Date());
        auditMessage.setMessage(message);
        auditMessage.setUserName("Dummy");
        auditMessageRepository.save(auditMessage);
    }

    @Override
    @Transactional
    public List<PaymentInstrument> findPaymentInstruments() {
        Iterable<PaymentInstrument> paymentInstruments = paymentInstrumentRepository.findAll();
        List<PaymentInstrument> result = new ArrayList<PaymentInstrument>();
        for (PaymentInstrument paymentInstrument : paymentInstruments) {
            result.add(paymentInstrument);
        }
        return result;
    }

    @Override
    public void lockPaymentInstrument(
            long paymentInstrumentId
    ) throws LockException {
        createAuditMessage(String.format("Locks payment instrument %s", paymentInstrumentId));
        PaymentInstrument paymentInstrument = paymentInstrumentRepository.findOne(paymentInstrumentId);
        if (paymentInstrument.getStatus() == PaymentInstrumentStatus.Locked) {
            throw new LockException(String.format("Payment instrument %s is already locked!", paymentInstrumentId), ErrorCode.LOCK);
        }
        paymentInstrument.setStatus(PaymentInstrumentStatus.Locked);
        paymentInstrumentRepository.save(paymentInstrument);
    }

    @Override
    public void unlockPaymentInstrument(
            long paymentInstrumentId
    ) throws LockException {
        createAuditMessage(String.format("Unlocks payment instrument %s", paymentInstrumentId));
        PaymentInstrument paymentInstrument = paymentInstrumentRepository.findOne(paymentInstrumentId);
        if (paymentInstrument.getStatus() == PaymentInstrumentStatus.Active) {
            throw new LockException(String.format("Payment instrument %s is already active!", paymentInstrumentId), ErrorCode.LOCK);
        }
        paymentInstrument.setStatus(PaymentInstrumentStatus.Active);
        paymentInstrumentRepository.save(paymentInstrument);
    }

    @Override
    public long createBankAccount(
            String accountHolder,
            String bankName,
            String iban,
            String bic
    ) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setStatus(PaymentInstrumentStatus.Active);
        bankAccount.setCreationDateTime(new Date());
        bankAccount.setAccountHolder(accountHolder);
        bankAccount.setBankName(bankName);
        bankAccount.setIban(iban);
        bankAccount.setBic(bic);
        PaymentInstrument saved = paymentInstrumentRepository.save(bankAccount);
        return saved.getId();
    }

    @Override
    public long createCreditCard(
            String accountHolder,
            CreditCardScheme scheme,
            String pan,
            int expiryMonth,
            int expiryYear
    ) {
        CreditCard creditCard = new CreditCard();
        creditCard.setStatus(PaymentInstrumentStatus.Active);
        creditCard.setCreationDateTime(new Date());
        creditCard.setAccountHolder(accountHolder);
        creditCard.setScheme(scheme);
        creditCard.setPan(pan);
        creditCard.setExpiryMonth(expiryMonth);
        creditCard.setExpiryYear(expiryYear);
        PaymentInstrument saved = paymentInstrumentRepository.save(creditCard);
        return saved.getId();
    }

}
