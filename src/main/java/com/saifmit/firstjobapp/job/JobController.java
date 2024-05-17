package com.saifmit.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/*
    GET /jobs: Get all jobs
    GET /jobs/{id}: Get a specific job by ID
    POST /jobs: Create a new job (request body should contain the job details)
    DELETE /jobs/{id}: Delete a specific job by ID
    PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated job)

 */
@RestController
@RequestMapping("/app/v1")
public class JobController {
   private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){

        Job job = jobService.getJobById(id);
        if(job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something is fetching the defeculty :-( ",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/jobs/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT) --> It is also an option for routing.
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id,updatedJob);
        if(updated){
            return new ResponseEntity<>("Job updated Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something is wrong to do",HttpStatus.BAD_REQUEST);
    }

}
