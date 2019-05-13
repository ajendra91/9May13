package com.example.Ajax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	public List<Emp> lst=new ArrayList<Emp>(Arrays.asList(new Emp(1,"ajay","24"),new Emp(2,"php","24")));

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(){
		return new ResponseEntity<Object>(lst, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public ResponseEntity<Object> getAll3(){
		EmpService <List<Emp>> es= new EmpService<List<Emp>>("success",lst);
		return new ResponseEntity<Object>(es, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll2", method = RequestMethod.GET)
	public List<Emp> getAll2(){
		return lst;
	}

	@RequestMapping(value = "/getId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getId(@PathVariable int id){
		return new ResponseEntity<Object>(lst.get(id),HttpStatus.OK);
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public void add(@RequestBody Emp emp){
		lst.add(emp);
		System.out.println(emp.id+""+emp.name+""+emp.age);
	}


}
