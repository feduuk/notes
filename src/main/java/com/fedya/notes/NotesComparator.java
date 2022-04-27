package com.fedya.notes;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class NotesComparator implements Comparator<Note> {
    @Override
    public int compare(Note o1, Note o2) {
        if(o1.getDate().getTime()>o2.getDate().getTime()) return 1;
        else if(o1.getDate().getTime()<o2.getDate().getTime()) return -1;
        else return 0;
    }
}
