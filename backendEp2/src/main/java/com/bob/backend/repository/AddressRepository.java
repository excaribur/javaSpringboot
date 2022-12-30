package com.bob.backend.repository;

import com.bob.backend.entity.Address;
import com.bob.backend.entity.Social;
import com.bob.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);

}
