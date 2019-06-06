package br.com.divulplace.usuario.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	List<Usuario> findByEmail(String email);

}
