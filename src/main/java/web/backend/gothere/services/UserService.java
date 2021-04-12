package web.backend.gothere.services;

import java.text.MessageFormat;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.backend.gothere.repositories.entities.ConfirmationTokenEntity;
import web.backend.gothere.repositories.entities.UserEntity;
import web.backend.gothere.repositories.interfaces.UserRepository;
import web.backend.gothere.services.models.UserDTO;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository UserRepository;

    @Autowired
	private  ConfirmationTokenService confirmationTokenService;

    @Autowired
	private  EmailSenderService emailSenderService;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO getUserById(Long id){
        final Optional<UserEntity> optionalUser = UserRepository.findById(id);
        if (optionalUser.isPresent()) {
            return modelMapper.map(optionalUser.get(), UserDTO.class);
        }
        throw new UsernameNotFoundException(MessageFormat.format("User with id {0} cannot be found.", id)) ;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        final Optional<UserEntity> optionalUser = UserRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }
    public UserDTO signUpUser(UserDTO newUser) {
        
        UserEntity user = modelMapper.map(newUser, UserEntity.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    
        user.setPassword(encryptedPassword);
    
        final UserEntity createdUser = UserRepository.save(new UserEntity(user.getEmail(), user.getName(), user.getLastName(), user.getPassword()));
    
        final ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(createdUser);
    
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        //activar esto cuando tengamos email
        //sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
        return modelMapper.map(createdUser, UserDTO.class);

    }
    public boolean signInUser(UserEntity user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<UserEntity> optionalUser = UserRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            if(encoder.matches(user.getPassword(), optionalUser.get().getPassword())){
                return true;
            }else{
                return false;
            }
        }else{
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", user.getEmail()));
        }
       
    }
   
    public void confirmUser(ConfirmationTokenEntity confirmationToken) {

        final UserEntity user = confirmationToken.getUser();
      
        user.setEnabled(true);
      
        UserRepository.save(user);
      
        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
      
    }
   public  void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation Link!");
        //TODO poner email
        mailMessage.setFrom("");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/confirm?token="
                        + token);
    
        emailSenderService.sendEmail(mailMessage);
    }

}
