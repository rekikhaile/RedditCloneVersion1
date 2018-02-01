package com.riri.redditclone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RedditCloneController {

    @Autowired
    RedditCloneRepository redditCloneRepository;

    @RequestMapping("/")
    public String listReddit(Model model){
        model.addAttribute("redditClones", redditCloneRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String redditCloneForm(Model model){
        model.addAttribute("redditClone", new RedditClone());
        return "redditcloneform";
    }

    @PostMapping("/process")
    public String processForm(@Valid RedditClone redditClone, BindingResult result){
        if(result.hasErrors()){
            return "redditcloneform";
        }
        redditCloneRepository.save(redditClone);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showReddit(@PathVariable("id") long id, Model model){
        model.addAttribute("redditClone", redditCloneRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateReddit(@PathVariable("id") long id, Model model){
        model.addAttribute("redditClone", redditCloneRepository.findOne(id));
        return "redditcloneform";
    }


    @RequestMapping("/delete/{id}")
    public String delRedditClone(@PathVariable("id") long id){
        redditCloneRepository.delete(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String getSearch() {
        return "redditsearchform";

    }

    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model){
        String searchString = request.getParameter("search");
        model.addAttribute("search",searchString);
        model.addAttribute("redditClones", redditCloneRepository.findByTitleContainingIgnoreCase(searchString));
        return "redditsearchform";

    }

}
