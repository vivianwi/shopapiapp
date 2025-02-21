package edu.school21.shopapi.specification;

import org.springframework.data.jpa.domain.Specification;
import edu.school21.shopapi.model.Client;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ClientSpecification {
    public static Specification<Client> withFilters(String clientName, String clientSurname) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (clientName != null && !clientName.isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("clientName")), "%" + clientName.toLowerCase() + "%"));
            }
            if (clientSurname != null && !clientSurname.isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("clientSurname")), "%" + clientSurname.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}