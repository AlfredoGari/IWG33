package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Usuario;
import com.example.demo.persistence.UsuarioRepository;
import com.example.demo.util.DefaultData;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication()
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DefaultData defaultData;

	@Autowired
	private UsuarioRepository usuarioDAO;

	@Override
	public void run(String... args) throws Exception {
		defaultData.ensureUserAdmin();

		/*
		 * System.out.println("Clave codificada: "+passwordEncoder.encode("clave"));
		 * 
		 * 
		 * 
		 * List<Usuario> l=usuarioDAO.listadoCustomizado("%a%");
		 * System.out.println(l.get(0).getEmail());
		 * 
		 * System.out.println(usuarioDAO.cantidadUsuariosHabilitados());
		 * 
		 * System.out.println(usuarioDAO.listadoUsuariosDTO().get(0).getUsername());
		 * System.out.println(usuarioDAO.listado().get(0).getUsername());
		 */
	}

}
