package com.yonyou.base.support.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yonyou.iuap.base.web.BaseController;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.mvc.type.SearchParams;

import cn.hutool.core.date.DateUtil;

@Controller
@RequestMapping(value="/common_excel")
public class ExcelController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(ExcelController.class);

	@RequestMapping(value = "/export_excel")
    @ResponseBody
    public Object exportExcel(PageRequest pageRequest, SearchParams searchParams,HttpServletResponse response) throws InterruptedException {
		List<BaseVo> list = new ArrayList<>();
		BaseVo baseVo =null;
		Thread thread = new Thread();
		for (int i = 0; i < 5000; i++) {
			thread.sleep(1);
			baseVo = new BaseVo(UUID.randomUUID().toString().replace("-", ""),DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss SSS"),DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"),new Date().getTime()+"",new Date());
			list.add(baseVo);
		}
		try {
			ExcelUtil.downloadExcelList(list,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return this.buildSuccess();
    }
	
	
    /**
     * 批量导入用户
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/import_excel", produces = { "text/html;charset=utf-8" }, method = RequestMethod.POST)
    @ResponseBody
    public Object importExcel(
            @RequestParam(value = "importExcelFile", required = false) MultipartFile importExcelFile, HttpServletRequest request, HttpServletResponse response) {
        if (importExcelFile != null && !StringUtils.isEmpty(importExcelFile.getOriginalFilename())) {
            InputStream inputStream = null;
            try {
                inputStream = importExcelFile.getInputStream();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if (inputStream != null) {
                List<BaseVo> resultList=null;
				try {
					resultList = ExcelUtil.readExcelToEntity(inputStream);
				} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
					e.printStackTrace();
				}
                if (resultList!=null&&resultList.size()==0) {
                    System.out.println("读取的excel获取用户个数：" + resultList.size());
                    // 先校验excel数据是否 不重复
//                    for (int i = 0; i < resultList.size(); i++) {
//                    	BaseVo user = resultList.get(i);
//                        if (BaseVo.checkName(user.getName())) {
//                            continue; 
//                        }
//                        else {
//                            return this.buildError("msg", "第" + (i + 1) + "行用户名已经被使用", RequestStatusEnum.FAIL_FIELD);
//                        }
//                    }
                    return this.buildSuccess(resultList);
                }
            }
        }
        return this.buildError("msg", "File inputStream is null !", RequestStatusEnum.FAIL_FIELD);
    }

}
