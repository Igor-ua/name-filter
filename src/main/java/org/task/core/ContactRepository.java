package org.task.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Unused at this moment
 * //todo delete in case*
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
