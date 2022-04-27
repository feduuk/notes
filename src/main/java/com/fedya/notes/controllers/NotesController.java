package com.fedya.notes.controllers;

import com.fedya.notes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class NotesController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NotesComparator notesComparator;

    @GetMapping(value = "/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/notes")
    public String showNotes(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<Note> notes = noteRepository.findNotesByUserId(user.getId());
        Collections.sort(notes, notesComparator);
        model.addAttribute("notes", notes);
        model.addAttribute("note", new Note());
        return "notes";
    }
    @PostMapping("/saveNote")
    public String saveNote(@ModelAttribute Note note){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        if(note != null){
            note.setUser(user);
            noteRepository.save(note);
        }
        return "redirect:/notes";
    }



}
