package com.interview.sapro.aib;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class BankAccount extends PaymentInstrument {

    @Column
    private String bankName;

    @Column
    private String iban;

    @Column
    private String bic;

    public BankAccount() {
        super(PaymentInstrumentType.BankAccount);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getDescription() {
        return String.format("Bank Account %s (%s)", getId(), getStatus() == PaymentInstrumentStatus.Active ? "Active" : "Locked");
    }
}
