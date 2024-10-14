package ro.itschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.ITSchoolUser;
import ro.itschool.repository.ITSchoolUserRepository;

@Service
public class ITSchoolUserService implements UserDetailsService {
    @Autowired
    private ITSchoolUserRepository itSchoolUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ITSchoolUser itSchoolUser = itSchoolUserRepository.findByUsername(username).get();
//        itSchoolUser.getAuthorities().add(new SimpleGrantedAuthority("USER"))
        return itSchoolUser;
    }

    public void registerUser(ITSchoolUser user){
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        itSchoolUserRepository.save(user);
    }
}
