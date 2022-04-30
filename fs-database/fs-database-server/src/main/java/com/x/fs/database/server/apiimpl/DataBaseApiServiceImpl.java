package com.x.fs.database.server.apiimpl;

import com.x.fs.common.exception.FsServiceException;
import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import com.x.fs.database.api.bean.RegistryInfoDTO;
import com.x.fs.database.api.service.DataBaseApiService;
import com.x.fs.database.server.service.MysqlDataBaseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static sun.font.FontUtilities.isWindows;

/**
 * @author x
 */
@Controller
@Slf4j
@RequestMapping("/database")
public class DataBaseApiServiceImpl implements DataBaseApiService {

    @Autowired
    private MysqlDataBaseService mysqlDataBaseService;


    /**
     * Mysql数据库备份接口
     * @param dataBaseBackUpDTO
     */
    @ApiOperation(value = "MySQL数据库备份接口")
    @RequestMapping(value = "/mySqlDataBaseBackUp", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public void mySqlDataBaseBackUp(@RequestBody DataBaseBackUpDTO dataBaseBackUpDTO) {
        if(StringUtils.isBlank(dataBaseBackUpDTO.getDatabaseId())){
            throw new FsServiceException("入参字段[databaseId]数据库地址不能为null");
        }
        if(StringUtils.isBlank(dataBaseBackUpDTO.getDatabaseName())){
            throw new FsServiceException("入参字段[databaseName]数据库名称不能为null");
        }
        if(StringUtils.isBlank(dataBaseBackUpDTO.getDatabasePort())){
            throw new FsServiceException("入参字段[databasePort]数据库端口号不能为null");
        }
        mysqlDataBaseService.mySqlDataBaseBackUp(dataBaseBackUpDTO);
    }

    /**
     * 新增字典
     * @param
     */
    @ApiOperation(value = "MySQL数据库备份接口")
    @RequestMapping(value = "/insertRegistry", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public int insertRegistryInfo(@RequestBody RegistryInfoDTO input) {

        if(isWindows){

        }
        return mysqlDataBaseService.insertRegistryInfo(input);
    }
}
