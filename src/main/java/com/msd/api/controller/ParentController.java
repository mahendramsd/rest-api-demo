package com.msd.api.controller;

import com.google.gson.Gson;
import com.msd.api.domain.Parent;
import com.msd.api.dto.ParentChildDto;
import com.msd.api.dto.ParentDto;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.*;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/parent")
@Api(value = "Parent Controller", description = "Used for get Parent endpoints")
public class ParentController {

    private static final Logger logger = LoggerFactory.getLogger(ParentController.class);

    private final ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Parent Data upload endpoint", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type", value = "application/json", paramType = "header"),
            @ApiImplicitParam(name = "Authorization", value = "Generated access token",
                    paramType = "header")})
    public ResponseEntity addPerson(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {

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
            String parentData = new String(bytes);
            //Parse String to JSONObject
            JSONObject jsonObject = (JSONObject) parser.parse(parentData);
            // Get data object Array from jsonObject
            JSONArray jsonArray = (JSONArray) new JSONObject(jsonObject).get("data");
            // Parse Parent object[] using Gson
            Parent[] parents = new Gson().fromJson(jsonArray.toString(),Parent[].class);

            for (Parent parent: parents) {
                //Persist parent data
                parentService.addParent(parent);
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Parents data get endpoint",response = Parent.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type", value = "application/json", paramType = "header"),
            @ApiImplicitParam(name = "Authorization", value = "Generated access token",
                    paramType = "header")})
    public ResponseEntity<List<ParentDto>> getParents(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(parentService.findParentData(page,limit));
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Parent By Id endpoint",response = Parent.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type", value = "application/json", paramType = "header"),
            @ApiImplicitParam(name = "Authorization", value = "Generated access token",
                    paramType = "header")})
    public ResponseEntity<List<ParentChildDto>> getParents(@RequestParam("parentId") int parentId) {
        return ResponseEntity.ok(parentService.findChildDetails(parentId));
    }

}
