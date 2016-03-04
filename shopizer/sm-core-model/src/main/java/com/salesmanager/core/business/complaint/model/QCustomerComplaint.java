package com.salesmanager.core.business.complaint.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;
import com.mysema.query.types.PathMetadata;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;
import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.QCategory;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.QCustomer;


/**
 * QComplaintsReason is a Querydsl query type for ComplaintsReason
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerComplaint extends EntityPathBase<CustomerComplaint> {

    private static final long serialVersionUID = -23277537L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerComplaint customerComplaint = new QCustomerComplaint("customerComplaint");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    

    public final StringPath note = createString("note");

    
    public final QCustomer qCustomer;
    public final QComplaintsReason qComplaintsReason;
    

    public final NumberPath<Long> id = createNumber("id", Long.class);

    
    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

   

    public QCustomerComplaint(String variable) {
        this(CustomerComplaint.class, forVariable(variable), INITS);
    }

    public QCustomerComplaint(Path<? extends CustomerComplaint> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerComplaint(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerComplaint(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerComplaint.class, metadata, inits);
    }

    public QCustomerComplaint(Class<? extends CustomerComplaint> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.qCustomer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.qComplaintsReason = inits.isInitialized("ComplaintsReason") ? new QComplaintsReason(forProperty("ComplaintsReason"), inits.get("ComplaintsReason")) : null;
       
    }

}

