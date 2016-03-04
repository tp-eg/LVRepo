package com.salesmanager.core.business.complaints.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.complaint.model.QComplaintsReason;
import com.salesmanager.core.business.complaint.model.QCustomerComplaint;
import com.salesmanager.core.business.customer.model.QCustomer;
import com.salesmanager.core.business.customer.model.attribute.QCustomerAttribute;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOption;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.reference.language.model.Language;
@Repository("complaintsDAO")
public class ComplaintsDaoImpl extends SalesManagerEntityDaoImpl<Long, CustomerComplaint>
		implements ComplaintsDao {

	@Override
	public List<CustomerComplaint> listComplaintsReason() {
		QCustomerComplaint qCustomerComplaint = QCustomerComplaint.customerComplaint;
		QCustomer qCustomer=QCustomer.customer;
		QComplaintsReason qComplaintsReason=QComplaintsReason.complaintsReason;
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerComplaint).
		fullJoin(qCustomerComplaint.qCustomer, qCustomer).fetch()
		.fullJoin(qCustomerComplaint.qComplaintsReason, qComplaintsReason).fetch()
			
			.orderBy(qCustomerComplaint.id.asc());
		
		return query.distinct().list(qCustomerComplaint);
	}

	@Override
	public List<CustomerComplaint> getByName(String categoryName,
			Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
