package com.bethesda.raphaelsv.user.domain;

import com.bethesda.raphaelsv.common.domain.MemberRole;
import com.bethesda.raphaelsv.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class UsersVO extends CommonVO implements Serializable {
    private static final long serialVersionUID = -8274004534207618049L;

    private String userid;
    private String pwd;
    private String usernm;
    private String level;
    private String useyn;

    private List<MemberRole> roleList;
}
