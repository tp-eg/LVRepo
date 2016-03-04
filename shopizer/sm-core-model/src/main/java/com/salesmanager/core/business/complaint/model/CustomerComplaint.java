package com.salesmanager.core.business.complaint.model;

import java.io.Serializable;

import javax.persistence.*;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.model.SalesManagerEntity;


/**
 * The persistent class for the customer_complaint database table.
 * 
 */
@Entity
@Table(name="customer_complaint")
@NamedQuery(name="CustomerComplaint.findAll", query="SELECT c FROM CustomerComplaint c")
public class CustomerComplaint extends SalesManagerEntity<Long, CustomerComplaint> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customer_complaint_reason_id", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "customer_complaint_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	private String notes;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional one-to-one association to CustomerComplaintReason
	@OneToOne
	@JoinColumn(name="customer_complaints_reason_id")
	private ComplaintsReason customerComplaintReason;

	public CustomerComplaint() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ComplaintsReason getCustomerComplaintReason() {
		return this.customerComplaintReason;
	}

	public void setCustomerComplaintReason(ComplaintsReason customerComplaintReason) {
		this.customerComplaintReason = customerComplaintReason;
	}

}