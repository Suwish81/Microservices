package com.wish.ws.timediff.controller;

import com.wish.ws.timediff.domain.DateRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/course")
public class TimeTestController {
     @GetMapping(value = "/my-course-assessment",produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting() {

        return "{\"status\":true,\"data\":[{\"_id\":\"5edf179c4c946b091bec2b1b\",\"courseId\":\"5e09d71b95e799ce8d66976f\",\"assessmentId\":\"5e09d6ca40bb2cce76e3862e\",\"courseTitle\":\"Resolution of Stressed Assets\",\"duration\":\"25\",\"assessmentTitle\":\"Resolution of Stressed Assets\",\"totalQuestions\":\"25\",\"passCriteria\":\"70\",\"completionStatus\":50,\"studyMaterialPercentage\":100,\"assessmentResult\":[{\"assessmentId\":\"5e09d6ca40bb2cce76e3862e\",\"assessmentAttemptId\":\"600c49e04b9f223f390c89e8\",\"title\":\"Resolution of Stressed Assets\",\"maxAttempts\":\"5\",\"passCriteria\":\"70\",\"totalQuestions\":25,\"noOfAttempt\":4,\"considerCertification\":\"Yes\",\"obtainedMark\":14,\"totalAttendedQuestions\":25,\"totalCorrectAnswers\":14,\"totalMark\":25,\"obtainedGrade\":\"Failed\",\"obtainedPercent\":56,\"assessmentType\":\"post\",\"isFinished\":\"Yes\"}]}],\"totalRecords\":0,\"isCompletedStudyMaterial\":\"\",\"message\":\"Success\"}";
    }

    @PostMapping("/time")
    public String[] TryDate(@RequestBody DateRequest dateReq){
        String[] strArray=new String[10];
        strArray[0]=dateReq.getInpuDate().toString();
        strArray[1]=new Date().toString();
        strArray[2]= DateTimeFormatter.ISO_INSTANT.toString();
        return strArray;
    }

}
