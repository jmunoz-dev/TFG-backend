package web.backend.gothere.Services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.ConfirmationTokenEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Interfaces.ConfirmationTokenRepository;
import web.backend.gothere.Services.Models.UserDTO;

public class ConfirmationTokenService {

	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
    private ModelMapper modelMapper;

	public void saveConfirmationToken(ConfirmationTokenEntity confirmationToken) {

		confirmationTokenRepository.save(confirmationToken);
	}
	public void deleteConfirmationToken(Long id){

        confirmationTokenRepository.deleteById(id);
    }
	public Optional<ConfirmationTokenEntity> findConfirmationTokenByToken(String token) {
		
		return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
	}
	public String findConfirmationTokenByUser(UserDTO user) {
		UserEntity userToSearch  = modelMapper.map(user, UserEntity.class);
		Optional<ConfirmationTokenEntity> confirmationToken =  confirmationTokenRepository.findConfirmationTokenByUser(userToSearch);
		if(confirmationToken.isPresent()){
			return confirmationToken.get().getConfirmationToken();
		}
		throw new ElementNotFoundException();
	}
}
