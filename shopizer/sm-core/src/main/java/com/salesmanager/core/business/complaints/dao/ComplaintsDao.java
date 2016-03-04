package com.salesmanager.core.business.complaints.dao;


import java.util.List;


import com.salesmanager.core.business.complaint.model.CustomerComplaint;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ComplaintsDao extends SalesManagerEntityDao<Long, CustomerComplaint>{
	public List<CustomerComplaint> listComplaintsReason();

	public List<CustomerComplaint> getByName(String categoryName, Language language) ;
}
