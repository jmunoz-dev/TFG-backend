package web.backend.gothere.Services;

import java.lang.StackWalker.Option;
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

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Exceptions.UserAlreadyExistException;
import web.backend.gothere.Repositories.Entities.ConfirmationTokenEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Interfaces.UserRepository;
import web.backend.gothere.Services.Models.UserDTO;

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
    public UserDTO signUpUser(UserDTO newUser) throws UserAlreadyExistException{
        Optional <UserEntity> exist = UserRepository.findByEmail(newUser.getEmail());
        if(exist.isPresent()){
            return null;
        }
        UserEntity user = modelMapper.map(newUser, UserEntity.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    
        user.setPassword(encryptedPassword);
    
        final UserEntity createdUser = UserRepository.save(new UserEntity(user.getEmail(), user.getName(), user.getLastName(), user.getPassword()));
    
        final ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(createdUser);
    
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        //TODO activar esto cuando tengamos email
        //sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
        return modelMapper.map(createdUser, UserDTO.class);

    }
    public UserDTO signInUser(UserDTO userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<UserEntity> optionalUser = UserRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            if(encoder.matches(userDto.getPassword(), optionalUser.get().getPassword())){
                return modelMapper.map(optionalUser.get(), UserDTO.class);
            }else{
                return null;
            }
        }else{
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", userDto.getEmail()));
        }
       
    }
    public UserDTO updateUser(Long idUser, UserDTO newUser){
        Optional<UserEntity> userToUpdate = UserRepository.findById(idUser);
        if(userToUpdate.isPresent()){
            userToUpdate.get().setName(newUser.getName());
            userToUpdate.get().setLastName(newUser.getLastName());
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            final String encryptedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
            userToUpdate.get().setPassword(encryptedPassword);
            return modelMapper.map( UserRepository.save(userToUpdate.get()), UserDTO.class);
        }
        throw new ElementNotFoundException();
    }
    public void confirmUser(ConfirmationTokenEntity confirmationToken) {

        final UserEntity user = confirmationToken.getUser();
      
        user.setEnabled(true);
      
        UserRepository.save(user);
      
        //confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
      
    }
    public boolean updatePassword(Long idUser, String newPassword){
        Optional<UserEntity> userToUpdate = UserRepository.findById(idUser);
        if(userToUpdate.isPresent()){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            final String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
            userToUpdate.get().setPassword(encryptedPassword);
            UserRepository.save(userToUpdate.get());
            return true;
        }
        return false;
    }
    public void sendPasswordReset(String userMail){
       Optional<UserEntity> user = UserRepository.findByEmail(userMail);
       if(user.isPresent()){
           UserDTO userToSearch = modelMapper.map(user.get(), UserDTO.class);
           String token = confirmationTokenService.findConfirmationTokenByUser(userToSearch);
           final SimpleMailMessage mailMessage = new SimpleMailMessage();
           mailMessage.setTo(userMail);
           mailMessage.setSubject("Reset your password!");
           //TODO poner email
           mailMessage.setFrom("");
           mailMessage.setText(
                   "Please click on the below link to change your password." + "http://localhost:8080/reset-password/"
                           + token);
       
           emailSenderService.sendEmail(mailMessage);
       }
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
