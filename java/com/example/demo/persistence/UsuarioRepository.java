package com.example.demo.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;
import com.example.demo.model.dto.UsuarioDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public List<Usuario> findByEnabledTrueOrderByUsername();

	public List<Usuario> findByUsername(String username);

	public List<Usuario> findByUsernameOrEmail(String username, String email);


	//@Transactional
	@Modifying
	@Query(value="UPDATE usuarios SET enabled=1 WHERE id=?",nativeQuery=true)
	public int habilitaCuenta(int id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE usuarios SET enabled=0 WHERE id=?",nativeQuery=true)
	public int inhabilitaCuenta(int id);

	
	@Query(value="SELECT * FROM usuarios WHERE enabled=1 AND email like ? AND id IN (SELECT id_usuario FROM usuarios_roles ur INNER JOIN roles r ON ur.id_rol=r.id WHERE rol='ROLE_ADMIN')",nativeQuery=true)
	public List<Usuario> listadoCustomizado(String parteDelEmail);
	
	@Query(value="SELECT COUNT(*) FROM usuarios WHERE enabled=1",nativeQuery=true)
	public Integer cantidadUsuariosHabilitados();
	
	@Query(name="listadoUsuariosDTO",nativeQuery = true)
	public List<UsuarioDTO> listadoUsuariosDTO();
	
	
	@Query(value="FROM Usuario u ORDER BY u.firstName")
	public List<Usuario> listado();
	
}
