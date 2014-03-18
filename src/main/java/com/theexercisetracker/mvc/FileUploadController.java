package com.theexercisetracker.mvc;

import com.theexercisetracker.mvc.utils.AjaxUtils;
import com.theexercisetracker.tcx.TcxParser;
import com.theexercisetracker.tcx.TcxParserImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

    @ModelAttribute
    public void ajaxAttribute(WebRequest request, Model model) {
        model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
    }

    @RequestMapping(method = RequestMethod.GET)
    public void fileUploadForm() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
        TcxParser parser = new TcxParserImpl();

        model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
    }

}
