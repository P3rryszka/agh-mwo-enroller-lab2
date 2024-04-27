package com.company.enroller.controllers;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import com.company.enroller.persistence.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

    @Autowired
    MeetingService meetingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMeeting(@PathVariable("id") long id) {
        Meeting meeting = meetingService.findById(id);
        if(meeting == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(meeting,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        Collection<Meeting> meetings = meetingService.getAll();
        if(meetings == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(meetings,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addMeeting(@RequestBody Meeting meeting) {
        if(meetingService.findById(meeting.getId()) != null)
            return new ResponseEntity<>("Meeting exists already!", HttpStatus.CONFLICT);
        else {
            meetingService.addMeeting(meeting);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
