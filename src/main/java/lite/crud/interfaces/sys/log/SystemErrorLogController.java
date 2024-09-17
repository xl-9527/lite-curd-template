package lite.crud.interfaces.sys.log;

import lite.crud.application.handler.sys.log.SystemErrorLogService;
import lite.crud.config.common.pojo.ApiResult;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.sys.log.dto.SystemErrorLogQueryDto;
import lite.crud.domain.sys.log.vo.SystemErrorLogVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xl-9527
 * @since 2024/9/17
 **/
@RestController
@RequestMapping("/sys/log/sys-error-log")
public class SystemErrorLogController {

    private final SystemErrorLogService systemErrorLogService;

    public SystemErrorLogController(final SystemErrorLogService systemErrorLogService) {
        this.systemErrorLogService = systemErrorLogService;
    }

    @GetMapping("list")
    public ApiResult<Page<SystemErrorLogVo>> queryListPage(SystemErrorLogQueryDto queryDto) {
        return ApiResult.success(systemErrorLogService.queryListPage(queryDto));
    }
}
