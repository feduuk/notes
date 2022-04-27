package com.fedya.notes.controllers;

import com.fedya.notes.Note;
import com.fedya.notes.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RefactorNote {
    @Autowired
    NoteRepository noteRepository;
    @PostMapping("/refactorNote")
    public String refactorNote(@ModelAttribute("obj") Note note, Model model){
        noteRepository.changeNote(note.getId(), note.getText());
        return "redirect:/notes";
    }

}
