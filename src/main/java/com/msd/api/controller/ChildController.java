package com.msd.api.controller;

import com.google.gson.Gson;
import com.msd.api.domain.Child;
import com.msd.api.domain.Parent;
import com.msd.api.dto.ChildDto;
import com.msd.api.service.ChildService;
import com.msd.api.service.ParentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/child")
@Api(value = "Child Controller", description = "Used for Child endpoints")
public class ChildController {

    private static final Logger logger = LoggerFactory.getLogger(ParentController.class);

    private final ChildService childService;

    @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Child Data upload endpoint", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type", value = "application/json", paramType = "header"),
            @ApiImplicitParam(name = "Authorization", value = "Generated access token",
                    paramType = "header")})
    public ResponseEntity addPerson(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        /**
         * Check file empty or not
         */
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        JSONParser parser = new JSONParser();
        try {
            byte[] bytes = file.getBytes();
            //Convert Byte Array plain string
            String childData = new String(bytes);
            //Parse String to JSONObject
            JSONObject jsonObject = (JSONObject) parser.parse(childData);
            // Get data object Array from jsonObject
            JSONArray jsonArray = (JSONArray) new JSONObject(jsonObject).get("data");
            // Parse Child object[] using Gson
            ChildDto[] childDtos = new Gson().fromJson(jsonArray.toString(),ChildDto[].class);
            childService.removeAll();
            for (ChildDto child: childDtos) {
                //Persist child data
                childService.addChild(child);
            }
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            logger.error("File uploading fail");
        } catch (ParseException e) {
            logger.error("File formatting Error");
        }
        return new ResponseEntity(HttpStatus.OK) ;
    }
}
