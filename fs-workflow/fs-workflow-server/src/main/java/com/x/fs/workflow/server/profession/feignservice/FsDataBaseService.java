package com.x.fs.workflow.server.profession.feignservice;

import com.x.fs.database.api.bean.DataBaseBackUpDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("fs-database")
public interface FsDataBaseService {
    @PostMapping("/database/mySqlDataBaseBackUp")
    void mySqlDataBaseBackUp(DataBaseBackUpDTO dataBaseBackUpDTO);

}
