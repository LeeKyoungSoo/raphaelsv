package com.bethesda.raphaelsv.security;

import com.bethesda.raphaelsv.common.domain.MemberRole;
import com.bethesda.raphaelsv.user.domain.UsersVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ZerockSecurityUser extends User {
    private static final String ROLE_PREFIX = "ROLE_";

    public ZerockSecurityUser(UsersVO vo) {
        super(vo.getUserid(), vo.getPwd(), makeGrantedAuthority(vo.getRoleList()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {
        List<GrantedAuthority> roleList = new ArrayList<>();

        roles.forEach(
                role -> roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));

        return  roleList;
    }
}
