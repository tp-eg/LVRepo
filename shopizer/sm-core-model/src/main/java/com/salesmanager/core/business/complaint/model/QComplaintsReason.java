package com.salesmanager.core.business.complaint.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;
import com.mysema.query.types.PathMetadata;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QComplaintsReason is a Querydsl query type for ComplaintsReason
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QComplaintsReason extends EntityPathBase<ComplaintsReason> {

    private static final long serialVersionUID = -23277537L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComplaintsReason complaintsReason = new QComplaintsReason("ComplaintsReason");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    

    public final StringPath englishName = createString("englishName");

    

    

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath arabicName = createString("arabicName");
    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

   

    public QComplaintsReason(String variable) {
        this(ComplaintsReason.class, forVariable(variable), INITS);
    }

    public QComplaintsReason(Path<? extends ComplaintsReason> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComplaintsReason(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComplaintsReason(PathMetadata<?> metadata, PathInits inits) {
        this(ComplaintsReason.class, metadata, inits);
    }

    public QComplaintsReason(Class<? extends ComplaintsReason> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
       
    }

}

