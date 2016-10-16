package org.geant.cloud.sid.samples;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.geant.cloud.sid.Identifier;
import org.geant.cloud.sid.party.Organization;
import org.geant.cloud.sid.resource.Capacity;
import org.geant.cloud.sid.resource.DeviceInterface;
import org.geant.cloud.sid.resource.LogicalDevice;
import org.geant.cloud.sid.resource.NetworkAddress;
import org.geant.cloud.sid.resource.NetworkAddressType;
import org.geant.cloud.sid.resource.PhysicalPort;
import org.geant.cloud.sid.resource.Pipe;
import org.geant.cloud.sid.resource.TerminationPoint;

public class InstitutionBuilder {
	
	public static Organization buildNtua(long cspId) {
		Organization ntua = new Organization(new Identifier("domain-specific", "institutionId", "10"), "National Technical University of Athens", "NTUA");
		ntua.getIdentifiers().add(new Identifier("eduGAIN", "id", "urn:oasis:names:tc:SAML:2.0:nameid-format:entity_1"));
		ntua.add(linkTo(methodOn(CspController.class).getCspInstitution(cspId, 10)).withSelfRel());
		ntua.add(linkTo(methodOn(CspController.class).getCspInstitution(cspId, 10)).slash("devices").withRel("devices"));
		return ntua;
	}
	
	public static Organization buildUoa(long cspId) {
		Organization uoa = new Organization(new Identifier("domain-specific", "institutionId", "11"), "University of Athens", "UOA");
		uoa.getIdentifiers().add(new Identifier("eduGAIN", "id", "urn:oasis:names:tc:SAML:2.0:nameid-format:entity_2"));
		uoa.add(linkTo(methodOn(CspController.class).getCspInstitution(cspId, 11)).withSelfRel());
		uoa.add(linkTo(methodOn(CspController.class).getCspInstitution(cspId, 11)).slash("devices").withRel("devices"));
		return uoa;
	}
	
	public static Organization buildUop(long cspId) {
		Organization uop = new Organization(new Identifier("domain-specific", "institutionId", "12"), "University of Patras", "UOP");
		uop.getIdentifiers().add(new Identifier("eduGAIN", "id", "urn:oasis:names:tc:SAML:2.0:nameid-format:entity_33"));
		uop.add(linkTo(methodOn(CspController.class).getCspInstitution(cspId, 12)).withSelfRel());
		uop.add(linkTo(methodOn(CspController.class).getCspInstitution(cspId, 12)).slash("devices").withRel("devices"));
		return uop;
	}
	
	public static List<LogicalDevice> buildNtuaDevices() {
		List<LogicalDevice> devices = new ArrayList<>();
		devices.add(buildNtuaDevice1());
		devices.add(buildNtuaDevice2());
		return devices;
	}
	
	public static List<LogicalDevice> buildAmazonDevices() {
		List<LogicalDevice> devices = new ArrayList<>();
		devices.add(buildUoaDevice1());
		return devices;
	}
	
	public static LogicalDevice buildNtuaDevice1() {
		LogicalDevice device1 = new LogicalDevice(new Identifier("domain-specific", "deviceId", "100"), buildNtuaDeviceInterface1());
		device1.add(linkTo(methodOn(CspController.class).getCspDevice(1, 100)).withSelfRel());
		return device1;
	}
	
	
	public static DeviceInterface buildNtuaDeviceInterface1() {
		DeviceInterface iface = new DeviceInterface(new Identifier("domain-specific", "ifaceId", "500"),
				new NetworkAddress(NetworkAddressType.IPv4, "195.250.26.60"));
		iface.add(linkTo(methodOn(CspController.class).getCspDeviceInterface(1, 100, 500)).withSelfRel());
		return iface;
	}
	
	public static LogicalDevice buildNtuaDevice2() {
		LogicalDevice device2 = new LogicalDevice(new Identifier("domain-specific", "deviceId", "101"), buildNtuaDeviceInterface2());
		device2.add(linkTo(methodOn(CspController.class).getCspDevice(1, 101)).withSelfRel());
		return device2;
	}
	
	public static DeviceInterface buildNtuaDeviceInterface2() {
		DeviceInterface iface = new DeviceInterface(new Identifier("domain-specific", "ifaceId", "600"),
				new NetworkAddress(NetworkAddressType.IPv4, "195.250.26.61"));
		iface.add(linkTo(methodOn(CspController.class).getCspDeviceInterface(1, 101, 600)).withSelfRel());
		return iface;
	}
	
	public static List<LogicalDevice> buildUoaDevices() {
		List<LogicalDevice> devices = new ArrayList<>();
		devices.add(buildUoaDevice1());
		return devices;
	}
	
	public static LogicalDevice buildUoaDevice1() {
		LogicalDevice device1 = new LogicalDevice(new Identifier("domain-specific", "deviceId", "200"), buildUoaDeviceInterface1());
		device1.add(linkTo(methodOn(CspController.class).getCspDevice(2, 200)).withSelfRel());
		return device1;
	}
	
	public static DeviceInterface buildUoaDeviceInterface1() {
		DeviceInterface iface = new DeviceInterface(new Identifier("domain-specific", "ifaceId", "700"),
				new NetworkAddress(NetworkAddressType.IPv4, "191.251.29.30"));
		iface.add(linkTo(methodOn(CspController.class).getCspDeviceInterface(2, 200, 700)).withSelfRel());
		return iface;
	}
	
	public static List<LogicalDevice> buildUopDevices() {
		List<LogicalDevice> devices = new ArrayList<>();
		return devices;
	}
	
	public static DeviceInterface buildNtuaDeviceInterface(long deviceId, long ifaceId) {
		DeviceInterface iface = null;
		if (100 == deviceId && 500 == ifaceId) {
			iface = buildNtuaDeviceInterface1();
			iface.getPhysicalPorts().add(new PhysicalPort(new Identifier("domain-specific", "portId", "1000"),
					"ge-1/2/2",
					new TerminationPoint(new Pipe(new Capacity("Kbps", "10000")))));
		}
		if (101 == deviceId && 600 == ifaceId) {
			iface = buildNtuaDeviceInterface2();
			iface.getPhysicalPorts().add(new PhysicalPort(new Identifier("domain-specific", "portId", "1030"),
					"ge-1/0/2",
					new TerminationPoint(new Pipe(new Capacity("Mbps", "2")))));
			iface.getPhysicalPorts().add(new PhysicalPort(new Identifier("domain-specific", "portId", "1031"),
					"ge-1/0/3",
					new TerminationPoint(new Pipe(new Capacity("Mbps", "3")))));
		}
		return iface;
	}
	
	public static DeviceInterface buildUoaDeviceInterface(long deviceId, long ifaceId) {
		DeviceInterface iface = null;
		
		if (200 == deviceId && 700 == ifaceId) {
			iface = buildUoaDeviceInterface1();
			iface.getPhysicalPorts().add(new PhysicalPort(new Identifier("domain-specific", "portId", "2030"),
					"xe-1/0/2",
					new TerminationPoint(new Pipe(new Capacity("Mbps", "5")))));
			iface.getPhysicalPorts().add(new PhysicalPort(new Identifier("domain-specific", "portId", "2037"),
					"xe-1/0/3",
					new TerminationPoint(new Pipe(new Capacity("Mbps", "8")))));
		}
		
		return iface;
	}

}