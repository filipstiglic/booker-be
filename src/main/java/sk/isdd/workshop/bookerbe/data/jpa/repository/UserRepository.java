package sk.isdd.workshop.bookerbe.data.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sk.isdd.workshop.bookerbe.data.jpa.entity.User;

/**
 * @Author Filip Stiglic
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
