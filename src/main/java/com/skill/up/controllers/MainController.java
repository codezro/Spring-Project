package com.skill.up.controllers;

import com.google.gson.Gson;
import com.skill.up.models.MainRepository;
import com.skill.up.models.MainForm;
import com.skill.up.models.MyFormResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jpmc on 11/11/2016.
 */
@Controller
public class MainController {

    private MainRepository mainRepository;

    @Autowired
    public MainController(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String index(){return "main/index";}

    @RequestMapping (value="/main", method=RequestMethod.POST)
    public void loadThankYou(@ModelAttribute @Valid MainForm mainForm,
                             BindingResult bindingResult,
                             HttpServletResponse resp) throws IOException {
        String getEmail = mainForm.getEmail();
        Integer emailDuplicates = 0;
        String currEmail;

        MyFormResponse myFormResponse = new MyFormResponse();
        Writer writer = resp.getWriter();
        Gson gson = new Gson();

        try {

            if(bindingResult.hasErrors()){
                myFormResponse.setHasError(true);
                myFormResponse.setErrors(bindingResult.getAllErrors());
                String json = gson.toJson(myFormResponse);
                resp.setContentType("application/json");
                writer.write(json);
            }else{
                mainRepository.save(mainForm);
                Map<String, String> options = new LinkedHashMap<>();
                myFormResponse.setHasError(false);
                options.put("fname", mainForm.getFirstname());
                options.put("lname", mainForm.getLastname());
                options.put("email", mainForm.getEmail());
                String json = gson.toJson(options);
                resp.setContentType("application/json");
                writer.write(json);
            }
        } catch (Exception ex) {
            if (mainRepository.findAll().size() != 0 && getEmail != "") {
                for (int i = 0; i < mainRepository.findAll().size(); i++) {
                    currEmail = mainRepository.findAll().get(i).getEmail();
                    emailDuplicates = currEmail.compareTo(getEmail);
                }
                if (emailDuplicates != null) {

                    myFormResponse.setHasError(true);
                    myFormResponse.setDuplicate(true);
                    String json = gson.toJson(myFormResponse);
                    resp.setContentType("application/json");
                    writer.write(json);
                }
            }
        }
    }


    @RequestMapping(value = "/displaylist", method = RequestMethod.GET)
    public String loadList(Map<String, Object> model){
        List<MainForm> users = mainRepository.findAll();
        model.put("lists", users);
        return "main/displayusers";
    }

    @RequestMapping(value= "/displaylist/{id}", method =RequestMethod.GET)
    public @ResponseBody List<MainForm> listOfUsers(ModelAndView model,@PathVariable Integer id){
        List<MainForm> listOfUsers = mainRepository.findAllById(id);
        return listOfUsers;
    }



}
