package br.edu.escola.jogos.config;

import br.edu.escola.jogos.aluno.Aluno;
import br.edu.escola.jogos.aluno.AlunoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataSeed {

    @Bean
    CommandLineRunner carregarAlunos(AlunoRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() > 0) return;
            repo.save(new Aluno("2024001", "Ana Souza", "2º Ano", "7–8 anos", encoder.encode("1234")));
            repo.save(new Aluno("2024002", "Bruno Lima", "4º Ano", "9–10 anos", encoder.encode("5678")));
            repo.save(new Aluno("2024003", "Carla Mendes", "7º Ano", "12–13 anos", encoder.encode("4321")));
        };
    }
}