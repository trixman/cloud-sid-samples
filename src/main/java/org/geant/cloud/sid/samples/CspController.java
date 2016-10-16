package org.geant.cloud.sid.samples;

import java.util.ArrayList;
import java.util.List;

import org.geant.cloud.sid.party.Organization;
import org.geant.cloud.sid.resource.DeviceInterface;
import org.geant.cloud.sid.resource.LogicalDevice;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("domain/api")
@Api("domain/api")
public class CspController {

	@RequestMapping(value = "csps", method = RequestMethod.GET)
	@ApiOperation(value="Returns the CSPs for which the domain directly serves customer/institution traffic", httpMethod="GET", produces="application/son")
	public HttpEntity<List<Organization>> getCsps() {
		List<Organization> orgs = new ArrayList<>();
		orgs.add(CspBuilder.buildMicrosoft());
		orgs.add(CspBuilder.buildAmazon());
		orgs.add(CspBuilder.buildYahoo());
        return new ResponseEntity<List<Organization>>(orgs, HttpStatus.OK);
    }
	
	@RequestMapping(value = "csps/{csp}", method = RequestMethod.GET)
	@ApiOperation(value="Returns a specific CSP for which the domain directly serves customer/institution traffic", httpMethod="GET", produces="application/son")
	public HttpEntity<Organization> getCsp(@PathVariable long csp) {
		if (1 == csp) {
			return new ResponseEntity<Organization>(CspBuilder.buildMicrosoft(), HttpStatus.OK);			
		} 
		if (2 == csp) {
			return new ResponseEntity<Organization>(CspBuilder.buildAmazon(), HttpStatus.OK);
		}
		if (3 == csp) {
			return new ResponseEntity<Organization>(CspBuilder.buildYahoo(), HttpStatus.OK);
		}
		return new ResponseEntity<Organization>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "csps/{csp}/devices", method = RequestMethod.GET)
	@ApiOperation(value="Returns the domain’s edge devices in peering with a specific CSP", httpMethod="GET", produces="application/son")
	public HttpEntity<List<LogicalDevice>> getCspDevices(@PathVariable long csp) {
		if (1 == csp) {
			return new ResponseEntity<List<LogicalDevice>>(CspBuilder.buildMicrosoftDevices(), HttpStatus.OK);			
		} 
		if (2 == csp) {
			return new ResponseEntity<List<LogicalDevice>>(CspBuilder.buildAmazonDevices(), HttpStatus.OK);
		}
		return new ResponseEntity<List<LogicalDevice>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "csps/{csp}/devices/{device}", method = RequestMethod.GET)
	@ApiOperation(value="Returns an edge device in peering with a specific CSP", httpMethod="GET", produces="application/son")
	public HttpEntity<LogicalDevice> getCspDevice(@PathVariable long csp, @PathVariable long device) {
		if (1 == csp && 100 == device) {
			return new ResponseEntity<LogicalDevice>(CspBuilder.buildMicrosoftDevice1(), HttpStatus.OK);			
		}
		if (1 == csp && 101 == device) {
			return new ResponseEntity<LogicalDevice>(CspBuilder.buildMicrosoftDevice2(), HttpStatus.OK);			
		} 
		if (2 == csp && 200 == device) {
			return new ResponseEntity<LogicalDevice>(CspBuilder.buildAmazonDevice1(), HttpStatus.OK);
		}
		return new ResponseEntity<LogicalDevice>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "csps/{csp}/devices/{device}/ifaces/{iface}", method = RequestMethod.GET)
	@ApiOperation(value="domain/api", httpMethod="GET", produces="application/son")
	public HttpEntity<DeviceInterface> getCspDeviceInterface(@PathVariable long csp, @PathVariable long device, @PathVariable long iface) {
		if (1 == csp) {
			return new ResponseEntity<DeviceInterface>(CspBuilder.buildMicrosoftDeviceInterface(device, iface), HttpStatus.OK);			
		} 
		if (2 == csp) {
			return new ResponseEntity<DeviceInterface>(CspBuilder.buildAmazonDeviceInterface(device, iface), HttpStatus.OK);
		}
		return new ResponseEntity<DeviceInterface>(HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value = "csps/{csp}/institutions", method = RequestMethod.GET)
	@ApiOperation(value="domain/api", httpMethod="GET", produces="application/son")
	public HttpEntity<List<Organization>> getCspInstitutions(@PathVariable long csp) {
		List<Organization> orgs = new ArrayList<>();
		if (1 == csp) {
			orgs.add(InstitutionBuilder.buildNtua(csp));
			orgs.add(InstitutionBuilder.buildUoa(csp));
			orgs.add(InstitutionBuilder.buildUop(csp));
			return new ResponseEntity<List<Organization>>(orgs, HttpStatus.OK);			
		} 
		if (2 == csp) {
			orgs.add(InstitutionBuilder.buildNtua(csp));
			orgs.add(InstitutionBuilder.buildUoa(csp));
			return new ResponseEntity<List<Organization>>(orgs, HttpStatus.OK);
		}
		if (3 == csp) {
			return new ResponseEntity<List<Organization>>(orgs, HttpStatus.OK);
		}
		return new ResponseEntity<List<Organization>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "csps/{csp}/institutions/{institution}", method = RequestMethod.GET)
	@ApiOperation(value="domain/api", httpMethod="GET", produces="application/son")
	public HttpEntity<Organization> getCspInstitution(@PathVariable long csp, @PathVariable long institution) {
		if (1 == csp) {
			return new ResponseEntity<Organization>(InstitutionBuilder.buildNtua(csp), HttpStatus.OK);			
		} 
		if (2 == csp) {
			return new ResponseEntity<Organization>(InstitutionBuilder.buildUoa(csp), HttpStatus.OK);
		}
		if (3 == csp) {
			return new ResponseEntity<Organization>(InstitutionBuilder.buildUop(csp), HttpStatus.OK);
		}
		return new ResponseEntity<Organization>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "csps/{csp}/institutions/{institution}/device", method = RequestMethod.GET)
	public HttpEntity<List<LogicalDevice>> getCspInstitutionDevice(@PathVariable long csp, @PathVariable long institution) {
		if (10 == institution) {
			return new ResponseEntity<List<LogicalDevice>>(InstitutionBuilder.buildNtuaDevices(), HttpStatus.OK);			
		} 
		if (11 == institution) {
			return new ResponseEntity<List<LogicalDevice>>(InstitutionBuilder.buildUoaDevices(), HttpStatus.OK);
		}
		
		return new ResponseEntity<List<LogicalDevice>>(HttpStatus.NOT_FOUND);
	}
		
}
