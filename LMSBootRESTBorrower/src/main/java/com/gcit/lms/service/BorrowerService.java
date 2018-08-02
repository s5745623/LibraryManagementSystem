/**
 * 
 */
package com.gcit.lms.service;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ppradhan
 *
 */
@RestController
public class BorrowerService {

	@RequestMapping(value = "/loginBorrower", method = RequestMethod.GET)
	public Boolean validateBorrower(@RequestParam("cardNo") Integer cardNo){
		if(new Integer(1).equals(cardNo)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
		
	}


}
