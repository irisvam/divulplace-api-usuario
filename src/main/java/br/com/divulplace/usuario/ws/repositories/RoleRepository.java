package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.Role;
import br.com.divulplace.usuario.util.enums.RoleName;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findByNome(RoleName nome);

}
