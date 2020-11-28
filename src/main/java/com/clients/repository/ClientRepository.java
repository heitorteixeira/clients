package com.clients.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clients.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Query(value = "SELECT c FROM Client c "
            + "WHERE c.name like CONCAT('%',:name,'%') "
            + "ORDER by c.name")
	List<Client> findByName(String name);

}
