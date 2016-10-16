package org.geant.cloud.sid.samples;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.geant.cloud.sid.CharacteristicSpecification;
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
import org.geant.cloud.sid.utils.SidUtils;

public class CspBuilder {
	
	public static Organization buildMicrosoft() {
		Organization microsoft = new Organization(new Identifier("domain-specific", "cspId", "1"), "Microsoft");
		microsoft.add(linkTo(methodOn(CspController.class).getCsp(1)).withSelfRel());
		microsoft.add(linkTo(methodOn(CspController.class).getCsp(1)).slash("devices").withRel("devices"));
		microsoft.add(linkTo(methodOn(CspController.class).getCsp(1)).slash("institutions").withRel("institutions"));
		return microsoft;
	}
	
	public static Organization buildAmazon() {
		Organization amazon = new Organization(new Identifier("domain-specific", "cspId", "2"), "Amazon");
		amazon.add(linkTo(methodOn(CspController.class).getCsp(2)).withSelfRel());
		amazon.add(linkTo(methodOn(CspController.class).getCsp(2)).slash("devices").withRel("devices"));
		amazon.add(linkTo(methodOn(CspController.class).getCsp(2)).slash("institutions").withRel("institutions"));
		return amazon;
	}
	
	public static Organization buildYahoo() {
		Organization yahoo = new Organization(new Identifier("domain-specific", "cspId", "3"), "Yahoo");
		yahoo.add(linkTo(methodOn(CspController.class).getCsp(3)).withSelfRel());
		yahoo.add(linkTo(methodOn(CspController.class).getCsp(3)).slash("devices").withRel("devices"));
		yahoo.add(linkTo(methodOn(CspController.class).getCsp(3)).slash("institutions").withRel("institutions"));
		return yahoo;
	}
	
	public static List<LogicalDevice> buildMicrosoftDevices() {
		List<LogicalDevice> devices = new ArrayList<>();
		devices.add(buildMicrosoftDevice1());
		devices.add(buildMicrosoftDevice2());
		return devices;
	}
	
	public static LogicalDevice buildMicrosoftDevice1() {
		LogicalDevice device1 = new LogicalDevice(new Identifier("domain-specific", "deviceId", "100"), buildMicrosoftDeviceInterface1());
		device1.add(linkTo(methodOn(CspController.class).getCspDevice(1, 100)).withSelfRel());
		return device1;
	}
	
	public static DeviceInterface buildMicrosoftDeviceInterface1() {
		DeviceInterface iface = new DeviceInterface(new Identifier("domain-specific", "ifaceId", "500"),
				new NetworkAddress(NetworkAddressType.IPv4, "195.250.26.60"));
		iface.add(linkTo(methodOn(CspController.class).getCspDeviceInterface(1, 100, 500)).withSelfRel());
		return iface;
	}
	
	public static LogicalDevice buildMicrosoftDevice2() {
		LogicalDevice device2 = new LogicalDevice(new Identifier("domain-specific", "deviceId", "101"), buildMicrosoftDeviceInterface2());
		device2.add(linkTo(methodOn(CspController.class).getCspDevice(1, 101)).withSelfRel());
		return device2;
	}
	
	public static DeviceInterface buildMicrosoftDeviceInterface2() {
		DeviceInterface iface = new DeviceInterface(new Identifier("domain-specific", "ifaceId", "600"),
				new NetworkAddress(NetworkAddressType.IPv4, "195.250.26.61"));
		iface.add(linkTo(methodOn(CspController.class).getCspDeviceInterface(1, 101, 600)).withSelfRel());
		return iface;
	}
	
	public static List<LogicalDevice> buildAmazonDevices() {
		List<LogicalDevice> devices = new ArrayList<>();
		devices.add(buildAmazonDevice1());
		return devices;
	}
	
	public static LogicalDevice buildAmazonDevice1() {
		LogicalDevice device1 = new LogicalDevice(new Identifier("domain-specific", "deviceId", "200"), buildAmazonDeviceInterface1());
		device1.add(linkTo(methodOn(CspController.class).getCspDevice(2, 200)).withSelfRel());
		return device1;
	}
	
	public static DeviceInterface buildAmazonDeviceInterface1() {
		DeviceInterface iface = new DeviceInterface(new Identifier("domain-specific", "ifaceId", "700"),
				new NetworkAddress(NetworkAddressType.IPv4, "191.251.29.30"));
		iface.add(linkTo(methodOn(CspController.class).getCspDeviceInterface(2, 200, 700)).withSelfRel());
		return iface;
	}
	
	public static List<LogicalDevice> buildYahooDevices() {
		List<LogicalDevice> devices = new ArrayList<>();
		return devices;
	}
	
	public static DeviceInterface buildMicrosoftDeviceInterface(long deviceId, long ifaceId) {
		DeviceInterface iface = null;
		if (100 == deviceId && 500 == ifaceId) {
			iface = buildMicrosoftDeviceInterface1();
			PhysicalPort p1 = new PhysicalPort(new Identifier("domain-specific", "portId", "1000"),
					"ge-1/2/2",
					new TerminationPoint(new Pipe(new Capacity("Kbps", "10000"))));
			CharacteristicSpecification spec1 = new CharacteristicSpecification();
			spec1.setName("Available-VLAN-Ids");
			spec1.getCharacteristics().add(SidUtils.createCharacteristic(null, "list", "1,2,3", null));
			spec1.getCharacteristics().add(SidUtils.createCharacteristic(null, "range", "100-199", null));
			CharacteristicSpecification spec2 = new CharacteristicSpecification();
			spec2.setName("Available-VC-Ids");
			spec2.getCharacteristics().add(SidUtils.createCharacteristic(null, "list", "5,6,7", null));
			spec2.getCharacteristics().add(SidUtils.createCharacteristic(null, "range", "200-299", null));
			p1.getCharacteristicSpecifications().add(spec1);
			p1.getCharacteristicSpecifications().add(spec2);
			
			iface.getPhysicalPorts().add(p1);
		}
		if (101 == deviceId && 600 == ifaceId) {
			iface = buildMicrosoftDeviceInterface2();
			PhysicalPort p1 = new PhysicalPort(new Identifier("domain-specific", "portId", "1030"),
					"ge-1/0/2",
					new TerminationPoint(new Pipe(new Capacity("Mbps", "2"))));
			iface.getPhysicalPorts().add(p1);
			
			CharacteristicSpecification spec1 = new CharacteristicSpecification();
			spec1.setName("Available-VLAN-Ids");
			spec1.getCharacteristics().add(SidUtils.createCharacteristic(null, "list", "1,3,17", null));
			spec1.getCharacteristics().add(SidUtils.createCharacteristic(null, "range", "100-199", null));
			CharacteristicSpecification spec2 = new CharacteristicSpecification();
			spec2.setName("Available-VC-Ids");
			spec2.getCharacteristics().add(SidUtils.createCharacteristic(null, "list", "5,60,71", null));
			spec2.getCharacteristics().add(SidUtils.createCharacteristic(null, "range", "200-299", null));
			p1.getCharacteristicSpecifications().add(spec1);
			p1.getCharacteristicSpecifications().add(spec2);
			
			PhysicalPort p2 = new PhysicalPort(new Identifier("domain-specific", "portId", "1031"),
					"ge-1/0/3",
					new TerminationPoint(new Pipe(new Capacity("Mbps", "3"))));
			CharacteristicSpecification spec3 = new CharacteristicSpecification();
			spec3.setName("Available-VLAN-Ids");
			spec3.getCharacteristics().add(SidUtils.createCharacteristic(null, "list", "8,16,45", null));
			spec3.getCharacteristics().add(SidUtils.createCharacteristic(null, "range", "100-199", null));
			CharacteristicSpecification spec4 = new CharacteristicSpecification();
			spec4.setName("Available-VC-Ids");
			spec4.getCharacteristics().add(SidUtils.createCharacteristic(null, "list", "5,12,34", null));
			spec4.getCharacteristics().add(SidUtils.createCharacteristic(null, "range", "200-299", null));
			p1.getCharacteristicSpecifications().add(spec1);
			p1.getCharacteristicSpecifications().add(spec2);
			iface.getPhysicalPorts().add(p2);
			
		}
		return iface;
	}
	
	public static DeviceInterface buildAmazonDeviceInterface(long deviceId, long ifaceId) {
		DeviceInterface iface = null;
		
		if (200 == deviceId && 700 == ifaceId) {
			iface = buildAmazonDeviceInterface1();
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