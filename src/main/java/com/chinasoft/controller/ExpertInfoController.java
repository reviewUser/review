package com.chinasoft.controller;

import com.chinasoft.param.ExpertParam;
import com.chinasoft.po.ExpertInfo;
import com.chinasoft.po.PwdInfo;
import com.chinasoft.service.ExpertInfoService;
import com.chinasoft.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/addExpert")
    public String addExpert(@RequestBody ExpertInfo expertInfo) throws Exception {
        expertInfoService.insert(expertInfo);
        return "新增成功";
    }

    /**
     * 查询专家基本信息以json的形式返回
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/queryExpertInfo")
    public Map<String, Object> queryExpertInfo(@RequestBody ExpertParam param) throws Exception {
        int pageNum = (param.getPageNum() - 1) * param.getPageSize();
        Map<String, Object> map = new HashMap<String, Object>();
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
     * @throws IOException
     */
    @PostMapping(value = "/importExpertExcel")
    public Map<String, Object> excelProTbZbzs(MultipartFile file) throws IOException {
        try {
            Map<String, Object> stringObjectMap = expertInfoService.importExpert(file);
            return stringObjectMap;
        } catch (RuntimeException e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 导出专家信息
     *
     * @param ids
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/exportExpertExcel")
    public void exportExpertExcel(@RequestBody List<Long> ids, HttpServletResponse response) throws IOException {
        try {
            expertInfoService.exportExpert(ids, response);
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    @PostMapping(value = "/delExpert")
    public Result delExpert(@RequestBody List<Long> ids) throws IOException {
        try {
            return expertInfoService.delExperts(ids);
        } catch (RuntimeException e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 修好密码信息
     *
     * @param pwdInfo
     * @return
     */
    @PostMapping(value = "/updatePwd")
    public Result updatePwd(@RequestBody PwdInfo pwdInfo) {
        try {
            return expertInfoService.updatePwd(pwdInfo);
        } catch (RuntimeException e) {
            e.getMessage();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
