package com.catenax.dft.controllers;

import com.catenax.dft.gateways.file.CsvGateway;
import com.catenax.dft.usecases.csvHandler.MapToAspectCsvHandlerUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
public class UploadFileController {

    private final CsvGateway csvGateway;
    private final MapToAspectCsvHandlerUseCase mapToAspectUseCase;

    public UploadFileController(CsvGateway storageService, MapToAspectCsvHandlerUseCase mapToAspectUseCase) {
        this.csvGateway = storageService;
        this.mapToAspectUseCase = mapToAspectUseCase;
    }

    @RequestMapping(value = "/upload")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {

        String processId = csvGateway.storeFile(file);

        Runnable runnable = () ->
        {
            List<String> csvRows = csvGateway.processFile(processId);
            csvRows.parallelStream().forEach(mapToAspectUseCase::run);
        };

        new Thread(runnable).start();
        return ok().body(processId);
    }
}