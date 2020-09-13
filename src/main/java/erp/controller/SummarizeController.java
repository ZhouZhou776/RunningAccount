package erp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import erp.service.SummarizeService;
import erp.util.ResultInfo;
import erp.entity.vo.req.SummarizeConditionQueryVO;
import erp.entity.vo.resp.DetailRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yhaobo
 * @date 2020/3/28
 */
@Slf4j
@RestController
public class SummarizeController {

    @Autowired
    private SummarizeService service;

    @RequestMapping("summarize")

    public ResultInfo summarize(SummarizeConditionQueryVO vo, Integer pageNum, Integer pageSize) {
        try {
            // 分页
            PageHelper.startPage(pageNum, pageSize);

            List<DetailRespVO> detailList = service.listByFilter(vo);
            PageSerializable<DetailRespVO> pageInfo = new PageSerializable<>(detailList);
            return new ResultInfo(true, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[method:summarize]" + e.getMessage());
            return new ResultInfo(false, "解析日期失败");
        }
    }
}
