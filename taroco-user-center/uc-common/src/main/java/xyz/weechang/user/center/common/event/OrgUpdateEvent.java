package xyz.weechang.user.center.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.base.event.AuditAbleAbstractEvent;
import xyz.weechang.taroco.base.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:03.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgUpdateEvent extends AuditAbleAbstractEvent{

    private static final long serialVersionUID = -3618294635402102769L;

    private String code;

    private String name;

    private Integer orderNum;

    private Boolean enable;

    private Boolean open;

    public OrgUpdateEvent(
            String id, AuditEntry auditEntry, String code,
            String name, Integer orderNum, Boolean enable, Boolean open) {
        super(id, auditEntry);
        new OrgUpdateEvent(code, name, orderNum, enable, open);
    }
}
