package org.example.controller;

import org.example.entity.StagingRequest;
import org.example.service.DataStaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/staging")
public class Staging {
    private DataStaging dataStaging;

    @Autowired
    public void setDataStaging(DataStaging dataStaging) {
        this.dataStaging = dataStaging;
    }

    @PostMapping("/echo")
    public StagingRequest echo(@RequestBody StagingRequest req) {
        return req;
    }

    @PostMapping("/delete")
    public boolean clear(@RequestBody StagingRequest req) {
        return this.dataStaging.delete(req.id);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody StagingRequest req) {
        return this.dataStaging.update(req.id, req.data);
    }

    @PostMapping("/search")
    public Map<String, String> search(@RequestBody StagingRequest req) {
        return this.dataStaging.search(req.id);
    }
}
