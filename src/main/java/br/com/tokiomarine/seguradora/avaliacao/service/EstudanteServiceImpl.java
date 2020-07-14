package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@Service
public class EstudanteServiceImpl implements EstudanteService {

	EstudanteRepository repository;

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		Optional<Estudante> estudanteEntity = repository.findById(estudante.getId());
		if (estudanteEntity.isPresent()) {
			estudanteEntity.get().setNome(estudante.getNome());
			estudanteEntity.get().setEmail(estudante.getEmail());
			estudanteEntity.get().setTelefone(estudante.getTelefone());
			estudanteEntity.get().setMatricula(estudante.getMatricula());
			estudanteEntity.get().setCurso(estudante.getCurso());
			repository.save(estudanteEntity.get());
		}
	}

	@Override
	public void apagarEstudante(@Valid Estudante estudante) {
		repository.delete(estudante);
	}
	
	@Override
	public List<Estudante> buscarEstudantes() {
		return repository.findAll();
		
	}

	@Override
	public Estudante buscarEstudante(long id) {
		throw new IllegalArgumentException("Identificador inválido:" + id);
	}

}
