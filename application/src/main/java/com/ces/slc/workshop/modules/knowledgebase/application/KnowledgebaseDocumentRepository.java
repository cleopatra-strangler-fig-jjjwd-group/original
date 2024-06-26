package com.ces.slc.workshop.modules.knowledgebase.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.core.application.document.DocumentRepository;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseDocument;

@Repository
public interface KnowledgebaseDocumentRepository extends DocumentRepository<KnowledgebaseDocument, KnowledgebaseComponent> {

    @Query("""
    select distinct estimate
    from EstimateDocument estimate
    join EstimateComponent estimateComponent on estimateComponent.document = estimate
    join estimateComponent.knowledgebaseComponent knowledgebaseComponent
    where knowledgebaseComponent.document = :knowledgebaseDocument
    and estimateComponent.knowledgebaseComponent is not null
    """)
    Set<EstimateDocument> getReferencingEstimateDocuments(KnowledgebaseDocument knowledgebaseDocument);
}
