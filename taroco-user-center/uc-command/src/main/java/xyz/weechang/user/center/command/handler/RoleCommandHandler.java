package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.weechang.taroco.core.exception.BusinessException;
import xyz.weechang.user.center.command.aggregate.Role;
import xyz.weechang.user.center.command.command.RoleCreateCommand;
import xyz.weechang.user.center.common.error.UCError;
import xyz.weechang.user.center.query.dao.RoleDao;
import xyz.weechang.user.center.query.domain.RoleEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:31.
 */
@Slf4j
public class RoleCommandHandler {

    private Repository<Role> repository;
    private EventBus eventBus;

    public RoleCommandHandler(Repository<Role> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Autowired
    private RoleDao roleDao;

    @CommandHandler
    public void handle(RoleCreateCommand command) throws Exception {
        RoleEntry role = roleDao.findByRoleName(command.getRoleName());
        if (role != null) {
            throw new BusinessException(UCError.ROLE_IS_EXIST);
        }
        repository.newInstance(() -> {
            return new Role(command);
        });
    }
}
