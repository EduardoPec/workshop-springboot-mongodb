package com.project.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.workshopmongo.domain.Post;
import com.project.workshopmongo.domain.User;
import com.project.workshopmongo.dto.AuthorDTO;
import com.project.workshopmongo.dto.CommentDTO;
import com.project.workshopmongo.repository.PostRepository;
import com.project.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User antonella = new User(null, "Antonella Vieira", "antonella@gmail.com");
		User ricardo = new User(null, "Ricardo Oliveira", "ricardo@gmail.com");
		User eduardo = new User(null, "Eduardo Peçanha", "eduardo@gmail.com");
		
		userRepository.saveAll(Arrays.asList(antonella, ricardo, eduardo));
		
		Post post1 = new Post(null, sdf.parse("21/03/2025"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(antonella));
		Post post2 = new Post(null, sdf.parse("23/03/2025"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(antonella));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2025"), new AuthorDTO(antonella));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2025"), new AuthorDTO(ricardo));
		CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", sdf.parse("23/03/2025"), new AuthorDTO(eduardo));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		antonella.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(antonella);
	}
}
