package com.interview.sapro.aib;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentInstrument {

    private transient PaymentInstrumentType type;

    @Id
    @GeneratedValue
    private long id;

    @Column
    private PaymentInstrumentStatus status;

    @Column
    private Date creationDateTime;

    @Column
    private String accountHolder;

    //	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="paymentInstrument")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "paymentInstrument")
    private List<Transaction> transactions;

    public PaymentInstrument(PaymentInstrumentType type) {
        this.type = type;
    }

    public PaymentInstrumentType getType() {
        return type;
    }

    public void setType(PaymentInstrumentType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentInstrumentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentInstrumentStatus status) {
        this.status = status;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public abstract String getDescription();

}
