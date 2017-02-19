package com.interview.sapro.aib;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private PaymentInstrument paymentInstrument;

    @Column
    private Date date;

    @Column
    private String subject;

    @Column
    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(PaymentInstrument bankAccount) {
        this.paymentInstrument = bankAccount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
