package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Transaction")
public class Transaction {
	
	@Id
	@Column(name="transaction_id")
	private String transactionId;
	
	@Column(name="transaction_type")
	private String transactionType;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Transaction(String transactionId, String transactionType) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
	}

	public Transaction() {
		super();
	}

}
