package com.kdt.dto;

public class TransactionDTO {

	private String transactionId;
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
	public TransactionDTO(String transactionId, String transactionType) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
	}
	public TransactionDTO() {
		super();
	}

}
