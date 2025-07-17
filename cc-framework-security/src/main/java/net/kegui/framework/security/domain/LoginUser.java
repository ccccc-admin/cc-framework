package net.kegui.framework.security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String password;
    private String nickName;
    private String avatar;
    private Integer status;

    /**
     * 角色对象
     */
    private List<?> roles;

    /**
     * 权限标识集合
     */
    private Set<String> permissions;

    /**
     * 菜单列表
     */
    private List<?> menus;

    /**
     * 创建时间
     * 使用自定义的序列化和反序列化器处理时间戳转换
     */
    private LocalDateTime createTime;

}