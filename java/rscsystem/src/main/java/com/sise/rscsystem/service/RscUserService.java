package com.sise.rscsystem.service;

        import com.sise.rscsystem.bean.Role;
import com.sise.rscsystem.bean.RscUser;
import com.sise.rscsystem.repository.RscUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

        import javax.annotation.Resource;
        import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RscUserService implements UserDetailsService {
    @Resource
    @Autowired
    RscUserRepository rscUserRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        RscUser rscUser = rscUserRepository.findByName(s);
        if (rscUser == null)
            throw new UsernameNotFoundException("用户名不存在!!!");
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = rscUser.getRoles();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return new User(rscUser.getAccount(), rscUser.getPassword(), authorities);
    }
    @Transactional
    public RscUser register(RscUser rscUser){
        return rscUserRepository.save(rscUser);
    }
    @Transactional
    public RscUser findUserByName(String name){
        return rscUserRepository.findByName(name);
    }
    @Transactional
    public void removeOneUser(RscUser rscUser){
        rscUserRepository.delete(rscUser);
    }

}
