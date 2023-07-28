package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class OrderSpecification {
    public Specification<Order> getOrdersByUser(Long userId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nonNull(userId)) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId));
            }
            query.orderBy(criteriaBuilder.desc(root.get("createAt")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
