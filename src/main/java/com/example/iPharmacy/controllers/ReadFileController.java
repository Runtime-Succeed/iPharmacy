package com.example.iPharmacy.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
public class ReadFileController {
    File myfile = new File("src/main/resources/words.txt");

    String contents = FileUtils.readFileToString(myfile,
            StandardCharsets.UTF_8.name());

    public ReadFileController() throws IOException {
    }

    @RequestMapping(value="/read-file", method = RequestMethod.GET )
public String file() throws IOException {
            return contents;
        }
        @RequestMapping(value="/read-file/numlines", method = RequestMethod.GET )
    public String numlines() throws IOException {
            List lines = FileUtils.readLines(myfile,
                    StandardCharsets.UTF_8.name());
            return "Number of lines in file: "+lines.size();
    }
public boolean isCopy(File filecontent) throws IOException {
    String contents = FileUtils.readFileToString(filecontent,
            StandardCharsets.UTF_8.name());
        return contents.equals(filecontent);
}

    }




