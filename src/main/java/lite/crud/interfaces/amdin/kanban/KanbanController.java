package lite.crud.interfaces.amdin.kanban;

import lite.crud.config.common.pojo.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * kanban controller
 *
 * @author xl-9527
 * @since 2024/9/5
 **/
@RestController
@RequestMapping("/admin/kanban")
public class KanbanController {

    @GetMapping
    public ApiResult<?> initKanbanData() {
        return ApiResult.success();
    }
}
