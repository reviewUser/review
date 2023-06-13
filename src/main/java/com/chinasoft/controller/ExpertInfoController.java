package com.chinasoft.controller;

import com.chinasoft.param.ExpertParam;
import com.chinasoft.entity.ExpertInfo;
import com.chinasoft.po.PwdInfo;
import com.chinasoft.service.ExpertInfoService;
import com.chinasoft.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expert")
public class ExpertInfoController {

    @Autowired
    private ExpertInfoService expertInfoService;

    /**
     * 新增评审专家
     * @param expertInfo
     * @return
     * @throws Exception
     */
    @PostMapping("/addExpert")
    public int addExpert(@RequestBody ExpertInfo expertInfo) throws Exception {
        return expertInfoService.insert(expertInfo);
    }

    /**
     * 查询专家基本信息以json的形式返回
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/queryExpertInfo")
    public Map<String, Object> queryExpertInfo(@RequestBody ExpertParam param) {
        int pageNum = (param.getPageNum() - 1) * param.getPageSize();
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<ExpertInfo> users = null;
        int totalCount = 0;
        param.setPageNum(pageNum);
        users = expertInfoService.queryExpertInfo(param);
        totalCount = expertInfoService.getTotalCount(param);
        map.put("code", 200);
        map.put("count", totalCount);
        map.put("msg", "查询专家信息成功");
        map.put("data", users);
        return map;
    }

    /**
     * 导入专家信息
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/importExpertExcel")
    public Map<String, Object> excelProTbZbzs(MultipartFile file) {
        Map<String, Object> stringObjectMap = expertInfoService.importExpert(file);
        return stringObjectMap;
    }

    /**
     * 导出专家信息
     * @param ids
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/exportExpertExcel")
    public void exportExpertExcel(@RequestBody List<Long> ids, HttpServletResponse response) throws IOException {
        expertInfoService.exportExpert(ids, response);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delExpert")
    public Result delExpert(@RequestBody List<Long> ids) {
        return expertInfoService.delExperts(ids);
    }

    /**
     * 未参会
     * @param unMeeting
     * @param id
     * @return
     */
    @PostMapping(value = "/unMeeting")
    public int updateUnMeeting(@RequestParam("unMeeting") int unMeeting, @RequestParam("id") long id) {
        return expertInfoService.updateUnMeetingNum(unMeeting, id);
    }

    /**
     * 批量解封
     * @param ids
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/unBan")
    public int unBan(@RequestBody List<Long> ids) {
        return expertInfoService.unBan(ids);
    }

    /**
     * 修好密码信息
     *
     * @param pwdInfo
     * @return
     */
    @PostMapping(value = "/updatePwd")
    public Result updatePwd(@RequestBody PwdInfo pwdInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return expertInfoService.updatePwd(pwdInfo);
    }
}
