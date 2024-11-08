package com.diningsystem.dining.system.repo;

import com.diningsystem.dining.system.model.User;
import com.diningsystem.dining.system.response.Message;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByIdAndIsActive(Long id, Boolean isActive);
}
