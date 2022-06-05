package com.x.fs.workflow.server.apiimpl;

import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import com.x.fs.workflow.api.dto.DataBaseBackUpParam;
import com.x.fs.workflow.api.dto.WorkFlowRunnerResult;
import com.x.fs.workflow.api.service.DataBaseApi;
import com.x.fs.workflow.server.profession.service.DataBaseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author x
 */
@Controller
@Slf4j
@RequestMapping("/dataBase")
@Service
public class DataBaseApiImpl implements DataBaseApi {
    @Autowired
    private DataBaseService dataBaseService;

    @ApiOperation(value = "mysql备份接口")
    @RequestMapping(value = "/dataBaseBackUp", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public WorkFlowRunnerResult dataBaseBackUp(@RequestBody DataBaseBackUpParam inputParam) {
        DataBaseBackUpDTO baseBackUpDTO = new DataBaseBackUpDTO();
        BeanUtils.copyProperties(inputParam, baseBackUpDTO);
        return dataBaseService.dataBaseBackUp(baseBackUpDTO);
    }
}
