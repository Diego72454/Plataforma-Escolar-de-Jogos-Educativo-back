package br.edu.escola.jogos.auth;

import br.edu.escola.jogos.aluno.Aluno;
import br.edu.escola.jogos.aluno.AlunoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AlunoRepository alunos;
    private final PasswordEncoder encoder;

    public AuthService(AlunoRepository alunos, PasswordEncoder encoder) {
        this.alunos = alunos;
        this.encoder = encoder;
    }

    public LoginResponse login(LoginRequest req) {
        // Mesmo erro para "matrícula não existe" e "senha errada": não revela qual dos dois falhou.
        Aluno aluno = alunos.findByMatricula(req.matricula().trim())
                .filter(a -> encoder.matches(req.senha(), a.getSenhaHash()))
                .orElseThrow(CredenciaisInvalidasException::new);

        // Critério 3: devolve turma e faixa etária do cadastro.
        return new LoginResponse(aluno.getMatricula(), aluno.getNome(), aluno.getTurma(), aluno.getFaixaEtaria());
    }
}
