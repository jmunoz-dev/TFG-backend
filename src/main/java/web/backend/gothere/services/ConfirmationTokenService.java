package web.backend.gothere.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Repositories.Entities.ConfirmationTokenEntity;
import web.backend.gothere.Repositories.Interfaces.ConfirmationTokenRepository;

public class ConfirmationTokenService {

	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

	public void saveConfirmationToken(ConfirmationTokenEntity confirmationToken) {

		confirmationTokenRepository.save(confirmationToken);
	}
	public void deleteConfirmationToken(Long id){

        confirmationTokenRepository.deleteById(id);
    }
	public Optional<ConfirmationTokenEntity> findConfirmationTokenByToken(String token) {

		return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
	}
}
